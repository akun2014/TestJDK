package com.gk.alibaba;

import org.joda.time.DateTime;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * Created by akun on 2017/7/5.
 */
public class SimpleCache<K, V> {
    ConcurrentHashMap<K, EntryWrapper<K, V>> CACHE_CONTAINER = new ConcurrentHashMap();
    static final int MAX_CACHED_KEY_NUM = 100;//最大缓存容量
    static final float DEFAULT_LOAD_FACTOR = 0.75f;//采取淘汰策略时CACHE_CONTAINER的最大缓存因子

    /**
     * 读数据
     */
    public Object get(K key) {
        boolean isContains = CACHE_CONTAINER.containsKey(key);
        if (isContains) {
            EntryWrapper entry = CACHE_CONTAINER.get(key);
            return entry.getValue();
        } else {
            //缓存没有读到数据，模拟从数据库load数据，写入缓存
            V value = readFromDB(key);
            if (value != null) {
                cache(key, value);
                return value;
            }
        }
        return null;
    }

    /**
     * 写数据
     */
    public void cache(K key, V value) {
        boolean isContains = CACHE_CONTAINER.containsKey(key);
        if (!isContains) {
            EntryWrapper entry = new EntryWrapper();
            entry.setValue(value);
            entry.setKey(key);
            CACHE_CONTAINER.put(key, entry);
        }
        clearCache();//执行缓存策略
    }

    /**
     * 非热点数据淘汰策略:
     * 当缓存容量大于 100*0.75时
     * 找到5分钟前缓存的数据(A)，然后对5分钟前缓存数据(A)的访问次数进行从小到大排序
     * 最后清除 CACHE_CONTAINER 中排在A中前一半的数据
     */
    private void clearCache() {
        if (CACHE_CONTAINER.size() >= MAX_CACHED_KEY_NUM * DEFAULT_LOAD_FACTOR) {
            synchronized (CACHE_CONTAINER) {
                List<EntryWrapper> entryList = CACHE_CONTAINER.entrySet().parallelStream().filter(new Predicate<Map.Entry<K, EntryWrapper<K, V>>>() {
                    @Override
                    public boolean test(Map.Entry<K, EntryWrapper<K, V>> kEntryWrapperEntry) {
                        EntryWrapper entry = kEntryWrapperEntry.getValue();
                        //找到5分钟前缓存的数据(A)
                        if (new DateTime().minusMinutes(5).isAfter(new DateTime(entry.getTheLastAccessDateTime()))) {
                            return true;
                        }
                        return false;
                    }
                }).map(new Function<Map.Entry<K, EntryWrapper<K, V>>, EntryWrapper<K, V>>() {
                    @Override
                    public EntryWrapper<K, V> apply(Map.Entry<K, EntryWrapper<K, V>> kEntryWrapperEntry) {
                        EntryWrapper<K, V> value = kEntryWrapperEntry.getValue();
                        return value;
                    }
                }).sorted(Comparator.comparingInt(new ToIntFunction<EntryWrapper>() {
                    //对5分钟前缓存数据(A)的访问次数进行从小到大排序
                    @Override
                    public int applyAsInt(EntryWrapper value) {
                        return value.getAccessTimes();
                    }
                })).collect(Collectors.toList());

                int size = entryList.size();
                entryList.parallelStream().limit(size / 2).forEach(new Consumer<EntryWrapper>() {
                    //清除 CACHE_CONTAINER 中排在A中前一半的数据
                    @Override
                    public void accept(EntryWrapper entry) {
                        CACHE_CONTAINER.remove(entry.getKey());
                    }
                });
            }
        }
    }

    /**
     * 模拟数据库读取数据
     *
     * @return
     */
    public V readFromDB(K key) {
        Map<K, V> db = new HashMap();//模拟数据库
        return db.get(key);
    }

    /**
     * 保存缓存数据
     */
    class EntryWrapper<K, V> {
        K key;
        V value;//缓存数据
        int accessTimes;//访问次数
        long theLastAccessDateTime;//最后一次访问时间

        public void setValue(V value) {
            this.value = value;
        }

        public Object getValue() {
            this.theLastAccessDateTime = System.currentTimeMillis();
            this.accessTimes++;
            return this.value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public K getKey() {
            return this.key;
        }

        public int getAccessTimes() {
            return this.accessTimes;
        }

        public long getTheLastAccessDateTime() {
            return this.theLastAccessDateTime;
        }
    }
}

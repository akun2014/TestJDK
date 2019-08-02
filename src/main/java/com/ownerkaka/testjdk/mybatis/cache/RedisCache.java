package com.ownerkaka.testjdk.mybatis.cache;

import com.ownerkaka.testjdk.redis.RedisClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.InitializingObject;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheException;
import org.apache.ibatis.io.Resources;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author akun
 * @since 2019-08-01
 * Redis实现mybatis一级缓存
 */
@Slf4j
public class RedisCache implements Cache, InitializingObject {

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private final byte[] REDIS_CACHE_KEY = "redisCacheKey".getBytes(StandardCharsets.UTF_8);

    private String id;
    //default cached object size
    private int size = 1024;

    public RedisCache(String id) {
        log.info("redis cache id:{}", id);
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if (value != null && Serializable.class.isAssignableFrom(value.getClass())) {
            Lock lock = this.lock.writeLock();
            try {
                if (lock.tryLock()) {
                    Jedis jedis = RedisClientUtil.getRedisClient();
                    int cachedSize = getSize();
                    if (cachedSize > size) {
                        //remove old cached object
                    }
                    jedis.hsetnx(REDIS_CACHE_KEY, key.toString().getBytes(StandardCharsets.UTF_8), serialize((Serializable) value));
                }
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public Object getObject(Object key) {
        if (key != null) {
            Lock lock = this.lock.readLock();
            try {
                if (lock.tryLock()) {
                    Jedis jedis = RedisClientUtil.getRedisClient();
                    byte[] bytes = jedis.hget(REDIS_CACHE_KEY, key.toString().getBytes(StandardCharsets.UTF_8));
                    if (bytes == null) {
                        return null;
                    }
                    return deserialize(bytes);
                }
            } finally {
                lock.unlock();
            }
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        Jedis jedis = RedisClientUtil.getRedisClient();
        return jedis.hdel(REDIS_CACHE_KEY, key.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void clear() {
        Jedis jedis = RedisClientUtil.getRedisClient();
        jedis.del(REDIS_CACHE_KEY);
    }

    @Override
    public int getSize() {
        Jedis jedis = RedisClientUtil.getRedisClient();
        return jedis.hlen(REDIS_CACHE_KEY).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return lock;
    }

    private byte[] serialize(Serializable value) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(value);
            oos.flush();
            return bos.toByteArray();
        } catch (Exception e) {
            throw new CacheException("Error serializing object.  Cause: " + e, e);
        }
    }

    private Serializable deserialize(byte[] value) {
        Serializable result;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(value);
             ObjectInputStream ois = new CustomObjectInputStream(bis)) {
            result = (Serializable) ois.readObject();
        } catch (Exception e) {
            throw new CacheException("Error deserializing object.  Cause: " + e, e);
        }
        return result;
    }

    public static class CustomObjectInputStream extends ObjectInputStream {

        public CustomObjectInputStream(InputStream in) throws IOException {
            super(in);
        }

        @Override
        protected Class<?> resolveClass(ObjectStreamClass desc) throws ClassNotFoundException {
            return Resources.classForName(desc.getName());
        }

    }

    /**
     * mybatis call this method after it's set all properties
     */
    @Override
    public void initialize() throws Exception {
        log.info("redis cache init finished");
    }
}
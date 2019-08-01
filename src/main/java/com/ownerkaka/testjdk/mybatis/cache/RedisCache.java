package com.ownerkaka.testjdk.mybatis.cache;

import com.ownerkaka.testjdk.redis.RedisClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.cache.Cache;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author akun
 * @since 2019-08-01
 * Redis实现mybatis一级缓存
 */
@Slf4j
public class RedisCache implements Cache {

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private final byte[] REDIS_CACHE_KEY = "redisCacheKey".getBytes(StandardCharsets.UTF_8);

    private String id;

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
        if (value != null) {
            Jedis jedis = RedisClientUtil.getRedisClient();
            jedis.hsetnx(REDIS_CACHE_KEY, key.toString().getBytes(StandardCharsets.UTF_8), serialize(value));
        }
    }

    @Override
    public Object getObject(Object key) {
        Jedis jedis = RedisClientUtil.getRedisClient();
        byte[] bytes = jedis.hget(REDIS_CACHE_KEY, key.toString().getBytes(StandardCharsets.UTF_8));
        if (bytes == null) {
            return null;
        }
        return deserialize(bytes);
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

    private byte[] serialize(Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(out);
            os.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(os);
        }
        return out.toByteArray();
    }

    private Object deserialize(byte[] data) {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(in);
            return is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(in);
        }
        return null;
    }
}
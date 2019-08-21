package com.ownerkaka.testjdk.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Properties;

/**
 * @author akun
 * @since 2019-07-31
 */
@Slf4j
public class RedisClientUtil {

    private static JedisPool jedisPool;

    static {
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
            String host = properties.getProperty("spring.redis.host");
            int port = Integer.parseInt(properties.getProperty("spring.redis.port", "6379"));
            jedisPool = new JedisPool(new JedisPoolConfig(), host, port, 300);
            log.info("redis pool init finished");
        } catch (IOException ignored) {
        }
    }

    public static void init() {
        jedisPool.getResource();
    }

    public static Jedis getRedisClient() {
        return jedisPool.getResource();
    }

    public static class JedisPoolConfig extends GenericObjectPoolConfig {
        public JedisPoolConfig() {
            setMaxTotal(500);
            setMaxIdle(500);
        }
    }

}
package com.ownerkaka.testjdk.mybatis;

import com.ownerkaka.testjdk.redis.RedisClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author akun
 * @since 2019-08-01
 */
@Slf4j
public class SqlSessionFactoryUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis/mybatis-config.xml";
            InputStream stream = Resources.getResourceAsStream(MyBatisTests.class.getClassLoader(), resource);
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        } catch (Exception ignore) {
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }

    public static void clearCache() {
        byte[] REDIS_CACHE_KEY = "redisCacheKey".getBytes(StandardCharsets.UTF_8);
        RedisClientUtil.getRedisClient().del(REDIS_CACHE_KEY);
    }
}
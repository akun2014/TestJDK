package com.ownerkaka.testjdk.mybatis;

import com.ownerkaka.testjdk.mybatis.domain.User;
import com.ownerkaka.testjdk.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author akun
 * @since 2019-08-01
 */
@Slf4j
public class MybatisRedisCacheTests {

    /**
     * 开启一级缓存（默认开启），同一session会共享缓存
     */
    @Test
    public void testSessionScopeCache() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getById(1);
        Assert.assertEquals(1L, user.getUid().longValue());
        sqlSession.close();
    }

    /**
     * 在没有开启二级缓存时，不同SqlSession不会共享缓存
     */
    @Test
    public void testDifferentSession() {
        SqlSession sqlSession1 = SqlSessionFactoryUtil.getSqlSession();
        SqlSession sqlSession2 = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user = userMapper1.getById(1);
        User user1 = userMapper2.getById(1);
        sqlSession1.close();
        sqlSession2.close();
    }
}
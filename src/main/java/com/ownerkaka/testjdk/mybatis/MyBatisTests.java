package com.ownerkaka.testjdk.mybatis;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.ownerkaka.testjdk.mybatis.domain.User;
import com.ownerkaka.testjdk.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author akun
 * @since 2019-07-13
 */
@Slf4j
public class MyBatisTests {

    private SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    @After
    public void closeSqlSession() {
        sqlSession.close();
    }

    @Test
    public void testConnection() throws Exception {
        Connection connection = sqlSession.getConnection();
        String id = sqlSession.getConfiguration().getEnvironment().getId();
        TransactionFactory transactionFactory = sqlSessionFactory.getConfiguration().getEnvironment().getTransactionFactory();
        if ("prod".equals(id)) {
            Assert.assertTrue(connection instanceof DruidPooledConnection);
            Assert.assertTrue(transactionFactory instanceof SpringManagedTransactionFactory);
        } else {
            Assert.assertTrue(transactionFactory instanceof JdbcTransactionFactory);
            Assert.assertTrue(Proxy.isProxyClass(connection.getClass()));
        }
        Assert.assertFalse(connection.isClosed());
    }

    @Test
    public void test() {
        Object all = sqlSession.selectOne("options.selectOne");
        log.info(all.toString());
    }

    @Test
    public void testUser() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getById(1);
        Assert.assertEquals(1L, user.getUid().longValue());
    }

    @Test
    public void tesUpdatetUser() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateUsername(1L, "update");
        User user = userMapper.getById(1);
        Assert.assertEquals("update", user.getUsername());
    }

    @Test
    public void testMapper() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getByUsername("admin");
        Assert.assertNotNull(user);
    }
}
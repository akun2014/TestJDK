package com.ownerkaka.testjdk.mybatis;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.ownerkaka.testjdk.mybatis.domain.User;
import com.ownerkaka.testjdk.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.*;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author akun
 * @since 2019-07-13
 */
@Slf4j
public class MyBatisTests {

    private static SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    @BeforeClass
    public static void createSqlSessionFactory() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream stream = Resources.getResourceAsStream(MyBatisTests.class.getClassLoader(), resource);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        Assert.assertTrue(sqlSessionFactory instanceof DefaultSqlSessionFactory);
    }

    @Before
    public void getSqlSession() {
        sqlSession = sqlSessionFactory.openSession();
        Assert.assertTrue(sqlSession instanceof DefaultSqlSession);
    }

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
    public void testMapper() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getByUsername("admin");
        Assert.assertNotNull(user);
    }


}
package com.ownerkaka.testjdk.mybatis;

import com.ownerkaka.testjdk.mybatis.domain.User;
import com.ownerkaka.testjdk.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author akun
 * @since 2019-08-01
 */
@Slf4j
public class MybatisRedisCacheTests {


    @Before
    public void init() {
        SqlSessionFactoryUtil.clearCache();
    }

    /**
     * 一级缓存默认开启，同一session会共享缓存
     * 默认配置为SESSION级别
     * <setting name="localCacheScope" value="SESSION"/>
     */
    @Test
    public void testSessionScopeCache() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //第一次查询走数据库
        User user = userMapper.getById(1);
        //第二次查询命中缓存
        userMapper.getById(1);
        Assert.assertEquals(1L, user.getUid().longValue());
        sqlSession.close();
    }

    /**
     * 在一次数据库回话中，如果对数据库发生修改操作（update | delete | insert），
     * 会导致一级缓存失效
     */
    @Test
    public void testSessionScopeCacheInvalid() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //第一次查询走数据库
        userMapper.getById(1);
        //update操作导致缓存失效
        userMapper.updateUsername(1L, "tests");
        //第二次查询命再次走数据库
        userMapper.getById(1);
        sqlSession.close();
    }

    /**
     * 在sqlsession2中对username做更新，但是在sqlsession1之后的查询，查到的username依然和update操作之前一样
     * ，出现了脏数据。这说明一级缓存只在数据库会话内部共享
     */
    @Test
    public void testSessionScopeCache2() {
        UserMapper userMapper1 = SqlSessionFactoryUtil.getSqlSession().getMapper(UserMapper.class);
        UserMapper userMapper2 = SqlSessionFactoryUtil.getSqlSession().getMapper(UserMapper.class);
        //第一次查询走数据库
        User user = userMapper1.getById(1);
        //在另外一个SQLSession内做update操作
        String newUsername = RandomStringUtils.randomAlphabetic(4);
        userMapper2.updateUsername(1L, newUsername);
        //第二次查询走缓存，导致脏读
        User user1 = userMapper1.getById(1);
        Assert.assertNotEquals(newUsername, user1.getUsername());
        Assert.assertEquals(user.getUsername(), user1.getUsername());
    }

    /**
     * sqlsession没有调用commit方法时，二级缓存不起作用
     */
    @Test
    public void testCacheWithoutCommitOrClose() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        SqlSession sqlSession2 = SqlSessionFactoryUtil.getSqlSession();

        UserMapper userMapper1 = sqlSession.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);

        User user = userMapper1.getById(1);
        Assert.assertEquals(1, user.getUid().longValue());
        User user1 = userMapper2.getById(1);
        Assert.assertEquals(1, user1.getUid().longValue());
    }

    /**
     * sqlsession调用commit方法后，二级缓存生效
     */
    @Test
    public void testCacheWithCommitOrClose() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        SqlSession sqlSession2 = SqlSessionFactoryUtil.getSqlSession();

        UserMapper userMapper1 = sqlSession.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);

        User user = userMapper1.getById(1);
        Assert.assertEquals(1, user.getUid().longValue());
        sqlSession.commit();
        User user1 = userMapper2.getById(1);
        Assert.assertEquals(1, user1.getUid().longValue());
    }

    /**
     * 更新后，二级缓存失效，后续查询再次走数据库
     */
    @Test
    public void testCacheWithUpdate() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        SqlSession sqlSession2 = SqlSessionFactoryUtil.getSqlSession();
        SqlSession sqlSession3 = SqlSessionFactoryUtil.getSqlSession();

        UserMapper userMapper1 = sqlSession.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);

        userMapper1.getById(1);
        sqlSession.commit();

        userMapper2.updateUsername(1L, "test");
        sqlSession2.commit();

        userMapper3.getById(1);
    }
}
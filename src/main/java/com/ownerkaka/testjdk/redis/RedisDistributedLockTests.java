package com.ownerkaka.testjdk.redis;

import com.ownerkaka.testjdk.threadpool.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author akun
 * @since 2019-07-31
 * 使用redis实现分布式锁
 */
@Slf4j
public class RedisDistributedLockTests {

    ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
    final String key = "redisLock";
    final String value = "redisLockValue";

    /**
     * 利用450个线程模拟并发场景下，争抢同一个锁的场景。正常情况下，450个线程只会有一个线程获取到锁
     */
    @Test
    public void testRedisLock() {
        final String lKey = "lKey";
        Jedis client = RedisClientUtil.getRedisClient();
        client.del(lKey);
        Runnable task = () -> {
            long id = Thread.currentThread().getId();
            Jedis redisClient = RedisClientUtil.getRedisClient();
            String clientId = value + "-" + id;
            boolean lock = acquireLock(redisClient, key, clientId, 500);
            if (lock) {
                //把获取到锁的clientId保存到list当中，正常获取锁情况。 list.size == 1
                redisClient.lpush(lKey, clientId);
            }
        };

        RedisClientUtil.init();
        for (int i = 0; i < 450; i++) {
            threadPool.execute(task);
        }
        //等所有线程执行完成
//        ThreadPoolUtil.await();

        Long llen = client.llen(lKey);
        //断言获取到锁的list.size == 1 为真
        Assert.assertEquals(1, llen.longValue());
        String clientId = client.lpop(lKey);
        //用获取到锁的clientId释放锁
        boolean unlock = unlock(client, key, clientId);
        Assert.assertTrue(unlock);
    }


    /**
     * 一个线程获取锁、释放锁
     */
    @Test
    public void testLock() {
        Jedis redisClient = RedisClientUtil.getRedisClient();
        boolean lock = acquireLock(redisClient, key, value, 500);
        Assert.assertTrue(lock);

        boolean unlocked = unlock(redisClient, key, value);
        Assert.assertTrue(unlocked);
    }

    private boolean acquireLock(Jedis redisClient, String lockKey, String requestId, int expireTime) {
        final String LOCK_SUCCESS = "OK";
        final String SET_IF_NOT_EXIST = "NX";
        final String SET_WITH_EXPIRE_TIME = "PX";
        SetParams params = new SetParams();
        params.ex(expireTime);
        params.nx();
        String result = redisClient.set(lockKey, requestId, params);

        return LOCK_SUCCESS.equals(result);
    }

    static String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
            "then return redis.call('del', KEYS[1]) " +
            "else return 0 " +
            "end";

    private boolean unlock(Jedis redisClient, String lockKey, String requestId) {
        Object result = redisClient.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        final Long RELEASE_SUCCESS = 1L;
        return RELEASE_SUCCESS.equals(result);
    }
}
package com.ownerkaka.testjdk.redis.redisson;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;

@Slf4j
public class RedisClient {

    private RedissonClient getClient() {
        Config config = new Config();
        MasterSlaveServersConfig slaveServers = config.useMasterSlaveServers();
        slaveServers.setMasterAddress("redis://127.0.0.1:6379");
        RedissonClient client = Redisson.create(config);
        return client;
    }

    @Test
    public void test() throws InterruptedException {
        RedissonClient client = getClient();
        RSet<Object> myset = client.getSet("myset");
        boolean test = myset.add("test");
        log.info("result:{}", test);
        Thread.sleep(1000 * 10);
    }

    @Test
    public void lockTest() {
        RedissonClient client = getClient();
        RLock mylock = client.getLock("mylock");
        mylock.lock();
    }
}

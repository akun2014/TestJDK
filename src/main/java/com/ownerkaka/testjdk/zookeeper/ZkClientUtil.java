package com.ownerkaka.testjdk.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

public class ZkClientUtil {

    public static CuratorFramework client;

    static {
        client = CuratorFrameworkFactory.builder()
                .retryPolicy(new RetryNTimes(1, 1000))
                .connectString("127.0.0.1:2181")
                .namespace("dubbo")
                .connectionTimeoutMs(1000)
                .build();
        client.start();
    }

}

package com.ownerkaka.testjdk.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class CuratorZkClientTest {

    private final String path = "/curator";
    private CuratorFramework client;

    @Before
    public void init() {
        client = CuratorFrameworkFactory.builder()
                .retryPolicy(new RetryNTimes(1, 1000))
                .connectString("127.0.0.1:2181")
                .connectionTimeoutMs(1000)
                .build();
        client.start();
    }

    @After
    public void close() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        client.close();
    }

    @Test
    public void create() throws Exception {
        String s = client.create()
                .creatingParentsIfNeeded()
                .forPath(path);
        log.info("{}", s);
    }

    @Test
    public void creatingParentsIfNeeded() throws Exception {
        String s = client.create()
                .creatingParentsIfNeeded()
                .forPath(path + "/test");
        log.info("{}", s);
    }

    @Test
    public void checkExists() throws Exception {
        Stat stat = client.checkExists().forPath(path);
        log.info("{}", stat.toString());
    }

    @Test
    public void delete() throws Exception {
        Void unused = client.delete().deletingChildrenIfNeeded().forPath(path);
    }

    @Test
    public void getChildren() throws Exception {
        List<String> list = client.getChildren()
                .forPath("/")
                .stream()
                .peek(System.out::println)
                .filter(v -> v.startsWith("ephemeralPath"))
                .collect(Collectors.toList());
        for (String path : list) {
            client.delete().forPath("/" + path);
        }
        log.info("{}", Arrays.toString(list.toArray()));
    }

    @Test
    public void usingWatcher() throws Exception {
        client.getChildren().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                Watcher.Event.EventType type = event.getType();
                String path = event.getPath();
                Watcher.Event.KeeperState state = event.getState();
            }
        }).forPath("/ownerkaka");
    }
}

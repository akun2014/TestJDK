package com.ownerkaka.testjdk.zookeeper;

import com.alibaba.fastjson.JSON;
import com.ownerkaka.testjdk.zookeeper.watcher.MyWatcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Curator 馆长
 */
@Slf4j
public class CuratorZkClientTest {

    private final String path = "/curator";
    private CuratorFramework client;

    @Before
    public void init() {
        client = ZkClientUtil.client;
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
                .forPath("/test");
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
        //监听服务器链接状态
        client.getConnectionStateListenable().addListener((client, newState) -> {
            boolean connected = newState.isConnected();
            log.info("connected={}", connected);
        });

        client.getChildren()
                .usingWatcher(new MyWatcher(client, "getChildren"))
                .forPath("/org.apache.dubbo.demo.DemoService");

        client.getData()
                .usingWatcher(new MyWatcher(client, "getData"))
                .forPath("/org.apache.dubbo.demo.DemoService");

        client.checkExists()
                .usingWatcher(new MyWatcher(client, "checkExists"))
                .forPath("/org.apache.dubbo.demo.DemoService");

        client.getCuratorListenable()
                .addListener((client, event) -> {
                    String path = event.getPath();
                    log.info("path={},event={}", path, JSON.toJSONString(event));
                });
        TimeUnit.HOURS.sleep(2);
    }
}

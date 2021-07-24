package com.ownerkaka.testjdk.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * zk客户端测试
 */
@Slf4j
public class ZkClientTest {

    final ExecutorService threadPool = Executors.newFixedThreadPool(1);
    private final String parentPath = "/ownerkaka";
    private final String childPath = "/test";
    private final String ephemeralPath = "/ephemeralPath";

    ZkClient zkClient;

    @Before
    public void init() throws InterruptedException {
        zkClient = new ZkClient("127.0.0.1:2181", 5000);
        TimeUnit.SECONDS.sleep(2);
        threadPool.execute(this::addListener);
        TimeUnit.SECONDS.sleep(2);

        log.info("zkclient inited.numberOfListeners={}", zkClient.numberOfListeners());
    }

    @After
    public void close() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        zkClient.close();
    }

    @Test
    public void countChildren() {
        int countChildren = zkClient.countChildren(parentPath);
        log.info("countChildren={}", countChildren);
    }

    @Test
    public void process() {
        WatchedEvent watchedEvent = new WatchedEvent(Watcher.Event.EventType.NodeCreated, Watcher.Event.KeeperState.SyncConnected, parentPath);
        zkClient.process(watchedEvent);
    }

    @Test
    public void connect() {
        zkClient.connect(200, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                log.info("{}", event.toString());
            }
        });
    }

    @Test
    public void createPersistent() {
        zkClient.createPersistent(parentPath);
        boolean exists = zkClient.exists(parentPath);
        assert exists;
    }

    @Test
    public void createPersistentWithData() {
        Map<String, String> data = new HashMap<>();
        data.put("key", "value");
        zkClient.create(parentPath + "/data2", data, CreateMode.PERSISTENT);
        Object o = zkClient.readData(parentPath + "/data2");
        log.info("{}", o.toString());
    }

    @Test
    public void createPersistentWithParent() {
        Map<String, String> data = new HashMap<>();
        data.put("key", "value");
        zkClient.createPersistent(parentPath + childPath, true);
        boolean exists = zkClient.exists(parentPath + childPath);
        assert exists;
    }

    /**
     * 创建持久顺序节点
     */
    @Test
    public void createPersistentSequential() {
        Map<String, String> data = new HashMap<>();
        data.put("key", "value");
        List<String> list = new ArrayList<>(15);
        for (int i = 0; i < 10; i++) {
            String ephemeralSequential = zkClient.createPersistentSequential(ephemeralPath, data);
            list.add(ephemeralSequential);
        }
        log.info("createEphemeralSequential={}", Arrays.toString(list.toArray()));
    }

    /**
     * 创建临时顺序节点
     */
    @Test
    public void createEphemeralSequential() {
        Map<String, String> data = new HashMap<>();
        data.put("key", "value");
        List<String> list = new ArrayList<>(15);
        for (int i = 0; i < 10; i++) {
            String ephemeralSequential = zkClient.createEphemeralSequential(ephemeralPath, data);
            list.add(ephemeralSequential);
        }
        log.info("createEphemeralSequential={}", Arrays.toString(list.toArray()));
    }


    @Test
    public void test() {
        boolean exists = zkClient.exists("/ownerkaka/test");
        assert exists;
    }

    private void addListener() {
        // 添加子节点状态监听->将监听创建，减少，删除子节点状态
        zkClient.subscribeChildChanges(parentPath, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                int size = currentChilds.size();
                log.info("size={}", size);
                log.info("parentPath={},currentChilds={}", parentPath, Arrays.toString(currentChilds.toArray()));
            }
        });
        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
                String stateStr = null;
                switch (state) {
                    case Disconnected:
                        stateStr = "Disconnected";
                        break;
                    case Expired:
                        stateStr = "Expired";
                        break;
                    case SyncConnected:
                        stateStr = "SyncConnected";
                        break;
                    default:
                        stateStr = "Unknow";
                        break;
                }
                log.info("handleStateChanged={}", stateStr);
            }

            @Override
            public void handleNewSession() throws Exception {
                log.info("handleNewSession");
            }

            @Override
            public void handleSessionEstablishmentError(Throwable error) throws Exception {
                log.info("handleSessionEstablishmentError");
            }
        });

        // 为各个节点添加数据状态监听
        zkClient.subscribeDataChanges(parentPath, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                log.info("handleDataChange dataPath={} data={}", dataPath, data.toString());
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                log.info("handleDataDeleted dataPath={}", dataPath);
            }
        });
    }
}

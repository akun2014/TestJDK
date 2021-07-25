package com.ownerkaka.testjdk.zookeeper.watcher;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

@Slf4j
@AllArgsConstructor
public class MyWatcher implements Watcher {


    private CuratorFramework client;
    private String methodName;

    @SneakyThrows
    @Override
    public void process(WatchedEvent event) {
        log.info("----------WatchedEvent------------");
        log.info("path:{} type:{} method:{}", event.getPath(), event.getType().name(), methodName);
        if ("checkExists".equals(methodName)) {
            client.checkExists()
                    .usingWatcher(this)
                    .forPath(event.getPath());
        } else if ("getData".equals(methodName)) {
            client.getData()
                    .usingWatcher(this)
                    .forPath(event.getPath());
        } else if ("getChildren".equals(methodName)) {
            client.getChildren()
                    .usingWatcher(this)
                    .forPath(event.getPath());
        }
    }
}

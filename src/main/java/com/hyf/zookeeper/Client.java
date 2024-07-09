package com.hyf.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.nio.charset.StandardCharsets;

/**
 * @author baB_hyf
 * @date 2022/04/30
 */
public class Client {

    public static void main(String[] args) throws Exception {

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(5 * 1000)
                .retryPolicy(new ExponentialBackoffRetry(3000, 5))
                .namespace("test")
                .build();

        client.start();

        String s = client.create().forPath("/test1", "test-value".getBytes(StandardCharsets.UTF_8));
        System.out.println(s);

        client.close();
    }
}

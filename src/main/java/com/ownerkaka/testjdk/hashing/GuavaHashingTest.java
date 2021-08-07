package com.ownerkaka.testjdk.hashing;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

public class GuavaHashingTest {

    public static void main(String[] args) {
        //实体缓存服务器
        String[] cacheServers = {"192.168.56.101:11211", "192.168.56.102:11211", "192.168.56.103:11211", "192.168.56.104:11211"};

// 缓存数据的key
        String key = "my-test-cache-key";

// 计算缓存 key 对应的 hash 值，这里使用 MurmurHash 算法，MurmurHash 是一种高性能低碰撞的算法。此外，还支持  md5、sha1/sha256/sha512、orc32、adler32 等哈希算法。
        HashCode hashCode = Hashing.murmur3_32().newHasher().putString(key, Charsets.UTF_8).hash();

// 通过一致性哈希方式计算，缓存key对应的服务器主机是那一台，bucket 的范围在 0 ~ cacheServers.length -1
        int bucket = Hashing.consistentHash(hashCode, cacheServers.length);
        System.out.println(bucket);
    }
}

package com.ownerkaka.testjdk.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by akun on 2016/11/9.
 */
@Slf4j
public class RedisDataTypeTest {

    private static Jedis jedis;
    final String key = "KEY";
    final String value = "VALUE";

    @BeforeClass
    public static void setup() {
        jedis = RedisClientUtil.getRedisClient();
    }

    /**
     * jedis存储字符串
     */
    @Test
    public void testString() {
        String nx = jedis.get("aaa");
        System.out.println("nx:" + nx);

        SetParams params = new SetParams();
        params.ex(10);
        params.nx();
        String set = jedis.set(key, value, params);
        Long flag1 = jedis.setnx(key, value);
        Long flag2 = jedis.setnx(key, value);
        System.out.println(flag1 + ":" + flag2);

        String setex = jedis.setex(key, 3, value);
        String setex1 = jedis.setex(key, 4, value);
        System.out.println("setex:" + setex + " setex1:" + setex1);

        //-----添加数据----------
        jedis.set("name", "xinxin");//向key-->name中放入了value-->xinxin
        System.out.println(jedis.get("name"));//执行结果：xinxin

        jedis.append("name", " is my lover"); //拼接
        System.out.println(jedis.get("name"));

        jedis.del("name");  //删除某个键
        System.out.println(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name", "liuling", "age", "23", "qq", "476777XXX");
        jedis.incr("age"); //进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));


        //gk test
        jedis.dump("");
        jedis.del("");
        jedis.exists("");
        jedis.type("");
        jedis.mget("");

        jedis.set("桂坤".getBytes(), "gk".getBytes());

    }

    /**
     * jedis操作Map
     */
    @Test
    public void testMap() {
        //-----添加数据----------
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user", map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        //删除map中的某个键值
        jedis.hdel("user", "age");
        System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value

        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }

    }

    /**
     * jedis操作List
     */
    @Test
    public void testList() {
        //开始前，先移除所有的内容
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));
        //先向key java framework中存放三条数据
        jedis.lpush("java framework", "spring");
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");
        //再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework", 0, -1));

        jedis.del("java framework");
        jedis.rpush("java framework", "spring");
        jedis.rpush("java framework", "struts");
        jedis.rpush("java framework", "hibernate");
        System.out.println(jedis.lrange("java framework", 0, -1));

    }

    /**
     * jedis操作Set
     */
    @Test
    public void testSet() {
        //添加
        jedis.sadd("user", "liuling");
        jedis.sadd("user", "xinxin");
        jedis.sadd("user", "ling");
        jedis.sadd("user", "zhangxinxin");
        jedis.sadd("user", "who");
        //移除noname
        jedis.srem("user", "who");
        System.out.println(jedis.smembers("user"));//获取所有加入的value
        System.out.println(jedis.sismember("user", "who"));//判断 who 是否是user集合的元素
        System.out.println(jedis.srandmember("user"));
        System.out.println(jedis.scard("user"));//返回集合的元素个数
    }


    @Test
    public void testZset() {
        final String key = "testZset";
        jedis.zadd(key, 1.2, "test1");
        jedis.zadd(key, 1.3, "test2");
        jedis.zadd(key, 1.5, "test3");
        jedis.zadd(key, 1.0, "test4");

        Long zcount = jedis.zcount(key, 1.0, 1.2);
        Assert.assertEquals(2, zcount.longValue());
        Double test1 = jedis.zscore(key, "test1");
        Assert.assertEquals(1.2, test1, 0.1);
        Long zcard = jedis.zcard(key);
        Assert.assertEquals(4, zcard.longValue());
    }

    @Test
    public void test() throws InterruptedException {
        //jedis 排序
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
        jedis.del("a");//先清除数据，再加入数据进行测试
        jedis.rpush("a", "1");
        jedis.lpush("a", "6");
        jedis.lpush("a", "3");
        jedis.lpush("a", "9");
        System.out.println(jedis.lrange("a", 0, -1));// [9, 3, 6, 1]
//        System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //输入排序后结果
//        System.out.println(jedis.lrange("a", 0, -1));
    }


    @Test
    public void bitMapTest() {
        jedis.set("bitmap", "0001");
        System.out.println(jedis.get("bitmap"));
        Long bitmap = jedis.bitcount("bitmap");
        System.out.println("bitmap:" + bitmap);
        jedis.setbit(key, 0, true);
    }


    @Test
    public void testByteKeyValue() {
        final byte[] byteKey = "byteKey".getBytes(StandardCharsets.UTF_8);
        final byte[] byteValue = "byteValue".getBytes(StandardCharsets.UTF_8);
        jedis.set(byteKey, byteValue);
        byte[] bytes = jedis.get(byteKey);
        Assert.assertArrayEquals(byteValue, bytes);
    }

    @Test
    public void lockTest() {
        SetParams params = new SetParams();
        params.ex(10);
        params.nx();
        String set = jedis.set(key, value,params);
        log.info("status:{}", set);
    }
}

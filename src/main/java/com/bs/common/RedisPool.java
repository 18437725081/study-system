package com.bs.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 张靖烽
 * @name RedisPool
 * @description
 * @create 2018-07-05 9:12
 **/
public class RedisPool {
    private static JedisPool pool;
    private static Integer maxTotal = 20;
    private static Integer maxIdle = 10;
    private static Integer minIdle = 5;

    private static boolean testOnBorrow = false;
    private static boolean testOnReturn = true;

    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setBlockWhenExhausted(true);
        pool = new JedisPool(config,"127.0.0.1",6379,1000*2);
    }

    static {
        initPool();
    }

    public static Jedis getJrdis(){
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis){
       pool.returnResource(jedis);
    }

    public static void returnBrokenResource(Jedis jedis){
        pool.returnBrokenResource(jedis);
    }

}

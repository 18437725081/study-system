package com.bs.util;

import com.bs.common.RedisPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author 暗香
 */

public class RedisPoolUtil {

    private static final Logger log = LoggerFactory.getLogger(RedisPoolUtil.class);

    /**
     * 存储redis
     *
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value) {
        Jedis jedis = null;
        String result;
        try {
            jedis = RedisPool.getJrdis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} ,value:{} ,error", key, value, e);
            RedisPool.returnBrokenResource(jedis);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * @param key
     * @param value
     * @param exTime
     * @return
     */
    public static String setEx(String key, String value, int exTime) {
        Jedis jedis = null;
        String result;
        try {
            jedis = RedisPool.getJrdis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("set key:{} ,value:{} ,error", key, value, e);
            RedisPool.returnBrokenResource(jedis);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 过期时间
     *
     * @param key
     * @param exTime
     * @return
     */
    public static Long expire(String key, int exTime) {
        Jedis jedis = null;
        Long result;
        try {
            jedis = RedisPool.getJrdis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("set key:{} ,value:{} ,error", key, e);
            RedisPool.returnBrokenResource(jedis);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 从redis中取值
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        Jedis jedis = null;
        String result;
        try {
            jedis = RedisPool.getJrdis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} ,error", key, e);
            RedisPool.returnBrokenResource(jedis);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 删除redis
     *
     * @param key
     * @return
     */
    public static Long del(String key) {
        Jedis jedis = null;
        Long result;
        try {
            jedis = RedisPool.getJrdis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} ,error", key, e);
            RedisPool.returnBrokenResource(jedis);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }
}

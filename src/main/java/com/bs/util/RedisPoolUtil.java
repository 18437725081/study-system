package com.bs.util;

import com.bs.common.RedisPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author 张靖烽
 * @name RedisPoolUtil
 * @description redis工具类
 * @create 2018-07-05 10:50
 **/
public class RedisPoolUtil {

    private static final Logger log = LoggerFactory.getLogger(RedisPoolUtil.class);

    /**
     * @author 张靖烽
     * @description 设值
     * @createtime 2018-07-05 11:19
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
     * @author 张靖烽
     * @description 设带有有效期的值，单位为秒
     * @createtime 2018-07-07 17:38
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
     * @author 张靖烽
     * @description 重置有效时间
     * @createtime 2018-07-06 9:04
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
     * @author 张靖烽
     * @description 获取值
     * @createtime 2018-07-05 11:19
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
     * @author 张靖烽
     * @description 删除key
     * @createtime 2018-07-06 9:04
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

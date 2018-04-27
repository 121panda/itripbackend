package com.ytzl.itrip.utils.common;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * redis工具类（api）
 * Created by Administrator on 2018/4/20 0020.
 */
@Component
public class RedisUtil {
    @Resource
    private JedisPool jedisPool;

    /**
     * 以key value键值对的形式保存到redis
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        // 获取连接
        Jedis jedis = jedisPool.getResource();
        try {
            String result = jedis.set(key, value);
            // 资源还回到连接池中
            jedisPool.returnResource(jedis);
        } catch (Exception e) {
            e.printStackTrace();
            // 销毁资源
            jedisPool.returnBrokenResource(jedis);
        }
    }

    /**
     *
     * @param key
     * @param value
     * @param expire 时间 单位【秒】
     */
    public void set(String key, String value, int expire) {
        // 获取连接
        Jedis jedis = jedisPool.getResource();
        try {
            String result = jedis.setex(key, expire, value);
            // 资源还回到连接池中
            jedisPool.returnResource(jedis);
        } catch (Exception e) {
            e.printStackTrace();
            // 销毁资源
            jedisPool.returnBrokenResource(jedis);
        }
    }

    /**
     * 获取字符串key
     * @param key
     * @return
     */
    public String get(String key) {
        // 获取连接
        Jedis jedis = jedisPool.getResource();
        try {
            String result = jedis.get(key);
            // 资源还回到连接池中
            jedisPool.returnResource(jedis);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // 销毁资源
            jedisPool.returnBrokenResource(jedis);
            return null;
        }
    }

    /**
     * 获取剩余秒数
     * @param key
     * @return
     */
    public Long ttl(String key) {
        // 获取连接
        Jedis jedis = jedisPool.getResource();
        try {
            Long result = jedis.ttl(key);
            // 资源还回到连接池中
            jedisPool.returnResource(jedis);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // 销毁资源
            jedisPool.returnBrokenResource(jedis);
            return null;
        }
    }

    /**
     * 删除
     * @param key
     * @return
     */
    public Long del(String key) {
        // 获取连接
        Jedis jedis = jedisPool.getResource();
        try {
            Long result = jedis.del(key);
            // 资源还回到连接池中
            jedisPool.returnResource(jedis);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // 销毁资源
            jedisPool.returnBrokenResource(jedis);
            return null;
        }
    }
    public boolean exists(String key) {
        // 获取连接
        Jedis jedis = jedisPool.getResource();
        try {
            boolean result = jedis.exists(key);
            // 资源还回到连接池中
            jedisPool.returnResource(jedis);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // 销毁资源
            jedisPool.returnBrokenResource(jedis);
            return false;
        }
    }
}

package com.chendehe.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Slf4j
@Service
public class RedisServiceImpl {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存
     *
     * @param key   K 不为空
     * @param value V
     * @return 失败返回false
     */
    public Boolean set(String key, String value) {
        if (StringUtils.isEmpty(key)) {
            log.warn("key 为空。");
            return false;
        }
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    /**
     * 保存，超时
     *
     * @param key        K 不为空
     * @param value      V
     * @param expireTime 超时时间 > 0，秒
     * @return 失败返回false
     */
    public Boolean set(String key, String value, Long expireTime) {
        if (StringUtils.isEmpty(key) || expireTime < 0) {
            log.warn("key:{}, value:{}, expireTime:{}", key, value, expireTime);
            return false;
        }
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
        return true;
    }

    /**
     * 如果键不存在则新增,存在则不改变已经有的值。
     *
     * @param key        K 不为空
     * @param value      V
     * @param expireTime 超时时间 > 0，秒
     * @return 如果键不存在则新增, 存在则不改变已经有的值。
     */
    public Boolean setIfAbsent(String key, String value, Long expireTime) {
        if (StringUtils.isEmpty(key) || expireTime < 0) {
            log.warn("key:{}, value:{}, expireTime:{}", key, value, expireTime);
            return false;
        }
        return redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 获取
     *
     * @param key K 不为空
     * @return value V
     */
    public Object get(String key) {
        if (StringUtils.isEmpty(key)) {
            log.warn("key 为空。");
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除
     *
     * @param key K
     * @return 删除成功返回true；key不存在或者删除失败返回false；
     */
    public Boolean delete(String key) {
        if (StringUtils.isEmpty(key)) {
            log.warn("key 为空。");
            return false;
        }
        return redisTemplate.delete(key);
    }

    /**
     * 批量删除
     *
     * @param keys key集合
     * @return 删除成功返回true；key不存在或者删除失败返回false；
     */
    public Long delete(Set<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            log.warn("key 为空。");
            return 0L;
        }
        return redisTemplate.delete(keys);
    }

    /**
     * 是否存在key
     *
     * @param key K
     * @return 存在返回true
     */
    public Boolean hasKey(String key) {
        if (StringUtils.isEmpty(key)) {
            log.warn("key 为空。");
            return false;
        }
        return redisTemplate.hasKey(key);
    }

}

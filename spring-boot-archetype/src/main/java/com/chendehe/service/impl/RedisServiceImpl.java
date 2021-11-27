package com.chendehe.service.impl;

import lombok.extern.slf4j.Slf4j;
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

    public Boolean set(String key, String value, Long expireTime) {
        redisTemplate.opsForValue().set(key, value);
        return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 如果键不存在则新增,存在则不改变已经有的值。
     */
    public Boolean setIfAbsent(String key, String value, Long expireTime) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除
     * @param key key
     * @return 删除成功返回true；key不存在或者删除失败返回false；
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public Long delete(Set<String> keys) {
        return redisTemplate.delete(keys);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

}

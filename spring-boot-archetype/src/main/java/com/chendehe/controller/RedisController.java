package com.chendehe.controller;

import com.chendehe.service.impl.RedisServiceImpl;
import com.chendehe.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisServiceImpl redisService;

    @GetMapping("/set/{key}/{value}/{expire}")
    public ResponseEntity set(@PathVariable String key, @PathVariable String value, @PathVariable Long expire) {

        log.info("set start");

        return ResultUtils.success(redisService.set(key, value, expire), HttpStatus.CREATED);
    }

    @GetMapping("/get/{key}")
    public ResponseEntity get(@PathVariable String key) {

        log.info("get start");

        return ResultUtils.success(redisService.get(key), HttpStatus.OK);
    }

    @GetMapping("/del/{key}")
    public ResponseEntity del(@PathVariable String key) {

        log.info("delete start");

        return ResultUtils.success(redisService.delete(key), HttpStatus.OK);
    }

}

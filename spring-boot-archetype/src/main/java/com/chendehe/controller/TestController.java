package com.chendehe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 修改：
 *
 * @author CDH
 * @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
 * 注释：UserController、UserServiceImpl
 * @since 2019/7/27 16:10
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Value("${feign.gray.tag:\"\"}")
    private String feignGrayTag;

    /**
     * 测试.
     */
    @GetMapping("/hello")
    public String hello() {
        LOGGER.info("test hello");
        return "Hello World:" + feignGrayTag;
    }

}
package com.chendehe.controller;

import com.chendehe.service.FeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 修改：
 *
 * @author CDH
 * @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
 * 注释：UserController、UserServiceImpl
 * @since 2019/7/27 16:10
 */
@RestController
@RequestMapping("/feign")
public final class FeignTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignTestController.class);

    @Autowired
    private FeignService feignService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${feign.gray.tag}")
    private String feignGrayTag;

    /**
     * 测试.
     */
    @GetMapping("/test/{type}")
    public String feign(@PathVariable int type) {
        LOGGER.info("test feign");
        LOGGER.info("type:{}, tag:{}", type, feignGrayTag);
        if (type == 1) {
            String hello = feignService.hello();
            LOGGER.info("test feign resp:{}", hello);
        } else {
            String url = "http://cdh-service.kube-system:1111/test/hello";
            ResponseEntity<String> hello = restTemplate.getForEntity(url, String.class);
            LOGGER.info("test feign resp:{}", hello);

            return "Hello feign AND " + hello;
        }

        return "Hello feign";
    }

}
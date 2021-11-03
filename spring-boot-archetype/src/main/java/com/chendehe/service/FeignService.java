package com.chendehe.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CDH
 */
@FeignClient(name = "feign-client", url = "http://${feign.service.name}.${spring.cloud.kubernetes.client.namespace}:${feign.service.port}")
public interface FeignService {

    @GetMapping(value = "/test/hello")
    String hello();

}

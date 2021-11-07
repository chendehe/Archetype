package com.chendehe.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CDH
 */
@FeignClient(name = "cdh-service", url = "cdh-service.${feign.services.namespace}:1111")
public interface FeignService {

    @GetMapping(value = "/test/hello")
    String hello();

}

package com.chendehe.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CDH
 */
@FeignClient(name = "feign-client", url = "${hello.url:http://cdh-service.kube-system:1111}")
public interface FeignService {

    @GetMapping(value = "/test/hello")
    String hello();

}

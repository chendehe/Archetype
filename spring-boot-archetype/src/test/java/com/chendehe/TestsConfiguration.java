package com.chendehe;

import org.springframework.boot.test.context.TestConfiguration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 测试配置类.
 */
@Getter
@Setter
@ToString
@TestConfiguration
public class TestsConfiguration {
    private String username;
    private Integer age;
}

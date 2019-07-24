package com.chendehe;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.test.context.TestConfiguration;

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

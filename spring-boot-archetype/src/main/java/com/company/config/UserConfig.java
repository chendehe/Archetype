package com.company.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 自定义配置样例.
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "user")
@PropertySource("classpath:user.properties")
public class UserConfig {
  private String username;
  private Integer age;
}

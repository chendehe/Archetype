package com.chendehe.common.prop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义配置样例.
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "user")
@PropertySource("classpath:user.properties")
public final class UserProperties {
  private String username;
  private Integer age;
}

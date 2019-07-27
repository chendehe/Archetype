package com.chendehe.common.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 自定义配置样例.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
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

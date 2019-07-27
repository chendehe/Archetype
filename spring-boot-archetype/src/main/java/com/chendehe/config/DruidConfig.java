package com.chendehe.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * druid配置类.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
 */
@Configuration
public class DruidConfig {

    /**
     * 引入application.yml文件中以spring.datasource开头的信息.
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}

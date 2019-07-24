package com.chendehe.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid配置类.
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

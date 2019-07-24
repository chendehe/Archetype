package com.chendehe.config;

import javax.validation.Validator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 校验配置类，用来支持注解式的校验.
 */
@Configuration
public class ValidationConfig {

  private final MessageSource messageSource;

  public ValidationConfig(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * 添加国际化配置.
   */
  @Bean
  public Validator getValidator() {
    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
    validator.setValidationMessageSource(messageSource);
    return validator;
  }

}
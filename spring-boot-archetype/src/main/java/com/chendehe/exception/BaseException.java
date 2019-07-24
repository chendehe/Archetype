package com.chendehe.exception;

import lombok.Getter;

/**
 * 业务基础异常类.
 */
@Getter
public class BaseException extends RuntimeException {

  private String errorCode;

  private String[] param;

  /**
   * 自定义异常构造器.
   *
   * @param errorCode 错误码
   * @param param     参数
   */
  BaseException(String errorCode, String... param) {
    super(ErrorMessage.message(errorCode, param));
    this.errorCode = errorCode;
    this.param = param;
  }

}

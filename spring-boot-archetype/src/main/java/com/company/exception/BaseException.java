package com.company.exception;


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

  String getErrorCode() {
    return errorCode;
  }

  String[] getParam() {
    return param;
  }
}

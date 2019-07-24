package com.chendehe.exception;

/**
 * 业务校验类.
 */
public class ValidationException extends BaseException {

  public ValidationException(String errorCode, String... param) {
    super(errorCode, param);
  }

}
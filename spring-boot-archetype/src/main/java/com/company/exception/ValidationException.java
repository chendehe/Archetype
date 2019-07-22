package com.company.exception;

public class ValidationException extends BaseException {

  public ValidationException(String errorCode, String... param) {
    super(errorCode, param);
  }

}
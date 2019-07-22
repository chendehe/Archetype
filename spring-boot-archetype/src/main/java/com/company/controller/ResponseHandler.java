package com.company.controller;

import com.company.exception.BaseException;
import com.company.exception.ErrorCode;
import com.company.exception.ErrorMessage;
import com.company.exception.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 拦截处理异常响应
 */
@ControllerAdvice
public class ResponseHandler {

  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity handle(Exception e) {
    if (e instanceof BaseException) {
      return new ResponseEntity<>(new ErrorResult(e.getMessage(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(new ErrorResult(ErrorMessage.message(ErrorCode.SYSTEM_ERROR), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }
}

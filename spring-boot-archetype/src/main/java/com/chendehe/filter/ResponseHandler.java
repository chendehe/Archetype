package com.chendehe.filter;

import com.chendehe.common.ErrorCode;
import com.chendehe.exception.BaseException;
import com.chendehe.exception.ErrorMessage;
import com.chendehe.exception.ErrorResult;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 拦截处理异常响应.
 */
@ControllerAdvice("com.chendehe.controller")
public class ResponseHandler extends ResponseEntityExceptionHandler {

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public ResponseEntity handleControllerException(HttpServletRequest request, Throwable ex) {
    if (ex instanceof BaseException) {
      return new ResponseEntity<>(new ErrorResult(ex.getMessage(), ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(new ErrorResult(ErrorMessage.message(ErrorCode.SYSTEM_ERROR), ex.getMessage()), getStatus(request));
    }
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return HttpStatus.valueOf(statusCode);
  }
}

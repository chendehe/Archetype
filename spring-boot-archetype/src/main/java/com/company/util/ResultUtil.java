package com.company.util;

import com.company.exception.BaseException;
import com.company.exception.ErrorCode;
import com.company.exception.ErrorMessage;
import com.company.exception.ErrorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResultUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(ResultUtil.class);

  private ResultUtil() {
  }

  /**
   * 成功返回.
   *
   * @param status http状态
   */
  public static ResponseEntity success(HttpStatus status) {
    return new ResponseEntity<>(status);
  }

  /**
   * 成功返回.
   *
   * @param t      返回的报文
   * @param status http状态
   * @param <T>    泛型类型
   */
  public static <T> ResponseEntity success(T t, HttpStatus status) {
    return new ResponseEntity<>(t, status);
  }

  /**
   * 异常返回.
   *
   * @param e BaseException
   */
  public static ResponseEntity exception(Exception e, HttpStatus status) {
    LOGGER.error("[Exception]: ", e);
    if (e instanceof BaseException) {
      return new ResponseEntity<>(new ErrorResult(e.getMessage(), e.getLocalizedMessage()), status);
    } else {
      return new ResponseEntity<>(
          new ErrorResult(ErrorMessage.message(ErrorCode.SYSTEM_ERROR), e.getMessage()), status);
    }
  }

}

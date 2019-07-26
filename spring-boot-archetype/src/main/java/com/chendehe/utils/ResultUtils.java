package com.chendehe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResultUtils {

  private ResultUtils() {
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

}

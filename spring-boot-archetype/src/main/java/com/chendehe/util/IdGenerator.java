package com.chendehe.util;

import java.util.UUID;

/**
 * ID 生成器.
 */
public final class IdGenerator {

  private IdGenerator() {
  }

  /**
   * UUID 去掉 -.
   *
   * @return 时间戳
   */
  public static String get() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

}
package com.chendehe.util;

import java.util.UUID;

public final class IdGenerator {

  private IdGenerator() {
  }

  /**
   * 生成主键id.
   *
   * @return 时间戳
   */
  public static String get() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

}
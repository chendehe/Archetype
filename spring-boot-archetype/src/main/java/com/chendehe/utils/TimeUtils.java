package com.chendehe.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * 时间工具类.
 */
public final class TimeUtils {

  private TimeUtils() {
  }

  private static final String DATE = "yyyy-MM-dd";
  private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

  /**
   * Temporal 转为字符串.
   */
  public static <T extends Temporal> String parseString(T time, String... format) {
    if (time instanceof LocalDate) {
      String dateFormat = 0 == format.length ? DATE : format[0];
      return ((LocalDate) time).format(DateTimeFormatter.ofPattern(dateFormat));
    } else if (time instanceof LocalDateTime) {
      String dateFormat = 0 == format.length ? DATE_TIME : format[0];
      return ((LocalDateTime) time).format(DateTimeFormatter.ofPattern(dateFormat));
    }

    return "";
  }

  /**
   * 字符串转为 LocalDate.
   */
  public static LocalDate parseLocalDate(String time, String... format) {
    String dateFormat = 0 == format.length ? DATE : format[0];
    return LocalDate.parse(time, DateTimeFormatter.ofPattern(dateFormat));
  }

  /**
   * 字符串转为 LocalDateTime.
   */
  public static LocalDateTime parseLocalDateTime(String time, String... format) {
    String dateFormat = 0 == format.length ? DATE_TIME : format[0];
    return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(dateFormat));
  }

  /**
   * LocalDateTime 转为 Long.
   */
  public static Long parseLong(LocalDateTime time) {
    return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
  }

  /**
   * Long 转为 LocalDateTime.
   */
  public static LocalDateTime parseLocalDateTime(long timestamp) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
  }

}

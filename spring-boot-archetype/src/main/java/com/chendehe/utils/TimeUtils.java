package com.chendehe.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;

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
  public static String parseString(Temporal time, String... format) {
    if (time instanceof LocalDate) {
      String dateFormat = null == format || 0 == format.length ? DATE : format[0];
      return ((LocalDate) time).format(DateTimeFormatter.ofPattern(dateFormat));
    } else if (time instanceof LocalDateTime) {
      String dateFormat = null == format || 0 == format.length ? DATE_TIME : format[0];
      return ((LocalDateTime) time).format(DateTimeFormatter.ofPattern(dateFormat));
    }

    return "";
  }

  /**
   * 字符串转为 LocalDate.
   */
  public static LocalDate parseLocalDate(String time, String... format) {
    String dateFormat = null == format || 0 == format.length ? DATE : format[0];
    return LocalDate.parse(time, DateTimeFormatter.ofPattern(dateFormat));
  }

  /**
   * 字符串转为 LocalDateTime.
   */
  public static LocalDateTime parseLocalDateTime(String time, String... format) {
    String dateFormat = null == format || 0 == format.length ? DATE_TIME : format[0];
    return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(dateFormat));
  }

  /**
   * Long 转为 LocalDateTime.
   */
  public static LocalDateTime parseLocalDateTime(long timestamp) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
  }

  /**
   * LocalDateTime 转为 Long.
   */
  public static Long parseLong(LocalDateTime time) {
    return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
  }

  /**
   * 当前月第一天.
   */
  public static Temporal startDayOfMonth(Temporal time) {
    if (time instanceof LocalDate) {
      return ((LocalDate) time).with(TemporalAdjusters.firstDayOfMonth());
    } else if (time instanceof LocalDateTime) {
      return ((LocalDateTime) time).with(TemporalAdjusters.firstDayOfMonth());
    }
    return null;
  }

  /**
   * 当前月最后一天.
   */
  public static Temporal endDayOfMonth(Temporal time) {
    if (time instanceof LocalDate) {
      return ((LocalDate) time).with(TemporalAdjusters.lastDayOfMonth());
    } else if (time instanceof LocalDateTime) {
      return ((LocalDateTime) time).with(TemporalAdjusters.lastDayOfMonth());
    }
    return null;
  }

  /**
   * 当前月第 N 天.
   */
  public static Temporal dayOfMonth(Temporal time, int n) {
    if (time instanceof LocalDate) {
      return ((LocalDate) time).withDayOfMonth(n);
    } else if (time instanceof LocalDateTime) {
      return ((LocalDateTime) time).withDayOfMonth(n);
    }
    return null;
  }

  /**
   * 当前时间 +/- n 天.
   */
  public static Temporal plusDay(Temporal time, int n) {
    if (time instanceof LocalDate) {
      return ((LocalDate) time).plusDays(n);
    } else if (time instanceof LocalDateTime) {
      return ((LocalDateTime) time).plusDays(n);
    }
    return null;
  }

  /**
   * 当前时间 +/- n 小时.
   */
  public static Temporal plusHours(Temporal time, int n) {
    if (time instanceof LocalTime) {
      return ((LocalTime) time).plusHours(n);
    } else if (time instanceof LocalDateTime) {
      return ((LocalDateTime) time).plusHours(n);
    }
    return null;
  }

  /**
   * LocalTime/LocalDateTime的时间差：(time2-time1)秒.
   */
  public static long getSeconds(Temporal time1, Temporal time2) {
    return Duration.between(time1, time2).getSeconds();
  }

  /**
   * 时间差：(date2-date1)天.
   */
  public static long getDays(LocalDate date1, LocalDate date2) {
    return Period.between(date1, date2).getDays();
  }

}
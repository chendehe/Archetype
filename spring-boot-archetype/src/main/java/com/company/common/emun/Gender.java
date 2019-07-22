package com.company.common.emun;

import java.util.HashMap;
import java.util.Map;

/**
 * 性别 1-男，2-女.
 */
public enum Gender {

  MALE(1),
  FEMALE(2);

  private int value;

  Gender(int value) {
    this.value = value;
  }

  private static Map<Integer, Gender> map = new HashMap<>();

  static {
    for (Gender en : Gender.values()) {
      map.put(en.value, en);
    }
  }

  public int getValue() {
    return value;
  }

  public static Enum forValue(Integer value) {
    return map.get(value);
  }

}
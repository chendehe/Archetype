package com.chendehe.common.emun;

import java.util.Collections;
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

  private static Map<Integer, Gender> map;

  static {
    Map<Integer, Gender> hashMap = new HashMap<>();
    for (Gender en : Gender.values()) {
      hashMap.put(en.value, en);
      map = Collections.unmodifiableMap(hashMap);
    }
  }

  public int getValue() {
    return value;
  }

  public static Enum forValue(Integer value) {
    return map.get(value);
  }

}
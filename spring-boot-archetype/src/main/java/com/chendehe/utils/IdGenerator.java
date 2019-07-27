package com.chendehe.utils;

import java.util.UUID;

/**
 * ID 生成器.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
 */
public final class IdGenerator {

    private IdGenerator() {}

    /**
     * UUID 去掉 -.
     *
     * @return 时间戳
     */
    public static String get() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
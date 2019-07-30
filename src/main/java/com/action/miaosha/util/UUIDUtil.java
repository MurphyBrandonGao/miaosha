package com.action.miaosha.util;

import java.util.UUID;

/**
 * @author Dell
 * @create 2019-07-19 22:27
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

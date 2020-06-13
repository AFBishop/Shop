package com.bishop.utils;

import java.util.UUID;

/**
 * @ClassName CommonsUtils
 * @Author Bishop
 */
public class CommonsUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }
}

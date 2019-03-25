package com.login.utils;

import java.util.UUID;

public class SysUtils {

    private SysUtils() {

    }

    public static String uid() {
        String uid = UUID.randomUUID().toString();
        return uid.replaceAll("-", "");
    }

}

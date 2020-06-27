package com.surya.android.log;

public class LogFactory {

    public static Logger create(Class<?> clazz) {
        return new Logger(clazz.getCanonicalName());
    }
}

package com.surya.android.log;

import android.util.Log;

public class Logger {
    private final String TAG;

    /**
     * Only a package class can create this class
     */
    Logger(String tag) {
        this.TAG = tag;
    }

    public void info(String data) {
        Log.d(TAG, data);
    }

    public void i(String data) {
        this.info(data);
    }

    public void d(String data) {
        this.info(data);
    }
}

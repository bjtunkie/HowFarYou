package com.surya.android.utils;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;

public class ServiceUtils {
    public static boolean isServiceRunning(Context context, Class<? extends Service> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(50)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                /***
                 * Service is running
                 */
                return true;
            }
        }
        return false;
    }
}

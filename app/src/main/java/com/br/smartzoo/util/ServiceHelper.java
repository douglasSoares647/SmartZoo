package com.br.smartzoo.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by Taibic on 5/16/2016.
 */
public class ServiceHelper {


    public static boolean isMyServiceRunning(Class<?> serviceClass, Activity context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}

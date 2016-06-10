package com.br.smartzoo;

import android.app.Application;

import com.br.smartzoo.util.ApplicationUtil;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class SmartZooApplication extends Application{

    public final static String NAME_PACKAGE= "com.br.smartzoo";

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationUtil.setApplicationContext(getApplicationContext());
    }
}

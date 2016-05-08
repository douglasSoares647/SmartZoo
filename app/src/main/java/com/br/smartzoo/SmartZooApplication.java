package com.br.smartzoo;

import android.app.Application;

import com.br.smartzoo.model.util.ApplicationUtil;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class SmartZooApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationUtil.setApplicationContext(getApplicationContext());
    }
}

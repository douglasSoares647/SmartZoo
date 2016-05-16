package com.br.smartzoo.model.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.br.smartzoo.model.business.BusinessRules;
import com.br.smartzoo.util.TimeUtil;

/**
 * Created by Douglas on 5/16/2016.
 */
public class VisitorService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(TimeUtil.generateVisitorTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    BusinessRules.calculateIdealPrice();
                    BusinessRules.generateVisitor();
                }
            }
        });

        thread.run();

        return super.onStartCommand(intent, flags, startId);
    }
}

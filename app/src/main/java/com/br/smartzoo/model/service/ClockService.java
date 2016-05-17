package com.br.smartzoo.model.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.interfaces.OnClockTickListener;
import com.br.smartzoo.util.TimeUtil;

/**
 * Created by Douglas on 5/16/2016.
 */
public class ClockService extends Service {

    public static OnClockTickListener context;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        TimeUtil.getFromPreferences();

        TimeUtil.startClock();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        TimeUtil.clock.interrupt();

        super.onDestroy();
    }
}

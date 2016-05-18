package com.br.smartzoo.model.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.interfaces.OnClockTickListener;
import com.br.smartzoo.ui.activity.MainActivity;
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
        TimeUtil.isRunning = true;
        TimeUtil.startClock();


        //Notification to show to the player that the game is running. StartForeground used to garantee that the server
        // will almost never be stopped, only if the user stop it manually;
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_animal_lion)
                        .setContentTitle("SmartZoo")
                        .setContentText("Seu jogo está rodando, toque para acessá-lo");
        int NOTIFICATION_ID = 12345;

        Intent targetIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        startForeground(NOTIFICATION_ID,builder.build());


        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        TimeUtil.isRunning = false;

        super.onDestroy();
    }



}

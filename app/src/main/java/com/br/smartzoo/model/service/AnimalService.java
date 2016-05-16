package com.br.smartzoo.model.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.util.TimeUtil;

/**
 * Created by Douglas on 5/16/2016.
 */
public class AnimalService extends Service {


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
                        Thread.sleep(TimeUtil.timeToFeelHungry);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for(Cage cage : ZooInfo.cages){
                        for(Animal animal : cage.getAnimals()){
                            animal.eat();
                        }
                    }
                }
            }
        });

        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }
}

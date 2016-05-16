package com.br.smartzoo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.br.smartzoo.model.business.BusinessRules;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.environment.Visitor;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.interfaces.OnClockTickListener;
import com.br.smartzoo.model.service.ClockService;

import java.sql.Time;
import java.util.List;
import java.util.Observer;

/**
 * Created by Taibic on 5/15/2016.
 */
public class TimeUtil {

    public static String my_pref = "timePreferences";

    //Animal
    public static int timeToFeelHungry = 10800000;// 3 hours
    public static int timeToDigest = 1800000; // 30 minutes
    public static int digestingInterval = 60000; // 1 minute
    public static int starvingTime = 60000;

    //Janitor
    public static int timeToRest = 1800000;//30 minutes
    public static int timeToCleanEachDirty = 30000; //30 seconds

    //Veterinary
    public static int timeToTreat = 1800000;



    public int day=1;
    public int month=1;
    public int year=2016;

    public int second = 0;
    public int minute = 0;
    public int hour = 0;



    public void startClock() {
        final Handler accessUIHandler = new Handler();

        //Creation of the timer. It was created manually so we can change his speed
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    second++;
                    if(second%5==0){
                        fiveSecondsTick();
                    }
                    if (second == 60) {
                        second = 0;
                        minute++;
                        if (minute == 60) {
                            minute = 0;
                            hour++;
                            if (hour % 3 == 0) {
                                threeHours();
                            }
                            if (hour == 24) {
                                hour = 0;
                                day++;
                                if (day == 30) {
                                    day = 0;
                                    month++;
                                    if (month == 12) {
                                        month = 0;
                                        year++;
                                    }
                                }
                            }
                        }
                    }

                    //Handler to access the UI Thread
                    accessUIHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            String strHour;
                            String strMinute;
                            String strSecond;

                            if(hour<10)
                                strHour = "0" +hour;
                            else
                                strHour = String.valueOf(hour);


                            if(minute<10)
                                strMinute = "0" + minute;
                            else
                                strMinute = String.valueOf(minute);


                            if(second<10)
                                strSecond = "0" + second;
                            else
                                strSecond = String.valueOf(second);


                            //Send an message to update the current context
                            ClockService.context.onTick(strHour+":"+strMinute+":"+strSecond);

                            //OBSERVERS
                            for(Visitor visitor : ZooInfo.visitors){
                                visitor.onTick();
                            }
                        }
                    });

                }
            }
        });

        thread.start();

    }

    //Called every 5 ingame seconds to create visitors
    private static void fiveSecondsTick() {
        BusinessRules.calculateIdealPrice();
        BusinessRules.generateVisitor();
    }


    //Called every 3 ingame hours to call the animal eat method
    private static void threeHours() {
        for(Cage cage : ZooInfo.cages){
            for(Animal animal : cage.getAnimals()){
                animal.eat();
            }
        }
    }


    public void saveToPreferences(){
        SharedPreferences preferences = ApplicationUtil.applicationContext
                                        .getSharedPreferences(my_pref,ApplicationUtil.applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("day", day);
        editor.putInt("month", month);
        editor.putInt("year", year);
        editor.putInt("hour", hour);
        editor.putInt("minute", minute);
        editor.putInt("second", second);

        editor.apply();
    }


    public void getFromPreferences(){
        SharedPreferences preferences = ApplicationUtil.applicationContext
                .getSharedPreferences(my_pref,ApplicationUtil.applicationContext.MODE_PRIVATE);

        day = preferences.getInt("day",1);
        month = preferences.getInt("month",1);
        year = preferences.getInt("year",2016);


        hour = preferences.getInt("hour",0);
        minute = preferences.getInt("minute",0);
        second = preferences.getInt("second",0);
    }

}

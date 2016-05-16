package com.br.smartzoo.util;

import android.content.SharedPreferences;

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

    //VisitorService
    public static int generateVisitorTime = 5000;




    public static int day=1;
    public static int month=1;
    public static int year=2016;

    public static int second = 0;
    public static int minute = 0;
    public static int hour = 0;



    public static void startClock() {
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
                    if (second == 60) {
                        second = 0;
                        minute++;
                        if(minute%30==0){
                            halfHour();
                        }
                        if (minute == 60) {
                            minute = 0;
                            hour++;
                            if(hour%3==0){
                                threeHours();
                            }
                            if (hour == 24) {
                                hour = 0;
                                day++;
                                if(day==30){
                                    day = 0;
                                    month++;
                                    if(month == 12){
                                        month = 0;
                                        year++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        thread.start();

    }

    private static void halfHour() {

    }

    private static void threeHours() {

    }


    public static void saveToPreferences(){
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


    public static void getFromPreferences(){
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

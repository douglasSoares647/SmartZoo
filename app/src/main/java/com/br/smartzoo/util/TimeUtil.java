package com.br.smartzoo.util;

/**
 * Created by Taibic on 5/15/2016.
 */
public class TimeUtil {

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

}

package com.br.smartzoo.game.environment;

import android.content.SharedPreferences;
import android.os.Handler;

import com.br.smartzoo.model.asynctask.SaveAllPreferences;
import com.br.smartzoo.model.business.BusinessRules;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.service.ClockService;
import com.br.smartzoo.util.ApplicationUtil;

/**
 * Created by Taibic on 5/15/2016.
 */
public class Clock {

    public static String my_pref = "timePreferences";

    //Animal
    public static int timeToFeelHungry =  500; //10800;// 3 hours
    public static int timeToDigest =  100; //1800; // 30 minutes
    public static int digestingInterval = 10;//60; // 1 minute

    //Janitor
    public static int timeToCleanEachDirty = 5; //30 seconds
    public static int timeToRest = 50;//300; //5 minutes

    //Veterinary
    public static int timeToTreat = 300;//1800;


    public static int day = 1;
    public static int month = 1;
    public static int year = 2016;

    public static int second = 0;
    public static int minute = 0;
    public static int hour = 0;

    public static Thread clock;
    public static Boolean isRunning = true;


    public static int speedFactor = 1;


    public static void startClock() {
        clock = new Thread(createClock());
        clock.start();

    }

    public static void stopClock(){
        Clock.isRunning = false;
        clock.interrupt();
    }

    private static Runnable createClock(){
        final Handler accessUIHandler = new Handler();

        //Creation of the timer. It was created manually so we can change his speed
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    try {
                        if (!clock.isInterrupted())
                            Thread.sleep(1000 / speedFactor);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    second++;
                    if (second % 5 == 0) {
                        fiveSecondsTick();
                    }
                    if (second == 60) {
                        second = 0;
                        minute++;
                        if (minute == 60) {
                            minute = 0;
                            hour++;
                            if (hour % 3 == 0) {
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
                            //Send an message to update the current context
                            if (ClockService.context != null) {
                                ClockService.context.onTick(getDateString(), getTimeString());
                            }


                            //Save to preferences
                            new SaveAllPreferences().execute();

                            //OBSERVERS
                            for (Visitor visitor : ZooInfo.visitors) {
                                visitor.onTick();
                            }
                            for (Employee employee : ZooInfo.employees) {
                                employee.onTick();
                            }
                            for (Cage cage : ZooInfo.cages) {
                                for (Animal animal : cage.getAnimals()) {
                                    animal.onTick();
                                }
                            }

                        }
                    });

                }
            }
        };

        return runnable;

    }


    //Called every 5 ingame seconds to create visitors
    private static void fiveSecondsTick() {

        BusinessRules.calculateIdealPrice();
        BusinessRules.generateVisitor();

    }


    public static void saveToPreferences() {
        SharedPreferences preferences = ApplicationUtil.applicationContext
                .getSharedPreferences(my_pref, ApplicationUtil.applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("day", day);
        editor.putInt("month", month);
        editor.putInt("year", year);
        editor.putInt("hour", hour);
        editor.putInt("minute", minute);
        editor.putInt("second", second);

        editor.apply();
    }


    public static void getFromPreferences() {
        SharedPreferences preferences = ApplicationUtil.applicationContext
                .getSharedPreferences(my_pref, ApplicationUtil.applicationContext.MODE_PRIVATE);

        if (preferences.contains("day")) {
            day = preferences.getInt("day", 1);
            month = preferences.getInt("month", 1);
            year = preferences.getInt("year", 2016);


            hour = preferences.getInt("hour", 0);
            minute = preferences.getInt("minute", 0);
            second = preferences.getInt("second", 0);
        }
    }


    public static String getTimeString() {
        String strHour;
        String strMinute;
        String strSecond;

        if (hour < 10)
            strHour = "0" + hour;
        else
            strHour = String.valueOf(hour);


        if (minute < 10)
            strMinute = "0" + minute;
        else
            strMinute = String.valueOf(minute);


        if (second < 10)
            strSecond = "0" + second;
        else
            strSecond = String.valueOf(second);

        return strHour + ":" + strMinute + ":" + strSecond;
    }


    public static String getDateString() {

        String strDay;
        String strMonth;
        String strYear;

        strDay = String.valueOf(day);
        if (day < 10)
            strDay = "0" + strDay;

        strMonth = String.valueOf(month);
        if (month < 10)
            strMonth = "0" + strMonth;


        strYear = String.valueOf(year);


        return strDay + "/" + strMonth + "/" + strYear;

    }





}

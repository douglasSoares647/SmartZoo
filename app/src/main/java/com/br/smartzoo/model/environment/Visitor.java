package com.br.smartzoo.model.environment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by douglas on 03/05/16.
 */
public class Visitor {

    private Date arrivalDate;
    private Double reputationGenerated = 0.0;

    public Visitor() {
        arrivalDate = Calendar.getInstance().getTime();

        BusinessRules.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ZooInfo.visitors.remove(Visitor.this);
            }
        },60);
    }

    public void visit(){

    }

}

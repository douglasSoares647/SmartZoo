package com.br.smartzoo.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Douglas on 5/10/2016.
 */
public class DateUtil {

    public static Date stringToDate(String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate = null;
        if(date!=null || !date.isEmpty()) {
            try {
                newDate = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return newDate;
    }


    public static String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String format = null;
        if(date!=null) {
            format = dateFormat.format(date);
        }

        return format;
    }
}

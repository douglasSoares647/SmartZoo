package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dhb_s on 5/9/2016.
 */
public class FeederContract {
    public static String TABLE = "feeder";
    public static String CAGESTABLE = "cagesOfFeeder";
    public static String CAGEID = "cageId";
    public static String FEEDERID = "feederId";
    public static String ID = "id";


    public static String[] columns={ID};


    public static String createTable(){
        StringBuilder table = new StringBuilder();

        table.append(" create table " + TABLE + " ( ");
        table.append(ID + " integer primary key, ");
        table.append("FOREIGN KEY ("+ID+") references " + EmployeeContract.TABLE + "("+EmployeeContract.ID+")");
        table.append(" ); ");

        return table.toString();
    }

    public static String createTableFeederCages(){
        StringBuilder table = new StringBuilder();

        table.append(" create table " + CAGESTABLE + " ( ");
        table.append(ID + " integer primary key autoincrement, ");
        table.append(FEEDERID + " integer not null, ");
        table.append(CAGEID + " integer not null, ");
        table.append("FOREIGN KEY ("+FEEDERID+") references " + EmployeeContract.TABLE + "("+EmployeeContract.ID+"),");
        table.append("FOREIGN KEY ("+CAGEID+") references " + CageContract.TABLE + "("+CageContract.ID+")");
        table.append(" ); ");

        return table.toString();
    }



    public static ContentValues createContentValues(Feeder feeder){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,feeder.getId());

        return contentValues;
    }




    public static Feeder getFeeder(Cursor cursor){
        Feeder feeder =  new Feeder();

        if(!cursor.isBeforeFirst() || cursor.moveToNext()){
            feeder.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            feeder.setAge(cursor.getInt(cursor.getColumnIndex(EmployeeContract.AGE)));
            feeder.setName(cursor.getString(cursor.getColumnIndex(EmployeeContract.NAME)));
            feeder.setSalary(cursor.getDouble(cursor.getColumnIndex(EmployeeContract.SALARY)));

            Date startDate  = DateUtil.stringToDate(cursor.getString(cursor.getColumnIndex(EmployeeContract.STARTDATE)));
            Date endDate = DateUtil.stringToDate(cursor.getString(cursor.getColumnIndex(EmployeeContract.ENDDATE)));

            feeder.setStartDate(startDate);
            feeder.setEndDate(endDate);

            feeder.setCpf(cursor.getString(cursor.getColumnIndex(EmployeeContract.CPF)));
        }

        return feeder;
    }

    public static List<Feeder> getFeeders(Cursor cursor){
        List<Feeder> feeders = new ArrayList<>();

        while(cursor.moveToNext()){
            feeders.add(getFeeder(cursor));
        }

        return feeders;
    }


}

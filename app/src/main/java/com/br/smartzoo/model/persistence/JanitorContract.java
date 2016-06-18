package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Douglas on 5/10/2016.
 */
public class JanitorContract {

    public static String TABLE = "janitor";
    public static String ID = "id";
    public static String CURRENTCAGE = "currentCage";
    public static String ISCLEANING = "isCleaning";
    public static String CURRENTDIRTYCLEANED = "currentDirtyCleaned";
    public static String TIMETOCLEANCAGE = "timeToCleanCage";
    public static String CLOCK = "clock";

    public static String CAGEID = "cageId";
    public static String JANITORID = "janitorId";
    public static String CAGESTABLE = "cagesOfJanitor";


    public static String[] columns = {ID};


    public static String createTable(){
        StringBuilder table = new StringBuilder();

        table.append(" create table " + TABLE + " ( ");
        table.append(ID + " integer primary key, ");
        table.append(CURRENTCAGE + " integer, ");
        table.append(ISCLEANING + " integer not null, ");
        table.append(CURRENTDIRTYCLEANED + " integer, ");
        table.append(TIMETOCLEANCAGE + " integer, ");
        table.append(CLOCK + " integer not null ");
        table.append( " ); ");

        return table.toString();

    }


    public static String createTableCages(){
        StringBuilder table = new StringBuilder();

        table.append(" create table " + CAGESTABLE + " ( ");
        table.append(ID + " integer primary key, ");
        table.append(JANITORID + " integer not null, ");
        table.append(CAGEID + " integer not null, ");
        table.append("Foreign key ("+CAGEID+") references " + CageContract.TABLE +"(" + CageContract.ID+")," );
        table.append("Foreign key ("+JANITORID+") references " + TABLE +"(" +ID+") ");
        table.append(" );");

        return table.toString();
    }


    public static ContentValues createContentValues(Janitor janitor){
        ContentValues values = new ContentValues();

        values.put(ID, janitor.getId());
        values.put(CURRENTCAGE, janitor.getCurrentCage()==null?null:janitor.getCurrentCage().getId());
        values.put(CURRENTDIRTYCLEANED, janitor.getCurrentDirtyCleaned());
        values.put(ISCLEANING, janitor.getCleaning()? 1 : 0);
        values.put(TIMETOCLEANCAGE, janitor.getTimeToCleanCage());
        values.put(CLOCK, janitor.getClock());

        return values;

    }


    public static Janitor getJanitor(Cursor cursor){
        Janitor janitor = new Janitor();

        if(!cursor.isBeforeFirst() || cursor.moveToNext()){
            janitor.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            janitor.setAge(cursor.getInt(cursor.getColumnIndex(EmployeeContract.AGE)));
            janitor.setImage(cursor.getString(cursor.getColumnIndex(EmployeeContract.IMAGE)));
            janitor.setName(cursor.getString(cursor.getColumnIndex(EmployeeContract.NAME)));
            janitor.setSalary(cursor.getDouble(cursor.getColumnIndex(EmployeeContract.SALARY)));
            janitor.setStatus(cursor.getString(cursor.getColumnIndex(EmployeeContract.STATUS)));
            Date endDate = DateUtil.stringToDate(cursor.getString(cursor
                    .getColumnIndex(EmployeeContract.ENDDATE)));
            Date startDate  = DateUtil.stringToDate(cursor.getString(cursor
                    .getColumnIndex(EmployeeContract.STARTDATE)));

            janitor.setStamina(cursor.getInt(cursor.getColumnIndex(EmployeeContract.STAMINA)));
            janitor.setStartDate(startDate);
            janitor.setEndDate(endDate);


            Cage cage = new Cage();
            cage.setId(cursor.getLong(cursor.getColumnIndex(CURRENTCAGE)));
            janitor.setCurrentCage(cage);
            janitor.setCleaning(cursor.getInt(cursor.getColumnIndex(ISCLEANING))==1?true:false);
            janitor.setClock(cursor.getInt(cursor.getColumnIndex(CLOCK)));
            janitor.setTimeToCleanCage(cursor.getInt(cursor.getColumnIndex(TIMETOCLEANCAGE)));
            janitor.setCurrentDirtyCleaned(cursor.getInt(cursor.getColumnIndex(CURRENTDIRTYCLEANED)));
        }

        return janitor;
    }



    public static List<Janitor> getJanitors(Cursor cursor){

        List<Janitor> janitors = new ArrayList<>();

        while(cursor.moveToNext()){
            janitors.add(getJanitor(cursor));
        }

        return janitors;
    }


    public static HashMap<Integer,Integer> getCagesCount(Cursor cursor){
        HashMap<Integer, Integer> cagesCount = new HashMap<>();

        while(cursor.moveToNext()){
            cagesCount.put(cursor.getInt(cursor.getColumnIndex("cageId")), cursor.getInt(cursor.getColumnIndex("count")));
        }

        return cagesCount;
    }

}

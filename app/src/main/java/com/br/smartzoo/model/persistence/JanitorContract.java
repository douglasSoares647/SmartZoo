package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

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
    public static String CAGEID = "cageId";
    public static String JANITORID = "janitorId";
    public static String CAGESTABLE = "cagesOfJanitor";


    public static String[] columns = {ID};


    public static String createTable(){
        StringBuilder table = new StringBuilder();

        table.append(" create table " + TABLE + " ( ");
        table.append(ID + " integer primary key ");
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

        return values;

    }


    public static Janitor getJanitor(Cursor cursor){
        Janitor janitor = new Janitor();

        if(!cursor.isBeforeFirst() || cursor.moveToNext()){
            janitor.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            janitor.setAge(cursor.getInt(cursor.getColumnIndex(EmployeeContract.AGE)));
            janitor.setName(cursor.getString(cursor.getColumnIndex(EmployeeContract.NAME)));
            janitor.setSalary(cursor.getDouble(cursor.getColumnIndex(EmployeeContract.SALARY)));

            Date startDate  = DateUtil.stringToDate(cursor.getString(cursor.getColumnIndex(EmployeeContract.STARTDATE)));
            Date endDate = DateUtil.stringToDate(cursor.getString(cursor.getColumnIndex(EmployeeContract.ENDDATE)));

            janitor.setStartDate(startDate);
            janitor.setEndDate(endDate);
            janitor.setCpf(cursor.getString(cursor.getColumnIndex(EmployeeContract.CPF)));
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

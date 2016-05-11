package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.util.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Douglas on 5/10/2016.
 */
public class JanitorRepository {


    public static void save(Janitor janitor){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = JanitorContract.createContentValues(janitor);

        if(janitor.getId()==null){
            db.insert(JanitorContract.TABLE,null,values);
        }
        else{

            String where = " id = " + janitor.getId();
            db.update(JanitorContract.TABLE,values,where,null);
        }

        db.close();
        databaseHelper.close();
    }


    public static void saveCageOnHistory(Janitor janitor, Cage cage){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        db.execSQL(" insert into " + JanitorContract.CAGESTABLE + " ( "+ JanitorContract.JANITORID + ", "+JanitorContract.CAGEID + "), values (" + janitor.getId() +", "+cage.getId());

        db.close();
        databaseHelper.close();
    }


    public static HashMap<Integer,Integer> getCagesHistoryOfJanitor(Janitor janitor, Date startDate, Date endDate){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String start = DateUtil.dateToString(startDate);
        String end = DateUtil.dateToString(endDate);

        String sql = " select cageId, count(cageId) as 'count' from " + JanitorContract.CAGESTABLE + " where janitorId like "+janitor.getId() +
                " and date between " + start + " and  " + end + " group by cageId;";

        Cursor cursor = db.rawQuery(sql,null);

        db.close();
        databaseHelper.close();

        return FeederContract.getCagesCount(cursor);

    }

    public static HashMap<Integer,Integer> getCagesHistoryOfJanitor(Feeder feeder){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " select cageId, count(cageId) as 'count' from " + JanitorContract.CAGESTABLE + " where janitorId like " + feeder.getId() +
                " group by cageId";

        Cursor cursor = db.rawQuery(sql,null);

        return FeederContract.getCagesCount(cursor);

    }

    public static List<Janitor> getJanitors(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " Select j.id, j.expedient, e.name, e.age, e.cpf, e.startDate, e.endDate, e.salary from "+ EmployeeContract.TABLE + " e join " + JanitorContract.TABLE + " j on j.id = e.id;";

        Cursor cursor = db.rawQuery(sql,null);

        return JanitorContract.getJanitors(cursor);
    }
}

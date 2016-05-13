package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dhb_s on 5/9/2016.
 */
public class FeederRepository {

    public static void save(Feeder feeder){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = FeederContract.createContentValues(feeder);

        if(feeder.getId()==null){
            db.insert(FeederContract.TABLE,null,values);
        }
        else{

            String where = " id = " + feeder.getId();
            db.update(FeederContract.TABLE,values,where,null);
        }

        db.close();
        databaseHelper.close();
    }


    public static void saveCageOnHistory(Feeder feeder, Cage cage){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        db.execSQL(" insert into " + FeederContract.CAGESTABLE + " ( "+ FeederContract.FEEDERID + ", "+FeederContract.CAGEID + "), values (" + feeder.getId() +", "+cage.getId());

        db.close();
        databaseHelper.close();
    }


    public static HashMap<Integer,Integer> getCagesHistoryOfFeeder(Feeder feeder, Date startDate,  Date endDate){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String start = DateUtil.dateToString(startDate);
        String end = DateUtil.dateToString(endDate);

        String sql = " select cageId, count(cageId) as 'count' from " + FeederContract.CAGESTABLE + " where feederId like "+feeder.getId() +
                " and date between " + start + " and  " + end + " group by cageId;";

        Cursor cursor = db.rawQuery(sql,null);

        db.close();
        databaseHelper.close();

        return FeederContract.getCagesCount(cursor);

    }

    public static HashMap<Integer,Integer> getCagesHistoryOfFeeder(Feeder feeder){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " select cageId, count(cageId) as 'count' from " + FeederContract.CAGESTABLE + " where feederId like " + feeder.getId() +
                " group by cageId";

        Cursor cursor = db.rawQuery(sql,null);

        db.close();
        databaseHelper.close();

        return FeederContract.getCagesCount(cursor);

    }

    public static List<Feeder> getAllFeeders(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " Select f.id, e.name, e.age, e.cpf, e.startDate, e.endDate, e.salary from "+ EmployeeContract.TABLE + " e join " + FeederContract.TABLE + " f on f.id = e.id;";

        Cursor cursor = db.rawQuery(sql,null);

        return FeederContract.getFeeders(cursor);
    }


}

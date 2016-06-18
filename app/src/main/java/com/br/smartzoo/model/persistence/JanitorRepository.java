package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Douglas on 5/10/2016.
 */
public class JanitorRepository {


    public static void save(Janitor janitor) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = JanitorContract.createContentValues(janitor);

        if (JanitorRepository.existsJanitor(janitor)) {
            String where = " id = " + janitor.getId();
            db.update(JanitorContract.TABLE, values, where, null);
        } else {
            db.insert(JanitorContract.TABLE, null, values);
        }


        db.close();
        databaseHelper.close();
    }

    private static boolean existsJanitor(Janitor janitor) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] args = {String.valueOf(janitor.getId())};
        String where = "Select * from " + JanitorContract.TABLE + " where id = ? ;";


        Cursor cursor = db.rawQuery(where, args);

        boolean exists = cursor.moveToFirst();

        cursor.close();
        db.close();
        databaseHelper.close();

        return exists;
    }


    public static void saveCageOnHistory(Janitor janitor, Cage cage) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        db.execSQL(" insert into " + JanitorContract.CAGESTABLE + " ( " + JanitorContract.JANITORID + ", " + JanitorContract.CAGEID + "), values (" + janitor.getId() + ", " + cage.getId());

        db.close();
        databaseHelper.close();
    }


    public static HashMap<Integer, Integer> getCagesHistoryOfJanitor(Janitor janitor, Date startDate, Date endDate) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String start = DateUtil.dateToString(startDate);
        String end = DateUtil.dateToString(endDate);

        String sql = " select cageId, count(cageId) as 'count' from " + JanitorContract.CAGESTABLE + " where janitorId like " + janitor.getId() +
                " and date between " + start + " and  " + end + " group by cageId;";

        Cursor cursor = db.rawQuery(sql, null);

        HashMap<Integer, Integer> cagesCount = JanitorContract.getCagesCount(cursor);

        db.close();
        databaseHelper.close();

        return cagesCount;

    }

    public static HashMap<Integer, Integer> getCagesHistoryOfJanitor(Janitor janitor) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " select cageId, count(cageId) as 'count' from " + JanitorContract.CAGESTABLE + " where janitorId like " + janitor.getId() +
                " group by cageId";

        Cursor cursor = db.rawQuery(sql, null);

        HashMap<Integer, Integer> cagesCount = JanitorContract.getCagesCount(cursor);

        databaseHelper.close();
        db.close();

        return cagesCount;

    }

    public static List<Janitor> getJanitors() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " Select j.id, j.currentCage, j.clock, j.currentDirtyCleaned, j.timeToCleanCage,j.isCleaning, e.image, e.status, e.name, e.age, e.cpf, e.startDate," +
                " e.endDate, e.salary, e.stamina from " + EmployeeContract.TABLE + " e join "
                + JanitorContract.TABLE + " j on j.id = e.id;";

        Cursor cursor = db.rawQuery(sql, null);

        List<Janitor> janitors = JanitorContract.getJanitors(cursor);

        databaseHelper.close();
        db.close();

        return janitors;
    }

    public static List<Janitor> getJanitorsRested() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " Select j.id,j.currentCage, j.clock, j.currentDirtyCleaned, j.timeToCleanCage,j.isCleaning, e.image, e.status, e.name, e.age, e.cpf, e.startDate, " +
                "e.endDate, e.salary, e.stamina from " + EmployeeContract.TABLE + " e join "
                + JanitorContract.TABLE + " j on j.id = e.id where e.status = 'ready';";

        Cursor cursor = db.rawQuery(sql, null);

        List<Janitor> janitors = JanitorContract.getJanitors(cursor);

        databaseHelper.close();
        db.close();

        return janitors;
    }
}

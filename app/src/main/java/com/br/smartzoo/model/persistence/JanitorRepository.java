package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Janitor;

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


    public static void saveCages(Janitor janitor){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        for(Cage cage : janitor.getCages()) {
            db.execSQL(" insert into " + JanitorContract.CAGESTABLE + " ( "+ JanitorContract.JANITORID + ", "+JanitorContract.CAGEID + "), values (" + janitor.getId() +", "+cage.getId());

        }
    }

    public static List<Janitor> getJanitors(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " Select j.id, j.expedient, e.name, e.age, e.cpf, e.startDate, e.endDate, e.salary from "+ EmployeeContract.TABLE + " e join " + JanitorContract.TABLE + " j on j.id = e.id;";

        Cursor cursor = db.rawQuery(sql,null);

        return JanitorContract.getJanitors(cursor);
    }
}

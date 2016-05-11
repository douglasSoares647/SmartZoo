package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.entity.Janitor;

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


    public static void saveCages(Feeder feeder){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        for(Cage cage : feeder.getCages()) {
            db.execSQL(" insert into " + FeederContract.CAGESTABLE + " ( "+ FeederContract.FEEDERID + ", "+FeederContract.CAGEID + "), values (" + feeder.getId() +", "+cage.getId());

        }
    }


    public static List<Cage> getCagesOfFeeder(Feeder feeder){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " select c.id, c.name, c.isSupplied, c.isClean from cage c join " + FeederContract.CAGESTABLE + " ft on ft.cageId = c.id;";

        Cursor cursor = db.rawQuery(sql,null);

        return CageContract.getCages(cursor);


    }

    public static List<Feeder> getAllFeeders(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " Select f.id, e.name, e.age, e.cpf, e.startDate, e.endDate, e.salary from "+ EmployeeContract.TABLE + " e join " + FeederContract.TABLE + " f on f.id = e.id;";

        Cursor cursor = db.rawQuery(sql,null);

        return FeederContract.getFeeders(cursor);
    }
}

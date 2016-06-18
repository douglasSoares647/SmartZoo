package com.br.smartzoo.model.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.game.environment.ZooInfo;

/**
 * Created by Douglas on 5/11/2016.
 */
public class ZooInfoRepository {

    public static void save(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();


        String sql = " insert or replace into ZooInfo ( " + ZooInfoContract.NAME + " , " + ZooInfoContract.CASH + ", "
                + ZooInfoContract.PRICE + "," + ZooInfoContract.REPUTATION + " ) values ( " + ZooInfo.name + ", "
                + ZooInfo.money + ", " + ZooInfo.price + ", " + ZooInfoContract.REPUTATION + ");";

        db.execSQL(sql,null);

        db.close();
        databaseHelper.close();

    }


    public static void LoadZooInfo(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(ZooInfoContract.TABLE,ZooInfoContract.columns,null,null,null,null,null);

        ZooInfoContract.fillZooInfo(cursor);

        db.close();
        databaseHelper.close();
    }
}

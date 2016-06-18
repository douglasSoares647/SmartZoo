package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.smartzoo.game.environment.ZooInfo;

/**
 * Created by Douglas on 5/11/2016.
 */
public class ZooInfoContract {

    public static String TABLE = "ZooInfo";
    public static String NAME = "name";
    public static String CASH = "cash";
    public static String REPUTATION = "reputation";
    public static String PRICE = "price";
    public static String[] columns = {NAME, CASH, REPUTATION, PRICE};


    public static String createTableZooInfo() {
        StringBuilder table = new StringBuilder();

        table.append("create table " + TABLE + " ( ");
        table.append(NAME + " text primary key, ");
        table.append(CASH + " real not null, ");
        table.append(REPUTATION + " real not null, ");
        table.append(PRICE + " real not null ");
        table.append(" ); ");

        return table.toString();
    }


    public static ContentValues createContentValues() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, ZooInfo.name);
        contentValues.put(CASH, ZooInfo.money);
        contentValues.put(REPUTATION, ZooInfo.reputation);
        contentValues.put(PRICE, ZooInfo.price);


        return contentValues;
    }


    public static void fillZooInfo(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            ZooInfo.name = cursor.getString(cursor.getColumnIndex(NAME));
            ZooInfo.money = cursor.getDouble(cursor.getColumnIndex(CASH));
            ZooInfo.price = cursor.getDouble(cursor.getColumnIndex(PRICE));
            ZooInfo.reputation = cursor.getDouble(cursor.getColumnIndex(REPUTATION));
        }
    }

}

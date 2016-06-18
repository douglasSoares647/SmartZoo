package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.smartzoo.model.entity.Cage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class CageContract {

    public static String TABLE = "cage";
    public static String ID = "id";
    public static String ANIMAL_TYPE = "animalType";
    public static String NAME = "name";
    public static String CAPACITY = "capacity";
    public static String IS_CLEAN = "isClean";
    public static String IS_SUPPLIED = "isSupplied";


    public static String[] columns = {ID, NAME, ANIMAL_TYPE, CAPACITY, IS_CLEAN, IS_SUPPLIED};


    public static String createTable() {
        StringBuilder table = new StringBuilder();

        table.append(" create table " + TABLE + " ( ");
        table.append(ID + " integer primary key, ");
        table.append(NAME + " text , ");
        table.append(ANIMAL_TYPE + " text not null, ");
        table.append(CAPACITY + " integer not null, ");
        table.append(IS_CLEAN + " integer not null, ");
        table.append(IS_SUPPLIED + " integer not null ");
        table.append(" ); ");

        return table.toString();
    }


    public static ContentValues getContentValues(Cage cage) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, cage.getId());
        contentValues.put(NAME, cage.getName());
        contentValues.put(ANIMAL_TYPE, cage.getAnimalType());
        contentValues.put(CAPACITY, cage.getCapacity());
        contentValues.put(IS_CLEAN, cage.isClean() ? 1 : 0);
        contentValues.put(IS_SUPPLIED, cage.isSupplied() == true ? 1 : 0);

        return contentValues;
    }


    public static Cage getCage(Cursor cursor) {
        Cage cage = new Cage();

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {

            cage.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            cage.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            cage.setAnimalType(cursor.getString(cursor.getColumnIndex(ANIMAL_TYPE)));
            cage.setCapacity(cursor.getInt(cursor.getColumnIndex(CAPACITY)));
            cage.setIsClean((cursor.getInt(cursor.getColumnIndex(IS_CLEAN))) == 1);
            cage.setIsSupplied((cursor.getInt(cursor.getColumnIndex(IS_SUPPLIED))) == 1);
        }
        return cage;
    }


    public static List<Cage> getCages(Cursor cursor) {
        List<Cage> cages = new ArrayList<>();

        while (cursor.moveToNext()) {
            cages.add(getCage(cursor));
        }

        return cages;
    }

    public static ContentValues createUpdateClean() {
        ContentValues values = new ContentValues();
        values.put(IS_CLEAN, 1);
        return values;
    }
}

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
    public static String NAME = "name";
    public static String CAPACITY = "capacity";
    public static String ISCLEAN = "isClean";
    public static String ISSUPPLIED = "isSupplied";


    public static String[] columns = {ID,NAME,CAPACITY,ISCLEAN,ISSUPPLIED};



    public static String createTable(){
        StringBuilder table = new StringBuilder();

        table.append(" create table "+ TABLE + " ( ");
        table.append(ID + " integer primary key, ");
        table.append(NAME + " text , ");
        table.append(CAPACITY + " integer not null, ");
        table.append(ISCLEAN + " integer not null, ");
        table.append(ISSUPPLIED + " integer not null ");
        table.append(" ); ");

        return table.toString();
    }



    public static ContentValues getContentValues(Cage cage){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, cage.getId());
        contentValues.put(NAME, cage.getName());
        contentValues.put(CAPACITY, cage.getCapacity());
        contentValues.put(ISCLEAN, cage.isClean()==true? 1 : 0);
        contentValues.put(ISSUPPLIED, cage.isSupplied()==true? 1 : 0);

        return contentValues;
    }



    public static Cage getCage(Cursor cursor){
        Cage cage = new Cage();

        if(!cursor.isBeforeFirst()||cursor.moveToNext()){

            cage.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            cage.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            cage.setCapacity(cursor.getInt(cursor.getColumnIndex(CAPACITY)));
            cage.setIsClean((cursor.getInt(cursor.getColumnIndex(ISCLEAN)))==1?true :false);
            cage.setIsSupplied((cursor.getInt(cursor.getColumnIndex(ISSUPPLIED)))==1?true:false);
        }
        return cage;
    }


    public static List<Cage> getCages(Cursor cursor){
        List<Cage> cages = new ArrayList<>();

        while(cursor.moveToNext()){
            cages.add(getCage(cursor));
        }

        return cages;
    }
}

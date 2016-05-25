package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class AnimalContract {


    public static String TABLE = "animal";
    public static String ID = "id";
    public static String NAME = "name";
    public static String AGE = "age";
    public static String WEIGHT = "weight";
    public static String SEX = "sex";
    public static String CAGEID = "cage_id";
    public static String ISHEALTHY = "isHealthy";
    public static String RESISTENCE = "resistence";
    public static String POPULARITY = "popularity";

    public static String[] COLUMNS = {NAME,AGE,WEIGHT,SEX,CAGEID,ISHEALTHY,RESISTENCE,POPULARITY};



    public static String createTable(){
        StringBuilder table = new StringBuilder();

        table.append(" create table "+ TABLE + " ( ");
        table.append(ID + "integer primary key, ");
        table.append(NAME + " text not null, ");
        table.append(AGE + " integer, ");
        table.append(WEIGHT + " double not null, ");
        table.append(SEX + " text not null, ");
        table.append(CAGEID + " integer not null, ");
        table.append(ISHEALTHY + " integer not null, ");
        table.append(RESISTENCE + " integer not null, ");
        table.append(POPULARITY + " integer not null ");
        table.append(" ); ");

        return table.toString();

    }



    public static ContentValues createContentValues(Animal animal){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, animal.getId());
        contentValues.put(NAME, animal.getName());
        contentValues.put(AGE, animal.getAge());
        contentValues.put(WEIGHT, animal.getWeight());
        contentValues.put(SEX, animal.getSex());
        contentValues.put(CAGEID, animal.getCage().getId());
        contentValues.put(ISHEALTHY, animal.isHealthy()==true? 1 : 0);
        contentValues.put(RESISTENCE,animal.getResistance());
        contentValues.put(POPULARITY, animal.getPopularity());


        return contentValues;
    }


    private static Animal getAnimal(Cursor cursor){
        Animal animal = new Animal();

        if(!cursor.isBeforeFirst() ||cursor.moveToNext()) {
            animal.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            animal.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            animal.setAge(cursor.getInt(cursor.getColumnIndex(AGE)));
            animal.setWeight(cursor.getDouble(cursor.getColumnIndex(WEIGHT)));
            animal.setSex(cursor.getString(cursor.getColumnIndex(SEX)));

            Cage cage = new Cage();
            cage.setId(cursor.getLong(cursor.getColumnIndex(CAGEID)));
            animal.setCage(cage);

            animal.setIsHealthy((cursor.getInt(cursor.getColumnIndex(ISHEALTHY)))==1?true: false);
            animal.setResistance(cursor.getInt(cursor.getColumnIndex(RESISTENCE)));
            animal.setPopularity(cursor.getInt(cursor.getColumnIndex(POPULARITY)));
        }

        return animal;
    }



    public static List<Animal> getAnimals(Cursor cursor){

        List<Animal> animals = new ArrayList<>();


        while(cursor.moveToNext()){
            animals.add(getAnimal(cursor));
        }
        return animals;
    }
}

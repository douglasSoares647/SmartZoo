package com.br.smartzoo.model.persistence;

import android.content.ComponentName;
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
    public static String TYPE = "type";
    public static String STATUS = "status";
    public static String IMAGE = "image";
    public static String AGE = "age";
    public static String WEIGHT = "weight";
    public static String SEX = "sex";
    public static String CAGEID = "cage_id";
    public static String PRICE = "price";
    public static String ISHEALTHY = "isHealthy";
    public static String RESISTENCE = "resistence";
    public static String POPULARITY = "popularity";

    public static String[] COLUMNS = {ID, IMAGE, NAME,TYPE, PRICE, AGE, WEIGHT, SEX, STATUS, CAGEID, ISHEALTHY
            , RESISTENCE, POPULARITY};


    public static String createTable() {
        StringBuilder table = new StringBuilder();

        table.append(" create table " + TABLE + " ( ");
        table.append(ID + " integer primary key, ");
        table.append(NAME + " text not null, ");
        table.append(TYPE + " text not null, ");
        table.append(STATUS + " text not null, ");
        table.append(IMAGE + " text, ");
        table.append(PRICE + " double, ");
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


    public static ContentValues createContentValues(Animal animal) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, animal.getId());
        contentValues.put(NAME, animal.getName());
        contentValues.put(IMAGE, animal.getImage());
        contentValues.put(TYPE, animal.getType());
        contentValues.put(PRICE, animal.getPrice());
        contentValues.put(AGE, animal.getAge());
        contentValues.put(WEIGHT, animal.getWeight());
        contentValues.put(SEX, animal.getSex());
        contentValues.put(STATUS, animal.getStatus());
        contentValues.put(CAGEID, animal.getCage().getId());
        contentValues.put(ISHEALTHY, animal.isHealthy() ? 1 : 0);
        contentValues.put(RESISTENCE, animal.getResistance());
        contentValues.put(POPULARITY, animal.getPopularity());


        return contentValues;
    }


    private static Animal getAnimal(Cursor cursor) {
        Animal animal = new Animal();

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            animal.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            animal.setImage(cursor.getString(cursor.getColumnIndex(IMAGE)));
            animal.setStatus(cursor.getString(cursor.getColumnIndex(STATUS)));
            animal.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
            animal.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            animal.setAge(cursor.getInt(cursor.getColumnIndex(AGE)));
            animal.setWeight(cursor.getDouble(cursor.getColumnIndex(WEIGHT)));
            animal.setSex(cursor.getString(cursor.getColumnIndex(SEX)));
            animal.setPrice(cursor.getDouble(cursor.getColumnIndex(PRICE)));

            Cage cage = new Cage();
            cage.setId(cursor.getLong(cursor.getColumnIndex(CAGEID)));
            animal.setCage(cage);

            animal.setIsHealthy((cursor.getInt(cursor.getColumnIndex(ISHEALTHY))) == 1);
            animal.setResistance(cursor.getInt(cursor.getColumnIndex(RESISTENCE)));
            animal.setPopularity(cursor.getInt(cursor.getColumnIndex(POPULARITY)));
        }

        return animal;
    }


    public static List<Animal> getAnimals(Cursor cursor) {

        List<Animal> animals = new ArrayList<>();


        while (cursor.moveToNext()) {
            animals.add(getAnimal(cursor));
        }
        return animals;
    }
}

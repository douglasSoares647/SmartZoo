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
    public static String ISHUNGRY = "isHungry";
    public static String BIOLOGICALCLOCK = "clock";
    public static String FOODEATEN = "foodEaten";
    public static String ISDIGESTING = "isDigesting";


    public static String[] COLUMNS = {ID, IMAGE, NAME, TYPE, PRICE, AGE, WEIGHT, SEX, STATUS, CAGEID, ISHEALTHY
            , RESISTENCE, POPULARITY, ISHUNGRY, BIOLOGICALCLOCK, FOODEATEN, ISDIGESTING};


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
        table.append(CAGEID + " integer, ");
        table.append(ISHEALTHY + " integer not null, ");
        table.append(RESISTENCE + " integer not null, ");
        table.append(POPULARITY + " integer not null, ");
        table.append(ISHUNGRY + " integer, ");
        table.append(BIOLOGICALCLOCK + " integer not null, ");
        table.append(FOODEATEN + " real, ");
        table.append(ISDIGESTING + " integer not null ");
        table.append(" ); ");

        return table.toString();

    }


    public static ContentValues createContentValues(Animal animal) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, animal.getId());
        contentValues.put(NAME, animal.getName());
        contentValues.put(IMAGE, animal.getImage());
        contentValues.put(TYPE, animal.getSpecie());
        contentValues.put(PRICE, animal.getPrice());
        contentValues.put(AGE, animal.getAge());
        contentValues.put(WEIGHT, animal.getWeight());
        contentValues.put(SEX, animal.getSex());
        contentValues.put(STATUS, animal.getStatus());
        contentValues.put(CAGEID, animal.getCage().getId());
        contentValues.put(ISHEALTHY, animal.isHealthy() ? 1 : 0);
        contentValues.put(RESISTENCE, animal.getResistance());
        contentValues.put(POPULARITY, animal.getPopularity());
        contentValues.put(ISHUNGRY, animal.isHungry() ? 1 : 0);
        contentValues.put(BIOLOGICALCLOCK, animal.getBiologicalClock());
        contentValues.put(FOODEATEN, animal.getFoodEaten());
        contentValues.put(ISDIGESTING, animal.isDigesting() ? 1 : 0);


        return contentValues;
    }

    public static ContentValues updateCage() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(CAGEID, -1);

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

            Integer isHungry = cursor.getInt(cursor.getColumnIndex(ISHUNGRY));
            if (isHungry == 1) {
                animal.setHungry(true);
            }

            animal.setBiologicalClock(cursor.getInt(cursor.getColumnIndex(BIOLOGICALCLOCK)));
            animal.setFoodEaten(cursor.getDouble(cursor.getColumnIndex(FOODEATEN)));
            animal.setDigesting(cursor.getInt(cursor.getColumnIndex(ISDIGESTING)) == 1 ? true : false);
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

    public static ContentValues createContentValuesCage(Cage cage) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAGEID, cage.getId());

        return contentValues;
    }
}

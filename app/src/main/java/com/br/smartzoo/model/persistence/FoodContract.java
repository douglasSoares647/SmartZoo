package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by adenilson on 24/05/16.
 */
public class FoodContract {
    public static String TABLE = "food";
    public static String ID = "id";
    public static String NAME = "name";
    public static String IMAGE = "image";
    public static String PRICE = "price";
    public static String WEIGHT = "weight";
    public static String EXPIRATIONDATE = "expirationDate";


    public static String[] COLUMNS = {ID, NAME, IMAGE, PRICE, WEIGHT, EXPIRATIONDATE};

    public static String createTable() {
        StringBuilder table = new StringBuilder();

        table.append(" create table " + TABLE + " ( ");
        table.append(ID + " integer primary key, ");
        table.append(NAME + " text not null, ");
        table.append(IMAGE + " integer not null, ");
        table.append(PRICE + " double, ");
        table.append(WEIGHT + " double not null, ");
        table.append(EXPIRATIONDATE + " text not null ");

        table.append(" ); ");

        return table.toString();

    }


    public static ContentValues createContentValues(Food food) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, food.getId());
        contentValues.put(IMAGE,food.getImage());
        contentValues.put(NAME, food.getName());
        contentValues.put(PRICE, food.getPrice());
        contentValues.put(WEIGHT, food.getWeight());
        contentValues.put(EXPIRATIONDATE, DateUtil.dateToString(food.getExpirationDate()));

        return contentValues;
    }


    private static Food getFood(Cursor cursor) {
        Food food = new Food();

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            food.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            food.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            food.setImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex(IMAGE))));
            food.setPrice(cursor.getDouble(cursor.getColumnIndex(PRICE)));
            food.setWeight(cursor.getDouble(cursor.getColumnIndex(WEIGHT)));
            food.setExpirationDate(DateUtil
                    .stringToDate(cursor.getString(cursor.getColumnIndex(EXPIRATIONDATE))));

        }

        return food;
    }

    public static List<Food> getFoods(Cursor cursor) {

        List<Food> foods = new ArrayList<>();


        while (cursor.moveToNext()) {
            foods.add(getFood(cursor));
        }
        return foods;
    }

}

package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.Food;

import java.util.List;

/**
 * Created by adenilson on 24/05/16.
 */
public class FoodRepository {

    public static void save(Food food) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = FoodContract.createContentValues(food);


        if (food.getId() == null) {
            db.insert(FoodContract.TABLE, null, values);
        } else {
            String where = " id = " + food.getId();
            db.update(FoodContract.TABLE, values, where, null);
        }

        db.close();
        databaseHelper.close();

    }




    public static List<Food> getAllFoods() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();


        List<Food> foods;
        Cursor cursor = db.query(FoodContract.TABLE, FoodContract.COLUMNS, null, null, null
                , null, FoodContract.NAME);

        foods = FoodContract.getFoods(cursor);

        db.close();
        databaseHelper.close();


        return foods;
    }

    public static void delete(Long idFood){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = "id = " + idFood;
        db.delete(FoodContract.TABLE, where, null);

        db.close();
        databaseHelper.close();
    }
}

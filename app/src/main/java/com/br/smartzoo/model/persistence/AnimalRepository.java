package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class AnimalRepository {



    public static void save(Animal animal){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = AnimalContract.createContentValues(animal);


        if(animal.getId()==null){
            db.insert(AnimalContract.TABLE,null,values);
        }
        else{
            String where = " id = " + animal.getId();
            db.update(AnimalContract.TABLE,values,where,null);
        }

        db.close();
        databaseHelper.close();

    }


    public static List<Animal> getAllAnimals(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();


        List<Animal> animals = new ArrayList<>();
        Cursor cursor = db.query(AnimalContract.TABLE,AnimalContract.columns,null,null,null,null,AnimalContract.NAME);

        animals = AnimalContract.getAnimals(cursor);

        db.close();
        databaseHelper.close();


        return animals;
    }
}

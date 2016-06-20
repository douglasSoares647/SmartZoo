package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;

import java.util.List;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class AnimalRepository {


    public static Long save(Animal animal) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = AnimalContract.createContentValues(animal);


        Long id;
        if (animal.getId() == null) {
            id = db.insert(AnimalContract.TABLE, null, values);
        } else {
            String where = " id = " + animal.getId();
            db.update(AnimalContract.TABLE, values, where, null);
            id = animal.getId();
        }

        db.close();
        databaseHelper.close();

        return id;
    }


    public static List<Animal> getAllAnimals() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();


        List<Animal> animals;
        Cursor cursor = db.query(AnimalContract.TABLE,
                AnimalContract.COLUMNS, null, null, null, null, AnimalContract.NAME);

        animals = AnimalContract.getAnimals(cursor);

        db.close();
        databaseHelper.close();


        return animals;
    }

    public static int delete(Long animalId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = "id = " + animalId;
        int delete = db.delete(AnimalContract.TABLE, where, null);

        db.close();
        databaseHelper.close();

        return delete;
    }

    public static void removeAnimalsFromCage(List<Animal> animals) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = AnimalContract.updateCage();

        for (Animal a : animals) {
            String where = " id = " + a.getId();
            db.update(AnimalContract.TABLE, values, where, null);
        }


        db.close();
        databaseHelper.close();

    }

    public static List<Animal> getAnimalsByCage(Long idCage) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = AnimalContract.CAGEID + " = " + idCage;
        List<Animal> animals;
        Cursor cursor = db.query(AnimalContract.TABLE,
                AnimalContract.COLUMNS, where, null, null, null, AnimalContract.NAME);

        animals = AnimalContract.getAnimals(cursor);

        db.close();
        databaseHelper.close();


        return animals;

    }

    public static void putAnimalInCage(Cage cage, Animal animal) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = AnimalContract.createContentValuesCage(cage);


        String where = " id = " + animal.getId();
        db.update(AnimalContract.TABLE, values, where, null);


        db.close();
        databaseHelper.close();
    }
}

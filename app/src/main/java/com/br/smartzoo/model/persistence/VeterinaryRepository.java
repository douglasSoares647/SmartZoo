package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Douglas on 5/10/2016.
 */
public class VeterinaryRepository {

    public static void save(Veterinary veterinary) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = VeterinaryContract.createContentValues(veterinary);

        if (VeterinaryRepository.existsVeterinary(veterinary)) {
            String where = " id = " + veterinary.getId();
            db.update(VeterinaryContract.TABLE, values, where, null);
        } else {
            db.insert(VeterinaryContract.TABLE, null, values);
        }


        db.close();
        databaseHelper.close();

    }

    private static boolean existsVeterinary(Veterinary veterinary) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] args = {String.valueOf(veterinary.getId())};
        String where = "Select * from " + VeterinaryContract.TABLE + " where id = ? ;";


        Cursor cursor = db.rawQuery(where, args);

        boolean exists = cursor.moveToFirst();

        cursor.close();
        db.close();
        databaseHelper.close();

        return exists;
    }


    public static void saveAnimalHistory(Veterinary veterinary, Animal animal) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        db.execSQL(" insert into " + VeterinaryContract.ANIMALSTABLE + " ( " + VeterinaryContract.VETERINARYID + ", " + VeterinaryContract.ANIMALID + "), values (" + veterinary.getId() + ", " + animal.getId());


        db.close();
        databaseHelper.close();
    }

    public static HashMap<Integer, Integer> getAnimalsOnHistory(Veterinary veterinary, Date startDate, Date endDate) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String start = DateUtil.dateToString(startDate);
        String end = DateUtil.dateToString(endDate);

        String sql = " select animalId, count(animalId) as 'count' from " + VeterinaryContract.ANIMALSTABLE + " where veterinaryId like " + veterinary.getId() +
                " and date between " + start + " and  " + end + " group by animalId;";

        Cursor cursor = db.rawQuery(sql, null);

        HashMap<Integer, Integer> animalsCount = VeterinaryContract.getAnimalsCount(cursor);
        db.close();
        databaseHelper.close();

        return animalsCount;

    }


    public static HashMap<Integer, Integer> getAnimalsOnHistory(Veterinary veterinary) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " select animalId, count(animalId) as 'count' from " + VeterinaryContract.ANIMALSTABLE + " where veterinaryId like " + veterinary.getId() +
                " group by animalId;";

        Cursor cursor = db.rawQuery(sql, null);

        HashMap<Integer, Integer> animalsCount = VeterinaryContract.getAnimalsCount(cursor);

        db.close();
        databaseHelper.close();

        return animalsCount;

    }


    public static List<Veterinary> getVeterinaries() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " Select v.id, v.currentAnimal, v.clock, v.isTreating,  e.image, e.name, e.age, e.cpf, e.startDate, e.endDate, e.salary, e.stamina,e.status from "
                + EmployeeContract.TABLE + " e join " + VeterinaryContract.TABLE + " v on v.id = e.id;";

        Cursor cursor = db.rawQuery(sql, null);

        List<Veterinary> veterinaries = VeterinaryContract.getVeterinaries(cursor);

        databaseHelper.close();
        db.close();

        return veterinaries;
    }
}

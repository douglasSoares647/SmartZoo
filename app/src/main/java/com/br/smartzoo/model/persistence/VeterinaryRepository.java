package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.util.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Douglas on 5/10/2016.
 */
public class VeterinaryRepository {

    public static void save(Veterinary veterinary){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = VeterinaryContract.createContentValues(veterinary);

        if(veterinary.getId()==null){
            db.insert(VeterinaryContract.TABLE, null, values);
        }
        else{
            String where = " id = " + veterinary.getId();
            db.update(VeterinaryContract.TABLE,values,where,null);
        }

        db.close();
        databaseHelper.close();
    }



    public static void saveAnimalHistory(Veterinary veterinary, Animal animal){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        db.execSQL(" insert into " + VeterinaryContract.ANIMALSTABLE + " ( "+ VeterinaryContract.VETERINARYID + ", "+VeterinaryContract.ANIMALID + "), values (" + veterinary.getId() +", "+animal.getId());


        db.close();
        databaseHelper.close();
    }

    public static HashMap<Integer,Integer> getAnimalsOnHistory(Veterinary veterinary, Date startDate, Date endDate){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String start = DateUtil.dateToString(startDate);
        String end = DateUtil.dateToString(endDate);

        String sql = " select animalId, count(animalId) as 'count' from " + VeterinaryContract.ANIMALSTABLE+ " where veterinaryId like "+veterinary.getId() +
                " and date between " + start + " and  " + end + " group by animalId;";

        Cursor cursor = db.rawQuery(sql,null);

        db.close();
        databaseHelper.close();

        return VeterinaryContract.getAnimalsCount(cursor);

    }


    public static HashMap<Integer,Integer> getAnimalsOnHistory(Veterinary veterinary){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " select animalId, count(animalId) as 'count' from " + VeterinaryContract.ANIMALSTABLE+ " where veterinaryId like "+veterinary.getId() +
                " group by animalId;";

        Cursor cursor = db.rawQuery(sql,null);

        db.close();
        databaseHelper.close();

        return VeterinaryContract.getAnimalsCount(cursor);

    }



    public static List<Veterinary> getVeterinaries(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " Select v.id, v.credentials, e.name, e.age, e.cpf, e.startDate, e.endDate, e.salary from "+ EmployeeContract.TABLE + " e join " + VeterinaryContract.TABLE + " v on v.id = e.id;";

        Cursor cursor = db.rawQuery(sql,null);

        return VeterinaryContract.getVeterinaries(cursor);
    }
}

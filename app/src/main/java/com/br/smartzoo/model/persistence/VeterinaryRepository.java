package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.entity.Veterinary;

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



    public static void saveAnimals(Veterinary veterinary){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        for(Animal animal : veterinary.getAnimals()) {
            db.execSQL(" insert into " + VeterinaryContract.ANIMALSTABLE + " ( "+ VeterinaryContract.VETERINARYID + ", "+VeterinaryContract.ANIMALID + "), values (" + veterinary.getId() +", "+animal.getId());

        }
    }



    public static List<Veterinary> getVeterinaries(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = " Select v.id, v.credentials, e.name, e.age, e.cpf, e.startDate, e.endDate, e.salary from "+ EmployeeContract.TABLE + " e join " + VeterinaryContract.TABLE + " v on v.id = e.id;";

        Cursor cursor = db.rawQuery(sql,null);

        return VeterinaryContract.getVeterinaries(cursor);
    }
}

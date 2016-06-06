package com.br.smartzoo.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.smartzoo.util.ApplicationUtil;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smartZooDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance() {
        return new DatabaseHelper(ApplicationUtil.getContext());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(AnimalContract.createTable());
        sqLiteDatabase.execSQL(EmployeeContract.createTable());
        sqLiteDatabase.execSQL(CageContract.createTable());
        sqLiteDatabase.execSQL(FeederContract.createTable());
        sqLiteDatabase.execSQL(VeterinaryContract.createTable());
        sqLiteDatabase.execSQL(JanitorContract.createTable());
        sqLiteDatabase.execSQL(FoodContract.createTable());
        sqLiteDatabase.execSQL(JanitorContract.createTableCages());
        sqLiteDatabase.execSQL(FeederContract.createTableFeederCages());
        sqLiteDatabase.execSQL(VeterinaryContract.createTableAnimalsTreatedByVet());
        sqLiteDatabase.execSQL(NewsContract.createTable());


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
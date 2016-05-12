package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.Employee;

/**
 * Created by Douglas on 5/10/2016.
 */
public class EmployeeRepository {


    public static void save(Employee employee){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = EmployeeContract.createContentValues(employee);

        if(employee.getId()==null){
            db.insert(EmployeeContract.TABLE,null,values);
        }
        else{
            String where = " id = " + employee.getId();
            db.update(EmployeeContract.TABLE, values, where, null);
        }


        db.close();
        databaseHelper.close();
    }


}

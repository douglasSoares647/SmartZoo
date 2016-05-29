package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.business.EmployeeBusiness;
import com.br.smartzoo.model.entity.Employee;

/**
 * Created by Douglas on 5/10/2016.
 */
public class EmployeeRepository {


    public static Long save(Employee employee) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = EmployeeContract.createContentValues(employee);

        long id;

        if(EmployeeBusiness.existsEmployee(employee)){
            String where = " id = " + employee.getId();
            id = db.update(EmployeeContract.TABLE, values, where, null);
        }else{
            id = db.insert(EmployeeContract.TABLE, null, values);
        }

        db.close();
        databaseHelper.close();

        return id;
    }


    public static Integer delete(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = "id = " + id;
        int delete = db.delete(EmployeeContract.TABLE, where, null);

        db.close();
        databaseHelper.close();

        return delete;

    }

    public static Integer updateSalary(Long id, Double salary) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = "id = " + id;
        ContentValues values = EmployeeContract.createUpdateSalary(salary);
        int update = db.update(EmployeeContract.TABLE, values, where, null);

        db.close();
        databaseHelper.close();

        return update;
    }

    public static boolean existsEmployee(Employee employee) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] args = {String.valueOf(employee.getId())};
        String where = "Select * from " + EmployeeContract.TABLE + " where id = ? ;";


        Cursor cursor = db.rawQuery(where, args);

        boolean exists = cursor.moveToFirst();

        cursor.close();
        db.close();
        databaseHelper.close();

        return exists;
    }
}

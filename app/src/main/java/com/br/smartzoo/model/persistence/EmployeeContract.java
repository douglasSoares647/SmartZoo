package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.util.DateUtil;

import java.util.Date;

/**
 * Created by dhb_s on 5/9/2016.
 */
public class EmployeeContract {

    public static String TABLE = "employee";
    public static String ID = "id";
    public static String IMAGE = "image";
    public static String NAME = "name";
    public static String AGE = "age";
    public static String CPF = "cpf";
    public static String STATUS = "status";
    public static String STARTDATE = "startDate";
    public static String ENDDATE = "endDate";
    public static String SALARY = "salary";


    public static String[] columns = {ID, IMAGE, NAME, AGE, CPF, STATUS, STARTDATE, ENDDATE, SALARY};


    public static String createTable() {
        StringBuilder table = new StringBuilder();

        table.append("create table " + TABLE + " ( ");
        table.append(ID + " integer primary key, ");
        table.append(IMAGE + " integer, ");
        table.append(NAME + " text not null, ");
        table.append(AGE + " integer not null, ");
        table.append(CPF + " text unique, ");
        table.append(STARTDATE + " text not null, ");
        table.append(ENDDATE + " text, ");
        table.append(STATUS + " text, ");
        table.append(SALARY + " real not null ");
        table.append(" ) ;");

        return table.toString();
    }


    public static ContentValues createContentValues(Employee employee) {
        ContentValues values = new ContentValues();

        values.put(ID, employee.getId());
        values.put(IMAGE, employee.getImage());
        values.put(NAME, employee.getName());
        values.put(AGE, employee.getAge());
        values.put(STATUS, employee.getStatus());
        values.put(STARTDATE, DateUtil.dateToString(employee.getStartDate()));
        values.put(ENDDATE, DateUtil.dateToString(employee.getEndDate()));
        values.put(SALARY, employee.getSalary());

        return values;
    }

    public static ContentValues createUpdateSalary(Double salary) {
        ContentValues values = new ContentValues();
        values.put(SALARY, salary);


        return values;


    }

}

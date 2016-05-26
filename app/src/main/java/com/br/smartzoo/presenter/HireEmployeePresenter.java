package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.enums.EmployeeEnum;
import com.br.smartzoo.ui.view.HireEmployeeView;
import com.br.smartzoo.util.DateUtil;
import com.br.smartzoo.model.environment.Clock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by adenilson on 22/05/16.
 * test commit
 */
public class HireEmployeePresenter {

    private Activity mContext;
    private HireEmployeeView mHireEmployeeView;

    public HireEmployeePresenter(Activity context){
        this.mContext = context;
    }

    public void attachView(HireEmployeeView hireEmployeeView){
        this.mHireEmployeeView = hireEmployeeView;
    }

    public List<Employee> createEmployees() {
        List<Employee> employees = new ArrayList<>();

        for(int i=0; i<10; i++){
            employees.add(generateEmployee(EmployeeEnum.Veterinary));
            employees.add(generateEmployee(EmployeeEnum.Janitor));
            employees.add(generateEmployee(EmployeeEnum.Feeder));

        }


        return employees;
    }




    private Employee generateEmployee(EmployeeEnum type) {
        Random random = new Random();
        Employee employee;
        if (type.equals(EmployeeEnum.Veterinary)) {
            employee = new Veterinary();
            employee.setSalary(EmployeeEnum.Veterinary.getPrice());
            employee.setImage(EmployeeEnum.Veterinary.getImage());
            employee.setProfession(mContext.getString(EmployeeEnum.Veterinary.getProfession()));
            employee.setPrice(EmployeeEnum.Veterinary.getPrice());
        } else if (type.equals(EmployeeEnum.Janitor)) {
            employee = new Janitor();
            employee.setSalary(EmployeeEnum.Janitor.getPrice());
            employee.setImage(EmployeeEnum.Janitor.getImage());
            employee.setProfession(mContext.getString(EmployeeEnum.Janitor.getProfession()));
            employee.setPrice(EmployeeEnum.Janitor.getPrice());
        } else {
            employee = new Feeder();
            employee.setSalary(EmployeeEnum.Feeder.getPrice());
            employee.setImage(EmployeeEnum.Feeder.getImage());
            employee.setProfession(mContext.getString(EmployeeEnum.Feeder.getProfession()));
            employee.setPrice(EmployeeEnum.Feeder.getPrice());
        }

        employee.setName(mContext.getString(R.string.random_employee));
        employee.setAge(random.nextInt(50));

        employee.setStartDate(DateUtil.stringToDateWithBrazilianFormat(Clock.getDateString()));


        return employee;
    }
}

package com.br.smartzoo.presenter;

import android.app.Activity;
import android.content.res.Resources;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.environment.Clock;
import com.br.smartzoo.ui.view.HireEmployeeView;
import com.br.smartzoo.util.DateUtil;

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

        Resources resources = mContext.getResources();
        List<Employee> employees = new ArrayList<>();

        for(int i=0; i<10; i++){
            employees.add(generateEmployee(Employee.EmployeeEnum.Veterinary, resources));
            employees.add(generateEmployee(Employee.EmployeeEnum.Janitor, resources));
            employees.add(generateEmployee(Employee.EmployeeEnum.Feeder, resources));

        }


        return employees;
    }




    private Employee generateEmployee(Employee.EmployeeEnum type, Resources resources) {
        Random random = new Random();
        Employee employee;


        if (type.equals(Employee.EmployeeEnum.Veterinary)) {
            employee = new Veterinary();
            employee.setSalary(Employee.EmployeeEnum.Veterinary.getPrice());
            employee.setImage(resources.getResourceEntryName(Employee.EmployeeEnum.Veterinary.getImage()));
            employee.setProfession(mContext.getString(Employee.EmployeeEnum.Veterinary.getProfession()));
            employee.setPrice(Employee.EmployeeEnum.Veterinary.getPrice());
        } else if (type.equals(Employee.EmployeeEnum.Janitor)) {
            employee = new Janitor();
            employee.setSalary(Employee.EmployeeEnum.Janitor.getPrice());
            employee.setImage(resources.getResourceEntryName(Employee.EmployeeEnum.Janitor.getImage()));
            employee.setProfession(mContext.getString(Employee.EmployeeEnum.Janitor.getProfession()));
            employee.setPrice(Employee.EmployeeEnum.Janitor.getPrice());
        } else {
            employee = new Feeder();
            employee.setSalary(Employee.EmployeeEnum.Feeder.getPrice());
            employee.setImage(resources.getResourceEntryName(Employee.EmployeeEnum.Feeder.getImage()));
            employee.setProfession(mContext.getString(Employee.EmployeeEnum.Feeder.getProfession()));
            employee.setPrice(Employee.EmployeeEnum.Feeder.getPrice());
        }

        String[] names = mContext.getResources().getStringArray(R.array.names);

        int randomValue1 = random.nextInt(names.length-1);
        int randomValue2 = random.nextInt(names.length-1);

        String name1 = names[randomValue1].trim();
        String name2 = names[randomValue2].trim();

        employee.setName(name1 + " " + name2);
        employee.setAge(random.nextInt(50));
        employee.setStamina(100);

        employee.setStartDate(DateUtil.stringToDateWithBrazilianFormat(Clock.getDateString()));


        return employee;
    }
}

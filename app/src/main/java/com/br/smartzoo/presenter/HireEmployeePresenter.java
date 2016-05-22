package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.enums.EmployeeEnum;
import com.br.smartzoo.ui.view.HireEmployeeView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by adenilson on 22/05/16.
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

        employees.add(new Janitor(EmployeeEnum.Janitor.getImage(), "Estevão Coelho",  22
                , new Date(), null, EmployeeEnum.Janitor.getPrice()
                , mContext.getString(EmployeeEnum.Janitor.getProfession())));
        employees.add(new Veterinary(EmployeeEnum.Veterinary.getImage(), "Estevão Coelho",  22
                , new Date(), null, EmployeeEnum.Veterinary.getPrice()
                , mContext.getString(EmployeeEnum.Veterinary.getProfession())));
        employees.add(new Feeder(EmployeeEnum.Feeder.getImage(), "Estevão Coelho",  22
                , new Date(), null, EmployeeEnum.Feeder.getPrice()
                , mContext.getString(EmployeeEnum.Feeder.getProfession())));


        return employees;
    }
}

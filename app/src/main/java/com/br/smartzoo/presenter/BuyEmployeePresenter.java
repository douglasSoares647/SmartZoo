package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.ui.view.BuyAnimalView;
import com.br.smartzoo.ui.view.BuyEmployeeView;
import com.br.smartzoo.util.DateUtil;
import com.br.smartzoo.util.EmployeeHelper;
import com.br.smartzoo.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Taibic on 5/18/2016.
 */
public class BuyEmployeePresenter {


    private Activity mActivity;
    private BuyEmployeeView mBuyEmployeeView;

    public BuyEmployeePresenter(Activity activity) {
        this.mActivity = activity;
    }

    public void attachView(BuyEmployeeView buyEmployeeView){
        this.mBuyEmployeeView = buyEmployeeView;

    }

    public void detachView(){
        this.mBuyEmployeeView = null;

    }

    public List<Employee> createEmployeeList(){
        List<Employee> employees = new ArrayList<>();

        for(int i=0; i<30; i++){
            Employee feeder = EmployeeHelper.generateEmployee("Feeder");
            Employee janitor = EmployeeHelper.generateEmployee("Feeder");
            Employee veterinary = EmployeeHelper.generateEmployee("Feeder");


            employees.add(feeder);
            employees.add(janitor);
            employees.add(veterinary);


        }

        return employees;
    }




}

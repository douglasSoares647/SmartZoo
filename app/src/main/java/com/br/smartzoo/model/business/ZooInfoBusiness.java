package com.br.smartzoo.model.business;

import android.content.SharedPreferences;

import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.persistence.ZooInfoRepository;
import com.br.smartzoo.util.ApplicationUtil;

/**
 * Created by dhb_s on 5/12/2016.
 */
public class ZooInfoBusiness {

    public static String ZooPref = "ZooPref";
    public static boolean isLoaded = false;

    public static void save(){
        ZooInfoRepository.save();
    }


    public static void load(){
        ZooInfo.employees.addAll(FeederBusiness.getFeeders());
        ZooInfo.employees.addAll(JanitorBusiness.getJanitors());
        ZooInfo.employees.addAll(VeterinaryBusiness.getVeterinaries());

        ZooInfo.cages.addAll(CageBusiness.getAllCages());
    }



    public static void saveToPreferences(){
        SharedPreferences preferences = ApplicationUtil.applicationContext
                .getSharedPreferences(ZooPref,ApplicationUtil.applicationContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", ZooInfo.name);
        editor.putString("price", String.valueOf(ZooInfo.price));
        editor.putString("money", String.valueOf(ZooInfo.money));
        editor.putString("reputation", String.valueOf(ZooInfo.reputation));


        editor.apply();
    }


    public static void getFromPreferences(){
        SharedPreferences preferences = ApplicationUtil.applicationContext
                .getSharedPreferences(ZooPref,ApplicationUtil.applicationContext.MODE_PRIVATE);

        ZooInfo.price = Double.parseDouble(preferences.getString("price","0"));
        ZooInfo.money = Double.parseDouble(preferences.getString("money","0"));
        ZooInfo.reputation = Double.parseDouble(preferences.getString("reputation", "0"));
        ZooInfo.name = preferences.getString("name","");
    }

}

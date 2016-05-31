package com.br.smartzoo.model.business;

import android.content.SharedPreferences;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.persistence.ZooInfoRepository;
import com.br.smartzoo.util.ApplicationUtil;

import java.util.List;
import java.util.concurrent.Exchanger;

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

        List<Animal> animals = AnimalBusiness.getAllAnimals();

        for(Animal animal : animals){
            for(Cage cage : ZooInfo.cages){
                if(animal.getCage().getId() == cage.getId()){
                    animal.setCage(cage);
                    break;
                }
            }
        }
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
        try{
            SharedPreferences preferences = ApplicationUtil.applicationContext
                    .getSharedPreferences(ZooPref, ApplicationUtil.applicationContext.MODE_PRIVATE);

            ZooInfo.price = Double.parseDouble(preferences.getString("price", "0"));
            ZooInfo.money = Double.parseDouble(preferences.getString("money", "2000.00"));
            ZooInfo.reputation = Double.parseDouble(preferences.getString("reputation", "50.00"));
            ZooInfo.name = preferences.getString("name", "");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void addMoney(Double money){
        ZooInfo.money+= money;
        saveToPreferences();
    }

    public static void takeMoney(Double money){
        ZooInfo.money-=money;
        saveToPreferences();
    }

}

package com.br.smartzoo.model.business;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.smartzoo.model.entity.jail.Cage;
import com.br.smartzoo.model.persistence.CageContract;
import com.br.smartzoo.model.persistence.CageRepository;
import com.br.smartzoo.model.persistence.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class CageBusiness {

    public static void save(Cage cage){

        CageRepository.save(cage);

    }


    public static List<Cage> getAllCages(){
        List<Cage> cages = new ArrayList<>();

        cages = CageRepository.getAllCages();

        return cages;
    }


    public static Cage getCageById(Long id){
        Cage cage = new Cage();
        cage = CageRepository.getCageById(id);

        return cage;
    }
}

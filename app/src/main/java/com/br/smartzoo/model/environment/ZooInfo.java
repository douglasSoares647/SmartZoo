package com.br.smartzoo.model.environment;

import android.content.Context;
import android.widget.Chronometer;

import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by douglas on 03/05/16.
 */
public class ZooInfo {

    public static String name;
    public static Double money;
    public static Double reputation;
    public static Double price;
    public static List<Visitor> visitors = new ArrayList<>();
    public static List<Cage> cages = new ArrayList<>();
    public static List<Employee> employees = new ArrayList<>();



}

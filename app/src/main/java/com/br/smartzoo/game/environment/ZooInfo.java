package com.br.smartzoo.game.environment;

import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by douglas on 03/05/16.
 */
public class ZooInfo {

    public static String name;
    public static Double money = 2000.0;
    public static Double reputation = 50.0;
    public static Double price = 2.0;
    public static List<Visitor> visitors = new ArrayList<>();
    public static List<Cage> cages = new ArrayList<>();
    public static List<Employee> employees = new ArrayList<>();

}

package com.br.smartzoo.model.environment;

import com.br.smartzoo.model.entity.employee.Employee;
import com.br.smartzoo.model.entity.jail.Cage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by douglas on 03/05/16.
 */
public class ZooInfo {

    public static Double money;
    public static Double reputation;
    public static Double priceToVisit;
    public static List<Visitor> visitors = new ArrayList<>();
    public static List<Cage> cages;
    public static List<Employee> employees;
}

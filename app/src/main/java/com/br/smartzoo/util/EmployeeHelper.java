package com.br.smartzoo.util;

import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.entity.Veterinary;

import java.util.Random;

/**
 * Created by Taibic on 5/18/2016.
 */
public class EmployeeHelper {


    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++)
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        if (soma % 11 == 0 | soma % 11 == 1)
            primDig = new Integer(0);
        else
            primDig = new Integer(11 - (soma % 11));
        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++)
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        soma += primDig.intValue() * 2;
        if (soma % 11 == 0 | soma % 11 == 1)
            segDig = new Integer(0);
        else
            segDig = new Integer(11 - (soma % 11));
        return primDig.toString() + segDig.toString();
    }
    public static String generateCPF() {
        String iniciais = "";
        Integer numero;
        for (int i = 0; i < 9; i++) {
            numero = new Integer((int) (Math.random() * 10));
            iniciais += numero.toString();
        }
        return iniciais + calcDigVerif(iniciais);
    }
    public static boolean validateCPF(String cpf) {
        if (cpf.length() != 11)
            return false;
        String numDig = cpf.substring(0, 9);
        return calcDigVerif(numDig).equals(cpf.substring(9, 11));
    }






    public static Employee generateEmployee(String type){
        Random random = new Random();
        Employee employee;
        if(type.equals("Veterinary")){
            employee = new Veterinary();
            ((Veterinary)employee).setCredential(generateCPF());
        }
        else if(type.equals("Janitor")){
            employee = new Janitor();
        }
        else{
            employee = new Feeder();
        }

        employee.setName(type);
        employee.setAge(random.nextInt(50));

        String cpf = EmployeeHelper.generateCPF();
        while(!EmployeeHelper.validateCPF(cpf)){
            cpf = EmployeeHelper.generateCPF();
        }
        employee.setCpf(cpf);

        employee.setStartDate(DateUtil.stringToDateWithBrazilianFormat(TimeUtil.getDateString()));

        employee.setSalary((double) (random.nextInt(5000)+2000));

        return employee;

    }






}

package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.persistence.VeterinaryRepository;

import java.util.List;

/**
 * Created by Douglas on 5/10/2016.
 */
public class VeterinaryBusiness {

    public static void save(Veterinary veterinary){
        EmployeeBusiness.save(veterinary);
        VeterinaryRepository.save(veterinary);
    }



    public static List<Veterinary> getVeterinaries(){

        return VeterinaryRepository.getVeterinaries();
    }
}

package com.br.smartzoo.model.interfaces;

import com.br.smartzoo.model.entity.Veterinary;

/**
 * Created by adenilson on 26/05/16.
 */
public interface OnManageVeterinary {

    void onDemit(Veterinary veterinary);

    void onSalaryChange(Veterinary veterinary, Double value);
}

package com.br.smartzoo.model.interfaces;

import com.br.smartzoo.model.entity.Employee;

/**
 * Created by adenilson on 26/05/16.
 */
public interface OnManageEmployee {

    void onDemit(Employee veterinary);

    void onSalaryChange(Employee employee, Double value);

    void onClick(Employee employee);
}

package com.br.smartzoo.model.interfaces;

import com.br.smartzoo.model.entity.food.Food;
import com.br.smartzoo.model.entity.jail.Cage;

import java.util.List;



/**
 * Created by adenilson on 18/04/16.
 */
public interface Manageable {


    void prepareFoodAndFillCage(List<Food> foods, Cage cage);

    void toRetain(Cage cage);

}

package com.br.smartzoo.model.interfaces;

import com.br.smartzoo.model.entity.Food;

import java.util.HashMap;

/**
 * Created by adenilson on 15/05/16.
 */
public interface OnChangeBuyListener {

    void onQuantityChange(HashMap<Food, Integer> quantity);
}

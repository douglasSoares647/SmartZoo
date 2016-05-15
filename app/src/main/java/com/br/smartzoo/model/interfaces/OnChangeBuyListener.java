package com.br.smartzoo.model.interfaces;

import com.br.smartzoo.model.entity.Food;

/**
 * Created by adenilson on 15/05/16.
 */
public interface OnChangeBuyListener {

    void onQuantityChange(Double price, int quantity);
}

package com.br.smartzoo.model.interfaces;

import com.br.smartzoo.model.entity.Food;

/**
 * Created by adenilson on 24/05/16.
 */
public interface OnManageFood {

    void onSell(Food food);

    void onPrepare(Food food);

}

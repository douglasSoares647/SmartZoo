package com.br.smartzoo.ui.view;

import com.br.smartzoo.model.entity.Food;

import java.util.HashMap;
import java.util.List;

/**
 * Created by adenilson on 24/05/16.
 */
public interface ManageStockView {

   void  onFoodListLoaded();

    void showSnackBar(String message);
}

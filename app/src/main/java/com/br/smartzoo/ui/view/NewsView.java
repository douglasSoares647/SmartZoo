package com.br.smartzoo.ui.view;

import com.br.smartzoo.model.entity.New;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 05/06/16.
 */
public interface NewsView {

    void onLoadNews(List<New> news);

    void showSnackBar(String message);

    void onLoadNewsEmpty(ArrayList<New> news);
}

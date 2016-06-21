package com.br.smartzoo.ui.view;

import com.br.smartzoo.model.entity.Feeder;

import java.util.List;

/**
 * Created by adenilson on 29/05/16.
 */
public interface FeederListView {

    void onLoadFeederSuccess(List<Feeder> veterinaries);

    void onLoadFeederEmpty();
}

package com.br.smartzoo.ui.view;

import com.br.smartzoo.model.entity.Cage;

import java.util.List;

/**
 * Created by adenilson on 07/06/16.
 */

public interface CageListView {
    void onLoadCageList(List<Cage> cages);

    void onCageDestroyed(Cage cage);

    void onAnimalTypeChanged(Cage cage);

    void onLoadCageListEmpty();

}

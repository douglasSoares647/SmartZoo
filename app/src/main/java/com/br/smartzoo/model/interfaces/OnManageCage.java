package com.br.smartzoo.model.interfaces;

import com.br.smartzoo.model.entity.Cage;

/**
 * Created by adenilson on 07/06/16.
 */

public interface OnManageCage {

    void onDestroyCage(Cage cage);

    void onCleanCage(Cage cage);
}

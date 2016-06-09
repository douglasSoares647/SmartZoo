package com.br.smartzoo.model.interfaces;

import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Janitor;

/**
 * Created by adenilson on 08/06/16.
 */

public interface OnJanitorsRestedSelected {
    void onSelected(Janitor janitor, Cage cage);
}

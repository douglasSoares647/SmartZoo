package com.br.smartzoo.model.interfaces;

import com.br.smartzoo.model.entity.Cage;


/**
 * Created by adenilson on 18/04/16.
 */
public interface Manageable {


    void feedCage(Cage cage);

    void toRetain(Cage cage);

}

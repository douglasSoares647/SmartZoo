package com.br.smartzoo.model.interfaces;

/**
 * Created by Taibic on 6/15/2016.
 */
public interface OnTreatAnimalListener {

    void onTreatProgress(Integer progress);

    void onTreatFinish();

    void onStatusChange();

    void onStaminaChange();
}

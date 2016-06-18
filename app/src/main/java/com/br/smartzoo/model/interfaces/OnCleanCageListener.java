package com.br.smartzoo.model.interfaces;

/**
 * Created by Taibic on 6/15/2016.
 */
public interface OnCleanCageListener {

    void onCleanDirty(Integer currentDirtyCleaned);

    void onCleanFinish();

    void onStatusChange();

    void onStaminaChange();
}

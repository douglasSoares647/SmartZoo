package com.br.smartzoo.model.asynctask;

import android.os.AsyncTask;

import com.br.smartzoo.game.environment.Clock;
import com.br.smartzoo.model.business.ZooInfoBusiness;

/**
 * Created by Taibic on 6/17/2016.
 */
public class SaveAllPreferences extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... params) {
        Clock.saveToPreferences();
        ZooInfoBusiness.saveToPreferences();
        return null;
    }
}

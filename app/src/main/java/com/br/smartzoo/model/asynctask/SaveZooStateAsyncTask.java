package com.br.smartzoo.model.asynctask;

import android.os.AsyncTask;

import com.br.smartzoo.model.business.ZooInfoBusiness;

/**
 * Created by Taibic on 6/17/2016.
 */
public class SaveZooStateAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        ZooInfoBusiness.saveAll();
        return null;
    }

}

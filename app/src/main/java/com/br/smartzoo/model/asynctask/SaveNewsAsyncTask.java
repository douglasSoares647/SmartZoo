package com.br.smartzoo.model.asynctask;

import android.os.AsyncTask;

import com.br.smartzoo.model.business.NewsFeedBusiness;
import com.br.smartzoo.model.entity.New;

import java.util.List;

/**
 * Created by Taibic on 6/6/2016.
 */
public class SaveNewsAsyncTask extends AsyncTask<List<New>,Void,Void> {


    @Override
    protected Void doInBackground(List<New>... params) {
        for(New aNew : params[0])
        NewsFeedBusiness.save(aNew);

        return null;
    }
}

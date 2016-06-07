package com.br.smartzoo.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.br.smartzoo.R;
import com.br.smartzoo.model.asynctask.LoadNewsAsyncTask;
import com.br.smartzoo.model.business.NewsFeedBusiness;
import com.br.smartzoo.model.entity.New;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.view.NewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 05/06/16.
 */
public class NewsPresenter {

    private Activity mContext;
    private NewsView mNewsView;

    public NewsPresenter(Activity context) {
        this.mContext = context;
    }

    public void attachView(NewsView newsView){
        this.mNewsView = newsView;
    }

    public void loadNews() {

        new LoadNewsAsyncTask(new LoadNewsAsyncTask.OnLoadNewsList() {
            @Override
            public void onLoadListSuccess(List<New> news) {
                mNewsView.onLoadNews(news);
                mNewsView.showSnackBar(mContext.getString(R.string.msg_news_succesfully_loaded));
            }

            @Override
            public void onLoadListFailed() {
                mNewsView.showSnackBar(mContext.getString(R.string.msg_news_failed_to_load));
            }

            @Override
            public void onLoadListEmpty() {
                mNewsView.showSnackBar(mContext.getString(R.string.msg_no_news_to_load));
            }
        }, mContext).execute();


    }

    public void startTransaction(String fragment) {
        //string fragment irá verificar qual tag é a do new
        ((MainActivity)mContext).startTransaction(new Fragment());
    }
}

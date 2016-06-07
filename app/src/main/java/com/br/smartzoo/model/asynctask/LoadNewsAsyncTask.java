package com.br.smartzoo.model.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.NewsFeedBusiness;
import com.br.smartzoo.model.entity.New;
import com.br.smartzoo.util.ProgressDialogUtil;

import java.util.List;

/**
 * Created by Taibic on 6/6/2016.
 */
public class LoadNewsAsyncTask extends AsyncTask<Void,Integer,List<New>> {

    private OnLoadNewsList mCallBack;
    private Activity mContext;
    private ProgressDialog mProgressDialog;

    public LoadNewsAsyncTask(OnLoadNewsList mCallBack, Activity mContext) {
        this.mCallBack = mCallBack;
        this.mContext = mContext;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = ProgressDialogUtil
                .makeProgressDialog(mContext,mContext.getString(R.string.msg_loading_news_feed),
                        mContext.getString(R.string.title_loading_news));
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                LoadNewsAsyncTask.this.cancel(true);
            }
        });
        mProgressDialog.show();
    }

    @Override
    protected List<New> doInBackground(Void... params) {
        return NewsFeedBusiness.getLastNews();
    }

    @Override
    protected void onPostExecute(List<New> news) {
        if(news==null){
            mCallBack.onLoadListFailed();
        }
        else if(news.isEmpty()){
            mCallBack.onLoadListEmpty();
        }
        else{
            mCallBack.onLoadListSuccess(news);
        }

        mProgressDialog.dismiss();
        super.onPostExecute(news);
    }

    public interface OnLoadNewsList{

        void onLoadListSuccess(List<New> news);
        
        void onLoadListFailed();

        void onLoadListEmpty();
    }

}

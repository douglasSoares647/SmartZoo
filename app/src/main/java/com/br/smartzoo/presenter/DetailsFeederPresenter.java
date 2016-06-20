package com.br.smartzoo.presenter;

import android.app.Activity;
import android.view.animation.AccelerateInterpolator;

import com.br.smartzoo.ui.fragment.DetailsFeederFragment;
import com.br.smartzoo.ui.view.DetailsFeederView;

/**
 * Created by adenilson on 19/06/16.
 */

public class DetailsFeederPresenter {
    private Activity mContext;
    private DetailsFeederView mDetailsFeederView;

    public DetailsFeederPresenter(Activity context){
        this.mContext = context;
    }

    public void attachView(DetailsFeederView detailsFeederView) {
        this.mDetailsFeederView = detailsFeederView;
    }
}

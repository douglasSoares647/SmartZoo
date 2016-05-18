package com.br.smartzoo.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.fragment.BuyAnimalFragment;
import com.br.smartzoo.ui.fragment.BuyCageFragment;
import com.br.smartzoo.ui.fragment.BuyFoodFragment;
import com.br.smartzoo.ui.view.MainActivityView;

/**
 * Created by adenilson on 12/05/16.
 */
public class MainActivityPresenter {


    private MainActivity mActivity;
    private MainActivityView mMainActivityView;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mActivity = mainActivity;
    }

    public void attachView(MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    public void detachView() {
        this.mMainActivityView = null;
    }

    public void startTransaction(int container, Fragment fragment) {
        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left
                , android.R.anim.slide_out_right);


        if (fragment instanceof BuyAnimalFragment) {
            BuyAnimalFragment buyAnimalFragment = (BuyAnimalFragment) fragment;
            transaction.replace(container, buyAnimalFragment);
            transaction.commit();
        }else if(fragment instanceof BuyFoodFragment){
            BuyFoodFragment buyFruitFragment = (BuyFoodFragment) fragment;
            transaction.replace(container, buyFruitFragment);
            transaction.commit();
        }else if(fragment instanceof BuyCageFragment){
            BuyCageFragment buyFruitFragment = (BuyCageFragment) fragment;
            transaction.replace(container, buyFruitFragment);
            transaction.commit();
        }



    }


}

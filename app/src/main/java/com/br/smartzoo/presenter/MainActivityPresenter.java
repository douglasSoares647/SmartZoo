package com.br.smartzoo.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.fragment.AnimalListFragment;
import com.br.smartzoo.ui.fragment.BuyAnimalFragment;
import com.br.smartzoo.ui.fragment.BuyCageFragment;
import com.br.smartzoo.ui.fragment.BuyFoodFragment;
import com.br.smartzoo.ui.fragment.CageListFragment;
import com.br.smartzoo.ui.fragment.ChooseEmployeeFragment;
import com.br.smartzoo.ui.fragment.FeederListFragment;
import com.br.smartzoo.ui.fragment.HireEmployeeFragment;
import com.br.smartzoo.ui.fragment.JanitorListFragment;
import com.br.smartzoo.ui.fragment.ManageStockFragment;
import com.br.smartzoo.ui.fragment.NewsFragment;
import com.br.smartzoo.ui.fragment.StatusZooFragment;
import com.br.smartzoo.ui.fragment.VeterinaryListFragment;
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
        }else if(fragment instanceof HireEmployeeFragment){
            HireEmployeeFragment hireEmployeeFragment = (HireEmployeeFragment) fragment;
            transaction.replace(container, hireEmployeeFragment);
            transaction.commit();
        }else if(fragment instanceof ManageStockFragment){
            ManageStockFragment mangeStockFragment = (ManageStockFragment) fragment;
            transaction.replace(container, mangeStockFragment);
            transaction.commit();
        }else if(fragment instanceof ChooseEmployeeFragment){
            ChooseEmployeeFragment chooseEmployeeFragment = (ChooseEmployeeFragment) fragment;
            transaction.replace(container, chooseEmployeeFragment);
            transaction.commit();
        }else if(fragment instanceof VeterinaryListFragment){
            VeterinaryListFragment veterinaryListFragment = (VeterinaryListFragment) fragment;
            transaction.replace(container, veterinaryListFragment);
            transaction.commit();
        }else if(fragment instanceof FeederListFragment){
            FeederListFragment feederListFragment = (FeederListFragment) fragment;
            transaction.replace(container, feederListFragment);
            transaction.commit();
        }else if(fragment instanceof StatusZooFragment){
            StatusZooFragment statusZooFragment = (StatusZooFragment) fragment;
            transaction.replace(container, statusZooFragment);
            transaction.commit();
        }else if(fragment instanceof AnimalListFragment){
            AnimalListFragment animalListFragment = (AnimalListFragment) fragment;
            transaction.replace(container, animalListFragment);
            transaction.commit();
        }else if(fragment instanceof NewsFragment){
            NewsFragment newListFragment = (NewsFragment) fragment;
            transaction.replace(container, newListFragment);
            transaction.commit();
        }else if(fragment instanceof JanitorListFragment){
            JanitorListFragment janitorListFragment = (JanitorListFragment) fragment;
            transaction.replace(container, janitorListFragment);
            transaction.commit();
        }else if(fragment instanceof CageListFragment){
            CageListFragment cageListFragment = (CageListFragment) fragment;
            transaction.replace(container, cageListFragment);
            transaction.commit();
        }



    }


}

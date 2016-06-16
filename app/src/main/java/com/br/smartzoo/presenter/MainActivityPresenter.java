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
import com.br.smartzoo.ui.fragment.DetailsAnimalFragment;
import com.br.smartzoo.ui.fragment.DetailsJanitorFragment;
import com.br.smartzoo.ui.fragment.DetailsVeterinaryFragment;
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

    public static final String NEWS_FRAGMENT = "NEWS_FRAGMENT";
    public static final String ANIMAL_LIST_FRAGMENT = "ANIMAL_LIST_FRAGMENT";
    public static final String BUY_ANIMAL_FRAGMENT = "BUY_ANIMAL_FRAGMENT";
    public static final String BUY_CAGE_FRAGMENT = "BUY_CAGE_FRAGMENT";
    public static final String BUY_FOOD_FRAGMENT = "BUY_FOOD_FRAGMENT";
    public static final String CAGE_LIST_FRAGMENT = "CAGE_LIST_FRAGMENT";
    public static final String CHOOSE_EMPLOYEE_FRAGMENT = "CHOOSE_EMPLOYEE_FRAGMENT";
    public static final String DETAILS_ANIMAL_FRAGMENT = "DETAILS_ANIMAL_FRAGMENT";
    public static final String DETAILS_JANITOR_FRAGMENT = "DETAILS_JANITOR_FRAGMENT";
    public static final String DETAILS_VETERINARY_FRAGMENT = "DETAILS_VETERINARY_FRAGMENT";
    public static final String FEEDER_LIST_FRAGMENT = "FEEDER_LIST_FRAGMENT";
    public static final String HIRE_EMPLOYEE_FRAGMENT = "HIRE_EMPLOYEE_FRAGMENT";
    public static final String JANITOR_LIST_FRAGMENT = "JANITOR_LIST_FRAGMENT";
    public static final String MANAGE_STOCK_FRAGMENT = "MANAGE_STOCK_FRAGMENT";
    public static final String STATUS_ZOO_FRAGMENT = "STATUS_ZOO_FRAGMENT";
    public static final String VETERINARY_LIST_FRAGMENT = "VETERINARY_LIST_FRAGMENT";

    private MainActivity mActivity;
    private MainActivityView mMainActivityView;
    private static String actualFragment;

    public String getActualFragment() {
        return actualFragment;
    }

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
            transaction.replace(container, buyAnimalFragment, BUY_ANIMAL_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof BuyFoodFragment) {
            BuyFoodFragment buyFruitFragment = (BuyFoodFragment) fragment;
            transaction.replace(container, buyFruitFragment, BUY_FOOD_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof BuyCageFragment) {
            BuyCageFragment buyFruitFragment = (BuyCageFragment) fragment;
            transaction.replace(container, buyFruitFragment, BUY_CAGE_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof HireEmployeeFragment) {
            HireEmployeeFragment hireEmployeeFragment = (HireEmployeeFragment) fragment;
            transaction.replace(container, hireEmployeeFragment, HIRE_EMPLOYEE_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof ManageStockFragment) {
            ManageStockFragment mangeStockFragment = (ManageStockFragment) fragment;
            transaction.replace(container, mangeStockFragment, MANAGE_STOCK_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof ChooseEmployeeFragment) {
            ChooseEmployeeFragment chooseEmployeeFragment = (ChooseEmployeeFragment) fragment;
            transaction.replace(container, chooseEmployeeFragment, CHOOSE_EMPLOYEE_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof VeterinaryListFragment) {
            VeterinaryListFragment veterinaryListFragment = (VeterinaryListFragment) fragment;
            transaction.replace(container, veterinaryListFragment, VETERINARY_LIST_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof FeederListFragment) {
            FeederListFragment feederListFragment = (FeederListFragment) fragment;
            transaction.replace(container, feederListFragment, FEEDER_LIST_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof StatusZooFragment) {
            StatusZooFragment statusZooFragment = (StatusZooFragment) fragment;
            transaction.replace(container, statusZooFragment, STATUS_ZOO_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof AnimalListFragment) {
            AnimalListFragment animalListFragment = (AnimalListFragment) fragment;
            transaction.replace(container, animalListFragment, ANIMAL_LIST_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof NewsFragment) {
            NewsFragment newListFragment = (NewsFragment) fragment;
            transaction.replace(container, newListFragment, NEWS_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof JanitorListFragment) {
            JanitorListFragment janitorListFragment = (JanitorListFragment) fragment;
            transaction.replace(container, janitorListFragment, JANITOR_LIST_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof CageListFragment) {
            CageListFragment cageListFragment = (CageListFragment) fragment;
            transaction.replace(container, cageListFragment, CAGE_LIST_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof DetailsAnimalFragment) {
            DetailsAnimalFragment detailsAnimalFragment = (DetailsAnimalFragment) fragment;
            transaction.replace(container, detailsAnimalFragment, DETAILS_ANIMAL_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof DetailsJanitorFragment){
            DetailsJanitorFragment detailsJanitorFragment = (DetailsJanitorFragment) fragment;
            transaction.replace(container, detailsJanitorFragment, DETAILS_JANITOR_FRAGMENT);
            transaction.commit();
        }else if (fragment instanceof DetailsVeterinaryFragment) {
            DetailsVeterinaryFragment detailsVeterinaryFragment = (DetailsVeterinaryFragment)
                    fragment;
            transaction.replace(container, detailsVeterinaryFragment, DETAILS_VETERINARY_FRAGMENT);
            transaction.commit();
        }


    }


}

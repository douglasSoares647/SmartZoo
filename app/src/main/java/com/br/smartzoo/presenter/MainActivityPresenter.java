package com.br.smartzoo.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.br.smartzoo.game.GameState;
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

    private MainActivity mContext;
    private MainActivityView mMainActivityView;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mContext = mainActivity;
    }

    public void attachView(MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    public void detachView() {
        this.mMainActivityView = null;
    }

    public void startTransaction(int container, Fragment fragment) {
        FragmentTransaction transaction = mContext.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left
                , android.R.anim.slide_out_right);


        if (fragment instanceof BuyAnimalFragment) {
            BuyAnimalFragment buyAnimalFragment = (BuyAnimalFragment) fragment;
            transaction.replace(container, buyAnimalFragment, GameState.BUY_ANIMAL_FRAGMENT);
            transaction.addToBackStack(GameState.BUY_ANIMAL_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof BuyFoodFragment) {
            BuyFoodFragment buyFruitFragment = (BuyFoodFragment) fragment;
            transaction.replace(container, buyFruitFragment, GameState.BUY_FOOD_FRAGMENT);
            transaction.addToBackStack(GameState.BUY_FOOD_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof BuyCageFragment) {
            BuyCageFragment buyFruitFragment = (BuyCageFragment) fragment;
            transaction.replace(container, buyFruitFragment, GameState.BUY_CAGE_FRAGMENT);
            transaction.addToBackStack(GameState.BUY_CAGE_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof HireEmployeeFragment) {
            HireEmployeeFragment hireEmployeeFragment = (HireEmployeeFragment) fragment;
            transaction.replace(container, hireEmployeeFragment, GameState.HIRE_EMPLOYEE_FRAGMENT);
            transaction.addToBackStack(GameState.HIRE_EMPLOYEE_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof ManageStockFragment) {
            ManageStockFragment mangeStockFragment = (ManageStockFragment) fragment;
            transaction.replace(container, mangeStockFragment, GameState.MANAGE_STOCK_FRAGMENT);
            transaction.addToBackStack(GameState.MANAGE_STOCK_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof ChooseEmployeeFragment) {
            ChooseEmployeeFragment chooseEmployeeFragment = (ChooseEmployeeFragment) fragment;
            transaction.replace(container, chooseEmployeeFragment, GameState
                    .CHOOSE_EMPLOYEE_FRAGMENT);
            transaction.addToBackStack(GameState.CHOOSE_EMPLOYEE_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof VeterinaryListFragment) {
            VeterinaryListFragment veterinaryListFragment = (VeterinaryListFragment) fragment;
            transaction.replace(container, veterinaryListFragment, GameState
                    .VETERINARY_LIST_FRAGMENT);
            transaction.addToBackStack(GameState.VETERINARY_LIST_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof FeederListFragment) {
            FeederListFragment feederListFragment = (FeederListFragment) fragment;
            transaction.replace(container, feederListFragment, GameState.FEEDER_LIST_FRAGMENT);
            transaction.addToBackStack(GameState.FEEDER_LIST_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof StatusZooFragment) {
            StatusZooFragment statusZooFragment = (StatusZooFragment) fragment;
            transaction.replace(container, statusZooFragment, GameState.STATUS_ZOO_FRAGMENT);
            transaction.addToBackStack(GameState.STATUS_ZOO_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof AnimalListFragment) {
            AnimalListFragment animalListFragment = (AnimalListFragment) fragment;
            transaction.replace(container, animalListFragment, GameState.ANIMAL_LIST_FRAGMENT);
            transaction.addToBackStack(GameState.ANIMAL_LIST_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof NewsFragment) {
            NewsFragment newListFragment = (NewsFragment) fragment;
            transaction.replace(container, newListFragment, GameState.NEWS_FRAGMENT);
            transaction.addToBackStack(GameState.NEWS_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof JanitorListFragment) {
            JanitorListFragment janitorListFragment = (JanitorListFragment) fragment;
            transaction.replace(container, janitorListFragment, GameState.JANITOR_LIST_FRAGMENT);
            transaction.addToBackStack(GameState.JANITOR_LIST_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof CageListFragment) {
            CageListFragment cageListFragment = (CageListFragment) fragment;
            transaction.replace(container, cageListFragment, GameState.CAGE_LIST_FRAGMENT);
            transaction.addToBackStack(GameState.CAGE_LIST_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof DetailsAnimalFragment) {
            DetailsAnimalFragment detailsAnimalFragment = (DetailsAnimalFragment) fragment;
            transaction.replace(container, detailsAnimalFragment, GameState
                    .DETAILS_ANIMAL_FRAGMENT);
            transaction.addToBackStack(GameState.DETAILS_ANIMAL_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof DetailsJanitorFragment) {
            DetailsJanitorFragment detailsJanitorFragment = (DetailsJanitorFragment) fragment;
            transaction.replace(container, detailsJanitorFragment, GameState
                    .DETAILS_JANITOR_FRAGMENT);
            transaction.addToBackStack(GameState.DETAILS_JANITOR_FRAGMENT);
            transaction.commit();
        } else if (fragment instanceof DetailsVeterinaryFragment) {
            DetailsVeterinaryFragment detailsVeterinaryFragment = (DetailsVeterinaryFragment)
                    fragment;
            transaction.replace(container, detailsVeterinaryFragment, GameState
                    .DETAILS_VETERINARY_FRAGMENT);
            transaction.addToBackStack(GameState.DETAILS_VETERINARY_FRAGMENT);
            transaction.commit();
        }

    }


}

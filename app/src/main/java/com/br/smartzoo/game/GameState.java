package com.br.smartzoo.game;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by adenilson on 16/06/16.
 */

public class GameState {

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
    public static final String DETAILS_FEEDER_FRAGMENT = "DETAILS_FEEDER_FRAGMENT";

    public static Fragment previousState(AppCompatActivity activity) {
        Fragment fragment = null;

        int backStackEntryCount = activity.getSupportFragmentManager()
                .getBackStackEntryCount();

        if (backStackEntryCount != 1) {
            FragmentManager.BackStackEntry backEntry = activity.getSupportFragmentManager()
                    .getBackStackEntryAt(backStackEntryCount - 1);
            String str = backEntry.getName();
            fragment = activity.getSupportFragmentManager().findFragmentByTag(str);

        }
        return fragment;
    }

}

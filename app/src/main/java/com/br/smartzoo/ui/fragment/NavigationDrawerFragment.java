package com.br.smartzoo.ui.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.br.smartzoo.R;
import com.br.smartzoo.model.interfaces.OnDrawerOptionClick;

/**
 * Created by adenilson on 05/05/16.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String PREF_FILE_NAME = "testpref";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mContainerView;
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private OnDrawerOptionClick mOnDrawerOptionClick;
    private RelativeLayout mRelativeSelected;

    public NavigationDrawerFragment() {}

        @Override
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            mUserLearnedDrawer = Boolean.valueOf(readToPreferences(getContext(), KEY_USER_LEARNED_DRAWER, "false"));

            if (savedInstanceState != null) {
                mFromSavedInstanceState = true;
            }
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState){

            View view = inflater.inflate(R.layout.navigation_drawer, container, false);

            bindOptionAnimals(view);
            bindOptionNews(view);
            bindOptionCages(view);
            bindOptionEmployees(view);
            bindOptionSettings(view);


            return view;
        }

    private void bindOptionSettings(View view) {
        final RelativeLayout relativeOptionSettings =
                (RelativeLayout) view.findViewById(R.id.relative_option_settings);
        relativeOptionSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRelativeSelected.setBackgroundColor(getResources().getColor(R.color.white));
                mRelativeSelected = relativeOptionSettings;
                relativeOptionSettings.setBackgroundColor(getResources().getColor(R.color.yellow_300));
                mOnDrawerOptionClick.onSettingsClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindOptionEmployees(View view) {
        final RelativeLayout relativeOptionEmployees =
                (RelativeLayout) view.findViewById(R.id.relative_option_employee);
        relativeOptionEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRelativeSelected.setBackgroundColor(getResources().getColor(R.color.white));
                mRelativeSelected = relativeOptionEmployees;
                relativeOptionEmployees.setBackgroundColor(getResources().getColor(R.color.yellow_300));
                mOnDrawerOptionClick.onEmployeeClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindOptionCages(View view) {
        final RelativeLayout relativeOptionCages =
                (RelativeLayout) view.findViewById(R.id.relative_option_cages);
        relativeOptionCages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRelativeSelected.setBackgroundColor(getResources().getColor(R.color.white));
                mRelativeSelected = relativeOptionCages;
                relativeOptionCages.setBackgroundColor(getResources().getColor(R.color.yellow_300));
                mOnDrawerOptionClick.onCagesClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindOptionNews(View view) {
        final RelativeLayout relativeOptionNews =
                (RelativeLayout) view.findViewById(R.id.relative_option_news);
        relativeOptionNews.setBackgroundColor(getResources().getColor(R.color.yellow_300));
        mRelativeSelected = relativeOptionNews;
        relativeOptionNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRelativeSelected.setBackgroundColor(getResources().getColor(R.color.white));
                mRelativeSelected = relativeOptionNews;
                relativeOptionNews.setBackgroundColor(getResources().getColor(R.color.yellow_300));
                mOnDrawerOptionClick.onNewsClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void bindOptionAnimals(View view) {
        final RelativeLayout relativeOptionAnimals =
                (RelativeLayout) view.findViewById(R.id.relative_option_animals);

        relativeOptionAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRelativeSelected.setBackgroundColor(getResources().getColor(R.color.white));
                mRelativeSelected = relativeOptionAnimals;
                relativeOptionAnimals.setBackgroundColor(getResources().getColor(R.color.yellow_300));
                mOnDrawerOptionClick.onAnimalsClick();
                mDrawerLayout.closeDrawers();
            }
        });
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                toolbar.setAlpha(1 - slideOffset);

            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mContainerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, preferenceValue);
    }

    public void attachView(OnDrawerOptionClick onDrawerOptionClick){
        this.mOnDrawerOptionClick = onDrawerOptionClick;
    }
}

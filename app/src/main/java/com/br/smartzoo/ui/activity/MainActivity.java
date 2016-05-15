package com.br.smartzoo.ui.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Chronometer;

import com.br.smartzoo.R;
import com.br.smartzoo.model.interfaces.OnDrawerOptionClick;
import com.br.smartzoo.presenter.MainActivityPresenter;
import com.br.smartzoo.ui.fragment.BuyAnimalFragment;
import com.br.smartzoo.ui.fragment.BuyFoodFragment;
import com.br.smartzoo.ui.fragment.NavigationDrawerFragment;
import com.br.smartzoo.ui.view.MainActivityView;

/**
 * Created by adenilson on 05/05/16.
 */
public class MainActivity extends AppCompatActivity implements OnDrawerOptionClick
        , MainActivityView {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private int mFrameContainer;
    private MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_time_line);

        bindmPresenter();
        bindToolbar();
        bindDrawerLayout();
        bindNavigationDrawer();
        bindContainerFragment();
    }
    
    private void bindmPresenter() {
        mPresenter = new MainActivityPresenter(this);
        mPresenter.attachView(this);
    }

    private void bindContainerFragment() {
        mFrameContainer = R.id.frame_container;
    }

    private void bindNavigationDrawer() {
        NavigationDrawerFragment navigationDrawer = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.left_drawer);
        navigationDrawer.setUp(R.id.left_drawer, mDrawerLayout, mToolbar);

        //necessary to callback of click
        navigationDrawer.attachView(this);
    }

    private void bindToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

    }

    private void bindDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    }

    @Override
    public void onNewsClick() {


    }

    @Override
    public void onAnimalsClick() {
    }

    @Override
    public void onEmployeeClick() {

    }

    @Override
    public void onCagesClick() {

    }

    @Override
    public void onSettingsClick() {

    }

    @Override
    public void onBuyFruitClick() {
        mPresenter.startTransaction(mFrameContainer, new BuyFoodFragment());
    }

    @Override
    public void onContractEmployeeClick() {

    }

    @Override
    public void onBuyAnimalClick() {
        mPresenter.startTransaction(mFrameContainer, new BuyAnimalFragment());
    }

    @Override
    public void onBuildCageClick() {

    }

    @Override
    public void changeFragment(Fragment fragment) {

    }
}

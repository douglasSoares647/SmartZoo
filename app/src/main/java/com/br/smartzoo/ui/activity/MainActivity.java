package com.br.smartzoo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.interfaces.OnClockTickListener;
import com.br.smartzoo.model.interfaces.OnDrawerOptionClick;
import com.br.smartzoo.model.service.ClockService;
import com.br.smartzoo.presenter.MainActivityPresenter;
import com.br.smartzoo.ui.fragment.BuyAnimalFragment;
import com.br.smartzoo.ui.fragment.BuyFoodFragment;
import com.br.smartzoo.ui.fragment.NavigationDrawerFragment;
import com.br.smartzoo.ui.view.MainActivityView;
import com.br.smartzoo.util.ServiceHelper;

/**
 * Created by adenilson on 05/05/16.
 */
public class MainActivity extends AppCompatActivity implements OnDrawerOptionClick
        , OnClockTickListener, MainActivityView {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private int mFrameContainer;
    private MainActivityPresenter mPresenter;
    private TextView mTextViewClock;
    private TextView mTextViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_time_line);

      //  loadZooInfo();
        bindmPresenter();
        bindToolbar();
        bindDrawerLayout();
        bindNavigationDrawer();
        bindContainerFragment();


        startClockService();


    }

    private void startClockService() {
        ClockService.context = this;
        if(!ServiceHelper.isMyServiceRunning(ClockService.class,this)) {
            startService(new Intent(this, ClockService.class));
        }
    }

    private void loadZooInfo() {
        ZooInfoBusiness.getFromPreferences();
        ZooInfoBusiness.load();
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

        mTextViewClock = (TextView) mToolbar.findViewById(R.id.text_view_clock);
        mTextViewDate = (TextView) mToolbar.findViewById(R.id.text_view_date);
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
    public void onStockClick() {

    }

    @Override
    public void changeFragment(Fragment fragment) {

    }

    @Override
    public void onTick(String date, String time) {
        mTextViewDate.setText(date);
        mTextViewClock.setText(time);
    }


}

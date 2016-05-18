package com.br.smartzoo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.br.smartzoo.util.TimeUtil;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.sql.Time;

/**
 * Created by adenilson on 05/05/16.
 */
public class MainActivity extends AppCompatActivity implements OnDrawerOptionClick, OnClockTickListener
        , MainActivityView {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private int mFrameContainer;
    private MainActivityPresenter mPresenter;
    private TextView textViewclock;
    private TextView textViewdate;
    private ImageView backward;
    private ImageView start;
    private ImageView foward;
    TextView textViewSpeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_time_line);

        //loadZooInfo();
        bindmPresenter();
        bindToolbar();
        bindFooterBar();
        bindDrawerLayout();
        bindNavigationDrawer();
        bindContainerFragment();


        setContextToService();


    }

    private void setContextToService() {
        ClockService.context = this;
    }

    private void bindFooterBar() {
        RelativeLayout footerBar = (RelativeLayout) findViewById(R.id.footer_bar);

        backward = (ImageView) findViewById(R.id.image_view_backward);
        start = (ImageView) findViewById(R.id.image_view_start);
        foward = (ImageView) findViewById(R.id.image_view_foward);
        textViewSpeed = (TextView) findViewById(R.id.text_view_speed);
        textViewSpeed.setText(getString(R.string.speed) + TimeUtil.speedFactor);

        Glide.with(this).load(R.drawable.ic_backward_button).asBitmap().into(backward);
        Glide.with(this).load(R.drawable.ic_foward_button).asBitmap().into(foward);

        if(ServiceHelper.isMyServiceRunning(ClockService.class,this))
            Glide.with(this).load(R.drawable.ic_pause_button).asBitmap().into(start);
        else
            Glide.with(this).load(R.drawable.ic_start_button).asBitmap().into(start);




        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ServiceHelper.isMyServiceRunning(ClockService.class,MainActivity.this)){
                    stopService(new Intent(MainActivity.this, ClockService.class));
                    Glide.with(MainActivity.this).load(R.drawable.ic_start_button).asBitmap().into(start);
                }
                else{
                    Glide.with(MainActivity.this).load(R.drawable.ic_pause_button).asBitmap().into(start);
                    startClockService();
                }
            }
        });


        foward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TimeUtil.speedFactor<3)
                TimeUtil.speedFactor++;
                textViewSpeed.setText(getString(R.string.speed) + TimeUtil.speedFactor);
            }
        });


        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TimeUtil.speedFactor>1){
                    TimeUtil.speedFactor--;
                    textViewSpeed.setText(getString(R.string.speed) + TimeUtil.speedFactor);
                }
            }
        });





    }

    private void startClockService() {
        ClockService.context = this;
        startService(new Intent(this, ClockService.class));
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


        textViewclock = (TextView) mToolbar.findViewById(R.id.text_view_clock);
        textViewdate = (TextView) mToolbar.findViewById(R.id.text_view_date);


        TimeUtil.getFromPreferences();

        textViewclock.setText(TimeUtil.getTimeString());
        textViewdate.setText(TimeUtil.getDateString());

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

    @Override
    public void onTick(String date, String time) {
        textViewdate.setText(date);
        textViewclock.setText(time);
    }


}

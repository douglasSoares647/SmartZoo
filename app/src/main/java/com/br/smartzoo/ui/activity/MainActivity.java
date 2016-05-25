package com.br.smartzoo.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
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
import com.br.smartzoo.ui.fragment.BuyCageFragment;
import com.br.smartzoo.ui.fragment.BuyFoodFragment;
import com.br.smartzoo.ui.fragment.HireEmployeeFragment;
import com.br.smartzoo.ui.fragment.ManageStockFragment;
import com.br.smartzoo.ui.fragment.NavigationDrawerFragment;
import com.br.smartzoo.ui.view.MainActivityView;
import com.br.smartzoo.util.AnimUtil;
import com.br.smartzoo.model.environment.Clock;
import com.br.smartzoo.util.ServiceHelper;
import com.bumptech.glide.Glide;

/**
 * Created by adenilson on 05/05/16.
 */
public class MainActivity extends AppCompatActivity implements OnDrawerOptionClick
        , OnClockTickListener, MainActivityView {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private int mIdFrameContainer;
    private MainActivityPresenter mPresenter;
    private TextView mTextViewClock;
    private TextView mTextViewDate;
    private ImageView mNormal;
    private ImageView mStart;
    private ImageView mForward;
    private TextView mTextViewSpeed;
    private FrameLayout mFrameContainer;
    private ImageView mFastForward;
    private boolean mCollapseControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loadZooInfo();
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
        final RelativeLayout footerBar = (RelativeLayout) findViewById(R.id.footer_bar);
        final ImageView imageViewPanel = (ImageView) findViewById(R.id.image_view_panel_time);
        AnimUtil.collapseWithoutAnim(footerBar, imageViewPanel);
        mCollapseControl = true;

        if (imageViewPanel != null) {
            imageViewPanel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mCollapseControl) {
                        AnimUtil.collapseControlTime(footerBar, imageViewPanel);
                        mCollapseControl = true;
                    } else {
                        AnimUtil.openControlTime(footerBar, imageViewPanel);
                        mCollapseControl = false;
                    }
                }
            });
        }

        mNormal = (ImageView) findViewById(R.id.image_view_normal);
        mNormal.setColorFilter(Color.parseColor("#757575"));
        mStart = (ImageView) findViewById(R.id.image_view_start);
        mForward = (ImageView) findViewById(R.id.image_view_forward);
        mFastForward = (ImageView) findViewById(R.id.image_view_fast_forward);

        Glide.with(this).load(R.drawable.ic_play_normal_button).asBitmap().into(mNormal);
        Glide.with(this).load(R.drawable.ic_forward_button).asBitmap().into(mForward);

        if (ServiceHelper.isMyServiceRunning(ClockService.class, this))
            Glide.with(this).load(R.drawable.ic_pause_button_orange_th).asBitmap().into(mStart);
        else
            Glide.with(this).load(R.drawable.ic_button_play).asBitmap().into(mStart);


        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ServiceHelper.isMyServiceRunning(ClockService.class, MainActivity.this)) {
                    Glide.with(MainActivity.this).load(R.drawable.ic_button_play).into(mStart);
                    stopService(new Intent(MainActivity.this, ClockService.class));
                } else {
                    Glide.with(MainActivity.this).load(R.drawable.ic_pause_button_orange_th)
                            .into(mStart);
                    startClockService();
                }
            }
        });


        mForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Clock.speedFactor <= 3)
                    Clock.speedFactor = 2;
                mNormal.clearColorFilter();
                mForward.setColorFilter(Color.parseColor("#757575"));
                mFastForward.clearColorFilter();
                //         mTextViewSpeed.setText(getString(R.string.speed) + Clock.speedFactor);
            }
        });

        mFastForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Clock.speedFactor <= 3)
                    Clock.speedFactor = 3;
                mNormal.clearColorFilter();
                mFastForward.setColorFilter(Color.parseColor("#757575"));
                mForward.clearColorFilter();
                //         mTextViewSpeed.setText(getString(R.string.speed) + Clock.speedFactor);
            }
        });


        mNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Clock.speedFactor > 1) {
                    Clock.speedFactor = 1;
                    mNormal.setColorFilter(Color.parseColor("#757575"));
                    mForward.clearColorFilter();
                    mFastForward.clearColorFilter();
                    //            mTextViewSpeed.setText(getString(R.string.speed) + Clock.speedFactor);
                }
            }
        });


    }


    private void startClockService() {
        ClockService.context = this;
        startService(new Intent(this, ClockService.class));
    }

    private void loadZooInfo() {
        //  ZooInfoBusiness.getFromPreferences();
        ZooInfoBusiness.load();
    }

    private void bindmPresenter() {
        mPresenter = new MainActivityPresenter(this);
        mPresenter.attachView(this);
    }

    private void bindContainerFragment() {
        mFrameContainer = (FrameLayout) findViewById(R.id.frame_container);
        mIdFrameContainer = R.id.frame_container;
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


        Clock.getFromPreferences();

        mTextViewClock.setText(Clock.getTimeString());
        mTextViewDate.setText(Clock.getDateString());

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
        mPresenter.startTransaction(mIdFrameContainer, new BuyFoodFragment());
    }

    @Override
    public void onHireEmployeeClick() {
        mPresenter.startTransaction(mIdFrameContainer, new HireEmployeeFragment());

    }

    @Override
    public void onBuyAnimalClick() {
        mPresenter.startTransaction(mIdFrameContainer, new BuyAnimalFragment());
    }

    @Override
    public void onBuildCageClick() {
        mPresenter.startTransaction(mIdFrameContainer, new BuyCageFragment());
    }

    @Override
    public void onStockClick() {
        mPresenter.startTransaction(mIdFrameContainer, new ManageStockFragment());
    }

    @Override
    public void onTick(String date, String time) {
        mTextViewClock.setText(time);
        mTextViewDate.setText(date);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(mFrameContainer, message, Snackbar.LENGTH_SHORT);
    }
}

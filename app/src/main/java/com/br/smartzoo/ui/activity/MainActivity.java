package com.br.smartzoo.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.br.smartzoo.R;
import com.br.smartzoo.model.interfaces.OnDrawerOptionClick;
import com.br.smartzoo.ui.fragment.NavigationDrawerFragment;

/**
 * Created by adenilson on 05/05/16.
 */
public class MainActivity extends AppCompatActivity implements OnDrawerOptionClick {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_time_line);

        bindToolbar();
        bindDrawerLayout();
        bindNavigationDrawer();
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

    }

    @Override
    public void onContractEmployeeClick() {

    }

    @Override
    public void onBuyAnimalClick() {

    }

    @Override
    public void onBuildCageClick() {

    }
}

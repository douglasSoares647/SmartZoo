package com.br.smartzoo.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.util.ServiceHelper;
import com.bumptech.glide.Glide;


/**
 * Created by adenilson on 05/05/16.
 */
public class SplashScreenActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayerSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);


        bindMediaPlayerSound();
        bindRelativeButtons();
        bindImageViewLogo();
        bindButtonNewGame();
        bindButtonLoadGame();


        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void bindMediaPlayerSound() {
        mMediaPlayerSound = MediaPlayer.create(this, R.raw.sound_splash_button);
    }

    private void bindButtonLoadGame() {

        Button buttonLoadGame = (Button) findViewById(R.id.button_load_game);

        if (buttonLoadGame != null) {
            buttonLoadGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMediaPlayerSound.start();
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                }
            });
        }
    }

    private void bindRelativeButtons() {
        final RelativeLayout relativeLayoutButtons = (RelativeLayout) findViewById(R.id.relative_buttons);
        if (relativeLayoutButtons != null) {

            relativeLayoutButtons.setY(500);
            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    relativeLayoutButtons.setVisibility(View.VISIBLE);
                    relativeLayoutButtons.animate().translationY(-60).setDuration(800);
                }
            }, 2000);
        }

    }

    private void bindButtonNewGame() {
        Button buttonNewGame = (Button) findViewById(R.id.button_new_game);
        if (buttonNewGame != null) {
            buttonNewGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMediaPlayerSound.start();
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                }
            });
        }
    }

    private void bindImageViewLogo() {
        ImageView imageViewLogo = (ImageView) findViewById(R.id.image_view_logo);

        if (imageViewLogo != null) {
            Glide.with(this).load(R.drawable.logo_splash).into(imageViewLogo);
        }
    }




}

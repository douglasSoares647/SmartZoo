package com.br.smartzoo.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by adenilson on 22/05/16.
 */
public class AnimUtil {

    public static void openControlTime(RelativeLayout footerBar, ImageView imageViewPanel
           ) {
        footerBar.animate().translationY(0).alpha(1f).setDuration(500);
        imageViewPanel.animate().rotation(180).alpha(0.3f).translationY(-80).setDuration(500);

    }

    public static void collapseControlTime(RelativeLayout footerBar, ImageView imageViewPanel
           ) {
        footerBar.animate().translationY(180).alpha(0).setDuration(500);
        imageViewPanel.animate().rotation(0).alpha(1f).translationY(0).setDuration(500);

    }

    public static void collapseWithoutAnim(RelativeLayout footerBar, ImageView imageViewPanel
    ) {
        footerBar.animate().translationY(180).setDuration(0);
    }

}

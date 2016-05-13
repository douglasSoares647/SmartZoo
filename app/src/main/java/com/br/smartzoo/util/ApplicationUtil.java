package com.br.smartzoo.util;

import android.content.Context;

/**
 * Created by dhb_s on 5/7/2016.
 */
public class ApplicationUtil {
    public static Context applicationContext;

    public ApplicationUtil(){
        super();
    }

    public static Context getContext(){
        return applicationContext;
    }


    public static void setApplicationContext(Context context){
        applicationContext = context;
    }


}

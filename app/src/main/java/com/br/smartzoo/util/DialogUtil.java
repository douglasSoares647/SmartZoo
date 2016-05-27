package com.br.smartzoo.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

import com.br.smartzoo.R;

/**
 * Created by adenilson on 26/05/16.
 */
public class DialogUtil{

    public static Dialog makeDialogSalary(Activity context){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_salary_change);
   //     dialog.


        return dialog;
    }
}

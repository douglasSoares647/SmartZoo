package com.br.smartzoo.util;

import android.app.Activity;
import android.app.ProgressDialog;

import com.br.smartzoo.R;

/**
 * Created by adenilson on 29/05/16.
 */
public class ProgressDialogUtil {


    public static ProgressDialog makeProgressDialog(Activity context, String title, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.setMessage(message);
        progressDialog.setTitle(title);
        return progressDialog;

    }

}

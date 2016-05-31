package com.br.smartzoo.util;

import android.app.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.br.smartzoo.R;

/**
 * Created by adenilson on 26/05/16.
 */
public class AlertDialogUtil {

    public static AlertDialog.Builder makeAlertDialogExit(final Activity context, String title
            , String message, DialogInterface.OnClickListener listener){
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(context.getString(R.string.btn_yes), listener);
        alert.setNegativeButton(context.getString(R.string.btn_no), null);

        return alert;
    }


    public static AlertDialog.Builder makeConfirmationDialog(final Activity context, String title, String message, DialogInterface.OnClickListener listener){
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(context.getString(R.string.btn_yes),listener);
        alert.setNegativeButton(context.getString(R.string.btn_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return alert;
    }
}

package com.br.smartzoo.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

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
        alert.setPositiveButton("YES", listener);
        alert.setNegativeButton("NO", null);

        return alert;
    }
}

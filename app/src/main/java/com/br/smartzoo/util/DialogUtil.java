package com.br.smartzoo.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.interfaces.OnManageEmployee;

/**
 * Created by adenilson on 26/05/16.
 */
public class DialogUtil{

    public static OnManageEmployee mOnManageVeterinary;


    public static Dialog makeDialogSalary(Activity context, OnManageEmployee dialogSalary
    , final Employee employee){
        mOnManageVeterinary = dialogSalary;
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_salary_change);
        final SeekBar seekBarSalary = (SeekBar) dialog.findViewById(R.id.seek_bar_salary);
        seekBarSalary.setMax(10000);
        final TextView textViewSalary = (TextView) dialog.findViewById(R.id.text_view_salary);
        Button buttonOK = (Button) dialog.findViewById(R.id.button_ok);
        Button buttonCancel = (Button) dialog.findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnManageVeterinary.onSalaryChange(employee, (double) seekBarSalary.getProgress());
                dialog.dismiss();
            }
        });

        seekBarSalary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < 880){
                    progress = 880;
                }
                textViewSalary.setText(progress + ",00 $");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return dialog;
    }
}

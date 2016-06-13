package com.br.smartzoo.util;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.interfaces.OnJanitorsRestedSelected;
import com.br.smartzoo.model.interfaces.OnManageCage;
import com.br.smartzoo.model.interfaces.OnManageEmployee;
import com.br.smartzoo.model.interfaces.OnSetAnimalNameListener;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.JanitorsRestedAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;

import java.util.List;

/**
 * Created by adenilson on 26/05/16.
 */
public class DialogUtil{

    private static final int VERTICAL_ITEM_SPACE = 30;
    public static OnManageEmployee mOnManageVeterinary;
    public static OnManageCage mOnManageCage;


    public static Dialog makeDialogSalary(Activity context, OnManageEmployee dialogSalary
    , final Employee employee){
        mOnManageVeterinary = dialogSalary;
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_salary_change);
        final SeekBar seekBarSalary = (SeekBar) dialog.findViewById(R.id.seek_bar_salary);
        seekBarSalary.setMax(10000);
        seekBarSalary.setProgress(employee.getSalary().intValue());
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



    public static Dialog makeDialogSetAnimalName(Activity context, final OnSetAnimalNameListener onSetAnimalNameListener, final Animal animal){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_animal_name);

        TextView textViewTitle = (TextView) dialog.findViewById(R.id.text_view_set_animal_name);
        final EditText editTextAnimalName = (EditText) dialog.findViewById(R.id.edit_text_set_animal_name);
        Button buttonCancel = (Button) dialog.findViewById(R.id.button_cancel_animal_name);
        Button buttonContinue = (Button) dialog.findViewById(R.id.button_continue);


        textViewTitle.setText(context.getString(R.string.title_set_name_to_animal)+ animal.getSpecie());


        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animal.setName(editTextAnimalName.getText().toString());
                onSetAnimalNameListener.afterSetAnimalName(animal);
                dialog.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    public static Dialog makeDialogRestedJanitors(Activity context, List<Janitor> janitors,
                                                OnJanitorsRestedSelected onJanitorsRestedSelected,
                                                Cage cage) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_janitors_rested);

        RecyclerView recyclerView
                = (RecyclerView) dialog.findViewById(R.id.recycler_view_janitors_rested);
        JanitorsRestedAdapter janitorsRestedAdapter = new JanitorsRestedAdapter(context, janitors,cage);
        janitorsRestedAdapter.addOnJanitorRestedSelected(onJanitorsRestedSelected);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(context, R.drawable.divider_recycler_view));
        recyclerView.setItemViewCacheSize(janitors.size());

        return dialog;
    }
}

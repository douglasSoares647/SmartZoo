package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.presenter.DetailsFeederPresenter;
import com.br.smartzoo.ui.view.DetailsFeederView;
import com.br.smartzoo.util.DateUtil;
import com.br.smartzoo.util.DialogUtil;

import java.text.DecimalFormat;

/**
 * Created by adenilson on 19/06/16.
 */

public class DetailsFeederFragment extends Fragment implements DetailsFeederView {
    public static final String SELECTED_FEEDER = "SELECTED_FEEDER";
    private DetailsFeederPresenter mPresenter;
    private Feeder mSelectedFeeder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_feeder, container, false);

        bindPresenter();
        initSelectFeeder();
        bindTextViewNameFeeder(view);
        bindTextViewInitFeeder(view);
        bindTextViewSalaryFeeder(view);
        bindTextViewStatusFeeder(view);
        bindProgressBarStaminaFeeder(view);
        bindButtonFeed(view);

        return view;
    }

    private void bindButtonFeed(View view) {
        Button buttonFeed = (Button) view.findViewById(R.id.button_feed);
        buttonFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.makeDialogShowCage(getActivity(), DetailsFeederFragment.this);
            }
        });
    }

    private void bindProgressBarStaminaFeeder(View view) {
        ProgressBar progressBarStamina = (ProgressBar) view.findViewById(R.id
                .progress_bar_stamina_feeder);

    }

    private void bindTextViewStatusFeeder(View view) {
        TextView textViewStatus = (TextView) view.findViewById(R.id.text_view_status_feeder);
        textViewStatus.setText(mSelectedFeeder.getStatus() != null ? mSelectedFeeder.getStatus()
                : "Unknown");
    }

    private void bindTextViewSalaryFeeder(View view) {
        TextView textViewSalary = (TextView) view.findViewById(R.id.text_view_salary_feeder);
        DecimalFormat decimalFormat = new DecimalFormat("0,00");

        textViewSalary.setText(mSelectedFeeder.getSalary() != null ? decimalFormat.format
                (mSelectedFeeder.getSalary()) : "Unknown");
    }

    private void bindTextViewInitFeeder(View view) {
        TextView textViewInit = (TextView) view.findViewById(R.id.text_view_init_feeder);
        textViewInit.setText(mSelectedFeeder.getStartDate() != null ? DateUtil.dateToString
                (mSelectedFeeder.getStartDate()) : "Unknown");
    }

    private void bindTextViewNameFeeder(View view) {
        TextView textViewName = (TextView) view.findViewById(R.id.text_view_name_feeder);
        textViewName.setText(mSelectedFeeder.getName() != null ? mSelectedFeeder.getName() :
                "Unknown");
    }

    private void initSelectFeeder() {
        Bundle values = getArguments();
        Parcelable parcelable = values.getParcelable(SELECTED_FEEDER);

        mSelectedFeeder = parcelable != null ? (Feeder) parcelable : new Feeder();

    }

    private void bindPresenter() {
        mPresenter = new DetailsFeederPresenter(getActivity());
        mPresenter.attachView(this);
    }

}

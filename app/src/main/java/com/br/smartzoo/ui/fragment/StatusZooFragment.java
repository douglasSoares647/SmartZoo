package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.game.environment.ZooInfo;
import com.br.smartzoo.model.business.BusinessRules;
import com.br.smartzoo.presenter.StatusZooPresenter;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.view.StatusZooView;

/**
 * Created by adenilson on 29/05/16.
 */
public class StatusZooFragment extends Fragment implements StatusZooView {

    private StatusZooPresenter mPresenter;
    private SeekBar mSeekBar;
    private TextView mTextViewPrice;
    private TextView mTextIdealPrice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status_zoo, container, false);

        bindPresenter();
        bindTextViewPrice(view);
        bindSeekBarPrice(view);
        bindTextViewIdealPrice(view);
        bindToolbarName();

        return view;
    }

    private void bindToolbarName() {
        ((MainActivity)getActivity()).changeToolBarText(getString(R.string.option_settings));
    }

    private void bindTextViewIdealPrice(View view) {
        mTextIdealPrice = (TextView) view.findViewById(R.id.text_view_ideal_price);

        mTextIdealPrice.setText("Ideal price : " + String.format("%.2f",BusinessRules.calculateIdealPrice()));
    }

    private void bindTextViewPrice(View view) {
        mTextViewPrice = (TextView) view.findViewById(R.id.text_view_price_zoo);

        BusinessRules.calculateIdealPrice();
        String statusPrice = BusinessRules.calculatePriceIndicator(ZooInfo.price);
        setPriceIndicator(statusPrice);

        mTextViewPrice.setText(ZooInfo.price + ",00 $ - " + statusPrice);
    }

    private void bindSeekBarPrice(View view) {
        mSeekBar = (SeekBar) view.findViewById(R.id.seek_bar_price);
        mSeekBar.setMax(BusinessRules.calculateIdealPrice().intValue()*2);
        mSeekBar.setProgress(ZooInfo.price.intValue());
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                ZooInfo.price = ((Integer)progress).doubleValue();
                String statusPrice = BusinessRules.calculatePriceIndicator(ZooInfo.price);
                mTextViewPrice.setText(progress+ ",00 $ - " + statusPrice);
                setPriceIndicator(statusPrice);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setPriceIndicator(String statusPrice) {


        if(statusPrice.equals("Good")){
            mTextViewPrice.setTextColor(ContextCompat.getColor(getActivity(), R.color.green_500));
        }
        else{
            mTextViewPrice.setTextColor(ContextCompat.getColor(getActivity(),R.color.red_500));
        }


    }

    private void bindPresenter() {
        mPresenter = new StatusZooPresenter(getActivity());
        mPresenter.attachView(this);
    }
}

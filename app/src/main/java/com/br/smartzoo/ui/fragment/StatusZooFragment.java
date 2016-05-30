package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.presenter.StatusZooPresenter;
import com.br.smartzoo.ui.view.StatusZooView;

/**
 * Created by adenilson on 29/05/16.
 */
public class StatusZooFragment extends Fragment implements StatusZooView {

    private StatusZooPresenter mPresenter;
    private SeekBar mSeekBar;
    private TextView mTextViewPrice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status_zoo, container, false);

        bindPresenter();
        bindTextViewPrice(view);
        bindSeekBarPrice(view);

        return view;
    }

    private void bindTextViewPrice(View view) {
        mTextViewPrice = (TextView) view.findViewById(R.id.text_view_price_zoo);
    }

    private void bindSeekBarPrice(View view) {
        mSeekBar = (SeekBar) view.findViewById(R.id.seek_bar_price);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTextViewPrice.setText(progress+ ",00 $");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void bindPresenter() {
        mPresenter = new StatusZooPresenter(getActivity());
        mPresenter.attachView(this);
    }
}

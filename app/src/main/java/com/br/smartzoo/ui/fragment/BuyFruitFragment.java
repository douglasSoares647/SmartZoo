package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.smartzoo.R;

/**
 * Created by adenilson on 12/05/16.
 */
public class BuyFruitFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_buy_fruits, container, false);
        return inflate;
    }
}

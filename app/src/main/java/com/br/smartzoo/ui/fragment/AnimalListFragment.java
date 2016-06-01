package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.smartzoo.R;
import com.br.smartzoo.presenter.AnimalListPresenter;
import com.br.smartzoo.ui.view.AnimalListView;

/**
 * Created by adenilson on 01/06/16.
 */
public class AnimalListFragment extends Fragment implements AnimalListView {

    private AnimalListPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal_list, container, false);

        bindPresenter();


        return view;
    }

    private void bindPresenter() {
        mPresenter = new AnimalListPresenter(getActivity());
        mPresenter.attachView(this);
    }
}

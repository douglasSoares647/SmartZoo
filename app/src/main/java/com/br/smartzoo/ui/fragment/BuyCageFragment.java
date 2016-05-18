package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.presenter.BuyCagePresenter;
import com.br.smartzoo.ui.view.BuyCageView;

import java.util.List;

/**
 * Created by adenilson on 17/05/16.
 */
public class BuyCageFragment extends Fragment implements BuyCageView {
    private RecyclerView mRecyclerViewCages;
    private BuyCagePresenter mPresenter;
    private List<Cage> mCagesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_cage, container, false);

        bindPresenter();
        populateCagesList();
        bindRecyclerViewCages(view);

        return view;
    }

    private void populateCagesList() {
        mCagesList = mPresenter.createCages();
    }

    private void bindPresenter() {
        mPresenter = new BuyCagePresenter(getActivity());
        mPresenter.attachView(this);
    }

    private void bindRecyclerViewCages(View view) {
        mRecyclerViewCages = (RecyclerView) view.findViewById(R.id.recycler_view_buy_cage);
    }
}

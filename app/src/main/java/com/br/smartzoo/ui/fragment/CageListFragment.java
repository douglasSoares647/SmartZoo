package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.interfaces.OnManageCage;
import com.br.smartzoo.presenter.CageListPresenter;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.adapter.CageListAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.CageListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 07/06/16.
 */

public class CageListFragment extends Fragment implements CageListView, OnManageCage{
    private static final int VERTICAL_ITEM_SPACE = 30;
    private CageListPresenter mPresenter;
    private List<Cage> mCageList = new ArrayList<>();
    private CageListAdapter mAdapter;
    private RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cage_list, container, false);

        bindPresenter();
        bindRecyclerViewCage(view);
        bindToolbarName();
        loadCageList();

        return view;
    }

    private void bindToolbarName() {
        ((MainActivity)getActivity()).changeToolBarText(getString(R.string.option_cages));
    }

    private void loadCageList() {
        mPresenter.loadCageList();
    }

    private void bindRecyclerViewCage(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_cages);
        mAdapter = new CageListAdapter(getActivity()
                , mCageList);
        mAdapter.addOnManageCage(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        mRecyclerView.setItemViewCacheSize(mCageList.size());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void bindPresenter() {
        mPresenter = new CageListPresenter(getActivity());
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroyCage(Cage cage) {
        mPresenter.destroyCage(cage);
    }

    @Override
    public void onCleanCage(Cage cage) {
        mPresenter.cleanCage(cage);
    }

    @Override
    public void onLoadCageList(List<Cage> cages) {
        mAdapter.setCageList(cages);
    }

    @Override
    public void onCageDestroyed(Cage cage) {
        mAdapter.notifyDataSetChanged();
    }
}

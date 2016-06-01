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
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.interfaces.OnManageEmployee;
import com.br.smartzoo.presenter.FeederListPresenter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.FeederListAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.FeederListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 26/05/16.
 */
public class FeederListFragment extends Fragment implements FeederListView, OnManageEmployee{

    private static final int VERTICAL_ITEM_SPACE = 30;
    private RecyclerView mRecyclerViewFeeder;
    private FeederListPresenter mPresenter;
    private FeederListAdapter mAdapter;
    private List<Feeder> mFeederList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeder_list, container, false);

        bindPresenter();
        loadFeederList();
        bindRecyclerViewFeeder(view);



        return view;
    }

    private void bindRecyclerViewFeeder(View view) {
        mRecyclerViewFeeder = (RecyclerView) view.findViewById(R.id.recycler_view_feeders);
        mAdapter = new FeederListAdapter(getActivity()
                , mFeederList);
        mAdapter.addOnMaganeEmployee(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewFeeder.setLayoutManager(layoutManager);
        mRecyclerViewFeeder.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerViewFeeder.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        mRecyclerViewFeeder.setItemViewCacheSize(mFeederList.size());
        mRecyclerViewFeeder.setAdapter(mAdapter);
    }

    private void loadFeederList() {
        mPresenter.loadFeederList();
    }

    private void bindPresenter() {
        mPresenter = new FeederListPresenter(getActivity());
        mPresenter.attachView(this);
    }

    @Override
    public void onLoadFeederSuccess(List<Feeder> feeder) {
        mAdapter.setFeederList(feeder);
    }

    @Override
    public void onDemit(Employee feeder) {
        mPresenter.demitFeeder(feeder);
    }

    @Override
    public void onSalaryChange(Employee employee, Double value) {
        mPresenter.updateSalaryFeeder(employee, value);
    }
}

package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.br.smartzoo.R;
import com.br.smartzoo.game.environment.ZooInfo;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.interfaces.OnManageEmployee;
import com.br.smartzoo.presenter.FeederListPresenter;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.FeederListAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.FeederListView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 26/05/16.
 */
public class FeederListFragment extends Fragment implements FeederListView, OnManageEmployee {

    private static final int VERTICAL_ITEM_SPACE = 30;
    private RecyclerView mRecyclerViewFeeder;
    private FeederListPresenter mPresenter;
    private FeederListAdapter mAdapter;
    private List<Feeder> mFeederList = new ArrayList<>();
    private RelativeLayout mRelative;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeder_list, container, false);

        bindPresenter();
        bindRelativeEmpty(view);
        bindRecyclerViewFeeder(view);
        loadFeederList();
        bindToolbarName();


        return view;
    }


    private void bindRelativeEmpty(View view) {
        mRelative = (RelativeLayout) view.findViewById(R.id.relative_empty);
        ImageView imageViewEmpty = (ImageView) view.findViewById(R.id.image_view_empty);
        Glide.with(getActivity()).load(R.drawable.ic_empty).into(imageViewEmpty);
        mRelative.setVisibility(View.GONE);

    }

    private void bindToolbarName() {
        ((MainActivity) getActivity()).changeToolBarText(getString(R.string.title_feeders));
    }

    private void bindRecyclerViewFeeder(View view) {
        mRecyclerViewFeeder = (RecyclerView) view.findViewById(R.id.recycler_view_feeders);
        mAdapter = new FeederListAdapter(getActivity()
                , mFeederList);
        mAdapter.addOnManageEmployee(this);
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
        mRelative.setVisibility(View.GONE);
        mAdapter.setFeederList(feeder);
    }

    @Override
    public void onLoadFeederEmpty() {
        mRelative.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDemit(Employee feeder) {
        mPresenter.demitFeeder(feeder);
        ((FeederListAdapter) mRecyclerViewFeeder.getAdapter()).removeFeeder(feeder);
    }

    @Override
    public void onSalaryChange(Employee employee, Double value) {
        for (Employee zooInfoemployee : ZooInfo.employees) {
            if (zooInfoemployee.equals(employee)) {
                zooInfoemployee.setSalary(value);
            }
        }
        mPresenter.updateSalaryFeeder(employee, value);
    }

    @Override
    public void onClick(Employee employee) {
        mPresenter.startTransaction((Feeder) employee);
    }


}

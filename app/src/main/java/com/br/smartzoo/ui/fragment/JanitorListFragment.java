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
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.interfaces.OnManageEmployee;
import com.br.smartzoo.presenter.JanitorListPresenter;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.JanitorListAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.JanitorListView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 26/05/16.
 */
public class JanitorListFragment extends Fragment implements OnManageEmployee, JanitorListView {
    private static final int VERTICAL_ITEM_SPACE = 30;
    private JanitorListPresenter mPresenter;
    private RecyclerView mRecyclerViewJanitor;
    private JanitorListAdapter mAdapter;
    private List<Janitor> mJanitorList = new ArrayList<>();
    private RelativeLayout mRelative;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_janitor_list, container, false);

        bindPresenter();
        bindRelativeEmpty(view);
        bindRecyclerViewJanitor(view);
        loadJanitors();

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
        ((MainActivity)getActivity()).changeToolBarText(getString(R.string.title_janitors));
    }

    private void loadJanitors() {
        mPresenter.loadJanitors();
    }

    private void bindRecyclerViewJanitor(View view) {
        mRecyclerViewJanitor = (RecyclerView) view.findViewById(R.id.recycler_view_janitors);
        mAdapter = new JanitorListAdapter(getActivity()
                , mJanitorList);
        mAdapter.addOnManageEmployee(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewJanitor.setLayoutManager(layoutManager);
        mRecyclerViewJanitor.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerViewJanitor.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        mRecyclerViewJanitor.setItemViewCacheSize(mJanitorList.size());
        mRecyclerViewJanitor.setAdapter(mAdapter);
    }

    private void bindPresenter() {
        mPresenter = new JanitorListPresenter(getActivity());
        mPresenter.attachView(this);
    }

    @Override
    public void onDemit(Employee janitor) {
        mPresenter.demitJanitor(janitor);
        ((JanitorListAdapter) mRecyclerViewJanitor.getAdapter()).removeJanitor(janitor);
    }

    @Override
    public void onSalaryChange(Employee janitor, Double value) {
        for(Employee employee : ZooInfo.employees){
            if(employee.equals(janitor)){
                employee.setSalary(value);
            }
        }
        mPresenter.updateSalaryJanitor(janitor, value);

    }

    @Override
    public void onClick(Employee employee) {

        mPresenter.openJanitorDetails(employee);
    }

    @Override
    public void onLoadJanitorList(List<Janitor> janitorList) {
        mAdapter.setJanitorList(janitorList);
        mRecyclerViewJanitor.setItemViewCacheSize(janitorList.size());
        mRelative.setVisibility(View.GONE);
    }

    @Override
    public void onLoadJanitorListEmpty() {
        mRelative.setVisibility(View.VISIBLE);
    }
}

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
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.interfaces.OnManageFood;
import com.br.smartzoo.model.singleton.Stock;
import com.br.smartzoo.presenter.ManageStockPresenter;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.StockListAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.ManageStockView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 24/05/16.
 */
public class ManageStockFragment extends Fragment implements ManageStockView, OnManageFood {

    private static final int VERTICAL_ITEM_SPACE = 30;
    private ManageStockPresenter mPresenter;
    private RecyclerView mRecyclerViewFoods;
    private StockListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock, container, false);

        bindPresenter();
        bindRecyclerViewFoodList(view);
        populateFoodList();
        bindToolbarName();

        return view;
    }

    private void bindToolbarName() {
        ((MainActivity)getActivity()).changeToolBarText(getString(R.string.option_stock));
    }

    private void bindRecyclerViewFoodList(View view) {
        mRecyclerViewFoods = (RecyclerView) view.findViewById(R.id.recycler_view_foods_stock);
        mAdapter = new StockListAdapter(getActivity(), new ArrayList<Food>());
        mAdapter.addOnManageFood(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewFoods.setLayoutManager(layoutManager);
        mRecyclerViewFoods.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerViewFoods.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        mRecyclerViewFoods.setAdapter(mAdapter);

    }

    private void populateFoodList() {
        mPresenter.getAllFoods();
    }

    private void bindPresenter() {
        mPresenter = new ManageStockPresenter(getActivity());
        mPresenter.attachView(this);
    }

    @Override
    public void onFoodListLoaded() {
        if(mAdapter != null){
            List<Food> foods = Stock._INSTANCE.getFoods();
            mAdapter.setFoodList(foods);
            mRecyclerViewFoods.setItemViewCacheSize(Stock._INSTANCE.getFoods().size());
        }

    }

    @Override
    public void showSnackBar(String message) {
        ((MainActivity) getActivity()).showSnackBar(message);
    }

    @Override
    public void onSell(Food food) {
        mPresenter.sellFood(food);
        mAdapter.removeFood(food);

    }

}

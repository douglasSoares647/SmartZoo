package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.interfaces.OnChangeBuyListener;
import com.br.smartzoo.presenter.BuyFoodPresenter;
import com.br.smartzoo.ui.adapter.BuyFoodListAdapter;
import com.br.smartzoo.ui.view.BuyFoodView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by adenilson on 12/05/16.
 */
public class BuyFoodFragment extends Fragment implements BuyFoodView, OnChangeBuyListener {

    private List<Food> mFoods;
    private RecyclerView mRecyclerViewFoods;
    private BuyFoodPresenter mPresenter;
    private TextView mTextViewTotalFoods;
    private TextView mTextViewTotalPrice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_fruits, container, false);


        bindPresenter();
        populateFoodList();
        bindRecyclerViewFoods(view);
        bindPriceAndQuantity(view);
        bindButtonBuy(view);
        bindButtonInsufficient(view);

        return view;
    }

    private void bindPriceAndQuantity(View view) {
        mTextViewTotalFoods = (TextView) view.findViewById(R.id.text_view_total_fruits);
        String totalFruits = getString(R.string.total_fruits) + " 0";
        mTextViewTotalFoods.setText(totalFruits);

        mTextViewTotalPrice = (TextView) view.findViewById(R.id.text_view_price_fruits);
        String totalPrice = getString(R.string.total_price) + " 0,00 $";
        mTextViewTotalPrice.setText(totalPrice);
    }

    private void bindButtonInsufficient(View view) {
        Button buttonInsufficient =
                (Button) view.findViewById(R.id.button_insufficient_funds_fruits);

        buttonInsufficient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.message_insufficient_funds
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindButtonBuy(View view) {
        Button buttonBuy = (Button) view.findViewById(R.id.button_buy_fruits);
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void populateFoodList() {
        mFoods = mPresenter.createListFood();
    }

    private void bindPresenter() {
        mPresenter = new BuyFoodPresenter(getActivity());
        mPresenter.attachView(this);
    }


    private void bindRecyclerViewFoods(View view) {
        mRecyclerViewFoods = (RecyclerView) view.findViewById(R.id.recycler_view_available_fruits);
        BuyFoodListAdapter buyFoodListAdapter = new BuyFoodListAdapter(getActivity(), mFoods);
        buyFoodListAdapter.addCallBack(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewFoods.setLayoutManager(layoutManager);
        mRecyclerViewFoods.setItemViewCacheSize(mFoods.size());
        mRecyclerViewFoods.setAdapter(buyFoodListAdapter);


    }

    @Override
    public void onQuantityChange(Double totalPrice, int quantity) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format(totalPrice);
        String price = getString(R.string.total_price) + " " + format + "$";
        mTextViewTotalPrice.setText(price);

        String totalQuantity = getString(R.string.total_fruits) + " " + quantity;
        mTextViewTotalFoods.setText(totalQuantity);

    }
}

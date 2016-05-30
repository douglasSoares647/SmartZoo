package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.br.smartzoo.model.business.FoodBusiness;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.interfaces.OnChangeBuyListener;
import com.br.smartzoo.model.singleton.Stock;
import com.br.smartzoo.model.singleton.Supplier;
import com.br.smartzoo.presenter.BuyFoodPresenter;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.adapter.BuyFoodListAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.BuyFoodView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by adenilson on 12/05/16.
 */
public class BuyFoodFragment extends Fragment implements BuyFoodView, OnChangeBuyListener {

    private static final int VERTICAL_ITEM_SPACE = 30;

    private List<Food> mFoods;
    private RecyclerView mRecyclerViewFoods;
    private BuyFoodPresenter mPresenter;
    private TextView mTextViewTotalFoods;
    private TextView mTextViewTotalPrice;
    private int mTotalQuantity = 0;
    private Double mTotalPrice = 0.0;
    private Button buttonInsufficient;
    private Button buttonBuy;
    private HashMap<Food, Integer> totalFoods;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_foods, container, false);


        bindPresenter();
        populateFoodList();
        bindRecyclerViewFoods(view);
        bindPriceAndQuantity(view);
        bindButtonBuy(view);
        bindButtonInsufficient(view);

        return view;
    }

    private void bindPriceAndQuantity(View view) {
        mTextViewTotalFoods = (TextView) view.findViewById(R.id.text_view_total_foods);
        String totalfoods = getString(R.string.total_foods) + " 0";
        mTextViewTotalFoods.setText(totalfoods);

        mTextViewTotalPrice = (TextView) view.findViewById(R.id.text_view_price_foods);
        String totalPrice = getString(R.string.total_price) + " 0,00 $";
        mTextViewTotalPrice.setText(totalPrice);
    }

    private void bindButtonInsufficient(View view) {

        buttonInsufficient = (Button) view.findViewById(R.id.button_insufficient_funds_foods);

        if(ZooInfo.money < mTotalPrice){
            buttonInsufficient.setVisibility(View.VISIBLE);
        }
        buttonInsufficient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar(getActivity().getString(R.string.message_insufficient_funds));
            }
        });
    }

    private void bindButtonBuy(View view) {

        buttonBuy = (Button) view.findViewById(R.id.button_buy_foods);

        if(ZooInfo.money>mTotalPrice){
            buttonBuy.setVisibility(View.VISIBLE);
        }
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,List<Food>> foodMap = Supplier.buyFoods(totalFoods);
                Stock.getInstance().putFoods(foodMap);
                FoodBusiness.saveAll(foodMap);
                showSnackBar(getActivity().getString(R.string.msg_food_successfully_bought));

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
        mRecyclerViewFoods = (RecyclerView) view.findViewById(R.id.recycler_view_available_foods);
        BuyFoodListAdapter buyFoodListAdapter = new BuyFoodListAdapter(getActivity(), mFoods);
        buyFoodListAdapter.addCallBack(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewFoods.setLayoutManager(layoutManager);
        mRecyclerViewFoods.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerViewFoods.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        mRecyclerViewFoods.setItemViewCacheSize(mFoods.size());
        mRecyclerViewFoods.setAdapter(buyFoodListAdapter);


    }

    @Override
    public void onQuantityChange(HashMap<Food, Integer> foods) {

        calculateQuantityAndPrice(foods);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format(mTotalPrice);
        String price = getString(R.string.total_price) + " " + format + "$";
        mTextViewTotalPrice.setText(price);

        String totalQuantity = getString(R.string.total_foods) + " " + mTotalQuantity;
        mTextViewTotalFoods.setText(totalQuantity);


        if(mTotalPrice< ZooInfo.money){
            buttonInsufficient.setVisibility(View.GONE);
            buttonBuy.setVisibility(View.VISIBLE);
        }
        else{
            buttonInsufficient.setVisibility(View.VISIBLE);
            buttonBuy.setVisibility(View.GONE);
        }

        totalFoods = foods;

    }


    private void calculateQuantityAndPrice(HashMap<Food, Integer> foods) {

        mTotalQuantity = 0;
        mTotalPrice = 0D;

        Iterator it = foods.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Integer value = (Integer) pair.getValue();
            mTotalQuantity += value;
            Food food = (Food) pair.getKey();
            mTotalPrice += food.getPrice() * value;

        }


    }

    @Override
    public void showSnackBar(String message) {
        ((MainActivity) getActivity()).showSnackBar(message);
    }
}

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
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.interfaces.OnChangeBuyListener;
import com.br.smartzoo.presenter.BuyAnimalPresenter;
import com.br.smartzoo.ui.adapter.BuyAnimalListAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.BuyAnimalView;

import java.util.List;

/**
 * Created by adenilson on 12/05/16.
 */
public class BuyAnimalFragment extends Fragment implements BuyAnimalView{

    private static final int VERTICAL_ITEM_SPACE = 30;
    private BuyAnimalPresenter mPresenter;
    private List<Animal> mAnimalList;
    private RecyclerView mRecyclerViewAnimals;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buy_animal, container, false);

        bindPresenter();
        populateAnimalList();

        mRecyclerViewAnimals =
                (RecyclerView) view.findViewById(R.id.recycler_view_available_animals);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewAnimals.setLayoutManager(layoutManager);
        mRecyclerViewAnimals.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerViewAnimals.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        mRecyclerViewAnimals.setItemViewCacheSize(mAnimalList.size());
        BuyAnimalListAdapter buyAnimalListAdapter =
                new BuyAnimalListAdapter(getActivity(), mAnimalList);
        mRecyclerViewAnimals.setAdapter(buyAnimalListAdapter);



        return view;
    }

    private void populateAnimalList() {
        mAnimalList = mPresenter.createAnimalList();
    }

    private void bindPresenter() {
        mPresenter = new BuyAnimalPresenter(getActivity());
        mPresenter.attachView(this);
    }
}

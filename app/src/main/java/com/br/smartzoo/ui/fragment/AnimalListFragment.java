package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.interfaces.OnManageAnimal;
import com.br.smartzoo.presenter.AnimalListPresenter;
import com.br.smartzoo.ui.adapter.AnimalListAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.AnimalListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 01/06/16.
 */
public class AnimalListFragment extends Fragment implements AnimalListView, OnManageAnimal {

    public static final Long SELECT_NAME = 1L;
    public static final Long SELECT_STATUS = 2L;
    public static final Long SELECT_AGE = 3L;
    public static final Long SELECT_RESISTENCE = 4L;
    public static final Long SELECT_SATISFACTION = 5L;
    public static final Long SELECT_WEIGHT = 6L;
    public static final Long SELECT_SEX = 7L;
    public static final Long SELECT_POPULARITY = 8L;


    private static final int VERTICAL_SPACE = 30;

    private AnimalListPresenter mPresenter;
    private RecyclerView mRecyclerViewAnimals;
    private AnimalListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal_list, container, false);

        bindPresenter();
        bindRecyclerViewAnimal(view);
        bindSpinnerFilter(view);
        loadAnimalList();


        return view;
    }

    private void bindSpinnerFilter(View view) {
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_filter_animal);
        String[] strings = createSelectStrings();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity()
                , android.R.layout.simple_dropdown_item_1line, strings);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = (String)parent.getItemAtPosition(position);
                mPresenter.loadAnimalList(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private String[] createSelectStrings() {

        return new String[]{getActivity().getString(R.string.select_name),
                getActivity().getString(R.string.select_status),
                getActivity().getString(R.string.select_age),
                getActivity().getString(R.string.select_resistance),
                getActivity().getString(R.string.select_satisfaction),
                getActivity().getString(R.string.select_weight),
                getActivity().getString(R.string.select_sex),
                getActivity().getString(R.string.select_popularity)};
    }

    private void bindRecyclerViewAnimal(View view) {
        mRecyclerViewAnimals = (RecyclerView) view.findViewById(R.id.recycler_view_animals);
        mAdapter = new AnimalListAdapter(getActivity(), new ArrayList<Animal>());
        mAdapter.addOnManageAnimal(this);
        LinearLayoutManager llManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewAnimals.setLayoutManager(llManager);
        mRecyclerViewAnimals.addItemDecoration(new DividerItemDecoration(getContext()
                , R.drawable.divider_recycler_view));
        mRecyclerViewAnimals.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_SPACE));
        mRecyclerViewAnimals.setAdapter(mAdapter);
    }

    private void loadAnimalList() {
        mPresenter.loadAnimalList(SELECT_NAME);
    }

    private void bindPresenter() {
        mPresenter = new AnimalListPresenter(getActivity());
        mPresenter.attachView(this);
    }

    @Override
    public void onSell(Animal animal) {
        mPresenter.sellAnimal(animal);
    }

    @Override
    public void onAnimalListLoad(List<Animal> animalList) {
        mRecyclerViewAnimals.setItemViewCacheSize(animalList.size());
        mAdapter.setAnimalList(animalList);
    }
}

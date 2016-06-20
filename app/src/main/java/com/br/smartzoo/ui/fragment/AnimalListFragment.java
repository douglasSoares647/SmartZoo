package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.interfaces.OnManageAnimal;
import com.br.smartzoo.presenter.AnimalListPresenter;
import com.br.smartzoo.ui.activity.MainActivity;
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

    private static final int VERTICAL_SPACE = 30;

    private AnimalListPresenter mPresenter;
    private RecyclerView mRecyclerViewAnimals;
    private AnimalListAdapter mAdapter;
    private List<Animal> animals;
    private Toolbar toolbar;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal_list, container, false);

        bindPresenter();
        bindRecyclerViewAnimal(view);
        loadAnimalList();

        setHasOptionsMenu(true);


        bindToolbarName();

        return view;
    }

    private void bindToolbarName() {
        ((MainActivity)getActivity()).changeToolBarText(getString(R.string.option_animals));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_animal_list_fragment,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.menu_sort_name:
                mPresenter.sortAnimalList(getString(R.string.select_name),animals);
                break;
            case R.id.menu_sort_age:
                mPresenter.sortAnimalList(getString(R.string.select_age),animals);
                break;
            case R.id.menu_sort_popularity:
                mPresenter.sortAnimalList(getString(R.string.select_popularity),animals);
                break;
            case R.id.menu_sort_resistance:
                mPresenter.sortAnimalList(getString(R.string.select_resistance),animals);
                break;
            case R.id.menu_sort_satisfaction:
                mPresenter.sortAnimalList(getString(R.string.select_satisfaction),animals);
                break;
            case R.id.menu_sort_sex:
                mPresenter.sortAnimalList(getString(R.string.select_sex),animals);
                break;
            case R.id.menu_sort_status:
                mPresenter.sortAnimalList(getString(R.string.select_status),animals);
                break;
            case R.id.menu_sort_weight:
                mPresenter.sortAnimalList(getString(R.string.select_weight),animals);
                break;

        }
        return super.onOptionsItemSelected(item);
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
        mPresenter.loadAnimalList();
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
    public void onClick(Animal animal) {
        mPresenter.startTransaction(animal);
    }

    @Override
    public void onPut(Animal animal) {
        mPresenter.putAnimalInCage(animal);
    }

    @Override
    public void onAnimalListLoad(List<Animal> animalList) {
        mRecyclerViewAnimals.setItemViewCacheSize(animalList.size());
        mAdapter.setAnimalList(animalList);
        animals = animalList;
    }

    @Override
    public void updateList(Animal animal) {
        if(mRecyclerViewAnimals!=null){
            ((AnimalListAdapter) mRecyclerViewAnimals.getAdapter()).removeAnimal(animal);
        }
    }

    @Override
    public void onAnimalPutted(Animal animal) {
       mPresenter.loadAnimalList();
    }
}

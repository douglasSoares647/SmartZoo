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
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.interfaces.OnManageEmployee;
import com.br.smartzoo.presenter.VeterinaryListPresenter;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.adapter.VeterinaryListAdapter;
import com.br.smartzoo.ui.view.VeterinaryListView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 26/05/16.
 */
public class VeterinaryListFragment extends Fragment implements VeterinaryListView
        , OnManageEmployee {


    private static final int VERTICAL_ITEM_SPACE = 30;
    private VeterinaryListPresenter mPresenter;
    private RecyclerView mRecyclerViewVeterinariesList;
    private List<Veterinary> mVeterinariesList = new ArrayList<>();
    private VeterinaryListAdapter mAdapter;
    private RelativeLayout mRelative;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_veterinary_list, container, false);

        bindPresenter();
        bindRelativeEmpty(view);
        bindRecyclerView(view);
        loadVeterinaries();
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
        ((MainActivity)getActivity()).changeToolBarText(getString(R.string.title_veterinaries));
    }

    private void bindRecyclerView(View view) {
        mRecyclerViewVeterinariesList =
                (RecyclerView) view.findViewById(R.id.recycler_view_veterinaries);
        mAdapter = new VeterinaryListAdapter(getActivity()
                , mVeterinariesList);
        mAdapter.addOnManageVeterinary(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewVeterinariesList.setLayoutManager(layoutManager);
        mRecyclerViewVeterinariesList.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerViewVeterinariesList.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        mRecyclerViewVeterinariesList.setItemViewCacheSize(mVeterinariesList.size());
        mRecyclerViewVeterinariesList.setAdapter(mAdapter);
    }

    private void loadVeterinaries() {
        mPresenter.loadVeterinaries();
    }

    private void bindPresenter() {
        mPresenter = new VeterinaryListPresenter(getActivity());
        mPresenter.attachView(this);
    }

    @Override
    public void onLoadVeterinaryList(List<Veterinary> veterinaries) {
        mAdapter.setVeterinaryList(veterinaries);
        mRelative.setVisibility(View.GONE);
    }

    @Override
    public void onLoadVeterinaryListEmpty() {
        mRelative.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDemit(Employee veterinary) {
        mPresenter.demitVeterinary(veterinary);
        ((VeterinaryListAdapter) mRecyclerViewVeterinariesList.getAdapter()).removeVeterinary(veterinary);

    }

    @Override
    public void onSalaryChange(Employee veterinary, Double value) {
        for(Employee zooInfoemployee : ZooInfo.employees){
            if(zooInfoemployee.equals(veterinary)){
                zooInfoemployee.setSalary(value);
            }
        }
        mPresenter.updateSalary(veterinary, value);
    }

    @Override
    public void onClick(Employee employee) {

        mPresenter.openVeterinaryDetails(employee);

    }
}

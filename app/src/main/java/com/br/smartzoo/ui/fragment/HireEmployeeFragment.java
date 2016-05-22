package com.br.smartzoo.ui.fragment;

import android.animation.FloatArrayEvaluator;
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
import com.br.smartzoo.model.interfaces.OnHireListener;
import com.br.smartzoo.presenter.HireEmployeePresenter;
import com.br.smartzoo.ui.adapter.BuyFoodListAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.HireEmployeeListAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.HireEmployeeView;

import java.util.List;

/**
 * Created by adenilson on 22/05/16.
 */
public class HireEmployeeFragment extends Fragment  implements OnHireListener, HireEmployeeView{
    private static final int VERTICAL_ITEM_SPACE = 30;
    private HireEmployeePresenter mPresenter;
    private List<Employee> mEmployeeList;
    private RecyclerView mRecyclerViewEmployee;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hire_employee, container, false);

        bindPresenter();
        bindRecyclerView(view);

        return view;
    }

    private void bindRecyclerView(View view) {
        mRecyclerViewEmployee =
                (RecyclerView) view.findViewById(R.id.recycler_view_available_employees);
        HireEmployeeListAdapter hireEmployeeListAdapter = new HireEmployeeListAdapter(getActivity()
                , mEmployeeList);
        hireEmployeeListAdapter.addOnHireListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewEmployee.setLayoutManager(layoutManager);
        mRecyclerViewEmployee.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerViewEmployee.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        mRecyclerViewEmployee.setItemViewCacheSize(mEmployeeList.size());
        mRecyclerViewEmployee.setAdapter(hireEmployeeListAdapter);
    }

    private void bindPresenter() {
        mPresenter = new HireEmployeePresenter(getActivity());
        mPresenter.attachView(this);
        mEmployeeList = mPresenter.createEmployees();

    }


    @Override
    public void onHire() {

    }
}

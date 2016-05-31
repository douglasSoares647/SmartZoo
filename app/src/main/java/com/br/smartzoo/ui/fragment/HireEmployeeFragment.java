package com.br.smartzoo.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.BusinessRules;
import com.br.smartzoo.model.business.EmployeeBusiness;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.interfaces.OnHireListener;
import com.br.smartzoo.presenter.HireEmployeePresenter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.HireEmployeeListAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.HireEmployeeView;
import com.br.smartzoo.util.AlertDialogUtil;

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
    public void onHire(Employee employee) {
        Boolean haveMoney = BusinessRules.haveMoneyToBuyEmployee(employee);
        if(haveMoney) {
            showConfirmationDialog(employee);
        }
        else{
            Toast.makeText(getContext(),R.string.msg_dont_have_money,Toast.LENGTH_SHORT).show();
        }
    }


    private void showConfirmationDialog(final Employee employee) {
        AlertDialog.Builder dialog = AlertDialogUtil.makeConfirmationDialog(getActivity(), getContext().getString(R.string.title_confirm), getContext().getString(R.string.msg_hire_employee_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ZooInfo.employees.add(employee);
                        EmployeeBusiness.save(employee);
                        ZooInfoBusiness.takeMoney(employee.getPrice());
                        HireEmployeeListAdapter hireEmployeeListAdapter =
                                ((HireEmployeeListAdapter)mRecyclerViewEmployee.getAdapter());
                        hireEmployeeListAdapter.getEmployeeList().remove(employee);

                        hireEmployeeListAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), R.string.msg_employee_hired,Toast.LENGTH_SHORT).show();
                    }
                });

        dialog.show();

    }
}

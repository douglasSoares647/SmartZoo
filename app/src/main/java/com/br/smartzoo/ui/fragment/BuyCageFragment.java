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
import com.br.smartzoo.model.business.CageBusiness;
import com.br.smartzoo.model.business.EmployeeBusiness;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.interfaces.OnConstructListener;
import com.br.smartzoo.presenter.BuyCagePresenter;
import com.br.smartzoo.ui.adapter.BuyCageAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.HireEmployeeListAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.BuyCageView;
import com.br.smartzoo.util.AlertDialogUtil;

import java.util.List;

/**
 * Created by adenilson on 17/05/16.
 */
public class BuyCageFragment extends Fragment implements BuyCageView, OnConstructListener {
    private static final int VERTICAL_SPACE = 30;
    private RecyclerView mRecyclerViewCages;
    private BuyCagePresenter mPresenter;
    private List<Cage> mCagesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_cage, container, false);

        bindPresenter();
        populateCagesList();
        bindRecyclerViewCages(view);

        return view;
    }

    private void populateCagesList() {
        mCagesList = mPresenter.createCages();
    }

    private void bindPresenter() {
        mPresenter = new BuyCagePresenter(getActivity());
        mPresenter.attachView(this);
    }

    private void bindRecyclerViewCages(View view) {
        mRecyclerViewCages = (RecyclerView) view.findViewById(R.id.recycler_view_buy_cage);
        BuyCageAdapter buyCageAdapter = new BuyCageAdapter(getActivity(), mCagesList);
        buyCageAdapter.addOnConstructListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewCages.setLayoutManager(layoutManager);
        mRecyclerViewCages.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_SPACE));
        mRecyclerViewCages.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        mRecyclerViewCages.setItemViewCacheSize(mCagesList.size());
        mRecyclerViewCages.setAdapter(buyCageAdapter);
    }

    @Override
    public void onConstruct(Cage cage) {

        Boolean haveMoney = BusinessRules.haveMoneyToBuyCage(cage);
        if(haveMoney) {
            showConfirmationDialog(cage);

        }

        else{
            Toast.makeText(getContext(),R.string.msg_dont_have_money,Toast.LENGTH_SHORT).show();
        }
    }



    private void showConfirmationDialog(final Cage cage) {
        AlertDialog.Builder dialog = AlertDialogUtil.makeConfirmationDialog(getActivity(), getActivity().getString(R.string.title_confirm), getActivity().getString(R.string.msg_construct_cage_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Long insertedCageId =  CageBusiness.save(cage);
                        cage.setId(insertedCageId);
                        ZooInfo.cages.add(cage);
                        ZooInfoBusiness.takeMoney(cage.getPrice());


                        Toast.makeText(getContext(), getString(R.string.msg_cage_sucessfully_built), Toast.LENGTH_SHORT).show();
                    }
                });

        dialog.show();



    }
}

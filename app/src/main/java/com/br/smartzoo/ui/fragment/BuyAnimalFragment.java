package com.br.smartzoo.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.interfaces.OnBuyAnimalListener;
import com.br.smartzoo.presenter.BuyAnimalPresenter;
import com.br.smartzoo.ui.adapter.BuyAnimalListAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.ListCageAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.BuyAnimalView;
import com.br.smartzoo.util.RecyclerItemClickListener;

import java.util.List;

/**
 * Created by adenilson on 12/05/16.
 */
public class BuyAnimalFragment extends Fragment implements BuyAnimalView, OnBuyAnimalListener{

    private static final int VERTICAL_ITEM_SPACE = 30;
    private BuyAnimalPresenter mPresenter;
    private List<Animal> mAnimalList;
    private RecyclerView mRecyclerViewAnimals;
    private Dialog selectCageDialog;
    private Animal animal;

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
        buyAnimalListAdapter.setOnBuyAnimalListener(this);
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

    @Override
    public void onBuy(final Animal animal) {
        this.animal = animal;

        selectCageDialog = new Dialog(getContext());
        selectCageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        selectCageDialog.setContentView(R.layout.dialog_animal_cage);


        final RecyclerView recyclerViewCages = (RecyclerView) selectCageDialog.findViewById(R.id.recycler_view_avaible_cages_to_put_animal);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCages.setLayoutManager(layoutManager);
        recyclerViewCages.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        recyclerViewCages.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        recyclerViewCages.setItemViewCacheSize(ZooInfo.cages.size());

        ListCageAdapter listCageAdapter = new ListCageAdapter(ZooInfo.cages,getActivity());
        recyclerViewCages.setAdapter(listCageAdapter);


        recyclerViewCages.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(selectCageDialog.isShowing())
                    selectCageDialog.dismiss();
                    animal.setCage(((ListCageAdapter)recyclerViewCages.getAdapter()).getCage(position));
                    Toast.makeText(getContext(), R.string.msg_animal_succesfully_bought, Toast.LENGTH_SHORT).show();

                    ((BuyAnimalListAdapter)mRecyclerViewAnimals.getAdapter()).getAnimalList().remove(animal);
                    mRecyclerViewAnimals.getAdapter().notifyDataSetChanged();

            }
        }));

        Button buttonCancel = (Button) selectCageDialog.findViewById(R.id.button_cancel_dialog);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCageDialog.dismiss();
            }
        });

        selectCageDialog.show();
    }

}

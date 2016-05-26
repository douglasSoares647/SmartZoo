package com.br.smartzoo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.smartzoo.R;
import com.br.smartzoo.ui.activity.MainActivity;

/**
 * Created by adenilson on 26/05/16.
 */
public class ChooseEmployeeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_employee, container, false);

        bindCardViewVeterinary(view);
        bindCardViewFeeder(view);
        bindCardViewJanitor(view);


        return view;
    }

    private void bindCardViewJanitor(View view) {
        CardView cardViewJanitor = (CardView) view.findViewById(R.id.card_view_janitor);
        cardViewJanitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).startTransaction(new JanitorListFragment());
            }
        });
    }

    private void bindCardViewFeeder(View view) {
        CardView cardViewFeeder = (CardView) view.findViewById(R.id.card_view_feeder);
        cardViewFeeder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).startTransaction(new FeederListFragment());
            }
        });
    }

    private void bindCardViewVeterinary(View view) {
        CardView cardViewVeterinary = (CardView) view.findViewById(R.id.card_view_veterinary);
        cardViewVeterinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).startTransaction(new VeterinaryListFragment());
            }
        });
    }
}

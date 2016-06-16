package com.br.smartzoo.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.environment.Clock;
import com.br.smartzoo.model.environment.ZooInfo;
import com.br.smartzoo.model.interfaces.OnTreatAnimalListener;
import com.br.smartzoo.ui.adapter.AnimalListAdapter;
import com.br.smartzoo.ui.adapter.AnimalsToTreatAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.ListCageAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.util.DateUtil;
import com.br.smartzoo.util.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taibic on 6/15/2016.
 */
public class DetailsVeterinaryFragment extends Fragment {

    private static final int VERTICAL_ITEM_SPACE = 30;
    public static final String SELECTED_VETERINARY = "SELECTED_VETERINARY";

    private Veterinary selected_veterinary;
    private ProgressBar progressBarTreatingAnimal;
    private ProgressBar progressBarStamina;
    private TextView textViewAnimalVeterinary;
    private Dialog selectAnimalDialog;
    private TextView textViewStatusVeterinary;
    private TextView textViewProgressBarTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_veterinary, container, false);

        initVeterinarySelected();
        bindTextViewNameVeterinary(view);
        bindTextViewInitVeterinary(view);
        bindTextViewSalaryVeterinary(view);
        bindTextViewStatusVeterinary(view);
        bindTextViewCageVeterinary(view);
        bindProgressBarTreatingAnimal(view);
        bindProgressBarStaminaVeterinary(view);
        bindButtonTreatVeterinary(view);

        return view;
    }

    private void bindButtonTreatVeterinary(View view) {
        Button buttonCleanCage = (Button) view.findViewById(R.id.button_clean);

        buttonCleanCage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectCageDialog();
            }
        });
    }

    private void openSelectCageDialog() {

        selectAnimalDialog = new Dialog(getContext());
        selectAnimalDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        selectAnimalDialog.setContentView(R.layout.item_animal_to_treat);


        final RecyclerView recyclerViewCages = (RecyclerView) selectAnimalDialog.findViewById(R.id.recycler_view_animals_to_treat);

        List<Animal> animalsToTreat = getAnimalsToTreat();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCages.setLayoutManager(layoutManager);
        recyclerViewCages.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        recyclerViewCages.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        recyclerViewCages.setItemViewCacheSize(animalsToTreat.size());



        AnimalsToTreatAdapter animalsToTreatAdapter = new AnimalsToTreatAdapter(getActivity(),animalsToTreat);
        recyclerViewCages.setAdapter(animalsToTreatAdapter);


        recyclerViewCages.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final Animal animal = ((AnimalsToTreatAdapter) recyclerViewCages.getAdapter()).getAnimal(position);
                selectAnimalDialog.dismiss();


                progressBarTreatingAnimal.setMax(Clock.timeToTreat);
                selected_veterinary.addOnTreatAnimalListener(new OnTreatAnimalListener() {
                    @Override
                    public void onTreatProgress(Integer progress) {
                        progressBarTreatingAnimal.setProgress(progress);
                        textViewProgressBarTask.setText(progress + "/" +Clock.timeToTreat);
                    }

                    @Override
                    public void onTreatFinish() {
                        progressBarTreatingAnimal.setProgress(0);
                        textViewProgressBarTask.setText("");

                        updateZooInfo(animal);
                    }

                    @Override
                    public void onStatusChange() {
                        textViewStatusVeterinary.setText(selected_veterinary.getStatus());
                    }
                });


                selected_veterinary.treat(animal);


            }
        }));

        Button buttonCancel = (Button) selectAnimalDialog.findViewById(R.id.button_cancel_dialog);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAnimalDialog.dismiss();
            }
        });

        selectAnimalDialog.show();

    }

    private void updateZooInfo(Animal animal) {
        ZooInfoBusiness.updateAnimalAfterTreat(animal);
    }

    private void bindProgressBarStaminaVeterinary(View view) {

        progressBarStamina = (ProgressBar) view.findViewById(R.id.progress_bar_stamina_veterinary);
    }

    private void bindProgressBarTreatingAnimal(View view) {
        progressBarTreatingAnimal = (ProgressBar) view.findViewById(R.id.progress_bar_task_veterinary);

        textViewProgressBarTask = (TextView) view.findViewById(R.id.text_view_progress_bar_task_veterinary);
    }

    private void bindTextViewCageVeterinary(View view) {

        textViewAnimalVeterinary = (TextView) view.findViewById(R.id.text_view_animal_veterinary);

    }

    private void bindTextViewStatusVeterinary(View view) {

        textViewStatusVeterinary = (TextView) view.findViewById(R.id.text_view_status_veterinary);
        textViewStatusVeterinary.setText(selected_veterinary.getStatus());
    }

    private void bindTextViewSalaryVeterinary(View view) {
        TextView textViewSalaryJanitor = (TextView) view.findViewById(R.id.text_view_salary_veterinary);
        textViewSalaryJanitor.setText(String.valueOf(selected_veterinary.getSalary()));
    }

    private void bindTextViewInitVeterinary(View view) {
        TextView textViewInitJanitor = (TextView) view.findViewById(R.id.text_view_init_veterinary);
        textViewInitJanitor.setText(DateUtil.dateToString(selected_veterinary.getStartDate()));
    }

    private void bindTextViewNameVeterinary(View view) {

        TextView textViewNameJanitor = (TextView) view.findViewById(R.id.text_view_name_veterinary);

        textViewNameJanitor.setText(selected_veterinary.getName());
    }


    private void initVeterinarySelected() {
        Bundle values = getArguments();
        Parcelable parcelable = values.getParcelable(SELECTED_VETERINARY);
        selected_veterinary = parcelable != null ? (Veterinary) parcelable : new Veterinary();
    }

    public List<Animal> getAnimalsToTreat() {

        List<Animal> animalsToTreat = new ArrayList<>();

        for(Cage cage:ZooInfo.cages){
            for(Animal animal : cage.getAnimals()){
                if(!animal.isHealthy()){
                    animalsToTreat.add(animal);
                }
            }
        }
        return animalsToTreat;
    }
}

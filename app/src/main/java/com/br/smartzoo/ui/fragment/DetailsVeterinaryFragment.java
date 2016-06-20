package com.br.smartzoo.ui.fragment;

import android.app.Dialog;
import android.content.res.Resources;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.SmartZooApplication;
import com.br.smartzoo.game.environment.Clock;
import com.br.smartzoo.model.business.AnimalBusiness;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.interfaces.OnTreatAnimalListener;
import com.br.smartzoo.ui.adapter.AnimalsToTreatAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.util.DateUtil;
import com.br.smartzoo.util.RecyclerItemClickListener;
import com.bumptech.glide.Glide;

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
    private Button buttonTreat;
    private ImageView imageViewAnimalVeterinary;
    private TextView textViewVeterinaryStamina;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_veterinary, container, false);

        initVeterinarySelected();
        bindTextViewNameVeterinary(view);
        bindTextViewInitVeterinary(view);
        bindTextViewSalaryVeterinary(view);
        bindTextViewStatusVeterinary(view);
        bindTextViewAnimalVeterinary(view);
        bindProgressBarTreatingAnimal(view);
        bindProgressBarStaminaVeterinary(view);
        bindButtonTreatVeterinary(view);
        bindImageViewAnimalVeterinary(view);

        setCurrentState();

        return view;
    }

    private void bindImageViewAnimalVeterinary(View view) {
        imageViewAnimalVeterinary = (ImageView) view.findViewById(R.id.image_view_animal_veterinary);


    }


    private void bindButtonTreatVeterinary(View view) {

        buttonTreat = (Button) view.findViewById(R.id.button_treat);

        buttonTreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectCageDialog();
            }
        });

    }

    private void openSelectCageDialog() {

        selectAnimalDialog = new Dialog(getContext());
        selectAnimalDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        selectAnimalDialog.setContentView(R.layout.dialog_select_animal_treat);


        final RecyclerView recyclerViewAnimals = (RecyclerView) selectAnimalDialog.findViewById(R.id.recycler_view_animals_to_treat);

        List<Animal> animalsToTreat = AnimalBusiness.getAnimalsToTreat();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewAnimals.setLayoutManager(layoutManager);
        recyclerViewAnimals.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        recyclerViewAnimals.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        recyclerViewAnimals.setItemViewCacheSize(animalsToTreat.size());



        AnimalsToTreatAdapter animalsToTreatAdapter = new AnimalsToTreatAdapter(getActivity(),animalsToTreat);
        recyclerViewAnimals.setAdapter(animalsToTreatAdapter);


        recyclerViewAnimals.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final Animal animal = ((AnimalsToTreatAdapter) recyclerViewAnimals.getAdapter()).getAnimal(position);
                selectAnimalDialog.dismiss();


                setTaskProgressAttributes(animal);

                selected_veterinary.treat(animal);
                setCurrentState();

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

    private void setTaskProgressAttributes(final Animal animal) {
        progressBarTreatingAnimal.setMax(Clock.timeToTreat);

        selected_veterinary.addOnTreatAnimalListener(new OnTreatAnimalListener() {
            @Override
            public void onTreatProgress(Integer progress) {
                progressBarTreatingAnimal.setProgress(progress);
                textViewProgressBarTask.setText(progress + "/" +Clock.timeToTreat);
            }

            @Override
            public void onTreatFinish() {
                buttonTreat.setAlpha(1f);
                buttonTreat.setEnabled(true);
                buttonTreat.setText(getString(R.string.button_treat));

                setCurrentState();


                updateZooInfo(animal);
            }

            @Override
            public void onStatusChange() {
                textViewStatusVeterinary.setText(selected_veterinary.getStatus());
            }

            @Override
            public void onStaminaChange() {
                progressBarStamina.setProgress(selected_veterinary.getStamina());
                textViewVeterinaryStamina.setText(selected_veterinary.getStamina()+ "/" + Veterinary.maxStamina);
            }
        });


    }

    private void updateZooInfo(Animal animal) {
        ZooInfoBusiness.updateAnimalAfterTreat(animal);
    }

    private void bindProgressBarStaminaVeterinary(View view) {

        progressBarStamina = (ProgressBar) view.findViewById(R.id.progress_bar_stamina_veterinary);
        progressBarStamina.setMax(Veterinary.maxStamina);
        progressBarStamina.setProgress(selected_veterinary.getStamina());

        textViewVeterinaryStamina = (TextView) view.findViewById(R.id.text_view_progress_bar_stamina_veterinary);
        textViewVeterinaryStamina.setText(selected_veterinary.getStamina()+ "/" + Veterinary.maxStamina);
    }

    private void bindProgressBarTreatingAnimal(View view) {
        progressBarTreatingAnimal = (ProgressBar) view.findViewById(R.id.progress_bar_task_veterinary);
        textViewProgressBarTask = (TextView) view.findViewById(R.id.text_view_progress_bar_task_veterinary);

    }

    private void bindTextViewAnimalVeterinary(View view) {

        textViewAnimalVeterinary = (TextView) view.findViewById(R.id.text_view_animal_veterinary);

    }

    private void bindTextViewStatusVeterinary(View view) {
        textViewStatusVeterinary = (TextView) view.findViewById(R.id.text_view_status_veterinary);
        textViewStatusVeterinary.setText(selected_veterinary.getStatus());
    }

    private void bindTextViewSalaryVeterinary(View view) {
        TextView textViewSalaryJanitor = (TextView) view.findViewById(R.id.text_view_salary_veterinary);
        textViewSalaryJanitor.setText(String.format("%02d", selected_veterinary.getSalary()));
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

    private void setCurrentState() {
        if(selected_veterinary.getTreating()) {

            setTaskProgressAttributes(selected_veterinary.getCurrentAnimal());
            textViewAnimalVeterinary.setText(selected_veterinary.getCurrentAnimal().getName());
            imageViewAnimalVeterinary.setVisibility(View.VISIBLE);

            Resources resources = getResources();
            int image = resources.getIdentifier(selected_veterinary.getCurrentAnimal().getImage(),"drawable", SmartZooApplication.NAME_PACKAGE);
            Glide.with(getContext()).load(image).into(imageViewAnimalVeterinary);

            buttonTreat.setAlpha(0.5f);
            buttonTreat.setEnabled(false);
            buttonTreat.setText("Already treating an animal");
        }
        else{
            imageViewAnimalVeterinary.setVisibility(View.INVISIBLE);
            textViewAnimalVeterinary.setText("");
            progressBarTreatingAnimal.setProgress(0);
            textViewProgressBarTask.setText("");
        }

    }
}

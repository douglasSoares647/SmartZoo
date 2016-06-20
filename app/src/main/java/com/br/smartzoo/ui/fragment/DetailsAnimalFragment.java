package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.SmartZooApplication;
import com.br.smartzoo.model.entity.Animal;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

/**
 * Created by adenilson on 12/06/16.
 */

public class DetailsAnimalFragment extends Fragment {

    public static final String SELECTED_ANIMAL = "SELECTED_ANIMAL";
    private Animal selectedAnimal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_animal, container, false);

        initAnimalSelected();
        bindImageViewAnimal(view);
        bindTextViewName(view);
        bindTextViewSpecie(view);
        bindTextViewAge(view);
        bindTextViewHealthy(view);
        bindTextViewWeight(view);
        bindTextViewSex(view);
        bindTextViewPopularity(view);
        bindTextViewPrice(view);
        bindTextViewResistance(view);
        bindImageViewFood(view);
        bindTextViewCostStamina(view);
        bindTextViewFoodSatisfied(view);

        return view;
    }

    private void bindImageViewAnimal(View view) {
        ImageView imageViewAnimal = (ImageView) view.findViewById(R.id.image_view_animal);
        Glide.with(getActivity()).load(getResources().getIdentifier(selectedAnimal.getImage(),
                "drawable", SmartZooApplication.NAME_PACKAGE))
                .into(imageViewAnimal);
    }

    private void bindTextViewFoodSatisfied(View view) {
        TextView textViewFoodSatisfied = (TextView) view.findViewById(R.id
                .text_view_food_satisfied_animal);
        if (selectedAnimal.getFoodToBeSatisfied() != null) {
            DecimalFormat decimalFormat = new DecimalFormat("0,00");
            textViewFoodSatisfied.setText(decimalFormat.format(selectedAnimal
                    .getFoodToBeSatisfied()) + "kg");
        } else {
            textViewFoodSatisfied.setText("Unknown");
            textViewFoodSatisfied.setTextSize(24);
        }
    }

    private void bindTextViewCostStamina(View view) {
        TextView textViewCostCured = (TextView) view.findViewById(R.id.text_view_cost_cured_animal);
        if (selectedAnimal.getStaminaToBeCured() != null) {
            textViewCostCured.setText(String.valueOf
                    (selectedAnimal.getStaminaToBeCured()));
        } else {
            textViewCostCured.setText("Unknown");
            textViewCostCured.setTextSize(24);
        }
    }

    private void bindImageViewFood(View view) {
        ImageView imageViewFoodFavorite = (ImageView) view.findViewById(R.id
                .image_view_food_favorite_animal);

        if (selectedAnimal.getFavoriteFood() != null) {
            int drawable = getResources().getIdentifier(selectedAnimal.getFavoriteFood(),
                    "drawable",
                    SmartZooApplication.NAME_PACKAGE);
            Glide.with(getActivity()).load(drawable).into(imageViewFoodFavorite);
        }

    }

    private void bindTextViewResistance(View view) {
        TextView textViewResistance = (TextView) view.findViewById(R.id
                .text_view_resistance_animal);
        if (selectedAnimal.getResistance() != null) {
            textViewResistance.setText(String.valueOf
                    (selectedAnimal
                            .getResistance()));
        } else {
            textViewResistance.setText("Unknown");
            textViewResistance.setTextSize(24);
        }
    }

    private void bindTextViewPrice(View view) {
        TextView textViewPrice = (TextView) view.findViewById(R.id.text_view_price_animal);
        if (selectedAnimal.getPrice() != null) {
            DecimalFormat decimalFormat = new DecimalFormat("0,00");

            textViewPrice.setText(decimalFormat.format(selectedAnimal.getPrice()) + " $");
        } else {
            textViewPrice.setText("Unknown");
            textViewPrice.setTextSize(24);
        }
    }

    private void bindTextViewPopularity(View view) {
        TextView textViewPopularity = (TextView) view.findViewById(R.id
                .text_view_popularity_animal);
        if (selectedAnimal.getPopularity() != null) {
            textViewPopularity.setText(String.valueOf
                    (selectedAnimal.getPopularity()));
        } else {
            textViewPopularity.setText("Unknown");
            textViewPopularity.setTextSize(24);
        }
    }

    private void bindTextViewSex(View view) {
        TextView textViewSex = (TextView) view.findViewById(R.id.text_view_sex_animal);
        if (selectedAnimal.getSex() != null) {
            textViewSex.setText(selectedAnimal.getSex());
        } else {
            textViewSex.setText("Unknown");
            textViewSex.setTextSize(24);
        }

    }

    private void bindTextViewWeight(View view) {
        TextView textViewWeight = (TextView) view.findViewById(R.id.text_view_weight_animal);
        if (selectedAnimal.getWeight() != null) {
            DecimalFormat decimalFormat = new DecimalFormat("0,00");

            textViewWeight.setText(decimalFormat.format(selectedAnimal.getWeight()) + "kg");
        } else {
            textViewWeight.setText("Unknown");
            textViewWeight.setTextSize(24);
        }
    }

    private void bindTextViewHealthy(View view) {
        TextView textViewHealth = (TextView) view.findViewById(R.id.text_view_health_animal);
        if (selectedAnimal.isHealthy()) {
            textViewHealth.setText("Healthy");
            textViewHealth.setTextColor(getActivity().getResources().getColor(R.color.green_700));
        } else {
            textViewHealth.setText("Sick");
            textViewHealth.setTextColor(getActivity().getResources().getColor(R.color.red_700));
        }
    }

    private void bindTextViewAge(View view) {
        TextView textViewAge = (TextView) view.findViewById(R.id.text_view_age_animal);
        textViewAge.setText(selectedAnimal.getAge() != null ? String.valueOf(selectedAnimal
                .getAge() + " " + getResources().getString(R.string.text_age)) : "Unknown");
    }

    private void bindTextViewSpecie(View view) {
        TextView textViewSpecie = (TextView) view.findViewById(R.id.text_View_specie_animal);
        textViewSpecie.setText(selectedAnimal.getSpecie() != null ? selectedAnimal.getSpecie() :
                "Unknown");
    }

    private void bindTextViewName(View view) {
        TextView textViewName = (TextView) view.findViewById(R.id.text_view_name_animal);
        textViewName.setText(selectedAnimal.getName() != null ? selectedAnimal.getName() :
                "Unknown");
    }

    private void initAnimalSelected() {
        Bundle bundle = this.getArguments();
        Parcelable parcelable = bundle.getParcelable(SELECTED_ANIMAL);
        selectedAnimal = parcelable != null ? (Animal) parcelable : new Animal();
    }
}

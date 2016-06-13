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

    private void bindTextViewFoodSatisfied(View view) {
        TextView textViewFoodSatisfied = (TextView) view.findViewById(R.id
                .text_view_food_satisfied_animal);
        textViewFoodSatisfied.setText(selectedAnimal.getFoodToBeSatisfied() != null ? String.valueOf
                (selectedAnimal.getFoodToBeSatisfied()) : "Unknown");
    }

    private void bindTextViewCostStamina(View view) {
        TextView textViewCostCured = (TextView) view.findViewById(R.id.text_view_cost_cured_animal);
        textViewCostCured.setText(selectedAnimal.getStaminaToBeCured() != null ? String.valueOf
                (selectedAnimal.getStaminaToBeCured()) : "Unknown");
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
        textViewResistance.setText(selectedAnimal.getResistance() != null ? String.valueOf
                (selectedAnimal
                        .getResistance()) : "Unknown");
    }

    private void bindTextViewPrice(View view) {
        TextView textViewPrice = (TextView) view.findViewById(R.id.text_view_price_animal);
        textViewPrice.setText(selectedAnimal.getPrice() != null ? String.valueOf(selectedAnimal
                .getPrice()) : "Unknown");
    }

    private void bindTextViewPopularity(View view) {
        TextView textViewPopularity = (TextView) view.findViewById(R.id
                .text_view_popularity_animal);
        textViewPopularity.setText(selectedAnimal.getPopularity() != null ? String.valueOf
                (selectedAnimal.getPopularity()) : "Unknown");
    }

    private void bindTextViewSex(View view) {
        TextView textViewSex = (TextView) view.findViewById(R.id.text_view_sex_animal);
        textViewSex.setText(selectedAnimal.getSex() != null ? selectedAnimal.getSex() : "Unknown");
    }

    private void bindTextViewWeight(View view) {
        TextView textViewWeight = (TextView) view.findViewById(R.id.text_view_weight_animal);
        textViewWeight.setText(selectedAnimal.getWeight() != null ? String.valueOf(selectedAnimal
                .getWeight()) : "Unknown");
    }

    private void bindTextViewHealthy(View view) {
        TextView textViewHealth = (TextView) view.findViewById(R.id.text_view_health_animal);
        textViewHealth.setText(selectedAnimal.isHealthy() ? "Healthy" : "Sick");
    }

    private void bindTextViewAge(View view) {
        TextView textViewAge = (TextView) view.findViewById(R.id.text_view_age_animal);
        textViewAge.setText(selectedAnimal.getAge() != null ? String.valueOf(selectedAnimal
                .getAge()) : "Unknown");
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

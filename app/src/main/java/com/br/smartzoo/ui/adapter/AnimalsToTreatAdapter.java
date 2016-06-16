package com.br.smartzoo.ui.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.SmartZooApplication;
import com.br.smartzoo.model.entity.Animal;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Taibic on 6/15/2016.
 */
public class AnimalsToTreatAdapter extends RecyclerView.Adapter<AnimalsToTreatAdapter.ViewHolder> {

    private Activity mContext;
    private List<Animal> mAnimalList;


    public AnimalsToTreatAdapter(Activity mContext, List<Animal> mAnimalList) {
        this.mContext = mContext;
        this.mAnimalList = mAnimalList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_animal_to_treat, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Animal animal = mAnimalList.get(position);

        Resources resources = mContext.getResources();
        Glide.with(mContext).load(resources.getIdentifier(animal.getImage(), "drawable",
                SmartZooApplication.NAME_PACKAGE)).into(holder.mImageAnimal);

        holder.mTextViewNameAnimal.setText(animal.getName());
        holder.mTextViewAgeAnimal.setText(animal.getAge());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public Animal getAnimal(int position){
        return mAnimalList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageAnimal;
        private TextView mTextViewNameAnimal;
        private TextView mTextViewAgeAnimal;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageAnimal = (ImageView) itemView.findViewById(R.id.image_view_animal);
            mTextViewNameAnimal = (TextView) itemView.findViewById(R.id.text_view_name_animal);
            mTextViewAgeAnimal = (TextView) itemView.findViewById(R.id.text_view_age_animal);
        }
    }
}

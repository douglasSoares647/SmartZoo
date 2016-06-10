package com.br.smartzoo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;
import com.bumptech.glide.Glide;

/**
 * Created by Taibic on 6/2/2016.
 */
public class AnimalTypeListAdapter extends RecyclerView.Adapter<AnimalTypeListAdapter.ViewHolder> {

    private Animal.AnimalEnum[] mAnimalList;
    private Context mContext;


    public AnimalTypeListAdapter(Context context, Animal.AnimalEnum[] animalList) {
        this.mAnimalList = animalList;
        this.mContext = context;
    }


    @Override
    public AnimalTypeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_select_animal_type, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnimalTypeListAdapter.ViewHolder holder, int position) {
        final Animal.AnimalEnum animal = mAnimalList[position];
        Glide.with(mContext).load(animal.getImage()).into(holder.mImageViewAnimal);
        holder.mTextViewNameAnimal.setText(mContext.getString(animal.getType()));

    }

    @Override
    public int getItemCount() {
        return mAnimalList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewAnimal;
        private TextView mTextViewNameAnimal;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewAnimal = (ImageView) itemView.findViewById(R.id.image_view_animal);
            mTextViewNameAnimal = (TextView) itemView.findViewById(R.id.text_view_name_animal);
        }
    }


    public String getAnimalType(int position){
        return mContext.getString(mAnimalList[position].getType());
    }
}

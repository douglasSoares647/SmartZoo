package com.br.smartzoo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 11/05/16.
 */
public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.ViewHolder> {

    private List<Animal> mAnimalList;
    private Context mContext;
    private Integer[] mIntegers = {0,1,2,3,4,5,6,7,8,9};

    public AnimalListAdapter(Context context, List<Animal> animalList) {
        this.mAnimalList = animalList;
        this.mContext = context;
    }


    @Override
    public AnimalListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_animal_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnimalListAdapter.ViewHolder holder, int position) {
        Animal animal = mAnimalList.get(position);
        Glide.with(mContext).load(R.drawable.ic_buy_animal).into(holder.mImageViewAnimal);
        holder.mTextViewNameAnimal.setText(animal.getName());
        holder.mTextViewAgeAnimal.setText(animal.getAge());
        boolean healthy = animal.isHealthy();
        if (healthy)
            holder.mTextViewHealthAnimal.setText("Saudável");
        else
            holder.mTextViewHealthAnimal.setText("Doente");

        holder.mTextViewCostAnimal.setText("2000");
        holder.mSpinnerAnimal.setAdapter(new ArrayAdapter<Integer>(mContext
                , android.R.layout.simple_list_item_1, mIntegers));

    }

    @Override
    public int getItemCount() {
        return mAnimalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewAnimal;
        private TextView mTextViewNameAnimal;
        private TextView mTextViewAgeAnimal;
        private TextView mTextViewHealthAnimal;
        private TextView mTextViewCostAnimal;
        private Spinner mSpinnerAnimal;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewAnimal = (ImageView) itemView.findViewById(R.id.image_view_animal);
            mTextViewNameAnimal = (TextView) itemView.findViewById(R.id.text_view_name_animal);
            mTextViewAgeAnimal = (TextView) itemView.findViewById(R.id.text_view_age_animal);
            mTextViewHealthAnimal = (TextView) itemView.findViewById(R.id.text_view_health_animal);
            mTextViewCostAnimal = (TextView) itemView.findViewById(R.id.text_view_cost_animal);
            mSpinnerAnimal = (Spinner) itemView.findViewById(R.id.spinner_number_buy_animal);
        }
    }
}

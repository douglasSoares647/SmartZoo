package com.br.smartzoo.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.SmartZooApplication;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.interfaces.OnBuyAnimalListener;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 11/05/16.
 */
public class BuyAnimalListAdapter extends RecyclerView.Adapter<BuyAnimalListAdapter.ViewHolder> {

    private List<Animal> mAnimalList;
    private Context mContext;
    private OnBuyAnimalListener mOnBuyAnimalListener;


    public void setOnBuyAnimalListener (OnBuyAnimalListener onBuyAnimalListener){
        this.mOnBuyAnimalListener = onBuyAnimalListener;
    }

    public BuyAnimalListAdapter(Context context, List<Animal> animalList) {
        this.mAnimalList = animalList;
        this.mContext = context;
    }


    @Override
    public BuyAnimalListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_buy_animal_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BuyAnimalListAdapter.ViewHolder holder, int position) {
        final Animal animal = mAnimalList.get(position);

        Resources resources = mContext.getResources();
        Glide.with(mContext).load(resources.getIdentifier(animal.getImage(), "drawable",
                SmartZooApplication.NAME_PACKAGE)).into(holder.mImageViewAnimal);
        holder.mTextViewNameAnimal.setText(animal.getSpecie());
        holder.mTextViewAgeAnimal.setText(String.valueOf(animal.getAge() + " "
                + mContext.getString(R.string.text_age)));
        boolean healthy = animal.isHealthy();
        if (healthy)
            holder.mTextViewHealthAnimal.setText(R.string.text_healthy);
        else
            holder.mTextViewHealthAnimal.setText(R.string.text_sick);

        holder.mTextViewCostAnimal.setText("$" + String.valueOf(animal.getPrice()));
        holder.mButtonAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mOnBuyAnimalListener.onBuy(animal);

            }
        });

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
        private Button mButtonAnimal;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewAnimal = (ImageView) itemView.findViewById(R.id.image_view_animal);
            mTextViewNameAnimal = (TextView) itemView.findViewById(R.id.text_view_name_animal);
            mTextViewAgeAnimal = (TextView) itemView.findViewById(R.id.text_view_age_animal);
            mTextViewHealthAnimal = (TextView) itemView.findViewById(R.id.text_view_health_animal);
            mTextViewCostAnimal = (TextView) itemView.findViewById(R.id.text_view_cost_animal);
            mButtonAnimal = (Button) itemView.findViewById(R.id.button_number_buy_animal);
        }
    }


    public List<Animal> getAnimalList() {
        return mAnimalList;
    }
}

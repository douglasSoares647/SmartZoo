package com.br.smartzoo.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Food;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 20/06/16.
 */

public class FoodsAvaiableListAdapter extends RecyclerView.Adapter<FoodsAvaiableListAdapter
        .ViewHolder> {


    private Activity mContext;
    private List<Food> mFoodList;

    public FoodsAvaiableListAdapter(Activity context, List<Food> foodList) {
        this.mContext = context;
        this.mFoodList = foodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_foods_avaiable, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = mFoodList.get(position);
        Glide.with(mContext).load(food.getImage()).into(holder.mImageViewFood);
        holder.mTextViewName.setText(food.getName());
        holder.mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private Button mButtonAdd;
        private ImageView mImageViewFood;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_food_avaiable);
            mButtonAdd = (Button) itemView.findViewById(R.id
                    .button_add_food);
            mImageViewFood = (ImageView) itemView.findViewById(R.id.image_view_food_avaiable);
        }
    }
}

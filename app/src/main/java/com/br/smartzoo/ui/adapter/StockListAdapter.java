package com.br.smartzoo.ui.adapter;

import android.app.Activity;
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
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.interfaces.OnManageFood;
import com.br.smartzoo.util.DateUtil;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 24/05/16.
 */
public class StockListAdapter extends RecyclerView.Adapter<StockListAdapter.ViewHolder> {


    private List<Food> mFoodList;
    private Activity mContext;
    private OnManageFood mOnManageFood;

    public StockListAdapter(Activity context, List<Food> foodList) {
        this.mContext = context;
        this.mFoodList = foodList;
    }

    public void addOnManageFood(OnManageFood onManageFood){
        this.mOnManageFood = onManageFood;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stock_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Food food = mFoodList.get(position);

        Resources resources = mContext.getResources();
        Glide.with(mContext).load(resources.getIdentifier(food.getImage(), "drawable",
                SmartZooApplication.NAME_PACKAGE)).into(holder.mImageView);
        holder.mTextViewName.setText(food.getName());
        holder.mTextViewExpiryDate.setText(DateUtil.dateToString(food.getExpirationDate()));

        holder.mButtonSell.setText(food.getPrice() + " SELL ");
        holder.mButtonSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnManageFood.onSell(food);

            }
        });

    }

    public void setFoodList(List<Food> foods){
        this.mFoodList = foods;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextViewName;
        private TextView mTextViewExpiryDate;
        private Button mButtonSell;
        private Button mButtonPrepare;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_view_food);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_food);
            mTextViewExpiryDate = (TextView) itemView.findViewById(R.id.text_view_expiry_date);
            mButtonSell = (Button) itemView.findViewById(R.id.button_sell_food);


        }
    }


    public void removeFood(Food food){
        mFoodList.remove(food);
        notifyDataSetChanged();
    }

}

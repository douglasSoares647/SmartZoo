package com.br.smartzoo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.interfaces.OnChangeBuyListener;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by adenilson on 15/05/16.
 */
public class BuyFoodListAdapter extends RecyclerView.Adapter<BuyFoodListAdapter.ViewHolder> {

    private List<Food> mFoodList;
    private Context mContext;
    private OnChangeBuyListener mOnChangeBuyListener;
    private HashMap<Food, Integer> mQuantity;



    public BuyFoodListAdapter(Context context, List<Food> foods) {
        this.mContext = context;
        this.mFoodList = foods;
        mQuantity = new HashMap<>();

        //TODO for each food, add new instance into hash to calculate total price
        for (Food f : foods) {
            mQuantity.put(f, 0);
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("onCreateViewHolder", "");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_buy_food_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.i("onBindViewHolder", "");
        final Food food = mFoodList.get(position);

        Glide.with(mContext).load(food.getImage()).into(holder.mImageViewFruit);
        holder.mTextViewName.setText(food.getName());
        String weightFruit = food.getWeight() + "kg";
        holder.mTextViewWeight.setText(weightFruit);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        final String price = (String.valueOf(decimalFormat.format(food.getPrice()))) + "$";
        holder.mTextViewPrice.setText(price);
        holder.mSeekBarQuantity.setMax(99);
        holder.mSeekBarQuantity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                holder.mTextViewQuantity.setText(String.valueOf(progress));
                mQuantity.put(food, progress);
                mOnChangeBuyListener.onQuantityChange(mQuantity);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }



    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public void addCallBack(OnChangeBuyListener buyFoodListener) {
        this.mOnChangeBuyListener = buyFoodListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewFruit;
        private TextView mTextViewName;
        private TextView mTextViewWeight;
        private TextView mTextViewPrice;
        private TextView mTextViewQuantity;
        private SeekBar mSeekBarQuantity;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageViewFruit = (ImageView) itemView.findViewById(R.id.image_view_fruit);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_fruit_name);
            mTextViewWeight = (TextView) itemView.findViewById(R.id.text_view_weight_fruit);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.text_view_fruit_price);
            mTextViewQuantity = (TextView) itemView.findViewById(R.id.text_view_food_quantity);
            mSeekBarQuantity = (SeekBar) itemView.findViewById(R.id.seek_bar_food_quantity);
        }
    }
}

package com.br.smartzoo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.interfaces.OnChangeBuyListener;
import com.br.smartzoo.ui.fragment.BuyFoodFragment;
import com.br.smartzoo.util.BuyHelper;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by adenilson on 15/05/16.
 */
public class BuyFoodListAdapter extends RecyclerView.Adapter<BuyFoodListAdapter.MyViewHolder> {

    private List<Food> mFoodList;
    private Context mContext;
    private OnChangeBuyListener mOnChangeBuyListener;



    public BuyFoodListAdapter(Context context, List<Food> foods) {
        this.mContext = context;
        this.mFoodList = foods;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("onCreateViewHolder", "");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_buy_food_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
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
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void calculateQuantityAndPrice() {

    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public void addCallBack(OnChangeBuyListener buyFoodListener) {
        this.mOnChangeBuyListener = buyFoodListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewFruit;
        private TextView mTextViewName;
        private TextView mTextViewWeight;
        private TextView mTextViewPrice;
        private TextView mTextViewQuantity;
        private SeekBar mSeekBarQuantity;
        public MyViewHolder(View itemView) {
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

package com.br.smartzoo.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.interfaces.OnConstructListener;
import com.br.smartzoo.presenter.BuyCagePresenter;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 22/05/16.
 */
public class BuyCageAdapter extends RecyclerView.Adapter<BuyCageAdapter.ViewHolder> {

    private List<Cage> mCagesList;
    private Activity mContext;
    private OnConstructListener mOnConstructListener;


    public void addOnConstructListener(OnConstructListener onConstructListener){
        this.mOnConstructListener = onConstructListener;
    }

    public BuyCageAdapter(Activity context, List<Cage> cages){
        this.mContext = context;
        this.mCagesList = cages;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_buy_cage_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cage cage = mCagesList.get(position);

        Glide.with(mContext).load(R.drawable.ic_cage).into(holder.mImageViewCage);
        holder.mTextViewName.setText(cage.getName());
        holder.mTextViewCapacity.setText(String.valueOf(cage.getCapacity()));
        holder.mTextViewPrice.setText(String.valueOf(cage.getPrice()));

        holder.mButtonConstruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnConstructListener.onConstruct(cage);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewCage;
        private TextView mTextViewName;
        private TextView mTextViewCapacity;
        private TextView mTextViewPrice;
        private Button mButtonConstruct;


        public ViewHolder(View itemView) {
            super(itemView);

            mImageViewCage = (ImageView) itemView.findViewById(R.id.image_view_cage);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_cage);
            mTextViewCapacity = (TextView) itemView.findViewById(R.id.text_view_capacity_cage);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.text_view_price_cage);
            mButtonConstruct = (Button) itemView.findViewById(R.id.button_construct_cage);
        }
    }
}

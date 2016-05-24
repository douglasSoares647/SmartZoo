package com.br.smartzoo.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.interfaces.OnConstructListener;
import com.br.smartzoo.model.interfaces.OnSelectCageListener;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Taibic on 5/23/2016.
 */
public class ListCageAdapter extends RecyclerView.Adapter<ListCageAdapter.ViewHolder> {

    private List<Cage> mCagesList;
    private Activity mContext;

    public ListCageAdapter(List<Cage> mCagesList, Activity mContext) {
        this.mCagesList = mCagesList;
        this.mContext = mContext;
    }

    @Override
    public ListCageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_list_cage, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListCageAdapter.ViewHolder holder, int position) {
        final Cage cage = mCagesList.get(position);

        Glide.with(mContext).load(R.drawable.ic_cage).into(holder.mImageViewCage);
        holder.mTextViewName.setText(cage.getName());
        holder.mTextViewCapacity.setText(String.valueOf(cage.getCapacity()));
    }

    @Override
    public int getItemCount() {
        return mCagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageViewCage;
        private TextView mTextViewName;
        private TextView mTextViewCapacity;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageViewCage = (ImageView) itemView.findViewById(R.id.image_view_list_cage);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_list_cage);
            mTextViewCapacity = (TextView) itemView.findViewById(R.id.text_view_capacity_list_cage);
        }
    }


    public Cage getCage(int position){
        return mCagesList.get(position);
    }
}

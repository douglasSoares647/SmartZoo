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
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.ui.view.DetailsFeederView;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 20/06/16.
 */

public class CagesAvailableListAdapter extends RecyclerView.Adapter<CagesAvailableListAdapter
        .ViewHolder> {


    private Activity mContext;
    private List<Cage> mCageList;
    private DetailsFeederView mCallBack;

    public CagesAvailableListAdapter(Activity context, List<Cage> foodList) {
        this.mContext = context;
        this.mCageList = foodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_foods_avaiable, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cage cage = mCageList.get(position);
        Glide.with(mContext).load(R.drawable.ic_cage).into(holder.mImageViewCage);
        holder.mTextViewName.setText(cage.getName());
        holder.mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.feedCage(cage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCageList.size();
    }

    public void addCallBack(DetailsFeederView callBack) {
        this.mCallBack = callBack;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private Button mButtonAdd;
        private ImageView mImageViewCage;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_cage_available);
            mButtonAdd = (Button) itemView.findViewById(R.id
                    .button_add_into_cage);
            mImageViewCage = (ImageView) itemView.findViewById(R.id.image_view_cage_available);
        }
    }
}

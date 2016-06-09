package com.br.smartzoo.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.interfaces.OnJanitorsRestedSelected;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 08/06/16.
 */

public class JanitorsRestedAdapter extends RecyclerView.Adapter<JanitorsRestedAdapter.ViewHolder> {
    private Activity mContext;
    private List<Janitor> mJanitorList;
    private OnJanitorsRestedSelected mOnJanitorRestedSelected;
    private Cage mCage;


    public JanitorsRestedAdapter(Activity context, List<Janitor> janitors, Cage cage){
        this.mContext  =context;
        this.mJanitorList = janitors;
        this.mCage = cage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_dialog_janitors_rested,
                parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Janitor janitor = mJanitorList.get(position);
        Glide.with(mContext).load(janitor.getImage()).into(holder.mImageViewJanitor);
        holder.mTextViewName.setText(janitor.getName());
        holder.mRelativeJanitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnJanitorRestedSelected.onSelected(janitor, mCage);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mJanitorList.size();
    }

    public void addOnJanitorRestedSelected(OnJanitorsRestedSelected onJanitorsRestedSelected) {
        this.mOnJanitorRestedSelected = onJanitorsRestedSelected;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewJanitor;
        private TextView mTextViewName;
        private RelativeLayout mRelativeJanitor;

        public ViewHolder(View itemView) {
            super(itemView);

            mRelativeJanitor = (RelativeLayout) itemView.findViewById(R.id.relative_janitor);
            mImageViewJanitor = (ImageView) itemView.findViewById(R.id.image_view_janitor);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_janitor);
        }
    }
}

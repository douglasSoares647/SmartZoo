package com.br.smartzoo.ui.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.SmartZooApplication;
import com.br.smartzoo.model.entity.New;
import com.br.smartzoo.model.interfaces.OnNewClick;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 05/06/16.
 */
public class NewListAdapter extends RecyclerView.Adapter<NewListAdapter.ViewHolder> {
    private Activity mContext;
    private List<New> mNewList;
    private OnNewClick mOnNewClick;
    private final static int FADE_DURATION = 500;


    public NewListAdapter(Activity context, List<New> news) {
        this.mContext = context;
        this.mNewList = news;
    }

    public void addOnNewClick(OnNewClick onNewClick){
        this.mOnNewClick = onNewClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final New aNew = mNewList.get(position);

        Resources resources = mContext.getResources();

        Glide.with(mContext).load(resources.getIdentifier(aNew.getImageType(), "drawable",
                SmartZooApplication.NAME_PACKAGE)).into(holder.mImageViewType);

        if(aNew.getImageSecondary()!=null && !aNew.getImageSecondary().equals("")) {
            Glide.with(mContext).load(resources.getIdentifier(aNew.getImageSecondary(), "drawable",
                    SmartZooApplication.NAME_PACKAGE)).into(holder.mImageViewSecondary);
            holder.mImageViewSecondary.setVisibility(View.VISIBLE);
        }
        else{
            holder.mImageViewSecondary.setVisibility(View.GONE);
        }

        holder.mTextViewTitle.setText(aNew.getTitle());
        holder.mTextViewMessage.setText(aNew.getMessage());

        if(aNew.getImageDescription()!=null) {
            holder.mTextViewDescription.setText(aNew.getImageDescription());
            holder.mTextViewDescription.setVisibility(View.VISIBLE);
        }
        else{
            holder.mTextViewDescription.setVisibility(View.GONE);
        }


        holder.mCardViewNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = aNew.getTag();
                mOnNewClick.newClicked(tag);
            }
        });

      //  setFadeAnimation(holder.mCardViewNew);


    }

    @Override
    public int getItemCount() {
        return mNewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewType;
        private TextView mTextViewTitle;
        private TextView mTextViewMessage;
        private ImageView mImageViewSecondary;
        private TextView mTextViewDescription;
        private CardView mCardViewNew;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageViewType = (ImageView) itemView.findViewById(R.id.image_view_type_new);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.text_view_title_new);
            mTextViewMessage = (TextView) itemView.findViewById(R.id.text_view_message_new);
            mImageViewSecondary = (ImageView) itemView.findViewById(R.id.image_view_secondary_image);
            mTextViewDescription
                    = (TextView) itemView.findViewById(R.id.text_view_secondary_description);
            mCardViewNew = (CardView) itemView.findViewById(R.id.card_view_new);
        }

        public void clearAnimation()
        {
            mCardViewNew.clearAnimation();
        }
    }

    public void setNewList(List<New> news){
        this.mNewList = news;
        notifyDataSetChanged();
    }

    public void addNewToList(New aNew){
        if(mNewList != null){
            if(mNewList.size()==50){
                mNewList.remove(mNewList.size()-1);
                notifyItemRemoved(50);

            }
            mNewList.add(0,aNew);
            notifyItemInserted(0);

        }

    }


    private void setFadeAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        holder.clearAnimation();
        super.onViewDetachedFromWindow(holder);
    }



}

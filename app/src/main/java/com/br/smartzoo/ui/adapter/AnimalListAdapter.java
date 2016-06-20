package com.br.smartzoo.ui.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.SmartZooApplication;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.interfaces.OnManageAnimal;
import com.br.smartzoo.util.AlertDialogUtil;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 31/05/16.
 */
public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.ViewHolder> {

    private Activity mContext;
    private List<Animal> mAnimalList;
    private OnManageAnimal mOnManageAnimal;

    public AnimalListAdapter(Activity context, List<Animal> animals) {
        this.mContext = context;
        this.mAnimalList = animals;
    }

    public void addOnManageAnimal(OnManageAnimal onManageAnimal) {
        this.mOnManageAnimal = onManageAnimal;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_animal_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Animal animal = mAnimalList.get(position);

        Resources resources = mContext.getResources();
        Glide.with(mContext).load(resources.getIdentifier(animal.getImage(), "drawable",
                SmartZooApplication.NAME_PACKAGE)).into(holder.mImageViewAnimal);
        Glide.with(mContext).load(R.drawable.ic_sell).into(holder.mImageViewSell);
        Glide.with(mContext).load(R.drawable.ic_treatments).into(holder.mImageViewTreat);
        if (animal.getCage().getId().equals(-1L)) {
            Glide.with(mContext).load(R.drawable.ic_cage).into(holder.mImageViewPutCage);
            holder.mRelativeAnimal.setBackgroundColor(mContext.getResources().getColor(R.color
                    .red_200));
            holder.mImageViewPutCage.setVisibility(View.VISIBLE);

            holder.mImageViewPutCage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnManageAnimal.onPut(animal);
                }
            });

        }

        holder.mTextViewName.setText(animal.getName());
        holder.mTextViewStatus.setText(animal.getStatus());
        holder.mTextViewPrice.setText("$" + String.format("%.2f", animal.getPrice()));
        holder.mTextViewSatisfaction.setText(String.format("%.2f", animal.getFoodToBeSatisfied()));

        holder.mImageViewSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogUtil.makeAlertDialogExit(mContext, mContext.getString(R.string.sell)
                        , mContext.getString(R.string.message_sell),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mOnManageAnimal.onSell(animal);
                            }
                        }).show();
            }
        });

        holder.mRelativeAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnManageAnimal.onClick(animal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mAnimalList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewPutCage;
        private RelativeLayout mRelativeAnimal;
        private ImageView mImageViewAnimal;
        private TextView mTextViewName;
        private TextView mTextViewStatus;
        private TextView mTextViewSatisfaction;
        private TextView mTextViewPrice;
        private ImageView mImageViewSell;
        private ImageView mImageViewTreat;

        public ViewHolder(View itemView) {
            super(itemView);

            mRelativeAnimal = (RelativeLayout) itemView.findViewById(R.id.relative_animal);
            mImageViewAnimal = (ImageView) itemView.findViewById(R.id.image_view_animal);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_animal);
            mTextViewStatus = (TextView) itemView.findViewById(R.id.text_view_status_animal);
            mTextViewSatisfaction
                    = (TextView) itemView.findViewById(R.id.text_view_satisfaction_animal);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.text_view_price_animal);
            mImageViewSell = (ImageView) itemView.findViewById(R.id.image_view_sell_animal);
            mImageViewTreat = (ImageView) itemView.findViewById(R.id.image_view_treat_animal);
            mImageViewPutCage = (ImageView) itemView.findViewById(R.id.image_view_put_cage);
        }
    }

    public void setAnimalList(List<Animal> animalList) {
        this.mAnimalList = animalList;
        notifyDataSetChanged();
    }

    public void removeAnimal(Animal animal) {
        this.mAnimalList.remove(animal);
        notifyDataSetChanged();
    }
}

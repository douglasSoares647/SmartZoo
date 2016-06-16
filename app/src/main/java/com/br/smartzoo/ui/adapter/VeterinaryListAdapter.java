package com.br.smartzoo.ui.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.SmartZooApplication;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.interfaces.OnManageEmployee;
import com.br.smartzoo.util.AlertDialogUtil;
import com.br.smartzoo.util.DialogUtil;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 26/05/16.
 */
public class VeterinaryListAdapter extends RecyclerView.Adapter<VeterinaryListAdapter.ViewHolder> {

    private OnManageEmployee mOnManageVeterinary;
    private List<Veterinary> mVeterinaryList;
    private Activity mContext;
    private AlertDialog.Builder mAlertDialog;

    public VeterinaryListAdapter(Activity context, List<Veterinary> veterinaries) {
        this.mVeterinaryList = veterinaries;
        this.mContext = context;
    }

    public void addOnManageVeterinary(OnManageEmployee onManageVeterinary) {
        this.mOnManageVeterinary = onManageVeterinary;
    }

    public void setVeterinaryList(List<Veterinary> veterinaryList) {
        this.mVeterinaryList = veterinaryList;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_veterinary_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         Veterinary veterinary = mVeterinaryList.get(position);
        Resources resources = mContext.getResources();

        Glide.with(mContext).load(resources.getIdentifier(veterinary.getImage(), "drawable",
                SmartZooApplication.NAME_PACKAGE)).into(holder.mImageViewVeterinary);
        Glide.with(mContext).load(R.drawable.ic_demit).into(holder.mImageViewDemission);
        Glide.with(mContext).load(R.drawable.ic_salary).into(holder.mImageViewSalary);
        Glide.with(mContext).load(R.drawable.ic_treatments).into(holder.mImageViewAnimalsTreatments);
        holder.mTextViewName.setText(veterinary.getName());
        holder.mTextViewAge.setText(String.valueOf(veterinary.getAge()) + mContext.getString(R.string.txt_years));
        holder.mTextViewSalary.setText(String.valueOf(veterinary.getSalary()) + " $");
        holder.mTextViewStatus.setText(veterinary.getStatus());
        holder.mTextViewNumberTreatments.setText(String.valueOf(veterinary.getNumberAnimalTreated()));

        addListenerToDemission(holder, veterinary);
        addListenerToSalary(holder, veterinary);
        addListenerToClick(holder,veterinary);

    }

    private void addListenerToClick(ViewHolder holder, final Veterinary veterinary) {

        holder.mRelativeVeterinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnManageVeterinary.onClick(veterinary);
            }
        });

    }

    private void addListenerToSalary(ViewHolder holder, final Veterinary veterinary) {


        holder.mImageViewSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = DialogUtil.makeDialogSalary(mContext
                        , mOnManageVeterinary, veterinary);
                dialog.show();

            }
        });
    }

    private void addListenerToDemission(ViewHolder holder, final Veterinary veterinary) {

        final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mOnManageVeterinary.onDemit(veterinary);
            }
        };


        holder.mImageViewDemission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog = AlertDialogUtil.makeAlertDialogExit(mContext,
                        mContext.getString(R.string.title_demission),
                        mContext.getString(R.string.message_sure), listener);

                mAlertDialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mVeterinaryList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mRelativeVeterinary;
        private ImageView mImageViewVeterinary;
        private TextView mTextViewName;
        private TextView mTextViewAge;
        private TextView mTextViewSalary;
        private TextView mTextViewStatus;
        private ImageView mImageViewDemission;
        private ImageView mImageViewSalary;
        private TextView mTextViewNumberTreatments;
        private ImageView mImageViewAnimalsTreatments;


        public ViewHolder(View itemView) {
            super(itemView);
            mRelativeVeterinary = (RelativeLayout) itemView.findViewById(R.id.container_relative_veterinary);
            mImageViewVeterinary = (ImageView) itemView.findViewById(R.id.image_view_veterinary);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_veterinary);
            mTextViewAge = (TextView) itemView.findViewById(R.id.text_view_age_veterinary);
            mTextViewSalary = (TextView) itemView.findViewById(R.id.text_view_salary_veterinary);
            mTextViewStatus = (TextView) itemView.findViewById(R.id.text_view_status_veterinary);
            mImageViewDemission = (ImageView) itemView.findViewById(R.id.image_view_demission);
            mImageViewSalary = (ImageView) itemView.findViewById(R.id.image_view_salary);
            mTextViewNumberTreatments = (TextView) itemView.findViewById(R.id.text_view_number_treatment);
            mImageViewAnimalsTreatments = (ImageView) itemView.findViewById(R.id.image_view_animals_treatments);
        }
    }

    public void removeVeterinary(Employee veterinary){
        this.mVeterinaryList.remove(veterinary);
        notifyDataSetChanged();
    }
}

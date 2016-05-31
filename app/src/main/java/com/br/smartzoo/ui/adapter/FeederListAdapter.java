package com.br.smartzoo.ui.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Feeder;
import com.br.smartzoo.model.entity.Veterinary;
import com.br.smartzoo.model.interfaces.OnManageEmployee;
import com.br.smartzoo.util.AlertDialogUtil;
import com.br.smartzoo.util.DialogUtil;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 29/05/16.
 */
public class FeederListAdapter extends RecyclerView.Adapter<FeederListAdapter.ViewHolder> {

    private List<Feeder> mFeederList;
    private Activity mContext;
    private OnManageEmployee mOnManageEmployee;
    private AlertDialog.Builder mAlertDialog;

    public FeederListAdapter(Activity context, List<Feeder> feeders) {
        this.mContext = context;
        this.mFeederList = feeders;
    }

    public void setFeederList(List<Feeder> feederList){
        this.mFeederList = feederList;
        notifyDataSetChanged();
    }

    public void addOnMaganeEmployee(OnManageEmployee onManageEmployee) {
        this.mOnManageEmployee = onManageEmployee;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_feeder_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Feeder feeder = mFeederList.get(position);

        Glide.with(mContext).load(feeder.getImage()).into(holder.mImageViewFeeder);
        holder.mTextViewName.setText(feeder.getName());
        holder.mTextViewAge.setText(String.valueOf(feeder.getAge()));
        holder.mTextViewStatus.setText(feeder.getStatus());
        holder.mTextViewSalary.setText(String.valueOf(feeder.getSalary()));
        Glide.with(mContext).load(R.drawable.ic_salary).into(holder.mImageViewSalary);
        Glide.with(mContext).load(R.drawable.ic_demit).into(holder.mImageViewDemission);

        addListenerToDemission(holder, feeder);
        addListenerToSalary(holder, feeder);

    }

    private void addListenerToSalary(ViewHolder holder, final Feeder feeder) {


        holder.mImageViewSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = DialogUtil.makeDialogSalary(mContext
                        , mOnManageEmployee, feeder);
                dialog.show();

            }
        });
    }

    private void addListenerToDemission(ViewHolder holder, final Employee employee) {

        final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mOnManageEmployee.onDemit(employee);
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
        return mFeederList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewFeeder;
        private TextView mTextViewName;
        private TextView mTextViewAge;
        private TextView mTextViewStatus;
        private TextView mTextViewSalary;
        private ImageView mImageViewDemission;
        private ImageView mImageViewSalary;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageViewFeeder = (ImageView) itemView.findViewById(R.id.image_view_feeder);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_feeder);
            mTextViewAge = (TextView) itemView.findViewById(R.id.text_view_age_feeder);
            mTextViewStatus = (TextView) itemView.findViewById(R.id.text_view_status_feeder);
            mTextViewSalary = (TextView) itemView.findViewById(R.id.text_view_salary_feeder);
            mImageViewDemission = (ImageView) itemView.findViewById(R.id.image_view_demission);
            mImageViewSalary = (ImageView) itemView.findViewById(R.id.image_view_salary);
        }
    }
}

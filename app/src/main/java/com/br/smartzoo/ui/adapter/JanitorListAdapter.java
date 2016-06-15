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
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.SmartZooApplication;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.interfaces.OnManageEmployee;
import com.br.smartzoo.util.AlertDialogUtil;
import com.br.smartzoo.util.DialogUtil;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 06/06/16.
 */

public class JanitorListAdapter extends RecyclerView.Adapter<JanitorListAdapter.ViewHolder> {
    private Activity mContext;
    private List<Janitor> mJanitorList;
    private OnManageEmployee mOnManageEmployee;
    private AlertDialog.Builder mAlertDialog;


    public JanitorListAdapter(Activity context, List<Janitor> janitors){
        this.mContext = context;
        this.mJanitorList = janitors;
    }

    public void addOnManageEmployee(OnManageEmployee onManageEmployee){
        this.mOnManageEmployee = onManageEmployee;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater
                .from(mContext).inflate(R.layout.item_janitor_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Janitor janitor = mJanitorList.get(position);

        Resources resources = mContext.getResources();

        Glide.with(mContext).load(resources.getIdentifier(janitor.getImage(), "drawable",
                SmartZooApplication.NAME_PACKAGE)).into(holder.mImageViewJanitor);
        Glide.with(mContext).load(R.drawable.ic_demit).into(holder.mImageViewDemission);
        Glide.with(mContext).load(R.drawable.ic_salary).into(holder.mImageViewSalary);
        holder.mTextViewName.setText(janitor.getName());
        String age = String.valueOf(janitor.getAge()) + mContext.getString(R.string.txt_years);
        holder.mTextViewAge.setText(age);
        holder.mTextViewStatus.setText(janitor.getStatus());
        holder.mTextViewSalary.setText(String.valueOf(janitor.getSalary()));

        addListenerToDemission(holder, janitor);
        addListenerToSalary(holder, janitor);

    }


    private void addListenerToSalary(JanitorListAdapter.ViewHolder holder
            , final Janitor janitor) {


        holder.mImageViewSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = DialogUtil.makeDialogSalary(mContext
                        , mOnManageEmployee, janitor);
                dialog.show();

            }
        });
    }

    private void addListenerToDemission(JanitorListAdapter.ViewHolder holder
            , final Employee employee) {

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
        return mJanitorList.size();
    }

    public void setJanitorList(List<Janitor> janitorList) {
        this.mJanitorList = janitorList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewJanitor;
        private TextView mTextViewName;
        private TextView mTextViewAge;
        private TextView mTextViewStatus;
        private TextView mTextViewSalary;
        private ImageView mImageViewDemission;
        private ImageView mImageViewSalary;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageViewJanitor = (ImageView) itemView.findViewById(R.id.image_view_janitor);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_janitor);
            mTextViewAge = (TextView) itemView.findViewById(R.id.text_view_age_janitor);
            mTextViewStatus = (TextView) itemView.findViewById(R.id.text_view_status_janitor);
            mTextViewSalary = (TextView) itemView.findViewById(R.id.text_view_salary_janitor);
            mImageViewDemission = (ImageView) itemView.findViewById(R.id.image_view_demission);
            mImageViewSalary = (ImageView) itemView.findViewById(R.id.image_view_salary);


        }
    }

    public void removeJanitor(Employee janitor){
        this.mJanitorList.remove(janitor);
        notifyDataSetChanged();
    }

}

package com.br.smartzoo.ui.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.SmartZooApplication;
import com.br.smartzoo.model.entity.Employee;
import com.br.smartzoo.model.interfaces.OnHireListener;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by adenilson on 22/05/16.
 */
public class HireEmployeeListAdapter extends
        RecyclerView.Adapter<HireEmployeeListAdapter.ViewHolder> {

    private  Activity mContext;
    private  List<Employee> mEmployeeList;
    private OnHireListener mOnHireListener;

    public HireEmployeeListAdapter(Activity context, List<Employee> employeeList){
        this.mContext = context;
        this.mEmployeeList = employeeList;
    }

    public void addOnHireListener(OnHireListener onHireListener){
        this.mOnHireListener = onHireListener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("onCreateViewHolder", "");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hire_employee_list, parent, false);

        return new ViewHolder(itemView);

    }



    public void onBindViewHolder(ViewHolder holder, int position) {
        final Employee employee = mEmployeeList.get(position);

        Resources resources = mContext.getResources();

        Glide.with(mContext).load(resources.getIdentifier(employee.getImage(), "drawable",
                SmartZooApplication.NAME_PACKAGE)).into(holder.mImageViewIcon);
        holder.mTextViewName.setText(employee.getName());
        holder.mTextViewProfession.setText(employee.getProfession());
        holder.mTextViewPrice.setText("$" + String.valueOf(employee.getSalary()));

        holder.mButtonEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnHireListener.onHire(employee);
            }


        });
    }

    @Override
    public int getItemCount() {
        return mEmployeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewIcon;
        private TextView mTextViewName;
        private TextView mTextViewProfession;
        private TextView mTextViewPrice;
        private Button mButtonEmployee;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageViewIcon = (ImageView) itemView.findViewById(R.id.image_view_employee);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_name_employee);
            mTextViewProfession = (TextView) itemView.findViewById(R.id.text_view_profession);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.text_view_price_employee);
            mButtonEmployee = (Button) itemView.findViewById(R.id.button_hire_employee);
        }
    }


    public List<Employee> getEmployeeList() {
        return mEmployeeList;
    }
}

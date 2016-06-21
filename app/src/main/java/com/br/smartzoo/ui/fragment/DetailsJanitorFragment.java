package com.br.smartzoo.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.smartzoo.R;
import com.br.smartzoo.game.environment.ZooInfo;
import com.br.smartzoo.model.business.ZooInfoBusiness;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.entity.Janitor;
import com.br.smartzoo.model.interfaces.OnCleanCageListener;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.ListCageAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.util.DateUtil;
import com.br.smartzoo.util.RecyclerItemClickListener;

/**
 * Created by Taibic on 6/15/2016.
 */
public class DetailsJanitorFragment extends Fragment {

    private static final int VERTICAL_ITEM_SPACE = 30;
    public static final String SELECTED_JANITOR = "SELECTED_VETERINARY";

    private Janitor selectedJanitor;
    private ProgressBar progressBarCleaningCage;
    private ProgressBar progressBarStamina;
    private TextView textViewCageJanitor;
    private Dialog selectCageDialog;
    private TextView textViewStatusJanitor;
    private TextView textViewProgressBarTask;
    private Button buttonCleanCage;
    private ImageView imageViewCageJanitor;
    private TextView textViewJanitorStamina;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_janitor, container, false);

        initJanitorSelected();
        bindTextViewNameJanitor(view);
        bindTextViewInitJanitor(view);
        bindTextViewSalaryJanitor(view);
        bindTextViewStatusJanitor(view);
        bindTextViewCageJanitor(view);
        bindProgressBarCleaningCageJanitor(view);
        bindProgressBarStaminaJanitor(view);
        bindButtonCleanCageJanitor(view);
        bindImageViewCageVeterinary(view);

        setCurrentState();

        return view;
    }

    private void bindImageViewCageVeterinary(View view) {
        imageViewCageJanitor = (ImageView) view.findViewById(R.id.image_view_cage_janitor);
    }

    private void bindButtonCleanCageJanitor(View view) {

        buttonCleanCage = (Button) view.findViewById(R.id.button_clean);

        buttonCleanCage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectCageDialog();
            }
        });
    }

    private void openSelectCageDialog() {

        selectCageDialog = new Dialog(getContext());
        selectCageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        selectCageDialog.setContentView(R.layout.dialog_dirty_cage);


        final RecyclerView recyclerViewCages = (RecyclerView) selectCageDialog.findViewById(R.id.recycler_view_dirty_cages);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCages.setLayoutManager(layoutManager);
        recyclerViewCages.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        recyclerViewCages.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider_recycler_view));
        recyclerViewCages.setItemViewCacheSize(ZooInfo.cages.size());



        ListCageAdapter listCageAdapter = new ListCageAdapter(ZooInfo.cages, getActivity());
        recyclerViewCages.setAdapter(listCageAdapter);


        recyclerViewCages.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final Cage cage = ((ListCageAdapter) recyclerViewCages.getAdapter()).getCage(position);
                selectCageDialog.dismiss();


                setTaskProgressAttributes(cage);

                selectedJanitor.clear(cage);
                setCurrentState();


            }
        }));

        Button buttonCancel = (Button) selectCageDialog.findViewById(R.id.button_cancel_dialog);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCageDialog.dismiss();
            }
        });

        selectCageDialog.show();

    }

    private void setTaskProgressAttributes(final Cage cage) {
        progressBarCleaningCage.setMax(cage.getDirtyFactor());

        if(selectedJanitor.getCurrentDirtyCleaned()>0) {
            progressBarCleaningCage.setProgress(selectedJanitor.getCurrentDirtyCleaned());
            textViewProgressBarTask.setText(selectedJanitor.getCurrentDirtyCleaned() + "/" + selectedJanitor.getCurrentCage().getDirtyFactor());
        }

        selectedJanitor.addOnCleanCageListener(new OnCleanCageListener() {
            @Override
            public void onCleanDirty(Integer currentDirtyCleaned) {
                progressBarCleaningCage.setProgress(currentDirtyCleaned);
                textViewProgressBarTask.setText(currentDirtyCleaned + "/" + cage.getDirtyFactor());
            }

            @Override
            public void onCleanFinish() {


                buttonCleanCage.setAlpha(1f);
                buttonCleanCage.setEnabled(true);
                buttonCleanCage.setText(getString(R.string.button_clean));

                setCurrentState();

                updateZooInfo(cage);
            }

            @Override
            public void onStatusChange() {
                textViewStatusJanitor.setText(selectedJanitor.getStatus());
            }

            @Override
            public void onStaminaChange() {
                progressBarStamina.setProgress(selectedJanitor.getStamina());
                textViewJanitorStamina.setText(selectedJanitor.getStamina() + " / " + Janitor.maxStamina);
            }
        });
    }

    private void updateZooInfo(Cage cage) {
        ZooInfoBusiness.updateCageAfterClean(cage);
    }

    private void bindProgressBarStaminaJanitor(View view) {

        progressBarStamina = (ProgressBar) view.findViewById(R.id.progress_bar_stamina_janitor);
        progressBarStamina.setMax(Janitor.maxStamina);
        progressBarStamina.setProgress(selectedJanitor.getStamina());

        textViewJanitorStamina = (TextView) view.findViewById(R.id.text_view_progress_bar_stamina_janitor);
        textViewJanitorStamina.setText(selectedJanitor.getStamina() + " / " + Janitor.maxStamina);
    }

    private void bindProgressBarCleaningCageJanitor(View view) {
        progressBarCleaningCage = (ProgressBar) view.findViewById(R.id.progress_bar_task_janitor);

        textViewProgressBarTask = (TextView) view.findViewById(R.id.text_view_progress_bar_task_janitor);
    }

    private void bindTextViewCageJanitor(View view) {

        textViewCageJanitor = (TextView) view.findViewById(R.id.text_view_name_cage_janitor);

    }

    private void bindTextViewStatusJanitor(View view) {

        textViewStatusJanitor = (TextView) view.findViewById(R.id.text_view_status_janitor);
        textViewStatusJanitor.setText(selectedJanitor.getStatus());
    }

    private void bindTextViewSalaryJanitor(View view) {
        TextView textViewSalaryJanitor = (TextView) view.findViewById(R.id.text_view_salary_janitor);
        textViewSalaryJanitor.setText(String.format("%.2f", selectedJanitor.getSalary()));
    }

    private void bindTextViewInitJanitor(View view) {
        TextView textViewInitJanitor = (TextView) view.findViewById(R.id.text_view_init_janitor);
        textViewInitJanitor.setText(DateUtil.dateToString(selectedJanitor.getStartDate()));
    }

    private void bindTextViewNameJanitor(View view) {

        TextView textViewNameJanitor = (TextView) view.findViewById(R.id.text_view_name_janitor);

        textViewNameJanitor.setText(selectedJanitor.getName());
    }


    private void initJanitorSelected() {
        Bundle values = getArguments();
        Parcelable parcelable = values.getParcelable(SELECTED_JANITOR);
        selectedJanitor = parcelable !=null ? (Janitor) parcelable : new Janitor();
    }


    private void setCurrentState() {
        if(selectedJanitor.getCleaning()) {

            setTaskProgressAttributes(selectedJanitor.getCurrentCage());
            textViewCageJanitor.setText(selectedJanitor.getCurrentCage().getName());
            imageViewCageJanitor.setVisibility(View.VISIBLE);


            buttonCleanCage.setAlpha(0.5f);
            buttonCleanCage.setEnabled(false);
            buttonCleanCage.setText("Already cleaning a cage");

        }
        else{
            imageViewCageJanitor.setVisibility(View.INVISIBLE);
            textViewCageJanitor.setText("");
            progressBarCleaningCage.setProgress(0);
            textViewProgressBarTask.setText("");

        }

    }

}

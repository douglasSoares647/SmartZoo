package com.br.smartzoo.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.br.smartzoo.R;
import com.br.smartzoo.model.environment.Clock;
import com.br.smartzoo.model.interfaces.OnCleanCageListener;
import com.br.smartzoo.util.ApplicationUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adenilson on 18/04/16.
 */
public class Janitor extends Employee implements Parcelable {
    private Integer maxStamina=50;
    private int clock = 0;

    private Long id;
    private String status;
    private HashMap<Integer, Integer> cagesCleanedThisMonth;


    private Integer currentDirtyCleaned = 0;
    private Integer timeToCleanCage = 0;
    private Boolean isCleaning = false;
    private Cage currentCage;
    private OnCleanCageListener mOnCleanCageListener;

    public Janitor(List<Cage> cages, int expedient) {
    }

    public Janitor(String image, String name, Integer age, Date startDate, Date endDate, Double salary
            , String profession,String status) {
        super(image, name, age, startDate, endDate, salary, profession, status);
    }

    public Janitor() {
    }




    @Override
    public Double calculateSalary() {
        if (cagesCleanedThisMonth.isEmpty()) {
            return super.getSalary();
        } else {
            int sum = 0;
            for (Map.Entry<Integer, Integer> entry : cagesCleanedThisMonth.entrySet()) {
                Integer cageId = entry.getKey();
                Integer quantity = entry.getValue();
                sum += quantity;
            }

            return super.getSalary() * +10 * sum;
        }
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void clear(final Cage cage) {

        if(getStamina()>cage.getDirtyFactor()) {
            clock = 0;
            timeToCleanCage = cage.getDirtyFactor() * Clock.timeToCleanEachDirty;

            status = ApplicationUtil.applicationContext.getString(R.string.cleaning_cage) + cage.getName();
            mOnCleanCageListener.onStatusChange();

            currentCage = cage;

            isCleaning = true;
        }

    }

    @Override
    public void onTick() {
        clock++;


        if(isCleaning) {
            if (clock <= timeToCleanCage) {
                if (clock % Clock.timeToCleanEachDirty == 0) {
                    currentDirtyCleaned++;
                    mOnCleanCageListener.onCleanDirty(currentDirtyCleaned);
                    setStamina(getStamina() - 1);
                }
            }
            else {
                currentCage.setClean(true);
                currentCage.setDirtyFactor(0);
                clock = 0;
                isCleaning = false;
                currentDirtyCleaned = 0;
                mOnCleanCageListener.onCleanFinish();
            }
        }

        else {
            status = ApplicationUtil.applicationContext.getString(R.string.ready);

            if(mOnCleanCageListener!=null)
            mOnCleanCageListener.onStatusChange();
        }

        if(clock== Clock.timeToRest){
            if(getStamina()<maxStamina)
            setStamina(getStamina()+1);
            clock =0;
        }

    }


    public void addOnCleanCageListener(OnCleanCageListener onCleanCageListener){
        this.mOnCleanCageListener = onCleanCageListener;
    }



    protected Janitor(Parcel in) {
        id = in.readLong();
        status = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(status);
    }

    public static final Creator<Janitor> CREATOR = new Creator<Janitor>() {
        @Override
        public Janitor createFromParcel(Parcel in) {
            return new Janitor(in);
        }

        @Override
        public Janitor[] newArray(int size) {
            return new Janitor[size];
        }
    };
}

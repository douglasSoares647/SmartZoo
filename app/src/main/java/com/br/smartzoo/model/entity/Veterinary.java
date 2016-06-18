package com.br.smartzoo.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.br.smartzoo.R;
import com.br.smartzoo.game.environment.Clock;
import com.br.smartzoo.model.interfaces.OnTreatAnimalListener;
import com.br.smartzoo.util.ApplicationUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adenilson on 18/04/16.
 */
public class Veterinary extends Employee implements Parcelable {
    public static final Integer maxStamina = 20;

    private int clock = 0;

    private OnTreatAnimalListener mOnTreatAnimalListener;
    private HashMap<Integer, Integer> animalsTreatedThisMonth;

    private Animal currentAnimal;
    private Boolean isTreating = false;


    public Veterinary() {

    }

    public Veterinary(List<Animal> animals) {
        setStatus(ApplicationUtil.applicationContext.getString(R.string.status_ready));
    }

    public Veterinary(String image, String name, Integer age, Date startDate, Date endDate
            , Double salary, String profession, String status) {
        super(image, name, age, startDate, endDate, salary, profession, status);
    }


    protected Veterinary(Parcel in) {
        super(in);
        clock = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(clock);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Veterinary> CREATOR = new Creator<Veterinary>() {
        @Override
        public Veterinary createFromParcel(Parcel in) {
            return new Veterinary(in);
        }

        @Override
        public Veterinary[] newArray(int size) {
            return new Veterinary[size];
        }
    };

    public void treat(final Animal animal) {

        if (getStamina() > animal.getStaminaToBeCured()) {
            setStatus(ApplicationUtil.applicationContext.getString(R.string.treating_animal) + animal.getName());

            if (mOnTreatAnimalListener != null)
                mOnTreatAnimalListener.onStatusChange();

            clock = 0;

            currentAnimal = animal;
            isTreating = true;
        }

    }


    @Override
    public Double calculateSalary() {
        if (animalsTreatedThisMonth.isEmpty()) {
            return super.getSalary();
        } else {
            int sum = 0;
            for (Map.Entry<Integer, Integer> entry : animalsTreatedThisMonth.entrySet()) {
                Integer animalId = entry.getKey();
                Integer quantity = entry.getValue();
                sum += quantity;
            }

            return super.getSalary() * +20 * sum;
        }
    }


    @Override
    public void onTick() {
        clock++;

        if (isTreating) {
            if (clock == Clock.timeToTreat) {
                currentAnimal.setIsHealthy(true);
                setStatus(ApplicationUtil.applicationContext.getString(R.string.animal_treatment) + currentAnimal.getName() + ApplicationUtil.applicationContext.getString(R.string.done));
                setStamina(getStamina() - currentAnimal.getStaminaToBeCured());
                currentAnimal = null;
                isTreating = false;

                if (mOnTreatAnimalListener != null) {
                    mOnTreatAnimalListener.onStatusChange();
                    mOnTreatAnimalListener.onTreatFinish();
                }
            } else {
                if (mOnTreatAnimalListener != null)
                    mOnTreatAnimalListener.onTreatProgress(clock);
            }
        } else {
            setStatus(ApplicationUtil.applicationContext.getString(R.string.status_ready));
            if (mOnTreatAnimalListener != null)
                mOnTreatAnimalListener.onStatusChange();

            if (clock == Clock.timeToRest) {
                if (getStamina() < maxStamina) {
                    setStamina(getStamina() + 1);
                    if (mOnTreatAnimalListener != null)
                        mOnTreatAnimalListener.onStaminaChange();
                }
                clock = 0;


            }

        }

    }

    public Integer getNumberAnimalTreated() {
        return animalsTreatedThisMonth != null ? animalsTreatedThisMonth.size() : 0;
    }

    public void addOnTreatAnimalListener(OnTreatAnimalListener onTreatAnimalListener) {
        this.mOnTreatAnimalListener = onTreatAnimalListener;
    }

    public Boolean getTreating() {
        return isTreating;
    }

    public Animal getCurrentAnimal() {
        return currentAnimal;
    }

    public void setTreating(Boolean treating) {
        isTreating = treating;
    }

    public void setCurrentAnimal(Animal currentAnimal) {
        this.currentAnimal = currentAnimal;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }
}

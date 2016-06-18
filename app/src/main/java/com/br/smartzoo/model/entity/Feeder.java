package com.br.smartzoo.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.br.smartzoo.game.environment.Clock;
import com.br.smartzoo.model.interfaces.Manageable;
import com.br.smartzoo.model.singleton.Stock;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by adenilson on 18/04/16.
 */
public class Feeder extends Employee implements Manageable, Parcelable {
    public static final int maxStamina = 50;

    private HashMap<Integer, Integer> cagesFeededThisMonth;
    private Stock stock;
    private int clock = 0;

    public Feeder() {
        cagesFeededThisMonth = new HashMap<Integer, Integer>();
        stock = Stock.getInstance();
    }


    public Feeder(String image, String name, Integer age, Date startDate, Date endDate
            , Double salary, String profession, String status) {
        super(image, name, age, startDate, endDate, salary, profession, status);
        stock = Stock.getInstance();
    }


    protected Feeder(Parcel in) {
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

    public static final Creator<Feeder> CREATOR = new Creator<Feeder>() {
        @Override
        public Feeder createFromParcel(Parcel in) {
            return new Feeder(in);
        }

        @Override
        public Feeder[] newArray(int size) {
            return new Feeder[size];
        }
    };

    @Override
    public Double calculateSalary() {
        if (cagesFeededThisMonth.isEmpty()) {
            return super.getSalary();
        } else {
            int sum = 0;
            for (Map.Entry<Integer, Integer> entry : cagesFeededThisMonth.entrySet()) {
                Integer cageId = entry.getKey();
                Integer quantity = entry.getValue();
                sum += quantity;
            }

            return super.getSalary() * +10 * sum;
        }

    }


    @Override
    public void feedCage(String foodName, Cage cage) {
        List<Food> foodsFromStock = stock.takeFoods(foodName, cage);

        Double foodWeight = 0D;

        for (Food food : foodsFromStock) {
            foodWeight += food.getWeight();
        }
        int stamina = (int) (foodWeight / 5);

        if (getStamina() > stamina) {
            cage.getFoods().addAll(foodsFromStock);
            cage.setIsSupplied(true);
        } else {
            stock.putFoods(foodsFromStock);
        }

        for (Animal animal : cage.getAnimals()) {
            animal.eat();
        }


    }

    @Override
    public void toRetain(Cage cage) {
        if (!cage.getFoods().isEmpty()) {
            stock.putFoods(cage.getFoods());
            cage.getFoods().clear();
        }
    }


    public HashMap<Integer, Integer> getCagesFeededThisMonth() {
        return cagesFeededThisMonth;
    }

    public void setCagesFeededThisMonth(HashMap<Integer, Integer> cagesFeededThisMonth) {
        this.cagesFeededThisMonth = cagesFeededThisMonth;
    }

    public Stock getStock() {
        return stock;
    }


    public void setStock(Stock stock) {
        this.stock = stock;
    }


    @Override
    public void onTick() {
        clock++;

        if (clock == Clock.timeToRest) {
            if (getStamina() < maxStamina)
                setStamina(getStamina() + 1);
            clock = 0;
        }
    }

}

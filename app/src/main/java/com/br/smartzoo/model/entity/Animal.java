package com.br.smartzoo.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.br.smartzoo.R;
import com.br.smartzoo.game.environment.Clock;
import com.br.smartzoo.model.business.NewsFeedBusiness;
import com.br.smartzoo.model.interfaces.Observer;
import com.br.smartzoo.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by taibic on 12/04/16.
 */
public class Animal implements Observer, Parcelable {

    private Integer maxResistance = 8;


    private int biologicalClock = 0;

    private Long id;
    private String image;
    private String name;
    private String type;
    private Integer age;
    private Double weight;
    private String sex;
    private Cage cage;
    private Integer resistance;
    private Integer popularity;
    private Double price;
    private boolean isHealthy;
    private boolean isHungry;
    private String status;
    private Integer staminaToBeCured;
    private String favoriteFood;


    //Variaveis de controle
    private boolean isDigesting = false;
    private Double foodEaten = 0.0;
    private Double foodToBeSatisfied;


    public Animal(String image, String type, Integer age, Double price, Double weight
            , Cage cage, Integer resistance, boolean isHealthy, String favoriteFood
            , Integer popularity) {
        this.type = type;
        this.favoriteFood = favoriteFood;
        this.image = image;
        this.age = age;
        this.weight = weight;
        this.price = price;
        this.cage = cage;
        this.cage.getAnimals().add(this);
        this.isHealthy = isHealthy;
        this.resistance = resistance;
        this.foodToBeSatisfied = weight * 0.15;
        this.popularity = popularity;
        foodToBeSatisfied = weight * 0.15;
    }

    public Animal() {

    }

    public int getBiologicalClock() {
        return biologicalClock;
    }

    public void setBiologicalClock(int biologicalClock) {
        this.biologicalClock = biologicalClock;
    }

    public boolean isDigesting() {
        return isDigesting;
    }

    public void setDigesting(boolean digesting) {
        isDigesting = digesting;
    }

    public Double getFoodEaten() {
        return foodEaten;
    }

    public void setFoodEaten(Double foodEaten) {
        this.foodEaten = foodEaten;
    }

    public String getType() {
        return type;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public Cage getCage() {
        return cage;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
        this.cage.getAnimals().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
        foodToBeSatisfied = weight * 0.15;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setIsHealthy(boolean isHealthy) {
        this.isHealthy = isHealthy;
    }


    public Double getFoodToBeSatisfied() {
        return foodToBeSatisfied;
    }

    public void setFoodToBeSatisfied(Double foodToBeSatisfied) {
        this.foodToBeSatisfied = foodToBeSatisfied;
    }

    public Integer getResistance() {
        return resistance;
    }

    public void setResistance(Integer resistance) {
        this.resistance = resistance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public Double getPrice() {
        return price;
    }

    public String getSpecie() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(Double price) {
        //Calculate the price based on animal age and health status. If the animal is a newborn,
        // the price is equal the default price offered by the enum
        price -= (price * age * 0.02);
        if (!isHealthy) {
            price = price * 0.5;
        }

        this.price = price;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public void eat() {
        final List<Food> cageFoods = cage.getFoods();
        List<Food> foodsToRemove = new ArrayList<>();

        if (cageFoods.isEmpty()) {
            status = ApplicationUtil.applicationContext.getString(R.string.starving);
        } else {
            status = ApplicationUtil.applicationContext.getString(R.string.eating);
            //SE O ANIMAL COMEÇAR A COMER O FOOD E FICAR SATISFEITO ENQUANTO ESTIVER COMENDO, ELE
            // COME O FOOD ATÉ O FIM
            for (Food food : cageFoods) {
                if (foodEaten < foodToBeSatisfied) {
                    foodEaten += food.getWeight();
                    foodsToRemove.add(food);


                    //Checa validade da comida e se estiver estragada o animal tem chance de
                    // ficar doente
                    Calendar expirationDate = Calendar.getInstance();
                    expirationDate.setTime(food.getExpirationDate());
                    Calendar currentDate = Calendar.getInstance();
                    currentDate.set(Calendar.MONTH, currentDate.get(Calendar.MONTH) + 1);

                    if (currentDate.after(expirationDate)) {
                        Random random = new Random();
                        int chanceToGetSick = random.nextInt(maxResistance) + 1; // 1 a 8
                        if (this.isHealthy) {
                            if (chanceToGetSick > resistance) {
                                isHealthy = false;
                                NewsFeedBusiness.addNew(New.TagEnum.ANIMAL_SICK.getTag(), this);
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            cageFoods.removeAll(foodsToRemove);

            biologicalClock = 0;
            weight = weight + foodEaten;
            status = ApplicationUtil.applicationContext.getString(R.string.digesting);

            isDigesting = true;
        }

    }


    public Integer getStaminaToBeCured() {
        return staminaToBeCured == null ? 0 : staminaToBeCured;
    }

    public void setStaminaToBeCured(Integer staminaToBeCured) {
        this.staminaToBeCured = staminaToBeCured;
    }

    @Override
    public void onTick() {
        biologicalClock++;


        if (isDigesting) {
            digest();
        } else if (biologicalClock == Clock.timeToFeelHungry) {
            setHungry(true);
        }

    }

    private void digest() {
        //Digerindo
        if (biologicalClock % Clock.digestingInterval == 0) {
            setWeight(weight - foodEaten * 0.95);
            afterDigest();
        } else
            weight -= foodEaten / 30;
    }

    private void afterDigest() {
        cage.setDirtyFactor(cage.getDirtyFactor() + 1);
        status = ApplicationUtil.applicationContext.getString(R.string.not_hungry);

        isDigesting = false;
        foodEaten = 0.0;


    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;

        if (hungry)
            eat();
    }


    public enum AnimalEnum {

        Lion(R.drawable.ic_animal_lion, R.string.animal_lion, 300.0, 3, 150, "Fine"),
        // Jaguar(R.string.animal_jaguar, 200.0, 2, 100),
        //Leopard(R.string.animal_leopard, 400.0, 4, 200),
        Tiger(R.drawable.ic_animal_tiger, R.string.animal_tiger, 500.0, 5, 250, "Fine"),
        //Boar(R.string.animal_boar, 200.0, 2, 100),
        //Macaw(R.string.animal_macaw, 400.0, 4, 200),
        //Snake(R.drawable.ic_snake_icon,R.string.animal_snake, 100.0, 1, 50),
        Turtle(R.drawable.ic_turtle, R.string.animal_turtle, 100.0, 1, 50, "Fine"),
        Goose(R.drawable.ic_animal_goose, R.string.animal_goose, 200.0, 2, 100, "Fine"),
        Monkey(R.drawable.ic_animal_monkey, R.string.animal_monkey, 600.0, 6, 300, "Fine"),
        Giraffe(R.drawable.ic_giraffe, R.string.animal_giraffe, 700.0, 7, 350, "Fine"),
        Elephant(R.drawable.ic_animal_elephant, R.string.animal_elephant, 700.0, 7, 350, "Fine"),
        // Spider(R.string.animal_spider, 300.0, 3, 150),
        //Parrot(R.string.animal_parrot, 200.0, 2, 100),
        //Toucan(R.string.animal_toucan, 200.0, 2, 100),
        Bear(R.drawable.ic_animal_bear, R.string.animal_bear, 400.0, 4, 200, "Fine");

        AnimalEnum(int image, int type, Double price, int popularity, int reputationToUnlock
                , String status) {
            this.type = type;
            this.image = image;
            this.price = price;
            this.popularity = popularity;
            this.reputationToUnlock = reputationToUnlock;
            this.status = status;
        }

        private String status;
        private int image;
        private int type;
        private Double price;
        private int popularity;
        private int reputationToUnlock;

        public Double getPrice() {
            return price;
        }

        public int getPopularity() {
            return popularity;
        }

        public int getType() {
            return this.type;
        }

        public int getImage() {
            return image;
        }

        public int getReputationToUnlock() {
            return reputationToUnlock;
        }

        public String getStatus() {
            return status;
        }


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.biologicalClock);
        dest.writeString(this.image);
        dest.writeString(this.status);
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeValue(this.age);
        dest.writeValue(this.weight);
        dest.writeString(this.sex);
        dest.writeParcelable(this.cage, flags);
        dest.writeByte(this.isHealthy ? (byte) 1 : (byte) 0);
        dest.writeValue(this.foodToBeSatisfied);
        dest.writeValue(this.resistance);
        dest.writeValue(this.popularity);
        dest.writeValue(this.price);
        dest.writeValue(this.staminaToBeCured);
    }

    protected Animal(Parcel in) {
        this.biologicalClock = in.readInt();
        this.image = in.readString();
        this.status = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.type = in.readString();
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
        this.weight = (Double) in.readValue(Double.class.getClassLoader());
        this.sex = in.readString();
        this.cage = in.readParcelable(Cage.class.getClassLoader());
        this.isHealthy = in.readByte() != 0;
        this.foodToBeSatisfied = (Double) in.readValue(Double.class.getClassLoader());
        this.resistance = (Integer) in.readValue(Integer.class.getClassLoader());
        this.popularity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.price = (Double) in.readValue(Double.class.getClassLoader());
        this.staminaToBeCured = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel source) {
            return new Animal(source);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };
}

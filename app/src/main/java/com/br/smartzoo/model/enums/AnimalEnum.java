package com.br.smartzoo.model.enums;

import android.app.Application;
import android.content.Context;

import com.br.smartzoo.R;
import com.br.smartzoo.util.ApplicationUtil;

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

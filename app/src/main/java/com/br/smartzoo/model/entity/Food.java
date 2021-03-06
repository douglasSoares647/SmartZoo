package com.br.smartzoo.model.entity;

import com.br.smartzoo.R;

import java.util.Date;

/**
 * Created by taibic on 12/04/16.
 */
public class Food {

    private Long id;
    private String image;
    private Double price;
    private String name;
    private Double weight;
    private Date expirationDate;

    public Food() {
    }

    public Food(String image, String name, Double price, Double weight, Date expirationDate) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public enum FoodEnum {
        ;

        public enum Meat {

            Beef(R.drawable.ic_beef, R.string.food_beef, 10.0, 1D),
            Chicken(R.drawable.ic_chicken, R.string.food_chicken, 8.0, 1D),
            Pork(R.drawable.ic_pork, R.string.food_pork, 9.75, 1D);

            private int image;
            private Double price;
            private int name;
            private Double weight;

            Meat(int ic, int meat, Double price, Double weight) {
                this.image = ic;
                this.name = meat;
                this.price = price;
                this.weight = weight;
            }


            public Double getWeight() {
                return weight;
            }

            public int getName() {
                return name;
            }

            public Double getPrice() {
                return price;
            }

            public int getImage() {
                return image;
            }
        }

        public enum Fruit {

            Apple(R.drawable.ic_apple, R.string.food_apple, 5.5, 1D),
            Grape(R.drawable.ic_grape, R.string.food_grape, 5.0, 1D),
            Banana(R.drawable.ic_banana, R.string.food_banana, 6.0, 1D);

            Fruit(int ic, int fruit, Double price, Double weight) {
                this.image = ic;
                this.name = fruit;
                this.price = price;
                this.weight = weight;


            }

            private int image;
            private int name;
            private Double price;
            private Double weight;

            public int getName() {
                return name;
            }

            public Double getPrice() {
                return price;
            }

            public int getImage() {
                return image;
            }

            public Double getWeight() {
                return weight;
            }
        }


    }

}

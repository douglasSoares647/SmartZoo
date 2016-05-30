package com.br.smartzoo.model.enums;

import com.br.smartzoo.R;

/**
 * Created by taibic on 19/04/16.
 */
public enum FoodEnum {
    ;

    public enum Meat {

        Beef(R.drawable.ic_beef,  R.string.food_beef, 10.0, 1D),
        Chicken(R.drawable.ic_chicken, R.string.food_chicken, 8.0,1D),
        Pork(R.drawable.ic_pork,R.string.food_pork, 9.75,1D);

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

        public int getImage() { return image; }
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

        public int getImage() { return image; }

        public Double getWeight() {
            return weight;
        }
    }


}

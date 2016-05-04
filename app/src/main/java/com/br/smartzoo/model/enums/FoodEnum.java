package com.br.smartzoo.model.enums;

/**
 * Created by taibic on 19/04/16.
 */
public enum FoodEnum {;

    public enum Meat {

        Beef("Beef", 10.0),
        Chicken("Chicken",8.0),
        Pork("Pork",9.0);

        Meat(String meat, Double price){
        this.value = meat;
        }

        private Double price;
        private String value;


        public String getName() {
            return value;
        }
        public Double getPrice(){
            return price;
        }

    }

    public enum Fruit {

        Apple("Apple", 5.0),
        Grappe("Grappe", 5.0),
        Banana("Banana", 6.0);

        Fruit (String fruit, Double price){
            this.value = fruit;
            this.price = price;

        }

        private String value;
        private Double price;

        public String getName() {
            return value;
        }
        public Double getPrice(){
            return price;
        }


    }



}

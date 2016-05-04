package com.br.smartzoo.model.enums;

/**
 * Created by taibic on 19/04/16.
 */
public enum FoodEnum {;

    public enum Meat {

        Beef("Beef"),
        Chicken("Chicken"),
        Pork("Pork");

        Meat(String meat){
        this.value = meat;
        }

        private String value;


        public String getValue() {
            return value;
        }

    }

    public enum Fruit {

        Apple("Apple"),
        Grappe("Grappe"),
        Banana("Banana");

        Fruit (String fruit){
            this.value = fruit;

        }

        private String value;


        public String getValue() {
            return value;
        }


    }



}

package com.br.smartzoo.model.enums;

/**
 * Created by Taibic on 5/15/2016.
 */
public enum CageEnum {

    Cage1("Jaula para 1 animal",100.0,1 ),
    Cage2("Jaula para 2 animais",200.0,2 ),
    Cage3("Jaula para 3 animais",300.0,3 ),
    Cage4("Jaula para 4 animais",400.0,4 ),
    Cage5("Jaula para 5 animais",500.0,5 ),
    Cage6("Jaula para 6 animais",600.0,6 ),
    Cage7("Jaula para 7 animais",700.0,7 ),
    Cage8("Jaula para 8 animais",800.0,8 ),
    Cage9("Jaula para 9 animais", 900.0,9),
    Cage10("Jaula para 10 animais",1000.0, 10 )
    ;


    private String name;
    private Double price;
    private Integer capacity;


    CageEnum(String name, Double price, Integer capacity) {
        this.name = name;
        this.price = price;
        this.capacity = capacity;
    }


    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCapacity() {
        return capacity;
    }
}

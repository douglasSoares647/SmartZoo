package com.br.smartzoo.model.enums;

import com.br.smartzoo.R;

/**
 * Created by adenilson on 22/05/16.
 */
public enum EmployeeEnum {

    Veterinary(R.drawable.ic_veterinary, 2400D, R.string.name_veterinary),
    Janitor(R.drawable.ic_janitor, 1000D,R.string.name_janitor),
    Feeder(R.drawable.ic_feeder, 1600D, R.string.name_feeder);

    private  int profession;
    private int image;
    private Double price;

    EmployeeEnum(int image, Double price, int profession) {
        this.image = image;
        this.price = price;
        this.profession = profession;
    }

    public int getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

    public int getProfession() {
        return profession;
    }
}

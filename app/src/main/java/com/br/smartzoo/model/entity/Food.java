package com.br.smartzoo.model.entity;

import java.util.Date;

/**
 * Created by taibic on 12/04/16.
 */
public class Food {

       private String name;
       private Double weight;
       private Date expirationDate;

    public Food() {
    }

    public Food(String name, Double weight, Date expirationDate) {
        this.name = name;
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

 
}

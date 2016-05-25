package com.br.smartzoo.model.entity;

import java.util.Date;

/**
 * Created by taibic on 12/04/16.
 */
public class Food {

    private Long id;
    private int image;
    private Double price;
    private String name;
    private Double weight;
    private Date expirationDate;

    public Food() {
    }

    public Food(int image, String name, Double price, Double weight, Date expirationDate) {
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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
}

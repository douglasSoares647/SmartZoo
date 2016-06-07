package com.br.smartzoo.model.entity;

import java.util.Date;

/**
 * Created by adenilson on 02/06/16.
 */
public class New {

    private Long id;
    private Integer imageType;
    private Integer imageSecondary;
    private String title;
    private String message;
    private String imageDescription;
    private Date date;
    private String tag;


    public New(Long id, Integer imageType,String imageDescription, Integer imageSecondary, String title, String message, Date date, String tag) {
        this.id = id;
        this.imageType = imageType;
        this.imageSecondary = imageSecondary;
        this.title = title;
        this.imageDescription = imageDescription;
        this.message = message;
        this.date = date;
        this.tag = tag;
    }



    public New() {
    }


    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public Integer getImageSecondary() {
        return imageSecondary;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public void setImageSecondary(Integer imageSecondary) {
        this.imageSecondary = imageSecondary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public enum TagEnum {
        VISITOR_ARRIVING("VisitorArriving"),
        VISITOR_LEAVING("VisitorLeaving"),
        ANIMAL_SICK("AnimalSick"),
        STOCK_RAN_OUT_OF_FOOD("StockRanOutOfFood"),
        FOOD_ROTTEN("FoodRotten");

        private String tag;

        TagEnum(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }

    }
}

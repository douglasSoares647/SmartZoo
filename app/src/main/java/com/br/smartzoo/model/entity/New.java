package com.br.smartzoo.model.entity;

import java.util.Date;

/**
 * Created by adenilson on 02/06/16.
 */
public class New {

    private Long id;
    private int imageType;
    private int imageSecondary;
    private String title;
    private String message;
    private String imageDescription;
    private Date date;
    private String tag;


    public New(Long id, int imageType,String imageDescription, int imageSecondary, String title, String message, Date date, String tag) {
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


    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public int getImageSecondary() {
        return imageSecondary;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public void setImageSecondary(int imageSecondary) {
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

    private enum TypeNew {
        VISITOR("Visitor"),
        ANIMAL("Animal"),
        VETERINARY("Veterinary"),
        JANITOR("Janitor"),
        FEEDER("Feeder"),
        STOCK("Stock"),
        FOOD("Food");

        private String tag;

        TypeNew(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }

    }
}

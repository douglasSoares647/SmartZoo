package com.br.smartzoo.util;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.entity.New;
import com.br.smartzoo.model.environment.Visitor;

import java.util.Date;

/**
 * Created by Douglas on 6/6/2016.
 */
public class NewsUtil {



    public static void createNews(String title, String message, int imageType, int imageSecondary,
                                  String imageDescription, Date date, String tag){
        New singleNew = new New();

        singleNew.setTitle(title);
        singleNew.setMessage(message);
        singleNew.setImageType(imageType);
        singleNew.setImageSecondary(imageSecondary);
        singleNew.setImageDescription(imageDescription);
        singleNew.setDate(date);
        singleNew.setTag(tag);
    }


    public static New createVisitorArrivingNews(Visitor object) {

        return null;
    }

    public static New createVisitorLeavingNews(Visitor object) {

        return null;
    }

    public static New createAnimalSickNews(Animal object) {

        return null;
    }

    public static New createStockOutOfFoodNews() {

        return null;
    }

    public static New createRottenFoodNews(Food object) {

        return null;
    }
}

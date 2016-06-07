package com.br.smartzoo.util;

import android.support.annotation.Nullable;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.entity.New;
import com.br.smartzoo.model.environment.Visitor;

import java.util.Date;

/**
 * Created by Douglas on 6/6/2016.
 */
public class NewsUtil {


    @Nullable
    private static New createNews(String title, String message, Integer imageType, Integer imageSecondary,
                                  String imageDescription, Date date, String tag){
        New singleNew = new New();

        singleNew.setTitle(title);
        singleNew.setMessage(message);
        singleNew.setImageType(imageType);

        if(imageSecondary!=null)
        singleNew.setImageSecondary(imageSecondary);
        if(imageDescription!=null)
        singleNew.setImageDescription(imageDescription);

        singleNew.setDate(date);
        singleNew.setTag(tag);

        return singleNew;
    }


    public static New createVisitorArrivingNews(Visitor object) {
        String title = ApplicationUtil.getContext().getString(R.string.title_new_visitor_arrived);
        String message = ApplicationUtil.getContext().getString(R.string.msg_visitor_arriving_part1) + object.getName() + ApplicationUtil.getContext().getString(R.string.msg_visitor_arriving_part2);
        int imageType = R.drawable.ic_visitors;

        Date date = new Date();

        String tag = New.TagEnum.VISITOR_ARRIVING.getTag();
        return createNews(title,message,imageType,null,null,date,tag);
    }

    public static New createVisitorLeavingNews(Visitor object) {

        String title = ApplicationUtil.getContext().getString(R.string.title_visitor_leaving);
        String message = ApplicationUtil.getContext().getString(R.string.msg_visitor_leaving_part1) + object.getName() + ApplicationUtil.getContext().getString(R.string.msg_visitor_leaving_part2);
        int imageType = R.drawable.ic_visitors;
        Integer imageSecondary;
        String imageDescription;

        if(object.getReputationGenerated()>0) {
            imageSecondary = R.drawable.ic_increase;
            imageDescription = "+" + object.getReputationGenerated() + ApplicationUtil.getContext().getString(R.string.msg_news_reputation);
        }
        else if(object.getReputationGenerated()<0) {
            imageSecondary = R.drawable.ic_decrease;
            imageDescription = "-" + object.getReputationGenerated() + ApplicationUtil.getContext().getString(R.string.msg_news_reputation);
        }
        else{
            imageSecondary = null;
            imageDescription = null;
        }

        Date date = new Date();
        String tag = New.TagEnum.VISITOR_LEAVING.getTag();

        return createNews(title,message,imageType,imageSecondary,imageDescription,date,tag);
    }

    public static New createAnimalSickNews(Animal object) {
        String title = ApplicationUtil.getContext().getString(R.string.title_animal_sick);
        String message = ApplicationUtil.getContext().getString(R.string.msg_animal_sick_part1) + object.getName() + ApplicationUtil.getContext().getString(R.string.msg_animal_sick_part2);
        int imageType = object.getImage();

        Date date = new Date();

        String tag = New.TagEnum.ANIMAL_SICK.getTag();


        return createNews(title,message,imageType,null,null,date,tag);
    }

    public static New createStockOutOfFoodNews() {
        String title = ApplicationUtil.getContext().getString(R.string.title_stock_out_of_food);
        String message = ApplicationUtil.getContext().getString(R.string.msg_stock_out_of_food);
        int imageType = R.drawable.ic_food_down;

        Date date = new Date();

        String tag = New.TagEnum.STOCK_RAN_OUT_OF_FOOD.getTag();

        return createNews(title,message,imageType,null,null,date,tag);
    }

    public static New createRottenFoodNews(Food object) {
        String title = ApplicationUtil.getContext().getString(R.string.title_food_rotten);
        String message = ApplicationUtil.getContext().getString(R.string.msg_food_rotten_part1) + object.getName() + ApplicationUtil.getContext().getString(R.string.msg_food_rotten_part2);
        int imageType = R.drawable.ic_food_down;

        Date date = new Date();

        String tag = New.TagEnum.FOOD_ROTTEN.getTag();


        return createNews(title,message,imageType,null,null,date,tag);
    }
}

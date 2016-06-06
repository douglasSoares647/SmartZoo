package com.br.smartzoo.model.business;

import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.entity.New;
import com.br.smartzoo.model.environment.Visitor;
import com.br.smartzoo.model.persistence.NewsRepository;
import com.br.smartzoo.util.NewsUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Douglas on 6/6/2016.
 */
public class NewsFeedBusiness {

    public static List<New> news = new ArrayList<>();

    public static void save(New news){
        NewsRepository.save(news);
    }

    public static List<New> getLastNews(){
        return NewsRepository.getLastNews();
    }


    public static void addNew(String tag, Object object){

        New newFeed = new New();

        if(tag.equals(New.TagEnum.VISITOR_ARRIVING)){
            newFeed = NewsUtil.createVisitorArrivingNews((Visitor)object);
        }
        else if(tag.equals(New.TagEnum.VISITOR_LEAVING)){
            newFeed = NewsUtil.createVisitorLeavingNews((Visitor)object);
        }
        else if(tag.equals(New.TagEnum.ANIMAL_SICK)){
            newFeed =  NewsUtil.createAnimalSickNews((Animal)object);
        }
        else if(tag.equals(New.TagEnum.STOCK_RAN_OUT_OF_FOOD)){
            newFeed = NewsUtil.createStockOutOfFoodNews();
        }
        else if(tag.equals(New.TagEnum.FOOD_ROTTEN)){
            newFeed = NewsUtil.createRottenFoodNews((Food)object);
        }


        //CRIAR UMA ASYNC PRO SAVE e chamar aqui

        if(news.size()==50){
            news.remove(news.size()-1);
        }
        news.add(newFeed);
    }


}

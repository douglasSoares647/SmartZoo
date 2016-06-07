package com.br.smartzoo.model.business;

import com.br.smartzoo.model.asynctask.SaveNewsAsyncTask;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Food;
import com.br.smartzoo.model.entity.New;
import com.br.smartzoo.model.environment.Visitor;
import com.br.smartzoo.model.interfaces.OnNewFeedUpdate;
import com.br.smartzoo.model.persistence.NewsRepository;
import com.br.smartzoo.ui.fragment.NewsFragment;
import com.br.smartzoo.util.NewsUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Douglas on 6/6/2016.
 */
public class NewsFeedBusiness {

    private static List<New> news = new ArrayList<>();
    public static OnNewFeedUpdate mOnNewFeedUpdate;

    public static void save(New news){
        NewsRepository.save(news);
    }

    public static List<New> getLastNews(){
        return NewsRepository.getLastNews();
    }


    public static void addNew(String tag, Object object){

        New newFeed = new New();

        if(tag.equals(New.TagEnum.VISITOR_ARRIVING.getTag())){
            newFeed = NewsUtil.createVisitorArrivingNews((Visitor)object);
        }
        else if(tag.equals(New.TagEnum.VISITOR_LEAVING.getTag())){
            newFeed = NewsUtil.createVisitorLeavingNews((Visitor)object);
        }
        else if(tag.equals(New.TagEnum.ANIMAL_SICK.getTag())){
            newFeed =  NewsUtil.createAnimalSickNews((Animal)object);
        }
        else if(tag.equals(New.TagEnum.STOCK_RAN_OUT_OF_FOOD.getTag())){
            newFeed = NewsUtil.createStockOutOfFoodNews();
        }
        else if(tag.equals(New.TagEnum.FOOD_ROTTEN.getTag())){
            newFeed = NewsUtil.createRottenFoodNews((Food)object);
        }

        news.add(newFeed);
        if(news.size()==10){
            SaveNewsAsyncTask asyncTask = new SaveNewsAsyncTask();
            List<New> newsToSave = new ArrayList<>();
            newsToSave.addAll(news);
            asyncTask.execute(newsToSave);
            news.clear();
        }

        if(mOnNewFeedUpdate!=null)
        mOnNewFeedUpdate.update(newFeed);
    }




}

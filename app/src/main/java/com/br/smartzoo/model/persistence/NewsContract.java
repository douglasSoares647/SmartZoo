package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.format.DateUtils;

import com.br.smartzoo.model.entity.New;
import com.br.smartzoo.util.DateUtil;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 6/6/2016.
 */
public class NewsContract {

   public static String TABLE = "newsFeed";
   public static String ID = "id";
   public static String IMAGETYPE = "imageType";
   public static String IMAGESECONDARY = "imageSecondary";
   public static String TITLE = "title";
   public static String MESSAGE = "message";
   public static String IMAGEDESRIPTION = "imageDescription";
   public static String DATE = "date";
   public static String TAG = "tage";


    public static String[] columns ={ID,IMAGETYPE,IMAGESECONDARY,TITLE,MESSAGE,IMAGEDESRIPTION,DATE,TAG};


    public static String createTable(){
        StringBuilder table = new StringBuilder();

        table.append("create table " + TABLE + " ( ");
        table.append(ID + " integer primary key, ");
        table.append(IMAGETYPE + " integer, ");
        table.append(IMAGESECONDARY + " integer, ");
        table.append(TITLE + " text not null, ");
        table.append(MESSAGE + " text not null, ");
        table.append(IMAGEDESRIPTION + " text , ");
        table.append(DATE + " text not null, ");
        table.append(TAG + " text not null ");
        table.append(");");

        return table.toString();
    }

    public static ContentValues createContentValues(New news){
        ContentValues contentValues = new ContentValues();

        contentValues.put(IMAGETYPE, news.getImageType());
        contentValues.put(IMAGESECONDARY, news.getImageSecondary());
        contentValues.put(TITLE, news.getTitle());
        contentValues.put(MESSAGE,news.getMessage());
        contentValues.put(IMAGEDESRIPTION, news.getImageDescription());
        contentValues.put(DATE , DateUtil.dateToString(news.getDate()));
        contentValues.put(TAG, news.getTag());

        return contentValues;
    }


    public static New getNew(Cursor cursor){

        New news = new New();

        if(!cursor.isBeforeFirst() || cursor.moveToNext()){
            news.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            news.setImageType(cursor.getInt(cursor.getColumnIndex(IMAGETYPE)));
            news.setImageSecondary(cursor.getInt(cursor.getColumnIndex(IMAGESECONDARY)));
            news.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            news.setMessage(cursor.getString(cursor.getColumnIndex(MESSAGE)));
            news.setImageDescription(cursor.getString(cursor.getColumnIndex(IMAGEDESRIPTION)));
            news.setDate(DateUtil.stringToDate(cursor.getString(cursor.getColumnIndex(DATE))));
            news.setTag(cursor.getString(cursor.getColumnIndex(TAG)));
        }

        return news;

    }


    public static List<New> getNews(Cursor cursor){
        List<New> news = new ArrayList<>();

        while(cursor.moveToNext()){
            news.add(0,getNew(cursor));
        }

        return news;
    }

}

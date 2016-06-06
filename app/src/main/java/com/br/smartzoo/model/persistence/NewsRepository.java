package com.br.smartzoo.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.smartzoo.model.entity.New;

import java.util.List;

/**
 * Created by Douglas on 6/6/2016.
 */
public class NewsRepository {

    public static void save(New news){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = NewsContract.createContentValues(news);
        if(news.getId()==null){
            db.insert(NewsContract.TABLE,null,values);
        }
        else{
            String where = NewsContract.ID + " = " + news.getId();
            db.update(NewsContract.TABLE,values,where,null);
        }

        db.close();
        databaseHelper.close();
    }


    public static List<New> getLastNews(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase readableDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = readableDatabase.query(NewsContract.TABLE,NewsContract.columns,null,null,null,null,NewsContract.DATE,String.valueOf(50));

        List<New> news = NewsContract.getNews(cursor);

        return news;
    }
}

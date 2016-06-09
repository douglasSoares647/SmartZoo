package com.br.smartzoo.model;

import android.content.Context;

import com.br.smartzoo.model.enums.AnimalEnum;
import com.br.smartzoo.util.ApplicationUtil;

/**
 * Created by adenilson on 08/06/16.
 */

public class AnimalHelper {

    public static int getImageByType(Context context, String type){

        if(type.equals(context.getString(AnimalEnum.Lion.getType()))){
            return AnimalEnum.Lion.getImage();
        }else if (type.equals(context.getString(AnimalEnum.Tiger.getType()))){
            return AnimalEnum.Tiger.getImage();
        }else if(type.equals(context.getString(AnimalEnum.Turtle.getType()))){
            return AnimalEnum.Turtle.getImage();
        }else if(type.equals(context.getString(AnimalEnum.Goose.getType()))){
            return AnimalEnum.Goose.getImage();
        }else if(type.equals(context.getString(AnimalEnum.Monkey.getType()))){
            return AnimalEnum.Monkey.getImage();
        }else if(type.equals(context.getString(AnimalEnum.Giraffe.getType()))){
            return AnimalEnum.Giraffe.getImage();
        }else if(type.equals(context.getString(AnimalEnum.Elephant.getType()))){
            return AnimalEnum.Elephant.getImage();
        }else if(type.equals(context.getString(AnimalEnum.Bear.getType()))){
            return AnimalEnum.Bear.getImage();
        }else
            return -1;
    }
}

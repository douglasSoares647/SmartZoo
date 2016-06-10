package com.br.smartzoo.model.helper;

import android.content.Context;

import com.br.smartzoo.model.entity.Animal;

/**
 * Created by adenilson on 08/06/16.
 */

public class AnimalHelper {

    public static int getImageByType(Context context, String type){

        if(type.equals(context.getString(Animal.AnimalEnum.Lion.getType()))){
            return Animal.AnimalEnum.Lion.getImage();
        }else if (type.equals(context.getString(Animal.AnimalEnum.Tiger.getType()))){
            return Animal.AnimalEnum.Tiger.getImage();
        }else if(type.equals(context.getString(Animal.AnimalEnum.Turtle.getType()))){
            return Animal.AnimalEnum.Turtle.getImage();
        }else if(type.equals(context.getString(Animal.AnimalEnum.Goose.getType()))){
            return Animal.AnimalEnum.Goose.getImage();
        }else if(type.equals(context.getString(Animal.AnimalEnum.Monkey.getType()))){
            return Animal.AnimalEnum.Monkey.getImage();
        }else if(type.equals(context.getString(Animal.AnimalEnum.Giraffe.getType()))){
            return Animal.AnimalEnum.Giraffe.getImage();
        }else if(type.equals(context.getString(Animal.AnimalEnum.Elephant.getType()))){
            return Animal.AnimalEnum.Elephant.getImage();
        }else if(type.equals(context.getString(Animal.AnimalEnum.Bear.getType()))){
            return Animal.AnimalEnum.Bear.getImage();
        }else
            return -1;
    }
}

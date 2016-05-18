package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.model.enums.AnimalEnum;
import com.br.smartzoo.ui.view.BuyAnimalView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by adenilson on 16/05/16.
 */
public class BuyAnimalPresenter {


    private Activity mActivity;
    private BuyAnimalView mBuyAnimalView;

    public BuyAnimalPresenter(Activity activity) {
        this.mActivity = activity;
    }

    public void attachView(BuyAnimalView buyAnimalView){
        this.mBuyAnimalView = buyAnimalView;

    }

    public void detachView(){
        this.mBuyAnimalView = null;

    }

    public List<Animal> createAnimalList() {

      List<Animal> animals = new ArrayList<>();

        for(AnimalEnum animalEnum : AnimalEnum.values()){
            for(int i=0; i<4; i++){
                Animal animal = new Animal();
                animal.setName(mActivity.getString(animalEnum.getName()));

                Random random = new Random();

                animal.setWeight((double) random.nextInt(20));
                animal.setAge(random.nextInt(10));
                animal.setIsHealthy(random.nextBoolean());
                animal.setImage(animalEnum.getImage());
                animal.setPopularity(animalEnum.getPopularity());
                animal.setPrice(animalEnum.getPrice());
                animal.setResistance(random.nextInt(7));
                animal.setSex(random.nextBoolean()==false?mActivity.getString(R.string.male):mActivity.getString(R.string.female));

                animals.add(animal);

            }

        }


        /* Animal lion = new Animal(R.drawable.ic_animal_lion, 0L, "Lion", 14, 10000D, 150.00D
                , new Cage(), 8, true);
        Animal goose = new Animal(R.drawable.ic_animal_goose, 1L, "Goose", 12, 2000D, 17.00D
                , new Cage(), 8, true);
        Animal turtle = new Animal(R.drawable.ic_turtle, 2L, "Turtle", 12, 2200D, 20.00D
                , new Cage(), 8, true);
        Animal giraffe = new Animal(R.drawable.ic_giraffe, 3L, "Giraffe", 17, 12000D, 200.00D
                , new Cage(), 8, true);
        Animal bear = new Animal(R.drawable.ic_animal_bear, 4L, "Bear", 8, 8000D, 150.00D
                , new Cage(), 8, true);
        Animal tiger = new Animal(R.drawable.ic_animal_tiger, 5L, "Tiger", 5, 7800D, 150.00D
                , new Cage(), 8, true);
        Animal monkey = new Animal(R.drawable.ic_animal_monkey, 6L, "Monkey", 12, 4000D, 150.00D
                , new Cage(), 8, true);
        Animal elephant = new Animal(R.drawable.ic_animal_elephant, 7L, "Elephant", 25, 18000D, 150.00D
                , new Cage(), 8, true);
*/

        return animals;
    }
}

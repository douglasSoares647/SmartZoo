package com.br.smartzoo.presenter;

import android.app.Activity;

import com.br.smartzoo.R;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.Cage;
import com.br.smartzoo.ui.view.BuyAnimalView;

import java.util.ArrayList;
import java.util.List;

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

        Animal lion = new Animal(R.drawable.ic_animal_lion, "Lion", 14, 10000D, 150.00D
                , new Cage(), 8, true);
        Animal goose = new Animal(R.drawable.ic_animal_goose, "Goose", 12, 2000D, 17.00D
                , new Cage(), 8, true);
        Animal turtle = new Animal(R.drawable.ic_turtle, "Turtle", 12, 2200D, 20.00D
                , new Cage(), 8, true);
        Animal giraffe = new Animal(R.drawable.ic_giraffe, "Giraffe", 17, 12000D, 200.00D
                , new Cage(), 8, true);
        Animal bear = new Animal(R.drawable.ic_animal_bear, "Bear", 8, 8000D, 150.00D
                , new Cage(), 8, true);
        Animal tiger = new Animal(R.drawable.ic_animal_tiger, "Tiger", 5, 7800D, 150.00D
                , new Cage(), 8, true);
        Animal monkey = new Animal(R.drawable.ic_animal_monkey, "Monkey", 12, 4000D, 150.00D
                , new Cage(), 8, true);
        Animal elephant = new Animal(R.drawable.ic_animal_elephant, "Elephant", 25, 18000D, 150.00D
                , new Cage(), 8, true);

        animals.add(lion);
        animals.add(goose);
        animals.add(turtle);
        animals.add(giraffe);
        animals.add(bear);
        animals.add(tiger);
        animals.add(monkey);
        animals.add(elephant);

        return animals;
    }
}

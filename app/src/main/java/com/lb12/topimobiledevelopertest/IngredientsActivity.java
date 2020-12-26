package com.lb12.topimobiledevelopertest;

import android.app.Activity;
import android.os.Bundle;

import static com.lb12.topimobiledevelopertest.R.layout.activity_ingredients;

public class IngredientsActivity extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(activity_ingredients);

        IngredientsViewModel.createIngredientsList(
                getApplicationContext(),
                findViewById( R.id.imageView ),
                findViewById( R.id.ingredientsList ),
                findViewById( R.id.strInstructions ),
                getIntent()
        );

    }

}

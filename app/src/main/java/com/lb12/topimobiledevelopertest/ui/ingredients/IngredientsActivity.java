package com.lb12.topimobiledevelopertest.ui.ingredients;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.lb12.topimobiledevelopertest.R;

import static com.lb12.topimobiledevelopertest.R.layout.activity_ingredients;

public class IngredientsActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(activity_ingredients);

        IngredientsViewModel.createIngredientsList(
                getApplicationContext(),
                findViewById( R.id.youtubeView ),
                findViewById( R.id.ingredientsList ),
                findViewById( R.id.strMealDescription ),
                findViewById( R.id.strMealArea ),
                findViewById( R.id.strInstructions ),
                getIntent()
        );

    }

}

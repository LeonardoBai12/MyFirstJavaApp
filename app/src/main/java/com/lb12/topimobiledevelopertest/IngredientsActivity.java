package com.lb12.topimobiledevelopertest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;

import java.net.MalformedURLException;

import javax.xml.parsers.ParserConfigurationException;

import static com.lb12.topimobiledevelopertest.R.layout.activity_ingredients;

public class IngredientsActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(activity_ingredients);

        try {
            IngredientsViewModel.createIngredientsList(
                    getApplicationContext(),
                    findViewById( R.id.youtubeView ),
                    findViewById( R.id.ingredientsList ),
                    findViewById( R.id.strInstructions ),
                    getIntent()
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}

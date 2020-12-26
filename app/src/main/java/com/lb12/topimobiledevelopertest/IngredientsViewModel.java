package com.lb12.topimobiledevelopertest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.lb12.topimobiledevelopertest.IngredientsAdapter.createIngredientsAdapter;
import static com.lb12.topimobiledevelopertest.Utils.extractYoutubeId;
import static com.lb12.topimobiledevelopertest.Utils.setListViewHeightBasedOnChildren;

public class IngredientsViewModel {

    private static ArrayAdapter<String> adapter;
    private static ArrayList<String> ingredientList;
    private static String strYoutube;

    public static void createIngredientsList(
            Context appContext,
            YouTubePlayerView youtubeView,
            ListView ingredientsListView,
            TextView strMealView,
            TextView strAreaView,
            TextView strInstructionsView,
            Intent intent
    ){

        Bundle bundle = intent.getExtras();

        if ( bundle != null) {
            ingredientList  = bundle.getStringArrayList("ingredientList");
            strYoutube      = bundle.getString("StrYoutube");

            strMealView.setText( bundle.getString("StrMeal") );
            strAreaView.setText( bundle.getString("StrArea") );
            strInstructionsView.setText( bundle.getString("StrInstructions") );

        }

        if ( strYoutube != null && !strYoutube.trim().isEmpty()) {

            youtubeView.initialize(Config.DEVELOPER_KEY,
                    new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                            YouTubePlayer youTubePlayer, boolean b) {
                            try {
                                youTubePlayer.cueVideo(extractYoutubeId(strYoutube));
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                            YouTubeInitializationResult youTubeInitializationResult) {
                        }
                    });
        }

        adapter = createIngredientsAdapter(
                appContext,
                ingredientList
        );

        ingredientsListView.setAdapter( adapter );
        setListViewHeightBasedOnChildren(ingredientsListView, adapter);

    }

}

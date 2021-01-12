package com.lb12.topimobiledevelopertest;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.lb12.topimobiledevelopertest.util.Config;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.lb12.topimobiledevelopertest.adapter.IngredientsAdapter.createIngredientsAdapter;
import static com.lb12.topimobiledevelopertest.util.Utils.extractYoutubeId;
import static com.lb12.topimobiledevelopertest.util.Utils.setListViewHeightBasedOnChildren;

@EActivity(R.layout.activity_ingredients)
public class IngredientsActivity extends YouTubeBaseActivity {

    private static ArrayAdapter<String> adapter;

    @Extra("ingredientList")
    ArrayList<String> ingredientList;

    @Extra("strYoutube")
    String strYoutube;

    @Extra("strMeal")
    String strMeal;

    @Extra("strInstructions")
    String strInstructions;

    @Extra("strArea")
    String strArea;

    @ViewById
    ListView ingredientsListView;

    @ViewById
    YouTubePlayerView youtubeView;

    @ViewById
    TextView strMealView;

    @ViewById
    TextView strAreaView;

    @ViewById
    TextView strInstructionsView;

    @AfterViews
    void afterViews() {

        strMealView.setText( strMeal );
        strAreaView.setText( strArea );
        strInstructionsView.setText( strInstructions );

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
                getApplicationContext(),
                ingredientList
        );

        ingredientsListView.setAdapter( adapter );
        setListViewHeightBasedOnChildren(ingredientsListView, adapter);
    }

}

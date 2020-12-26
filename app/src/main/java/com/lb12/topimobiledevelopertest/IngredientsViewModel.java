package com.lb12.topimobiledevelopertest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import static com.lb12.topimobiledevelopertest.IngredientsAdapter.createIngredientsAdapter;

public class IngredientsViewModel {

    private static ArrayAdapter<String> adapter;
    private static ArrayList<String> ingredientList;
    private static String strMealThumb;
    private static String strInstructions;
    public static void createIngredientsList(
            Context appContext,
            ImageView imageView,
            ListView ingredientsListView,
            TextView instructionsTextView,
            Intent intent
    ){

        Bundle bundle = intent.getExtras();

        if ( bundle != null) {
            ingredientList  = bundle.getStringArrayList("ingredientList");
            strMealThumb    = bundle.getString("StrMealThumb");
            strInstructions = bundle.getString("StrInstructions");
        }

        adapter = createIngredientsAdapter(
                appContext,
                ingredientList
        );

        ingredientsListView.setAdapter( adapter );
        setListViewHeightBasedOnChildren(ingredientsListView);

        instructionsTextView.setText( strInstructions );

        RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(appContext).
                load(strMealThumb).
                centerCrop().
                apply(requestOptions).
                into( imageView );

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {

        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}

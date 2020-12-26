package com.lb12.topimobiledevelopertest;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;


public class IngredientsAdapter {

    public void ArrayAdapter() {
    }

    static ArrayAdapter<String> createIngredientsAdapter(
            Context appContext,
            ArrayList<String> ingredientsList
    ) {

        ArrayAdapter<String> ingredientsAdapter = new ArrayAdapter<String>(
                appContext,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                ingredientsList
        );

        return ingredientsAdapter;
    }

}

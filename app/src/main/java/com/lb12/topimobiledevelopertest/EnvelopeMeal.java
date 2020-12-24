package com.lb12.topimobiledevelopertest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EnvelopeMeal {
    @SerializedName( "meals" )
    @Expose
    private List<MealsModel.Meal> mealList;
}

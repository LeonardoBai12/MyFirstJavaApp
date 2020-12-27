package com.lb12.topimobiledevelopertest.ui.meals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lb12.topimobiledevelopertest.ui.meals.MealsModel;

import java.util.List;

public class EnvelopeMeal {
    @SerializedName( "meals" )
    @Expose
    private List<MealsModel.Meal> mealList;

    public List<MealsModel.Meal> getMealList(){
        return mealList;
    }

    public void setMealList(List<MealsModel.Meal> mealList){
        this.mealList = mealList;
    }

}

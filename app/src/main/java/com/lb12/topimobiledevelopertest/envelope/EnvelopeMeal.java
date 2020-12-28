package com.lb12.topimobiledevelopertest.envelope;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lb12.topimobiledevelopertest.model.MealsModel;

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

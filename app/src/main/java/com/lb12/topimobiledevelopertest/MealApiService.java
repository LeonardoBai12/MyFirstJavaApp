package com.lb12.topimobiledevelopertest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealApiService {
    @GET("idMeal")
    Call<MealsModel.Meal> getMeals();
}

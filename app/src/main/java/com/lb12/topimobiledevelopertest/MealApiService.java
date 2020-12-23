package com.lb12.topimobiledevelopertest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MealApiService {
    @GET("/search.php")
    Call<MealsModel.Meal> getMeals( @Query("s=chicken") String chicken);
}

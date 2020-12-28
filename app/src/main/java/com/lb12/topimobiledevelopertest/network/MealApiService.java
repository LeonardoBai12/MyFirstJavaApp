package com.lb12.topimobiledevelopertest.network;

import com.lb12.topimobiledevelopertest.envelope.EnvelopeMeal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApiService {
    @GET("api/json/v1/1/search.php")
    Call<EnvelopeMeal> getMeals(@Query("s") String filter);
}

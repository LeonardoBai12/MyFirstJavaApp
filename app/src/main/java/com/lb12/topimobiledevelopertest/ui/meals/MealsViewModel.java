package com.lb12.topimobiledevelopertest.ui.meals;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lb12.topimobiledevelopertest.di.module.RetrofitClientInstance;
import com.lb12.topimobiledevelopertest.data.rest.MealApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.lb12.topimobiledevelopertest.ui.main.MainActivity.createRecyclerView;
import static com.lb12.topimobiledevelopertest.ui.meals.MealsAdapter.createRecyclerViewSwipe;

public class MealsViewModel{

    private static final String FILTER = "Chicken";

    public static void populateMealList(
            ProgressDialog progressDialog,
            Context appContext,
            RecyclerView recyclerView,
            MealsAdapter.Adapter adapter,
            SwipeRefreshLayout swipeContainer
    ){

        callMealListFromPHP(
                appContext,
                progressDialog,
                recyclerView,
                adapter
        );

        createRecyclerViewSwipe(
                swipeContainer,
                adapter,
                appContext,
                progressDialog
        );

    }

    static void callMealListFromPHP(
            Context appContext,
            ProgressDialog progressDialog,
            RecyclerView recyclerView,
            MealsAdapter.Adapter adapter
    ){

        MealApiService service = RetrofitClientInstance.getRetrofitInstance().create( MealApiService.class );
        Call<EnvelopeMeal> call = service.getMeals( FILTER );

        call.enqueue(new Callback<EnvelopeMeal>() {

            @Override
            public void onResponse(Call<EnvelopeMeal> call, Response<EnvelopeMeal> response) {
                progressDialog.dismiss();
                createRecyclerView(appContext);
                adapter.updateList(response.body().getMealList());
                recyclerView.setAdapter( adapter );
                MealsAdapter.createRecyclerViewClick(
                        recyclerView,
                        appContext,
                        response.body().getMealList()
                );

            }

            @Override
            public void onFailure(Call<EnvelopeMeal> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(
                        appContext,
                        "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        } );
    }


}

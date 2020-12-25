package com.lb12.topimobiledevelopertest;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.lb12.topimobiledevelopertest.MealsAdapter.createRecyclerViewSwipe;

public class MealsViewModel{

    private static RecyclerView recyclerView;
    private static List<MealsModel.Meal> mealList = new ArrayList<>();
    private static final String FILTER = "Chicken";
    private ProgressDialog progressDialog;

    static void populateMealList(
            ProgressDialog progressDialog,
            Context appContext,
            RecyclerView recyclerViewById,
            SwipeRefreshLayout swipeContainer
    ){

        recyclerView = recyclerViewById;
        MealsAdapter.Adapter adapter = new MealsAdapter.Adapter(appContext);

        callMealListFromPHP(
                swipeContainer,
                appContext,
                progressDialog,
                adapter
        );

        createRecyclerViewSwipe(
                swipeContainer,
                adapter,
                appContext,
                progressDialog
        );

    }

    static void createRecyclerView(
            Context appContext,
            MealsAdapter.Adapter adapter
    ){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( appContext );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration( new DividerItemDecoration( appContext, LinearLayout.VERTICAL ) );
        recyclerView.setAdapter( adapter );
    }

    static void callMealListFromPHP(
            SwipeRefreshLayout swipeContainer,
            Context appContext,
            ProgressDialog progressDialog,
            MealsAdapter.Adapter adapter
    ){

        MealApiService service = RetrofitClientInstance.getRetrofitInstance().create( MealApiService.class );
        Call<EnvelopeMeal> call = service.getMeals( FILTER );

        call.enqueue(new Callback<EnvelopeMeal>() {

            @Override
            public void onResponse(Call<EnvelopeMeal> call, Response<EnvelopeMeal> response) {
                progressDialog.dismiss();
                createRecyclerView(
                        appContext,
                        adapter
                );
                adapter.updateList(response.body().getMealList());
                MealsAdapter.createRecyclerViewClick(
                        recyclerView,
                        appContext,
                        response.body().getMealList()
                );

                swipeContainer.setRefreshing(false);

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

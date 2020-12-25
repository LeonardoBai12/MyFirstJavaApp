package com.lb12.topimobiledevelopertest;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsViewModel{

    private static RecyclerView recyclerView;
    private static List<MealsModel.Meal> mealList = new ArrayList<>();
    private static final String FILTER = "Chicken";

    static void populateMealList(
            ProgressDialog progressDialog,
            Context appContext,
            Context mainContext,
            RecyclerView recyclerViewById
    ){

        recyclerView = recyclerViewById;

        MealApiService service = RetrofitClientInstance.getRetrofitInstance().create( MealApiService.class );
        Call<EnvelopeMeal> call = service.getMeals( FILTER );

        call.enqueue(new Callback<EnvelopeMeal>() {

            @Override
            public void onResponse(Call<EnvelopeMeal> call, Response<EnvelopeMeal> response) {
                progressDialog.dismiss();
                MealsAdapter.Adapter adapter = new MealsAdapter.Adapter( mealList );
                createRecyclerView(
                        appContext,
                        mainContext,
                        adapter
                );
                createRecyclerViewClick( appContext );
            }

            @Override
            public void onFailure(Call<EnvelopeMeal> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(
                        mainContext,
                        "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        } );

    }

    static void createRecyclerView(
            Context appContext,
            Context mainContext,
            MealsAdapter.Adapter adapter
    ){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( appContext );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration( new DividerItemDecoration( mainContext, LinearLayout.VERTICAL ) );
        recyclerView.setAdapter( adapter );
    }

    static void createRecyclerViewClick( Context appContext ){

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        appContext,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        MealsModel.Meal meal = mealList.get( position );

                        Toast.makeText(
                                appContext,
                                meal.getStrMeal(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                        MealsModel.Meal meal = mealList.get( position );

                        Toast.makeText(
                                appContext,
                                meal.getStrMeal() + " segurando",
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                }
                )
        );

    }


}

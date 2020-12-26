package com.lb12.topimobiledevelopertest;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.lb12.topimobiledevelopertest.MealsAdapter.createRecyclerViewSwipe;

public class MealsViewModel{

    private static RecyclerView recyclerView;
    private static final String FILTER = "Chicken";

    static void populateMealList(
            ProgressDialog progressDialog,
            Context appContext,
            RecyclerView recyclerViewById,
            SwipeRefreshLayout swipeContainer
    ){

        recyclerView = recyclerViewById;
        MealsAdapter.Adapter adapter = new MealsAdapter.Adapter(appContext);

        callMealListFromPHP(
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

    static void createRecyclerView( Context appContext ){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( appContext );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration( new DividerItemDecoration( appContext, LinearLayout.VERTICAL ) );
    }

    static void callMealListFromPHP(
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

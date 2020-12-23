package com.lb12.topimobiledevelopertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MealsModel.Meal> mealList = new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        recyclerView = findViewById( R.id.recyclerView );

        //Populate Meals List
        this.populateMealList();

        //RecyclerView Events
        this.createRecyclerViewClick();

    }

    protected void populateMealList(){

        MealApiService service = RetrofitClientInstance.getRetrofitInstance().create( MealApiService.class );
        Call<MealsModel.Meal> call = service.getMeals( );
//
        call.enqueue(new Callback<MealsModel.Meal>() {
            @Override
            public void onResponse(Call<MealsModel.Meal> call, Response<MealsModel.Meal> response) {
                progressDialog.dismiss();
                MealsAdapter.Adapter adapter = new MealsAdapter.Adapter( mealList );
                createRecyclerView( adapter );
            }

            @Override
            public void onFailure(Call<MealsModel.Meal> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        } );

    }

    protected Call<List<MealsModel.Meal>> createRecyclerView( MealsAdapter.Adapter adapter ){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration( new DividerItemDecoration( this, LinearLayout.VERTICAL ) );
        recyclerView.setAdapter( adapter );

        return null;
    }

    protected void createRecyclerViewClick(){

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        MealsModel.Meal meal = mealList.get( position );

                        Toast.makeText(
                                getApplicationContext(),
                                meal.getStrMeal(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                        MealsModel.Meal meal = mealList.get( position );

                        Toast.makeText(
                                getApplicationContext(),
                                meal.getStrMeal() + " segurando",
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                }
                )
        );

    }

}
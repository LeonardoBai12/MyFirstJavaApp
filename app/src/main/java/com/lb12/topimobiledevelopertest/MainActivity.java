package com.lb12.topimobiledevelopertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MealsModel.Meal> mealList = new ArrayList<>();

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        recyclerView = findViewById( R.id.recyclerView );

        //Meals List
        MealsViewModel.populateMealsList( mealList );

        //Configure adapter
        MealsAdapter.Adapter adapter = new MealsAdapter.Adapter( mealList );

        //Configure recycleView
        this.createRecyclerView( adapter );

    }

    protected void createRecyclerView( MealsAdapter.Adapter adapter ){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration( new DividerItemDecoration( this, LinearLayout.VERTICAL ) );
        recyclerView.setAdapter( adapter );

    }

}
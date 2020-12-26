package com.lb12.topimobiledevelopertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        //Populate Meals List
        MealsViewModel.populateMealList(
                progressDialog,
                getApplicationContext(),
                findViewById( R.id.recyclerView ),
                findViewById(R.id.swipeContainer)
        );

    }






}
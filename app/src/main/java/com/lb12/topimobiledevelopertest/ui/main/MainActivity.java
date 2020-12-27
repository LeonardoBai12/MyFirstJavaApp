package com.lb12.topimobiledevelopertest.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.lb12.topimobiledevelopertest.R;
import com.lb12.topimobiledevelopertest.ui.meals.MealsAdapter;
import com.lb12.topimobiledevelopertest.ui.meals.MealsViewModel;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeContainer;
    private static RecyclerView recyclerView;
    private static MealsAdapter.Adapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        recyclerView = findViewById( R.id.recyclerView );
        adapter = new MealsAdapter.Adapter(getApplicationContext());

        createRecyclerView(getApplicationContext());

        //Populate Meals List
        MealsViewModel.populateMealList(
                progressDialog,
                getApplicationContext(),
                recyclerView,
                adapter,
                findViewById(R.id.swipeContainer)
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meals_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    public static void createRecyclerView(Context appContext){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( appContext );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration( new DividerItemDecoration( appContext, LinearLayout.VERTICAL ) );
    }

}
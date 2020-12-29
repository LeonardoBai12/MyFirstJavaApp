package com.lb12.topimobiledevelopertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

import com.lb12.topimobiledevelopertest.adapter.MealsAdapter;
import com.lb12.topimobiledevelopertest.model.MealsModel;
import com.lb12.topimobiledevelopertest.viewmodel.MealsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeContainer;
    private MealsViewModel viewModel;
    private List<MealsModel.Meal> mealModelList;
    private static RecyclerView recyclerView;
    private static MealsAdapter.Adapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        recyclerView = findViewById( R.id.recyclerView );
        adapter = new MealsAdapter.Adapter(
                this,
                mealModelList,
                recyclerView
        );

        createRecyclerView(this );
        recyclerView.setAdapter( adapter );

        viewModel = new ViewModelProvider(this).get(MealsViewModel.class);

        viewModel.getMealList().observe(this, new Observer<List<MealsModel.Meal>>() {
            @Override
            public void onChanged(List<MealsModel.Meal> mealList) {
                if ( !(mealList==null) ) {
                    mealModelList = mealList;
                    adapter.updateList(mealModelList);
                }
            }
        });

        createRecyclerViewSwipe();
        viewModel.makeAPICall( getApplicationContext(), progressDialog );

    }

    public void createRecyclerViewSwipe(){
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.makeAPICall( getApplicationContext(), progressDialog );
                swipeContainer.setRefreshing(false);
            }
        });
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
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
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.lb12.topimobiledevelopertest.adapter.MealsAdapter;
import com.lb12.topimobiledevelopertest.model.MealsModel;
import com.lb12.topimobiledevelopertest.util.ItemClickListener;
import com.lb12.topimobiledevelopertest.viewmodel.MealsViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InjectMenu;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import static com.lb12.topimobiledevelopertest.model.IngredientsModel.populateIngredientList;

@EActivity(R.layout.activity_main)
@OptionsMenu( R.menu.meals_menu )
public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private MealsViewModel viewModel;
    private List<MealsModel.Meal> mealModelList;
    private static MealsAdapter.Adapter adapter;

    @ViewById
    static RecyclerView recyclerView;

    @ViewById
    SwipeRefreshLayout swipeContainer;

    @AfterViews
    void afterViews(){
        createProccessDialog();
        createAndSetAdapter();
        createRecyclerView();
        createViewModel();
        createRecyclerViewSwipe();
    }

    @OptionsMenuItem( R.id.action_search )
    MenuItem searchItem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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

    void createProccessDialog(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
    }

    void createAndSetAdapter(){
        adapter = new MealsAdapter.Adapter(mealModelList);
    }

    void createRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration( new DividerItemDecoration( getApplicationContext(), LinearLayout.VERTICAL ) );
        recyclerView.setAdapter( adapter );
    }

    void createViewModel(){
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

    void createRecyclerViewSwipe(){
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.makeAPICall( getApplicationContext(), progressDialog );
                swipeContainer.setRefreshing(false);
            }
        });
    }

}
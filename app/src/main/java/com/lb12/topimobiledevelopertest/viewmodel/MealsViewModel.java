package com.lb12.topimobiledevelopertest.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lb12.topimobiledevelopertest.envelope.EnvelopeMeal;
import com.lb12.topimobiledevelopertest.model.MealsModel;
import com.lb12.topimobiledevelopertest.network.RetrofitClientInstance;
import com.lb12.topimobiledevelopertest.network.MealApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsViewModel extends ViewModel{

    private static final String FILTER = "Chicken";

    private MutableLiveData<List<MealsModel.Meal>> mealList = new MutableLiveData<>();

    public MutableLiveData<List<MealsModel.Meal>> getMealList(){
        return mealList;
    }
    public void setMealList(MutableLiveData<List<MealsModel.Meal>> mealList){
        this.mealList = mealList;
    }

    public void makeAPICall(
            Context appContext,
            ProgressDialog progressDialog
    ){

        MealApiService service = RetrofitClientInstance.getRetrofitInstance().create( MealApiService.class );
        Call<EnvelopeMeal> call = service.getMeals( FILTER );

        call.enqueue(new Callback<EnvelopeMeal>() {

            @Override
            public void onResponse(Call<EnvelopeMeal> call, Response<EnvelopeMeal> response) {
                progressDialog.dismiss();
                mealList.postValue(response.body().getMealList());
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

package com.lb12.topimobiledevelopertest.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lb12.topimobiledevelopertest.IngredientsActivity;
import com.lb12.topimobiledevelopertest.R;
import com.lb12.topimobiledevelopertest.util.RecyclerItemClickListener;
import com.lb12.topimobiledevelopertest.model.MealsModel;

import java.util.ArrayList;
import java.util.List;

import static com.lb12.topimobiledevelopertest.model.IngredientsModel.populateIngredientList;

public class MealsAdapter {

    public static MealsModel.Meal meal;

    public static class Adapter extends RecyclerView.Adapter< Adapter.MyViewHolder > implements Filterable {

        private final Context context;

        private List<MealsModel.Meal> mealsList;
        private List<MealsModel.Meal> mealsListFull;
        private RecyclerView recyclerView;
        private RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);

        public Adapter(
                Context context,
                List<MealsModel.Meal> mealsList,
                RecyclerView recyclerView
        ){
            this.context = context;
            this.mealsList = mealsList;
            this.recyclerView = recyclerView;
        }

        public void updateList(List<MealsModel.Meal> mealsList){
            this.mealsList = mealsList;
            mealsListFull = new ArrayList<>( mealsList );
            this.notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View item = LayoutInflater.from( parent.getContext() ).
                    inflate(
                            R.layout.adaptermeals,
                            parent,
                            false
                    );
            return new MyViewHolder( item );
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            MealsModel.Meal meal = mealsList.get( position );
            holder.strMeal.setText( meal.getStrMeal() );
            holder.strArea.setText( meal.getStrArea() );

            createRecyclerViewClick(
                    this.recyclerView,
                    holder.itemView.getContext(),
                    this.mealsList
            );

            Glide.with(holder.itemView.getContext()).
                    load(meal.getStrMealThumb()).
                    centerCrop().
                    apply(requestOptions).
                    into(holder.strMealThumb);
        }

        @Override
        public int getItemCount() {
            if( !( this.mealsList == null ) ) {
                return this.mealsList.size();
            }else{
                return 0;
            }
        }

        @Override
        public Filter getFilter() {
            return mealFilter;
        }

        private Filter mealFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<MealsModel.Meal> filteredList = new ArrayList<>();

                if( constraint == null || constraint.length() == 0 ){
                    filteredList.addAll( mealsListFull );
                }else{
                    String filterPattern =  constraint.toString().toLowerCase().trim();

                    for(MealsModel.Meal item : mealsListFull ){

                        if( item.getStrMeal().toLowerCase().contains(filterPattern) ||
                            item.getStrArea().toLowerCase().contains(filterPattern) ){
                            filteredList.add(item);
                        }

                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mealsList.clear();
                mealsList.addAll((List)results.values);
                notifyDataSetChanged();
            }

        };

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView strMeal;
            TextView strArea;
            ImageView strMealThumb;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                strMeal      = itemView.findViewById( R.id.strMeal      );
                strArea      = itemView.findViewById( R.id.strArea      );
                strMealThumb = itemView.findViewById( R.id.strMealThumb );
            }
        }

        public void clear() {
            mealsList.clear();
            notifyDataSetChanged();
        }

    }

    public static void createRecyclerViewClick(
            RecyclerView recyclerView,
            Context appContext,
            List<MealsModel.Meal> mealList
    ){

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        appContext,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {

                                MealsModel.Meal meal = mealList.get( position );
                                ArrayList<String> ingredientsList = new ArrayList<String>();

                                populateIngredientList(
                                        ingredientsList,
                                        meal
                                );

                                Intent intent =  new Intent(
                                        appContext,
                                        IngredientsActivity.class
                                );
                                Bundle bundle = new Bundle();

                                bundle.putString( "StrYoutube", meal.getStrYoutube() );
                                bundle.putString( "StrInstructions", meal.getStrInstructions() );
                                bundle.putString( "StrMeal", meal.getStrMeal() );
                                bundle.putString( "StrArea", meal.getStrArea() );
                                bundle.putStringArrayList( "ingredientList", ingredientsList );

                                intent.putExtras( bundle );
                                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                appContext.startActivity(intent);

                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                                MealsModel.Meal meal = mealList.get( position );
                                Toast.makeText(
                                        appContext,
                                        "Meal Code: " + meal.getIdMeal(),
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                        }
                )
        );

    }

}

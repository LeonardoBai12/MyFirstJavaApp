package com.lb12.topimobiledevelopertest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MealsAdapter {

    public static Object Adapter;

    public static class Adapter extends RecyclerView.Adapter< Adapter.MyViewHolder >{

        private List<MealsModel.Meal> mealsList;

        public Adapter( List<MealsModel.Meal> list ) {
            this.mealsList = list;
        }

        @NonNull
        @Override
        public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View item = LayoutInflater.from( parent.getContext() ).inflate( R.layout.adaptermeals, parent, false );
            return new MyViewHolder( item );
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            MealsModel.Meal meal = mealsList.get( position );
            holder.idMeal.setText( meal.getIdMeal() );
            holder.strMeal.setText( meal.getStrMeal() );
            holder.strCategory.setText( meal.getStrMeal() );
            holder.strArea.setText( meal.getStrMeal() );
        }

        @Override
        public int getItemCount() {
            return mealsList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView idMeal;
            TextView strMeal;
            TextView strCategory;
            TextView strArea;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                idMeal      = itemView.findViewById( R.id.idMeal      );
                strMeal     = itemView.findViewById( R.id.strMeal     );
                strCategory = itemView.findViewById( R.id.strCategory );
                strArea     = itemView.findViewById( R.id.strArea     );

            }
        }
    }

}

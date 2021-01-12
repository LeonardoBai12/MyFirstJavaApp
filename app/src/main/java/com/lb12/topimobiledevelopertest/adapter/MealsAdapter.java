package com.lb12.topimobiledevelopertest.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lb12.topimobiledevelopertest.IngredientsActivity_;
import com.lb12.topimobiledevelopertest.R;
import com.lb12.topimobiledevelopertest.model.MealsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lb12.topimobiledevelopertest.model.IngredientsModel.populateIngredientList;

public class MealsAdapter {

    public static MealsModel.Meal meal;

    public static class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements Filterable {

        private List<MealsModel.Meal> mealsList;
        private List<MealsModel.Meal> mealsListFull;
        private RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);

        public Adapter(List<MealsModel.Meal> mealsList) {
            this.mealsList = mealsList;
        }

        public void updateList(List<MealsModel.Meal> mealsList) {
            this.mealsList = mealsList;
            mealsListFull = new ArrayList<>(mealsList);
            this.notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).
                    inflate(
                            R.layout.adaptermeals,
                            parent,
                            false
                    );
            return new MyViewHolder(item);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            MealsModel.Meal meal = mealsList.get(position);
            holder.strMeal.setText(meal.getStrMeal());
            holder.strArea.setText(meal.getStrArea());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMealClick(meal, holder);
                }
            });

            Glide.with(holder.itemView.getContext()).
                    load(meal.getStrMealThumb()).
                    centerCrop().
                    apply(requestOptions).
                    into(holder.strMealThumb);
        }

        void onMealClick(MealsModel.Meal meal, @NonNull MyViewHolder holder) {
            ArrayList<String> ingredientsList = new ArrayList<String>();

            populateIngredientList(
                    ingredientsList,
                    meal
            );

            Intent intent = new Intent(
                    holder.itemView.getContext(),
                    IngredientsActivity_.class
            );

            Bundle bundle = new Bundle();

            bundle.putString("strYoutube", meal.getStrYoutube());
            bundle.putString("strInstructions", meal.getStrInstructions());
            bundle.putString("strMeal", meal.getStrMeal());
            bundle.putString("strArea", meal.getStrArea());
            bundle.putStringArrayList("ingredientList", ingredientsList);

            intent.putExtras(bundle);

            holder.itemView.getContext().startActivity(intent);
        }

        @Override
        public int getItemCount() {
            if (!(this.mealsList == null)) {
                return this.mealsList.size();
            } else {
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

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(mealsListFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (MealsModel.Meal item : mealsListFull) {

                        if (item.getStrMeal().toLowerCase().contains(filterPattern) ||
                                item.getStrArea().toLowerCase().contains(filterPattern)) {
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
                mealsList.addAll((List) results.values);
                notifyDataSetChanged();
            }

        };

        static class MyViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.strMeal)
            TextView strMeal;
            @BindView(R.id.strArea)
            TextView strArea;
            @BindView(R.id.strMealThumb)
            ImageView strMealThumb;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        public void clear() {
            mealsList.clear();
            notifyDataSetChanged();
        }

    }

}

package com.lb12.topimobiledevelopertest.model;

import java.util.ArrayList;

import static com.lb12.topimobiledevelopertest.util.Utils.addIfNotEmptyOrNull;

public class IngredientsModel {

    public static void populateIngredientList(
            ArrayList<String> ingredientsList,
            MealsModel.Meal meal
    ) {

        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient1());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient2());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient3());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient4());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient5());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient6());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient7());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient8());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient9());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient10());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient11());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient12());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient13());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient14());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient15());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient16());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient17());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient18());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient19());
        addIfNotEmptyOrNull(ingredientsList, meal.getStrIngredient20());

    }

}

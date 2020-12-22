package com.lb12.topimobiledevelopertest;

public class MealsModel {

    static class Meal{

        private String idMeal;
        private String strMeal;
        private String strCategory;
        private String strArea;

        Meal( String idMeal, String strMeal, String strCategory, String strArea ) {
            this.idMeal      = idMeal;
            this.strMeal     = strMeal;
            this.strCategory = strCategory;
            this.strArea     = strArea;
        }

        public String getIdMeal() {
            return idMeal;
        }

        public String getStrMeal() {
            return strMeal;
        }

        public String getStrCategory() {
            return strCategory;
        }

        public String getStrArea() {
            return strArea;
        }

        public void setIdMeal(String idMeal) {
            this.idMeal = idMeal;
        }

        public void setStrMeal(String strMeal) {
            this.strMeal = strMeal;
        }

        public void setStrCategory(String strCategory) {
            this.strCategory = strCategory;
        }

        public void setStrArea(String strArea) {
            this.strArea = strArea;
        }
    }
}

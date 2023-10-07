package com.dcca.jane.mmy.utilities;

import com.dcca.jane.mmy.data.AppDatabase;
import com.dcca.jane.mmy.data.Ingredient;
import com.dcca.jane.mmy.data.Meal;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Luke on 13/02/2018.
 *
 * Work in Progress. Utilities for database/Room persistence.
 */

public class AppDBUtils {

    public static Meal ingredientsToMeal(List<Ingredient> mealIngredients, long mealId, int mealType, Date mealTime) {

        Double totalCalories = 0.0;
        Double totalCarbs = 0.0;
        Double totalSugars = 0.0;
        Double totalFat = 0.0;
        Double totalSats = 0.0;
        Double totalProtein = 0.0;
        Double totalSodium = 0.0;

        for (int i = 0; i < mealIngredients.size(); i++) {
            Ingredient ingredient = mealIngredients.get(i);
            Double ingMultiplier = ingredient.weight/100.0;
            totalCalories = totalCalories + (ingredient.calories * ingMultiplier);
            totalCarbs = totalCarbs + (ingredient.carbs * ingMultiplier);
            totalSugars = totalSugars + (ingredient.sugar * ingMultiplier);
            totalFat = totalFat + (ingredient.fat * ingMultiplier);
            totalSats = totalSats + (ingredient.saturates * ingMultiplier);
            totalProtein = totalProtein + (ingredient.protein * ingMultiplier);
            totalSodium = totalSodium + (ingredient.sodium * ingMultiplier);
        }
        return makeMeal(mealId, mealType, mealTime, totalCalories, totalFat, totalSats, totalCarbs, totalSugars, totalProtein, totalSodium);
    }

    public static Date makeTimestamp(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }


//    public static Date getTodayPlusDays(int daysAgo) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, daysAgo);
//        return calendar.getTime();
//    }

    public static long makeBlankIngredient(AppDatabase db){
        Ingredient ingredient = makeIngredient(0,0,null,null,null,null,null,null,null,null,null,null);
        return addIngredient(db, ingredient);
    }
//
    public static Ingredient makeIngredient(final long id, final long mealId, final String name, final Double weight, final String ndbno, final Double calories,
                                            final Double fat, final Double sats, final Double carbs, final Double sugars, final Double protein, final Double sodium){
        Ingredient ingredient = new Ingredient();
        ingredient.id = id;
        ingredient.meal_id = mealId;
        ingredient.name = name;
        ingredient.ndbno = ndbno;
        ingredient.weight = weight;
        ingredient.calories = calories;
        ingredient.fat = fat;
        ingredient.saturates = sats;
        ingredient.carbs = carbs;
        ingredient.sugar = sugars;
        ingredient.protein = protein;
        ingredient.sodium = sodium;
        return ingredient;
    }

    public static Long addIngredient(final AppDatabase db, final Ingredient ingredient) {
        return db.ingredientModel().insertIngredient(ingredient);
    }

    public static void deleteIngredientWithId(final AppDatabase db, final long ingredientId) {
        db.ingredientModel().deleteIngredientById(ingredientId);
    }

    public static void deleteIngredientWithMealId(final AppDatabase db, final long mealId) {
        db.ingredientModel().deleteIngredientByMealId(mealId);
    }

    public static List<Ingredient> returnIngredientsWithMealId(final AppDatabase db, final long mealId){
        return db.ingredientModel().findIngredientsOfMeal(mealId);
    }

    public static Long makeBlankMeal(AppDatabase db, int mealType, Date mealTime) {

        Meal meal = makeMeal(0, mealType, mealTime,null,null,null,null,null,null, null);
        return addMeal(db, meal);
    }

    private static Meal makeMeal(final long id, final int mealType, Date mealTime, final Double calories,
                                 final Double fat, final Double sats, final Double carbs, final Double sugars, final Double protein, final Double sodium) {

        String mealTitle = getTitleFromInt(mealType);

        Meal meal = new Meal();
        meal.id = id;
        meal.mealType = mealType;
        meal.mealTime = mealTime;
        meal.mealTitle = mealTitle;
        meal.totalCalories = calories;
        meal.totalFat = fat;
        meal.totalSats = sats;
        meal.totalCarbs = carbs;
        meal.totalSugars = sugars;
        meal.totalProtein = protein;
        meal.totalSodium = sodium;
        return meal;
    }

    public static Long addMeal(final AppDatabase db, final Meal meal){
        return db.mealModel().insertMeal(meal);
    }

    public static void deleteMealWithId(final AppDatabase db, final long mealId){
        db.mealModel().deleteMealById((mealId));
    }

    public static int returnTypeOfMealWithId(final AppDatabase db, final long mealId){
        return db.mealModel().retrieveMealType(mealId);
    }

    public static Date returnTimeOfMealWithId(final AppDatabase db, final long mealId){
        return db.mealModel().retrieveMealTime(mealId);
    }

    public static List<Ingredient> returnIngredientsFromMealTypeAndDay(final AppDatabase db, int mealType, final Date dayStart, final Date dayEnd){
        return db.mealModel().findMealIngredientsByDayandType(mealType, dayStart, dayEnd);
    }

    public static long returnMealIdFromTypeAndDay(final AppDatabase db, final int mealType, final Date dayStart, final Date dayEnd){
        return db.mealModel().findMealIdByDayandType(mealType, dayStart, dayEnd);
    }

    private static String getTitleFromInt(int mealType) {
        String mealTitle = "";
        if(mealType == 1){
            mealTitle = "Breakfast";
        } else if(mealType == 2){
            mealTitle = "Lunch";
        } else if(mealType == 3){
            mealTitle = "Dinner";
        } else if(mealType == 4){
            mealTitle = "Snacks";
        }
        return mealTitle;
    }


}

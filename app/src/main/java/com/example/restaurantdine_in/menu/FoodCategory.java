package com.example.restaurantdine_in.menu;

import java.util.ArrayList;

public class FoodCategory {

    ArrayList<FoodItem> foodItems;
    private static ArrayList<String> foodItemNames = new ArrayList<>();

    public FoodCategory() {
        foodItemNames = new ArrayList<>();
    }

    public static ArrayList<String> getItemNamesFromFoodItemList(ArrayList<FoodItem> foodItems) {
        foodItemNames.clear();
        for(FoodItem foodItem : foodItems) {
            foodItemNames.add(foodItem.getItemName());
        }
        return foodItemNames;
    }
}

package com.example.restaurantdine_in.menu;

import com.example.restaurantdine_in.Constants;

import java.util.ArrayList;

public class BreadsCategory extends FoodCategory {

    private static ArrayList<FoodItem> breadsCategoryList = new ArrayList<>();

    final static FoodItem naan = new FoodItem(Constants.NAAN, 2.00);
    final static FoodItem tandooriRoti = new FoodItem(Constants.TANDOORI_ROTI, 2.00);
    final static FoodItem alooParatha = new FoodItem(Constants.ALOO_PARATHA, 3.00);
    final static FoodItem garlicNaan = new FoodItem(Constants.GARLIC_NAAN, 2.50);
    final static FoodItem paneerNaan = new FoodItem(Constants.PANEER_NAAN, 3.50);
    final static FoodItem keemaNaan = new FoodItem(Constants.KEEMA_NAAN, 4.00);
    final static FoodItem chickenNaan = new FoodItem(Constants.CHICKEN_NAAN, 4.00);


    public static ArrayList<FoodItem> getBreadsCategoryList() {
        breadsCategoryList.clear();
        breadsCategoryList.add(naan);
        breadsCategoryList.add(tandooriRoti);
        breadsCategoryList.add(alooParatha);
        breadsCategoryList.add(garlicNaan);
        breadsCategoryList.add(paneerNaan);
        breadsCategoryList.add(keemaNaan);
        breadsCategoryList.add(chickenNaan);
        return breadsCategoryList;
    }

    public void setBreadsCategoryList(ArrayList<FoodItem> breadsCategoryList) {
        this.breadsCategoryList = breadsCategoryList;
    }
}

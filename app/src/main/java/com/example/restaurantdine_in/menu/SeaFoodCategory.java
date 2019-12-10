package com.example.restaurantdine_in.menu;

import com.example.restaurantdine_in.Constants;

import java.util.ArrayList;

public class SeaFoodCategory extends FoodCategory {

    private static ArrayList<FoodItem> seaFoodCategoryList = new ArrayList<>();

    final static FoodItem shrimpCurry = new FoodItem(Constants.SHRIMP_CURRY, 13.50);
    final static FoodItem fishCurry = new FoodItem(Constants.FISH_CURRY, 13.50);
    final static FoodItem shrimpSaag = new FoodItem(Constants.SHRIMP_SAAG, 15.00);
    final static FoodItem shrimpKorma = new FoodItem(Constants.SHRIMP_KORMA, 15.00);
    final static FoodItem shrimpChilly = new FoodItem(Constants.SHRIMP_CHILLY, 15.00);
    final static FoodItem shrimpVindaloo = new FoodItem(Constants.SHRIMP_VINDALOO, 15.00);
    final static FoodItem shrimpJalfrazi = new FoodItem(Constants.SHRIMP_JALFRAZI, 15.00);
    final static FoodItem friedFish = new FoodItem(Constants.FRIED_FISH, 15.00);


    public static ArrayList<FoodItem> getSeaFoodCategoryList() {
        seaFoodCategoryList.clear();
        seaFoodCategoryList.add(shrimpCurry);
        seaFoodCategoryList.add(fishCurry);
        seaFoodCategoryList.add(shrimpSaag);
        seaFoodCategoryList.add(shrimpKorma);
        seaFoodCategoryList.add(shrimpChilly);
        seaFoodCategoryList.add(shrimpVindaloo);
        seaFoodCategoryList.add(shrimpJalfrazi);
        seaFoodCategoryList.add(friedFish);
        return seaFoodCategoryList;
    }

    public void setSeaFoodCategoryList(ArrayList<FoodItem> seaFoodCategoryList) {
        this.seaFoodCategoryList = seaFoodCategoryList;
    }
}

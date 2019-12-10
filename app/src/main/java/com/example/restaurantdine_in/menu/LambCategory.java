package com.example.restaurantdine_in.menu;

import com.example.restaurantdine_in.Constants;

import java.util.ArrayList;

public class LambCategory extends FoodCategory {

    private static ArrayList<FoodItem> lambCategoryList = new ArrayList<>();

    final static FoodItem lambCurry = new FoodItem(Constants.LAMB_CURRY, 11.50);
    final static FoodItem lambChilly = new FoodItem(Constants.LAMB_CHILLY, 13.50);
    final static FoodItem lambKorma = new FoodItem(Constants.LAMB_KORMA, 13.00);
    final static FoodItem lambVindaloo = new FoodItem(Constants.LAMB_VINDALOO, 13.00);
    final static FoodItem lambJalfrazi = new FoodItem(Constants.LAMB_JALFRAZI, 13.50);
    final static FoodItem lambSaag = new FoodItem(Constants.LAMB_SAAG, 13.00);
    final static FoodItem keemaMasala = new FoodItem(Constants.KEEMA_MASALA, 13.50);
    final static FoodItem lambMadarasi = new FoodItem(Constants.LAMB_MADRASI, 13.00);
    final static FoodItem lambBhuna = new FoodItem(Constants.LAMB_BHUNA, 13.50);


    public static ArrayList<FoodItem> getLambCategoryList() {
        lambCategoryList.clear();
        lambCategoryList.add(lambCurry);
        lambCategoryList.add(lambKorma);
        lambCategoryList.add(lambChilly);
        lambCategoryList.add(lambJalfrazi);
        lambCategoryList.add(lambSaag);
        lambCategoryList.add(lambMadarasi);
        lambCategoryList.add(lambVindaloo);
        lambCategoryList.add(lambBhuna);
        lambCategoryList.add(keemaMasala);
        return lambCategoryList;
    }

    public void setLambCategoryList(ArrayList<FoodItem> appetizersList) {
        this.lambCategoryList = lambCategoryList;
    }
}

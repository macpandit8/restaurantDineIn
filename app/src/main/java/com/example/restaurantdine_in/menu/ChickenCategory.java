package com.example.restaurantdine_in.menu;

import com.example.restaurantdine_in.Constants;

import java.util.ArrayList;

public class ChickenCategory extends FoodCategory {

    private static ArrayList<FoodItem> chickenCategoryList = new ArrayList<>();

    final static FoodItem chickenCurry = new FoodItem(Constants.CHICKEN_CURRY, 11.50);
    final static FoodItem butterChicken = new FoodItem(Constants.BUTTER_CHICKEN, 12.00);
    final static FoodItem chickenKorma = new FoodItem(Constants.CHICKEN_KORMA, 12.50);
    final static FoodItem chillyChicken = new FoodItem(Constants.CHILLY_CHICKEN, 13.00);
    final static FoodItem chickenJalfrazi = new FoodItem(Constants.CHICKEN_JALFRAZI, 12.50);
    final static FoodItem chickenSaag = new FoodItem(Constants.CHICKEN_SAAG, 12.00);
    final static FoodItem chickenMadrasi = new FoodItem(Constants.CHICKEN_MADRASI, 12.00);
    final static FoodItem chickenDhansak = new FoodItem(Constants.CHICKEN_DHANSAK, 12.00);
    final static FoodItem chickenTikkaMasala = new FoodItem(Constants.CHICKEN_TIKKA_MASALA, 13.00);
    final static FoodItem chickenVindaloo = new FoodItem(Constants.CHICKEN_VINDALOO, 12.00);


    public static ArrayList<FoodItem> getChickenCategoryList() {
        chickenCategoryList.clear();
        chickenCategoryList.add(chickenCurry);
        chickenCategoryList.add(butterChicken);
        chickenCategoryList.add(chickenKorma);
        chickenCategoryList.add(chillyChicken);
        chickenCategoryList.add(chickenJalfrazi);
        chickenCategoryList.add(chickenSaag);
        chickenCategoryList.add(chickenMadrasi);
        chickenCategoryList.add(chickenDhansak);
        chickenCategoryList.add(chickenTikkaMasala);
        chickenCategoryList.add(chickenVindaloo);
        return chickenCategoryList;
    }

    public void setChickenCategoryList(ArrayList<FoodItem> chickenCategoryList) {
        this.chickenCategoryList = chickenCategoryList;
    }

}

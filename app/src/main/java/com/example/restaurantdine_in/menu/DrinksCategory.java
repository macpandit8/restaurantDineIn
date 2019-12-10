package com.example.restaurantdine_in.menu;

import com.example.restaurantdine_in.Constants;

import java.util.ArrayList;

public class DrinksCategory extends FoodCategory {

    private static ArrayList<FoodItem> drinksCategoryList = new ArrayList<>();

    final static FoodItem indianTea = new FoodItem(Constants.INDIAN_TEA, 2.00);
    final static FoodItem mangoJuice = new FoodItem(Constants.MANGO_JUICE, 2.00);
    final static FoodItem saltyLassi = new FoodItem(Constants.SALTED_LASSI, 2.00);
    final static FoodItem softDrink = new FoodItem(Constants.SOFT_DRINK, 1.75);
    final static FoodItem indianSoftDrink = new FoodItem(Constants.INDIAN_SOFT_DRINK, 2.00);
    final static FoodItem mangoLassi = new FoodItem(Constants.MANGO_LASSI, 3.50);
    final static FoodItem sweetLassi = new FoodItem(Constants.SWEET_LASSI, 2.50);


    public static ArrayList<FoodItem> getDrinksCategoryList() {
        drinksCategoryList.clear();
        drinksCategoryList.add(indianTea);
        drinksCategoryList.add(mangoJuice);
        drinksCategoryList.add(saltyLassi);
        drinksCategoryList.add(softDrink);
        drinksCategoryList.add(indianSoftDrink);
        drinksCategoryList.add(mangoLassi);
        drinksCategoryList.add(sweetLassi);
        return drinksCategoryList;
    }

    public void setDrinksCategoryList(ArrayList<FoodItem> drinksCategoryList) {
        this.drinksCategoryList = drinksCategoryList;
    }
}

package com.example.restaurantdine_in.food_selection;

import java.util.ArrayList;

public interface IAddItemsToOrderListener {

    void setOrderList(ArrayList<Integer> foodCountList, ArrayList<String> foodNamesList, ArrayList<Double> foodItemPriceList);

}

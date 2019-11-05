package com.example.restaurantdine_in.food_selection;

import java.util.ArrayList;

public class MenuItems {
    private static ArrayList<String> appetizersList;
    private static ArrayList<String> vegetarianList;
    private static ArrayList<String> chickenList;
    private static ArrayList<String> lambList;
    private static ArrayList<String> seaFoodList;
    private static ArrayList<String> breadsList;
    private static ArrayList<String> drinksList;

    public MenuItems() {

    }

    public static ArrayList<String> getAppetizersList() {
        appetizersList.add("Daal Soup");
        appetizersList.add("Malcatani Soup");
        appetizersList.add("Veg Samosa");
        appetizersList.add("Ch. Samosa");
        appetizersList.add("Chana Samosa");
        appetizersList.add("Veg Pakora");
        appetizersList.add("Ch. Pakora");
        appetizersList.add("Fish Pakora");
        appetizersList.add("Paneer Pakora");
        appetizersList.add("Onion Bhaji");
        return appetizersList;
    }
}

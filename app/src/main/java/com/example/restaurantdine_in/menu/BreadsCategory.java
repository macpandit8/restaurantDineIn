package com.example.restaurantdine_in.menu;

import com.example.restaurantdine_in.Constants;

import java.util.ArrayList;

public class BreadsCategory extends FoodCategory {

    private static ArrayList<FoodItem> breadsCategoryList = new ArrayList<>();

    final static FoodItem daalSoup = new FoodItem(Constants.DAAL_SOUP, 3.50);
    final static FoodItem malcataniSoup = new FoodItem(Constants.MALCATANI_SOUP, 4.00);
    final static FoodItem sambarSoup = new FoodItem(Constants.SAMBAR_SOUP, 4.00);
    final static FoodItem vegSamosa = new FoodItem(Constants.VEG_SAMOSA, 3.50);
    final static FoodItem vegPakora = new FoodItem(Constants.VEG_PAKORA, 3.50);
    final static FoodItem onionBhaji = new FoodItem(Constants.ONION_BHAJI, 3.50);
    final static FoodItem chanaSamosa = new FoodItem(Constants.CHANA_SAMOSA, 6.00);
    final static FoodItem chaatPuri = new FoodItem(Constants.CHAAT_PURI, 6.00);
    final static FoodItem bhelPuri = new FoodItem(Constants.BHEL_PURI, 6.00);
    final static FoodItem masalaDhosa = new FoodItem(Constants.MASALA_DOSA, 9.00);
    final static FoodItem idli = new FoodItem(Constants.IDLI, 5.99);
    final static FoodItem vadaSambar = new FoodItem(Constants.VADA_SAMBAR, 6.00);
    final static FoodItem dahiVada = new FoodItem(Constants.DAHI_VADA, 6.00);
    final static FoodItem chickenSamosa = new FoodItem(Constants.CHICKEN_SAMOSA, 5.00);
    final static FoodItem paneerPakora = new FoodItem(Constants.PANEER_PAKORA, 5.00);
    final static FoodItem chickenPakora = new FoodItem(Constants.CHICKEN_PAKORA, 5.00);
    final static FoodItem fishPakora = new FoodItem(Constants.FISH_PAKORA, 5.00);


    public static ArrayList<FoodItem> getBreadsCategoryList() {
        breadsCategoryList.clear();
        breadsCategoryList.add(daalSoup);
        breadsCategoryList.add(malcataniSoup);
        breadsCategoryList.add(sambarSoup);
        breadsCategoryList.add(vegSamosa);
        breadsCategoryList.add(vegPakora);
        breadsCategoryList.add(onionBhaji);
        breadsCategoryList.add(chanaSamosa);
        breadsCategoryList.add(chaatPuri);
        breadsCategoryList.add(bhelPuri);
        breadsCategoryList.add(masalaDhosa);
        breadsCategoryList.add(idli);
        breadsCategoryList.add(vadaSambar);
        breadsCategoryList.add(dahiVada);
        breadsCategoryList.add(chickenSamosa);
        breadsCategoryList.add(paneerPakora);
        breadsCategoryList.add(chickenPakora);
        breadsCategoryList.add(fishPakora);
        return breadsCategoryList;
    }

    public void setBreadsCategoryList(ArrayList<FoodItem> breadsCategoryList) {
        this.breadsCategoryList = breadsCategoryList;
    }
}

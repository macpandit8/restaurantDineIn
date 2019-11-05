package com.example.restaurantdine_in.menu;

import com.example.restaurantdine_in.Constants;

import java.util.ArrayList;

public class VegetarianCategory extends FoodCategory {

    private static ArrayList<FoodItem> VegetarianCategoryList = new ArrayList<>();

    final static FoodItem mixVeg = new FoodItem(Constants.MIX_VEG, 8.50);
    final static FoodItem palakPaneer = new FoodItem(Constants.PALAK_PANEER, 9.50);
    final static FoodItem shahiPaneer = new FoodItem(Constants.SHAHI_PANEER, 11.00);
    final static FoodItem mutterPaneer = new FoodItem(Constants.MUTTER_PANEER, 9.50);
    final static FoodItem bainganBharta = new FoodItem(Constants.BAINGAN_BHARTA, 9.50);
    final static FoodItem alooGobi = new FoodItem(Constants.ALOO_GOBI, 9.50);
    final static FoodItem vegKorma = new FoodItem(Constants.VEG_KORMA, 9.50);
    final static FoodItem malaiKofta = new FoodItem(Constants.MALAI_KOFTA, 11.00);
    final static FoodItem chanaMasala = new FoodItem(Constants.CHANA_MASALA, 8.50);
    final static FoodItem daalMakhni = new FoodItem(Constants.DAAL_MAKHNI, 8.50);
    final static FoodItem tadkaDaal = new FoodItem(Constants.TADKA_DAAL, 8.50);
    final static FoodItem bhindiMasala = new FoodItem(Constants.BHINDI_MASALA, 11.50);
    final static FoodItem chillyPaneer = new FoodItem(Constants.CHILLI_PANEER, 11.50);
    final static FoodItem vegJalfraizi = new FoodItem(Constants.VEG_JALFRAIZI, 9.50);


    public static ArrayList<FoodItem> getVegetarianCategoryList() {
        VegetarianCategoryList.clear();
        VegetarianCategoryList.add(mixVeg);
        VegetarianCategoryList.add(palakPaneer);
        VegetarianCategoryList.add(shahiPaneer);
        VegetarianCategoryList.add(mutterPaneer);
        VegetarianCategoryList.add(bainganBharta);
        VegetarianCategoryList.add(alooGobi);
        VegetarianCategoryList.add(vegKorma);
        VegetarianCategoryList.add(malaiKofta);
        VegetarianCategoryList.add(chanaMasala);
        VegetarianCategoryList.add(daalMakhni);
        VegetarianCategoryList.add(tadkaDaal);
        VegetarianCategoryList.add(bhindiMasala);
        VegetarianCategoryList.add(chillyPaneer);
        VegetarianCategoryList.add(vegJalfraizi);
        return VegetarianCategoryList;
    }

    public void setVegetarianCategoryListList(ArrayList<FoodItem> vegetarianCategoryList) {
        this.VegetarianCategoryList = vegetarianCategoryList;
    }
}

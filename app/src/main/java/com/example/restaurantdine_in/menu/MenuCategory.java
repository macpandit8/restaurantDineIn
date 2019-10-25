package com.example.restaurantdine_in.menu;


import android.widget.TextView;

public class MenuCategory {

    TextView menuCategory;
    String categoryName;

    public MenuCategory(TextView menuCategory, String categoryName){
        this.menuCategory = menuCategory;
        this.categoryName = categoryName;
    }

    public TextView getMenuCategory() {
        return menuCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

}

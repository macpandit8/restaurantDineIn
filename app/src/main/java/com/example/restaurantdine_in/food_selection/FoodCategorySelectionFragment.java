package com.example.restaurantdine_in.food_selection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.restaurantdine_in.Constants;
import com.example.restaurantdine_in.R;

import java.util.ArrayList;

public class FoodCategorySelectionFragment extends Fragment {


    private TextView appetizersTextview, vegetarianTextview, chickenTextview, lambTextview, seaFoodTextview, breadsTextview, drinksTextview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View foodCategorySelectionView = inflater.inflate(R.layout.food_category_textviews_fragment, container, false);

        appetizersTextview = foodCategorySelectionView.findViewById(R.id.appetizersTextview);
        vegetarianTextview = foodCategorySelectionView.findViewById(R.id.vegetarianTextview);
        chickenTextview = foodCategorySelectionView.findViewById(R.id.chickenTextview);
        lambTextview = foodCategorySelectionView.findViewById(R.id.lambTextview);
        seaFoodTextview = foodCategorySelectionView.findViewById(R.id.seaFoodTextview);
        breadsTextview = foodCategorySelectionView.findViewById(R.id.breadsTextview);
        drinksTextview = foodCategorySelectionView.findViewById(R.id.drinksTextview);

        MenuCategory appetizers = new MenuCategory(appetizersTextview, Constants.APPETIZERS);
        MenuCategory vegetarian = new MenuCategory(vegetarianTextview, Constants.VEGETARIAN);
        MenuCategory chicken = new MenuCategory(chickenTextview, Constants.CHICKEN);
        MenuCategory lamb = new MenuCategory(lambTextview, Constants.LAMB);
        MenuCategory seaFood = new MenuCategory(seaFoodTextview, Constants.SEAFOOD);
        MenuCategory breads = new MenuCategory(breadsTextview, Constants.BREADS);
        MenuCategory drinks = new MenuCategory(drinksTextview, Constants.DRINKS);

        ArrayList<MenuCategory> menuCategories = new ArrayList<>();
        menuCategories.add(appetizers);
        menuCategories.add(vegetarian);
        menuCategories.add(chicken);
        menuCategories.add(lamb);
        menuCategories.add(seaFood);
        menuCategories.add(breads);
        menuCategories.add(drinks);

        onClickListenerForMenuCategory(menuCategories);


        return foodCategorySelectionView;
    }

    private void onClickListenerForMenuCategory(final ArrayList<MenuCategory> menuCategories) {

        for(final MenuCategory menuCategory : menuCategories) {
            menuCategory.getMenuCategory().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.foodItemSelectionFragmentContainer, new FoodItemSelectionFragment(), menuCategory.getCategoryName());
                    fragmentTransaction.commit();


                }
            });
        }
    }
}

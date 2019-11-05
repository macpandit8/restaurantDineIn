package com.example.restaurantdine_in.food_selection;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.restaurantdine_in.Constants;
import com.example.restaurantdine_in.R;
import com.example.restaurantdine_in.adapters.FoodItemListViewAdapter;
import com.example.restaurantdine_in.menu.AppetizersCategory;
import com.example.restaurantdine_in.menu.BreadsCategory;
import com.example.restaurantdine_in.menu.ChickenCategory;
import com.example.restaurantdine_in.menu.DrinksCategory;
import com.example.restaurantdine_in.menu.FoodItem;
import com.example.restaurantdine_in.menu.LambCategory;
import com.example.restaurantdine_in.menu.SeaFoodCategory;
import com.example.restaurantdine_in.menu.VegetarianCategory;

import java.util.ArrayList;

public class FoodItemSelectionFragment extends Fragment implements View.OnClickListener {

    private FoodItemListViewAdapter foodItemListViewAdapter;

    private Context mContext;
    ArrayList<String> foodNameList = new ArrayList<>();
    ArrayList<Integer> foodCountList = new ArrayList<>();
    ArrayList<Double> foodItemPriceList = new ArrayList<>();
    String selectedFoodCategory;
    TextView selectedCategoryTitle;
    ListView foodItemsListView;
    Button cancelBtn, addBtn;

    FragmentTransaction fragmentTransaction;

    ArrayList<Integer> selectedFoodItemCounts = new ArrayList<>();
    ArrayList<String> selectedFoodItemNames = new ArrayList<>();
    ArrayList<Double> selectedFoodItemPrice = new ArrayList<>();

    private IAddItemsToOrderListener addItemsToOrderListener = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View foodItemSelectionView = inflater.inflate(R.layout.food_list_by_category_fragment, container, false);

        mContext = getContext();
        Fragment fragment = this;
        selectedFoodCategory = getTag();
        foodItemsListView = foodItemSelectionView.findViewById(R.id.foodItemsListView);
        selectedCategoryTitle = foodItemSelectionView.findViewById(R.id.selectedCategoryTitle);
        cancelBtn = foodItemSelectionView.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);
        addBtn = foodItemSelectionView.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);

        addItemsToOrderListener = (IAddItemsToOrderListener) getActivity();

        extractFoodListForCategory(selectedFoodCategory);
        for(int i = 0; i < foodNameList.size(); i++) {
            foodCountList.add(0);
        }

        foodItemListViewAdapter = new FoodItemListViewAdapter(mContext, foodNameList, foodCountList, fragment);
        foodItemsListView.setAdapter(foodItemListViewAdapter);


        return foodItemSelectionView;
    }

    private void extractFoodListForCategory(String selectedFoodCategory) {

        switch (selectedFoodCategory) {
            case Constants.APPETIZERS :
                selectedCategoryTitle.setText(getResources().getString(R.string.appetizers));
                foodNameList = AppetizersCategory.getItemNamesFromFoodItemList(AppetizersCategory.getAppetizersList());
                extractFoodItemPriceList(AppetizersCategory.getAppetizersList());
                break;
            case Constants.VEGETARIAN :
                selectedCategoryTitle.setText(getResources().getString(R.string.vegetarian));
                foodNameList = VegetarianCategory.getItemNamesFromFoodItemList(VegetarianCategory.getVegetarianCategoryList());
                extractFoodItemPriceList(VegetarianCategory.getVegetarianCategoryList());
                break;
            case Constants.CHICKEN :
                selectedCategoryTitle.setText(getResources().getString(R.string.chicken));
                foodNameList = ChickenCategory.getItemNamesFromFoodItemList(ChickenCategory.getChickenCategoryList());
                extractFoodItemPriceList(ChickenCategory.getChickenCategoryList());
                break;
            case Constants.LAMB :
                selectedCategoryTitle.setText(getResources().getString(R.string.lamb));
                foodNameList = LambCategory.getItemNamesFromFoodItemList(LambCategory.getLambCategoryList());
                extractFoodItemPriceList(LambCategory.getLambCategoryList());
                break;
            case Constants.SEAFOOD :
                selectedCategoryTitle.setText(getResources().getString(R.string.seafood));
                foodNameList = SeaFoodCategory.getItemNamesFromFoodItemList(SeaFoodCategory.getSeaFoodCategoryList());
                extractFoodItemPriceList(SeaFoodCategory.getSeaFoodCategoryList());
                break;
            case Constants.BREADS :
                selectedCategoryTitle.setText(getResources().getString(R.string.breads));
                foodNameList = BreadsCategory.getItemNamesFromFoodItemList(BreadsCategory.getBreadsCategoryList());
                extractFoodItemPriceList(BreadsCategory.getBreadsCategoryList());
                break;
            case Constants.DRINKS :
                selectedCategoryTitle.setText(getResources().getString(R.string.drinks));
                foodNameList = DrinksCategory.getItemNamesFromFoodItemList(DrinksCategory.getDrinksCategoryList());
                extractFoodItemPriceList(DrinksCategory.getDrinksCategoryList());
                break;
        }
    }

    private void extractFoodItemPriceList(ArrayList<FoodItem> foodItemList) {
        for(FoodItem foodItem : foodItemList) {
            foodItemPriceList.add(foodItem.getItemAmount());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addBtn :
                addItemsToOrderList();
                if (!selectedFoodItemNames.isEmpty() && !selectedFoodItemCounts.isEmpty() && ! selectedFoodItemPrice.isEmpty()) {
                    addItemsToOrderListener.setOrderList(selectedFoodItemCounts, selectedFoodItemNames, selectedFoodItemPrice);
                    commitFragmentTransaction();
                } else {
                    Toast.makeText(mContext, "No item selected", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cancelBtn :
                commitFragmentTransaction();
                break;
        }
    }

    private void commitFragmentTransaction() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.foodItemSelectionFragmentContainer, new FoodCategorySelectionFragment());
        fragmentTransaction.commit();
    }

    private void addItemsToOrderList() {
        selectedFoodItemCounts.clear();
        selectedFoodItemNames.clear();
        selectedFoodItemPrice.clear();
        if(!foodItemListViewAdapter.getFoodCountList().isEmpty()) {
            for (int i = 0; i < foodItemListViewAdapter.getFoodCountList().size(); i++) {
                if(foodItemListViewAdapter.getFoodCountList().get(i) != 0) {
                    selectedFoodItemNames.add(foodNameList.get(i));
                    selectedFoodItemCounts.add(foodItemListViewAdapter.getFoodCountList().get(i));
                    selectedFoodItemPrice.add(calculateFoodItemPrice(foodItemListViewAdapter.getFoodCountList().get(i), foodItemPriceList.get(i)));
                }
            }
        }
    }

    private Double calculateFoodItemPrice(int itemCount, double itemPrice) {
        return itemCount*itemPrice;
    }


    public void setFoodQty(int position, String foodQty) {
        foodItemListViewAdapter.setFoodQty(position, foodQty);
    }
}

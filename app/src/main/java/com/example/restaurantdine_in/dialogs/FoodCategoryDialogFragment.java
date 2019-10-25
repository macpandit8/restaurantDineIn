package com.example.restaurantdine_in.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.restaurantdine_in.R;
import com.example.restaurantdine_in.adapters.FoodItemListViewAdapter;

import java.util.ArrayList;

public class FoodCategoryDialogFragment extends DialogFragment {

    private static Context mContext;
    private static String mFoodCategory;
    private static FoodCategoryDialogFragment fragment;

    private FoodItemListViewAdapter foodItemListViewAdapter;

    ArrayList<String> foodNameList = new ArrayList<>();
    ArrayList<Integer> foodCountList = new ArrayList<>();

    TextView dialogTitleTV;
    ListView foodItemsListView;
    Button cancelBtn, addBtn;

    public static FoodCategoryDialogFragment newInstance(Context context, String foodCategory) {
        mContext = context;
        mFoodCategory = foodCategory;
        fragment = new FoodCategoryDialogFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.food_category_dialog, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        foodItemsListView = rootView.findViewById(R.id.foodItemsListView);
        dialogTitleTV = rootView.findViewById(R.id.dialogTitle);
        cancelBtn = rootView.findViewById(R.id.cancelBtn);
        addBtn = rootView.findViewById(R.id.addBtn);

        foodNameList.add("item1");
        foodNameList.add("item2");
        foodNameList.add("item3");
        foodNameList.add("item4");
        foodNameList.add("item5");

        foodCountList.add(1);
        foodCountList.add(2);
        foodCountList.add(3);
        foodCountList.add(4);
        foodCountList.add(5);

        foodItemListViewAdapter = new FoodItemListViewAdapter(getContext(), foodNameList, foodCountList);
        foodItemsListView.setAdapter(foodItemListViewAdapter);
        foodItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(mContext, "item clicked", Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;
    }
}

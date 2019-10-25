package com.example.restaurantdine_in.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.restaurantdine_in.R;

import java.util.ArrayList;

public class FoodItemListViewAdapter extends ArrayAdapter {
    Context mContext;
    TextView foodNameTV, decreaseFoodCountTV, increaseFoodCountTV, foodCountEditText;
    ArrayList<String> foodNameList;
    ArrayList<Integer> foodCountList;

    public FoodItemListViewAdapter(@NonNull Context context, ArrayList<String> foodNameList, ArrayList<Integer> foodCountList) {
        super(context, R.layout.food_list_under_category_child_item, R.id.foodNameTV, foodNameList);
        this.mContext = context;
        this.foodNameList = foodNameList;
        this.foodCountList = foodCountList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View adapterView = layoutInflater.inflate(R.layout.food_list_under_category_child_item, parent, false);

        foodNameTV = adapterView.findViewById(R.id.foodNameTV);
        foodCountEditText = adapterView.findViewById(R.id.foodCountTV);
        foodNameTV.setText(foodNameList.get(position));
        foodCountEditText.setText(String.valueOf(foodCountList.get(position)));

        return adapterView;
    }
}

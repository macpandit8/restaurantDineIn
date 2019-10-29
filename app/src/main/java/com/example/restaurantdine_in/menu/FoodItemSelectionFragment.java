package com.example.restaurantdine_in.menu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.restaurantdine_in.R;
import com.example.restaurantdine_in.adapters.FoodItemListViewAdapter;

import java.util.ArrayList;

public class FoodItemSelectionFragment extends Fragment implements View.OnClickListener{

    private FoodItemListViewAdapter foodItemListViewAdapter;

    private Context mContext;

    ArrayList<String> foodNameList = new ArrayList<>();
    ArrayList<Integer> foodCountList = new ArrayList<>();

    TextView selectedCategoryTitle;
    ListView foodItemsListView;
    Button cancelBtn, addBtn;

    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View foodItemSelectionView = inflater.inflate(R.layout.food_list_by_category_fragment, container, false);

        mContext = getActivity();

        foodItemsListView = foodItemSelectionView.findViewById(R.id.foodItemsListView);
        selectedCategoryTitle = foodItemSelectionView.findViewById(R.id.selectedCategoryTitle);
        cancelBtn = foodItemSelectionView.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);
        addBtn = foodItemSelectionView.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);

        selectedCategoryTitle.setText(getTag());

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

        foodItemListViewAdapter = new FoodItemListViewAdapter(mContext, foodNameList, foodCountList);
        foodItemsListView.setAdapter(foodItemListViewAdapter);
        foodItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getActivity(), "item clicked", Toast.LENGTH_SHORT).show();

            }
        });


        return foodItemSelectionView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addBtn :
                Toast.makeText(getActivity(), "add button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancelBtn :
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.foodItemSelectionFragmentContainer, new FoodCategorySelectionFragment());
                fragmentTransaction.commit();
                break;
        }
    }
}

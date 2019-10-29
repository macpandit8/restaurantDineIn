package com.example.restaurantdine_in.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.restaurantdine_in.R;
import com.example.restaurantdine_in.dialogs.EditTextDialogFragment;

import java.util.ArrayList;

public class FoodItemListViewAdapter extends ArrayAdapter {
    Context mContext;
    TextView foodNameTV, decreaseFoodCountTV, increaseFoodCountTV, foodCountTV;
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
        final View adapterView = layoutInflater.inflate(R.layout.food_list_under_category_child_item, parent, false);

        foodNameTV = adapterView.findViewById(R.id.foodNameTV);
        foodCountTV = adapterView.findViewById(R.id.foodCountTV);
        decreaseFoodCountTV = adapterView.findViewById(R.id.decreaseFoodCountTV);
        increaseFoodCountTV = adapterView.findViewById(R.id.increaseFoodCountTV);
        foodNameTV.setText(foodNameList.get(position));
        foodCountTV.setText(String.valueOf(foodCountList.get(position)));

        setOnClickListeners(foodCountTV, decreaseFoodCountTV, increaseFoodCountTV, position);

        return adapterView;
    }

    private void setOnClickListeners(final TextView foodCountTV, TextView decreaseFoodCountTV, TextView increaseFoodCountTV, final int position) {

        foodCountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTextDialogFragment editTextDialogFragment = EditTextDialogFragment.newInstance(mContext, foodNameList.get(position),
                        mContext.getString(R.string.done_caps), mContext.getString(R.string.cancel_caps), false);
                editTextDialogFragment.setCancelable(false);
                editTextDialogFragment.show(((FragmentActivity)mContext).getSupportFragmentManager(), foodNameList.get(position));
            }
        });

        decreaseFoodCountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count;
                if(foodCountList.get(position) != 0) {
                    count = foodCountList.get(position) - 1;
                    foodCountList.set(position, count);
                    foodCountTV.setText(String.valueOf(count));
                }
            }
        });

        increaseFoodCountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count;
                count = foodCountList.get(position) + 1;
                foodCountList.set(position, count);
                foodCountTV.setText(String.valueOf(count));
            }
        });

    }
}

package com.example.restaurantdine_in.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.restaurantdine_in.R;

import java.util.ArrayList;

public class OrderItemListAdapter extends ArrayAdapter<String> {

    Context mContext;
    ArrayList<Integer> itemCount = new ArrayList<>();
    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<String> itemComment = new ArrayList<>();
    ArrayList<Double> itemAmount = new ArrayList<>();

    public OrderItemListAdapter(Context mContext, ArrayList<Integer> itemCount, ArrayList<String> itemName, ArrayList<String> itemComment, ArrayList<Double> itemAmount) {
        super(mContext, R.layout.order_list_child_item, R.id.tvItemName, itemName);
        this.mContext = mContext;
        this.itemCount = itemCount;
        this.itemName = itemName;
        this.itemComment = itemComment;
        this.itemAmount = itemAmount;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View adapterView = layoutInflater.inflate(R.layout.order_list_child_item, parent, false);

        TextView tvItemCount = adapterView.findViewById(R.id.tvItemCount);
        TextView tvItemName = adapterView.findViewById(R.id.tvItemName);
        TextView tvItemComment = adapterView.findViewById(R.id.tvItemComment);
        TextView tvItemamount = adapterView.findViewById(R.id.tvItemAmount);

        tvItemCount.setText(String.valueOf(itemCount.get(position)));
        tvItemName.setText(String.valueOf(itemName.get(position)));
        tvItemComment.setText(String.valueOf(itemComment.get(position)));
        tvItemamount.setText(String.valueOf(itemAmount.get(position)));




        return adapterView;
    }
}

package com.example.restaurantdine_in.printerLib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.restaurantdine_in.R;

import java.util.ArrayList;

public class PrinterListViewAdapter extends ArrayAdapter {

    private Context mContext;

    private TextView modelNameTextView, portNameTextView, macAddressTextView;
    private ImageView checkedIconImageView;
    private ArrayList<PrinterInfo> printersInfo;

    public PrinterListViewAdapter(@NonNull Context context, ArrayList<PrinterInfo> printersInfo) {
        super(context, R.layout.list_printer_info_row, R.id.modelNameTextView, printersInfo);
        this.mContext = context;
        this.printersInfo = printersInfo;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View adapterView = layoutInflater.inflate(R.layout.list_printer_info_row, parent, false);

        modelNameTextView = adapterView.findViewById(R.id.modelNameTextView);
        portNameTextView = adapterView.findViewById(R.id.portNameTextView);
        macAddressTextView = adapterView.findViewById(R.id.macAddressTextView);
        checkedIconImageView = adapterView.findViewById(R.id.checkedIconImageView);

        modelNameTextView.setText(printersInfo.get(position).getModelName());
        portNameTextView.setText(printersInfo.get(position).getPortName());
        macAddressTextView.setText(printersInfo.get(position).getMacAddress());
        if (printersInfo.get(position).isChecked()) {
            checkedIconImageView.setImageResource(R.drawable.checked_icon);
        } else {
            checkedIconImageView.setImageResource(R.drawable.unchecked_icon);
        }

        return adapterView;
    }
}

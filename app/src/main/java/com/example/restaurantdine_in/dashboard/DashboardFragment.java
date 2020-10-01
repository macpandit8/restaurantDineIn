package com.example.restaurantdine_in.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.restaurantdine_in.Constants;
import com.example.restaurantdine_in.printerLib.KitchenPrinterActivity;
import com.example.restaurantdine_in.food_selection.PlaceOrderActivity;
import com.example.restaurantdine_in.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment implements IDashboardActivityListener {

    ImageView tb1, tb2, tb3, tb4, tb5, tb6, tb7, tb8, tb9;
    ImageView kitchen;

    private DashboardActivity dashboardActivity = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View dashboardView = inflater.inflate(R.layout.dashboard_fragment, container, false);

        dashboardActivity = (DashboardActivity) getContext();

        tb1 = dashboardView.findViewById(R.id.tb1);
        tb2 = dashboardView.findViewById(R.id.tb2);
        tb3 = dashboardView.findViewById(R.id.tb3);
        tb4 = dashboardView.findViewById(R.id.tb4);
        tb5 = dashboardView.findViewById(R.id.tb5);
        tb6 = dashboardView.findViewById(R.id.tb6);
        tb7 = dashboardView.findViewById(R.id.tb7);
        tb8 = dashboardView.findViewById(R.id.tb8);
        tb9 = dashboardView.findViewById(R.id.tb9);

        kitchen = dashboardView.findViewById(R.id.kitchen);
        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kitchenIntent = new Intent(getActivity(), KitchenPrinterActivity.class);
                getActivity().startActivity(kitchenIntent);
            }
        });

        Table table1 = new Table(tb1, Constants.SEAT_CAP_FOUR, Constants.SQUARE_TABLE, Constants.TABLE_NO_1);
        Table table2 = new Table(tb2, Constants.SEAT_CAP_FOUR, Constants.SQUARE_TABLE, Constants.TABLE_NO_2);
        Table table3 = new Table(tb3, Constants.SEAT_CAP_FOUR, Constants.SQUARE_TABLE, Constants.TABLE_NO_3);
        Table table4 = new Table(tb4, Constants.SEAT_CAP_TWO, Constants.ROUND_TABLE, Constants.TABLE_NO_4);
        Table table5 = new Table(tb5, Constants.SEAT_CAP_FOUR, Constants.ROUND_TABLE, Constants.TABLE_NO_5);
        Table table6 = new Table(tb6, Constants.SEAT_CAP_FOUR, Constants.ROUND_TABLE, Constants.TABLE_NO_6);
        Table table7 = new Table(tb7, Constants.SEAT_CAP_FOUR, Constants.ROUND_TABLE, Constants.TABLE_NO_7);
        Table table8 = new Table(tb8, Constants.SEAT_CAP_TWO, Constants.SQUARE_TABLE, Constants.TABLE_NO_8);
        Table table9 = new Table(tb9, Constants.SEAT_CAP_TEN, Constants.SQUARE_TABLE, Constants.TABLE_NO_9);

        ArrayList<Table> tables = new ArrayList<>();
        tables.add(table1);
        tables.add(table2);
        tables.add(table3);
        tables.add(table4);
        tables.add(table5);
        tables.add(table6);
        tables.add(table7);
        tables.add(table8);
        tables.add(table9);

        onClickListener(tables);

        return dashboardView;
    }

    private void onClickListener(ArrayList<Table> tables) {

        for(final Table table : tables) {
            table.getAssociatedTable().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getActivity(), PlaceOrderActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.TABLE, table.getTableNumber());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    if (table.getSeatingCapacity() == Constants.SEAT_CAP_FOUR) {
                        if (table.getShape().equals(Constants.SQUARE_TABLE)) {
                            table.getAssociatedTable().setImageResource(R.drawable.tb4four_square_occupied);
                        } else {
                            table.getAssociatedTable().setImageResource(R.drawable.tb4four_round_occupied);
                        }
                    } else if (table.getSeatingCapacity() == Constants.SEAT_CAP_TWO) {
                        table.getAssociatedTable().setImageResource(R.drawable.tb4two_vertical_occupied);
                    } else if (table.getSeatingCapacity() == Constants.SEAT_CAP_TEN) {
                        table.getAssociatedTable().setImageResource(R.drawable.tb4ten_occupied);
                    }

                }
            });
        }
    }

}

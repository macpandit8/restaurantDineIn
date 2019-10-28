package com.example.restaurantdine_in;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.example.restaurantdine_in.menu.FoodCategorySelectionFragment;
import com.example.restaurantdine_in.adapters.OrderItemListAdapter;

import java.util.ArrayList;

public class MenuActivity extends BaseActivity implements View.OnClickListener{

    private OrderItemListAdapter orderItemListAdapter = null;

    ListView orderListView = null;

    ArrayList<Integer> itemCount = new ArrayList<>();
    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<String> itemComment = new ArrayList<>();
    ArrayList<Double> itemAmount = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MenuScreenTheme);
        setContentView(R.layout.activity_menu);

        setupActionBar();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.foodItemSelectionFragmentContainer, new FoodCategorySelectionFragment());
        fragmentTransaction.commit();

        itemCount.add(5);
        itemCount.add(4);
        itemCount.add(5);
        itemCount.add(5);
        itemCount.add(5);
        itemCount.add(5);
        itemCount.add(5);
        itemCount.add(5);
        itemCount.add(5);
        itemCount.add(5);
        itemCount.add(5);
        itemCount.add(5);

        itemName.add("Item");
        itemName.add("Item");
        itemName.add("Item");
        itemName.add("Item");
        itemName.add("Item");
        itemName.add("Item");
        itemName.add("Item");
        itemName.add("Item");
        itemName.add("Item");
        itemName.add("Item");
        itemName.add("Item");

        itemComment.add("Any Comment");
        itemComment.add("Any Comment");
        itemComment.add("Any Comment");
        itemComment.add("Any Comment");
        itemComment.add("Any Comment");
        itemComment.add("Any Comment");
        itemComment.add("Any Comment");
        itemComment.add("Any Comment");
        itemComment.add("Any Comment");
        itemComment.add("Any Comment");
        itemComment.add("Any Comment");
        itemComment.add("Any Comment");

        itemAmount.add(25.50);
        itemAmount.add(25.55);
        itemAmount.add(25.50);
        itemAmount.add(25.50);
        itemAmount.add(25.50);
        itemAmount.add(25.50);
        itemAmount.add(25.50);
        itemAmount.add(25.50);
        itemAmount.add(25.50);
        itemAmount.add(25.50);
        itemAmount.add(25.50);
        itemAmount.add(25.50);

        orderListView = (ListView) findViewById(R.id.orderListView);

        orderItemListAdapter =new OrderItemListAdapter(this, itemCount, itemName, itemComment, itemAmount);
        orderListView.setAdapter(orderItemListAdapter);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0) {
                    Toast.makeText(MenuActivity.this, "item clicked", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    /**
     * Method is called to setup action bar and onClickListeners for following buttons
     *
     * Back Button onClickListener
     * Reset Button onClickListener
     * Place Order Button onClickListener
     */
    private void setupActionBar() {
        setupActionBar("Table Number",

                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {    //Back Button
                        finish();
                    }
                },

                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {    //Reset Button
                        Log.i("mayank", "reset clicked");
                    }
                },

                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {    //Place Order Button
                        Log.i("mayank", "place order clicked");
                    }
                });
    }

    @Override
    public void onClick(View view) {

    }
}

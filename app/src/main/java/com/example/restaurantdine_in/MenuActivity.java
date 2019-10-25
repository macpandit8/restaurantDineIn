package com.example.restaurantdine_in;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantdine_in.dialogs.FoodCategoryDialogFragment;
import com.example.restaurantdine_in.menu.MenuCategory;
import com.example.restaurantdine_in.adapters.OrderItemListAdapter;

import java.util.ArrayList;

public class MenuActivity extends BaseActivity implements View.OnClickListener{

    private OrderItemListAdapter orderItemListAdapter = null;

    private TextView appetizersTextview, vegetarianTextview, chickenTextview, lambTextview, seaFoodTextview, breadsTextview, drinksTextview;

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

        appetizersTextview = findViewById(R.id.appetizersTextview);
        vegetarianTextview = findViewById(R.id.vegetarianTextview);
        chickenTextview = findViewById(R.id.chickenTextview);
        lambTextview = findViewById(R.id.lambTextview);
        seaFoodTextview = findViewById(R.id.seaFoodTextview);
        breadsTextview = findViewById(R.id.breadsTextview);
        drinksTextview = findViewById(R.id.drinksTextview);

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

    private void onClickListenerForMenuCategory(final ArrayList<MenuCategory> menuCategories) {

        for(final MenuCategory menuCategory : menuCategories) {
            menuCategory.getMenuCategory().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FoodCategoryDialogFragment foodCategoryDialogFragment = FoodCategoryDialogFragment.newInstance(MenuActivity.this, menuCategory.getCategoryName());
                    foodCategoryDialogFragment.setCancelable(false);
                    foodCategoryDialogFragment.show(getSupportFragmentManager(), menuCategory.getCategoryName());

                }
            });
        }
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

    public static class CustomScrollListener implements AbsListView.OnScrollListener {
        private Activity mActivity;

        public CustomScrollListener(Activity mContext) {
            mActivity = mContext;
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            // do nothing
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (SCROLL_STATE_TOUCH_SCROLL == scrollState) {
                View currentFocus = mActivity.getCurrentFocus();
                if (currentFocus != null) {
                    currentFocus.clearFocus();
                }
            }
        }
    }
}

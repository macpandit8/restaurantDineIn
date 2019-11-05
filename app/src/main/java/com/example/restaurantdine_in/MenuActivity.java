package com.example.restaurantdine_in;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.restaurantdine_in.dialogs.DialogBoxHelper;
import com.example.restaurantdine_in.food_selection.FoodCategorySelectionFragment;
import com.example.restaurantdine_in.adapters.OrderItemListAdapter;
import com.example.restaurantdine_in.food_selection.FoodItemSelectionFragment;
import com.example.restaurantdine_in.food_selection.IAddItemsToOrderListener;
import com.example.restaurantdine_in.food_selection.IFoodItemSelectionFragmentListener;
import com.example.restaurantdine_in.food_selection.IOnEditTextDialogListener;

import java.util.ArrayList;

public class MenuActivity extends BaseActivity implements View.OnClickListener, IOnEditTextDialogListener, IAddItemsToOrderListener {

    private OrderItemListAdapter orderItemListAdapter = null;

    ListView orderListView = null;
    TextView invoiceTotalTV = null;

    ArrayList<Integer> foodItemCountList = new ArrayList<>();
    ArrayList<String> foodItemNameList = new ArrayList<>();
    ArrayList<String> foodItemCommentList = new ArrayList<>();
    ArrayList<Double> foodItemPriceList = new ArrayList<>();

    private IFoodItemSelectionFragmentListener foodItemSelectionFragmentListener;

    FoodItemSelectionFragment foodItemSelectionFragment;

    double totalBill = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MenuScreenTheme);
        setContentView(R.layout.activity_menu);

        setupActionBar();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.foodItemSelectionFragmentContainer, new FoodCategorySelectionFragment());
        fragmentTransaction.commit();

        invoiceTotalTV = findViewById(R.id.invoiceTotalTV);
        invoiceTotalTV.setText(String.valueOf(totalBill));
        orderListView = (ListView) findViewById(R.id.orderListView);

        orderItemListAdapter =new OrderItemListAdapter(this, foodItemCountList, foodItemNameList, foodItemCommentList, foodItemPriceList);
        orderListView.setAdapter(orderItemListAdapter);
        orderListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                DialogBoxHelper.showDialog(MenuActivity.this, getString(R.string.delete_caps), getString(R.string.cancel_caps), getString(R.string.msg_do_you_want_to_delete),
                        true, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                foodItemCountList.remove(position);
                                foodItemCommentList.remove(position);
                                foodItemNameList.remove(position);
                                foodItemPriceList.remove(position);
                                notifyDataSetChangedAndCalculateTotalBill();
                            }
                        }, null).show();
                return false;
            }
        });
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
                        if(foodItemCountList.isEmpty() && foodItemNameList.isEmpty() && foodItemCommentList.isEmpty() && foodItemPriceList.isEmpty()) {
                            finish();
                        } else {
                            DialogBoxHelper.showDialog(MenuActivity.this, getString(R.string.yes_caps), getString(R.string.no_caps), getString(R.string.msg_discard_changes_go_back)
                                    , true, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    clearOrderLists();
                                    finish();
                                }
                            }, null).show();
                        }
                    }
                },

                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {    //Reset Button
                        if(!foodItemCountList.isEmpty() && !foodItemNameList.isEmpty() && !foodItemCommentList.isEmpty() && !foodItemPriceList.isEmpty()) {
                            clearOrderLists();
                            notifyDataSetChangedAndCalculateTotalBill();
                        }
                    }
                },

                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {    //Place Order Button
                        Log.i("mayank", "place order clicked");
                    }
                });
    }

    /**
     * Call this method when you want to clear whole order
     * and reset all lists
     */
    private void clearOrderLists() {
        foodItemCountList.clear();
        foodItemNameList.clear();
        foodItemCommentList.clear();
        foodItemPriceList.clear();
    }

    /**
     * Call this method whenever item is added/removed from the Order
     */
    private void notifyDataSetChangedAndCalculateTotalBill() {
        orderItemListAdapter.notifyDataSetChanged();
        totalBill = 0.00;
        if(!foodItemPriceList.isEmpty()) {
            for (double itemPrice : foodItemPriceList) {
                totalBill = totalBill + itemPrice;
            }
        }
        invoiceTotalTV.setText(String.valueOf(totalBill));
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDoneClicked(Context context, int position, String aText, Fragment fragment, boolean fromOrderItemList) {
        if(fromOrderItemList) {

        } else {
//            foodItemSelectionFragmentListener.setFoodQty(aText);
            onAttachFragment(fragment);
            foodItemSelectionFragment.setFoodQty(position, aText);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof FoodItemSelectionFragment) {
            foodItemSelectionFragment = (FoodItemSelectionFragment) fragment;
        }
    }

    @Override
    public void setOrderList(ArrayList<Integer> foodCountList, ArrayList<String> foodNamesList, ArrayList<Double> foodItemPriceList) {
        if (foodCountList.size() == foodNamesList.size() && foodNamesList.size() == foodItemPriceList.size()) {

            this.foodItemCountList.addAll(foodCountList);
            this.foodItemNameList.addAll(foodNamesList);
            this.foodItemPriceList.addAll(foodItemPriceList);

            for(int i = 0; i < foodCountList.size(); i++) {
                foodItemCommentList.add("");
            }
        }
        notifyDataSetChangedAndCalculateTotalBill();
    }
}

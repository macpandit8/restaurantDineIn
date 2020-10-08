package com.example.restaurantdine_in.food_selection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.restaurantdine_in.BaseActivity;
import com.example.restaurantdine_in.Constants;
import com.example.restaurantdine_in.R;
import com.example.restaurantdine_in.dialogs.DialogBoxHelper;
import com.example.restaurantdine_in.dialogs.EditTextDialogFragment;
import com.example.restaurantdine_in.adapters.OrderItemListAdapter;
import com.example.restaurantdine_in.printerLib.CommonAlertDialogFragment;
import com.example.restaurantdine_in.printerLib.Communication;
import com.example.restaurantdine_in.printerLib.ILocalizeReceipts;
import com.example.restaurantdine_in.printerLib.ModelCapability;
import com.example.restaurantdine_in.printerLib.PrinterFunctions;
import com.example.restaurantdine_in.printerLib.PrinterSettingManager;
import com.example.restaurantdine_in.printerLib.PrinterSettings;
import com.starmicronics.starioextension.StarIoExt;

import java.util.ArrayList;

public class PlaceOrderActivity extends BaseActivity implements View.OnClickListener, IOnEditTextDialogListener, IAddItemsToOrderListener,  CommonAlertDialogFragment.Callback{

    private OrderItemListAdapter orderItemListAdapter = null;

    ListView orderListView = null;
    TextView invoiceTotalTV = null;

    ArrayList<Integer> foodItemCountList = new ArrayList<>();
    ArrayList<String> foodItemNameList = new ArrayList<>();
    ArrayList<String> foodItemCommentList = new ArrayList<>();
    ArrayList<Double> foodItemPriceList = new ArrayList<>();

    private boolean mIsForeground;

    private IFoodItemSelectionFragmentListener foodItemSelectionFragmentListener;

    FoodItemSelectionFragment foodItemSelectionFragment;

    double totalBill = 0.00;
    int tableNo;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MenuScreenTheme);
        setContentView(R.layout.activity_place_order);

        if(getIntent().getExtras() != null) {
            bundle = getIntent().getExtras();
            tableNo = bundle.getInt(Constants.TABLE);
        }

        setupActionBar();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.foodItemSelectionFragmentContainer, new FoodCategorySelectionFragment());
        fragmentTransaction.commit();

        invoiceTotalTV = findViewById(R.id.invoiceTotalTV);
        invoiceTotalTV.setText(String.valueOf(totalBill));
        orderListView = (ListView) findViewById(R.id.orderListView);

        orderItemListAdapter =new OrderItemListAdapter(this, foodItemCountList, foodItemNameList, foodItemCommentList, foodItemPriceList);
        orderListView.setAdapter(orderItemListAdapter);

        /**
         * Delete the item from order list
         */
        orderListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                DialogBoxHelper.showDialog(PlaceOrderActivity.this, getString(R.string.delete_caps), getString(R.string.cancel_caps),
                        getString(R.string.msg_do_you_want_to_delete), true, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                foodItemCountList.remove(position);
                                foodItemCommentList.remove(position);
                                foodItemNameList.remove(position);
                                foodItemPriceList.remove(position);
                                notifyDataSetChangedAndCalculateTotalBill();
                            }
                        }, null).show();
                return true;
            }
        });

        /**
         * Add comments to the item in the order list
         */
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                EditTextDialogFragment editTextDialogFragment = EditTextDialogFragment.newInstance(PlaceOrderActivity.this, position,
                        "Add comment for "+foodItemNameList.get(position), getString(R.string.add_caps), getString(R.string.cancel_caps),
                        null, true);
                editTextDialogFragment.setCancelable(true);
                editTextDialogFragment.show(getSupportFragmentManager(), "");
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        mIsForeground = false;
    }


    /**
     * Method is called to setup action bar and onClickListeners for following buttons
     *
     * Back Button onClickListener
     * Reset Button onClickListener
     * Place Order Button onClickListener
     */
    private void setupActionBar() {
        setupActionBar(Constants.TABLE + " NO " + tableNo,

                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {    //Back Button
                        if(foodItemCountList.isEmpty() && foodItemNameList.isEmpty() && foodItemCommentList.isEmpty() && foodItemPriceList.isEmpty()) {
                            finish();
                        } else {
                            DialogBoxHelper.showDialog(PlaceOrderActivity.this, getString(R.string.yes_caps), getString(R.string.no_caps), getString(R.string.msg_discard_changes_go_back)
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
                            DialogBoxHelper.showDialog(PlaceOrderActivity.this, getString(R.string.yes_caps), getString(R.string.no_caps), getString(R.string.msg_clear_order)
                                    , true, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            clearOrderLists();
                                            notifyDataSetChangedAndCalculateTotalBill();
                                        }
                                    }, null).show();
                        }
                    }
                },

                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {    //Place Order Button
                        mIsForeground = true;
                        placeOrderAndSendPrintCommand();
                    }
                });
    }

    private void placeOrderAndSendPrintCommand() {

        progressDialog.show();

        byte[] commands;

        PrinterSettingManager settingManager = new PrinterSettingManager(this);
        PrinterSettings settings = settingManager.getPrinterSettings();

        StarIoExt.Emulation emulation = ModelCapability.getEmulation(settings.getModelIndex());
        int paperSize = settings.getPaperSize();

        ILocalizeReceipts localizeReceipts = ILocalizeReceipts.createLocalizeReceipts(false, paperSize);

        commands = PrinterFunctions.createTextReceiptData(emulation, localizeReceipts, false, tableNo, foodItemCountList, foodItemNameList, foodItemCommentList);

        if (settings != null) {
            Communication.sendCommands(this, commands, settings.getPortName(), settings.getPortSettings(), 10000, 30000, this, mCallback);
        }

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
            foodItemCommentList.set(position, aText);
            orderItemListAdapter.notifyDataSetChanged();
        } else {
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
                foodItemCommentList.add(" ");
            }
        }
        notifyDataSetChangedAndCalculateTotalBill();
    }

    private final Communication.SendCallback mCallback = new Communication.SendCallback() {
        @Override
        public void onStatus(Communication.CommunicationResult communicationResult) {
            if (!mIsForeground) {
                return;
            }

            if (progressDialog != null) {
                progressDialog.dismiss();
            }

            CommonAlertDialogFragment dialog = CommonAlertDialogFragment.newInstance("CommResultDialog");
            dialog.setTitle("Communication Result");
            dialog.setMessage(Communication.getCommunicationResultMessage(communicationResult));
            dialog.setPositiveButton("OK");
            dialog.show(getSupportFragmentManager());
        }
    };

    @Override
    public void onDialogResult(String tag, Intent data) {
        //Do nothing
    }
}

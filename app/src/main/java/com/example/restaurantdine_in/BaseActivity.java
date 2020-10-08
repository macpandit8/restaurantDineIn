package com.example.restaurantdine_in;

import androidx.fragment.app.FragmentActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurantdine_in.dialogs.DialogBoxHelper;

public class BaseActivity extends FragmentActivity {

    TextView menuScreenTitleBar = null;
    ImageView imgBack = null;
    Button resetButton = null;
    Button placeOrderButton = null;
    public AlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = DialogBoxHelper.progressDialog(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
//            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);

            View actionBarView ;
            if(layoutResID == R.layout.activity_place_order) {
                actionBarView =View.inflate(this, R.layout.menu_screen_actionbar, null);
                menuScreenTitleBar = actionBarView.findViewById(R.id.menuScreenTitleBar);
                imgBack = actionBarView.findViewById(R.id.backButton);
                resetButton = actionBarView.findViewById(R.id.resetButton);
                placeOrderButton = actionBarView.findViewById(R.id.placeOrderButton);
                getActionBar().setCustomView(actionBarView);
            }

        }

    }

    /**
     * Setting up action bar with one back button and two event buttons
     *
     * @param title
     * @param onBackBtnClickListener
     * @param onResetClickListener
     * @param onPlaceOrderClickListener
     */
    public void setupActionBar(String title, View.OnClickListener onBackBtnClickListener, View.OnClickListener onResetClickListener, View. OnClickListener onPlaceOrderClickListener) {
        menuScreenTitleBar.setText(title);
        imgBack.setOnClickListener(onBackBtnClickListener);
        resetButton.setOnClickListener(onResetClickListener);
        placeOrderButton.setOnClickListener(onPlaceOrderClickListener);
    }

    @Override
    public void onBackPressed() {
        if(imgBack != null && imgBack.isEnabled()) {
            imgBack.performClick();
        } else {
            super.onBackPressed();
        }
    }
}

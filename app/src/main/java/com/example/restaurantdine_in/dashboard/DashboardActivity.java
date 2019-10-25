package com.example.restaurantdine_in.dashboard;

import android.os.Bundle;

import com.example.restaurantdine_in.BaseActivity;
import com.example.restaurantdine_in.R;

public class DashboardActivity extends BaseActivity {

    private IDashboardActivityListener mDashboardActivityListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_dashboard);

        mDashboardActivityListener = (DashboardFragment) getSupportFragmentManager().findFragmentById(R.id.dashboard_fragment);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}

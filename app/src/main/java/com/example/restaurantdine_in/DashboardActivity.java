package com.example.restaurantdine_in;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class DashboardActivity extends BaseActivity {

    private IDashboardActivityListener mDashboardActivityListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mDashboardActivityListener = (DashboardFragment) getSupportFragmentManager().findFragmentById(R.id.dashboard_fragment);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}

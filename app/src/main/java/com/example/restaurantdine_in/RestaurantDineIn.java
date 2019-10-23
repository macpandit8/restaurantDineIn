package com.example.restaurantdine_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.restaurantdine_in.Dashboard.DashboardActivity;

public class RestaurantDineIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SPLASH_SCREEN_BG);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToDashboard();
            }
        }, Constants.SPLASH_SCREEN_DISPLAY_TIME);
    }

    private void goToDashboard() {
        Intent dashboardIntent = new Intent(this, DashboardActivity.class);
        startActivity(dashboardIntent);
    }
}

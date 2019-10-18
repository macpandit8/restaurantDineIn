package com.example.restaurantdine_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MenuActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MenuScreenTheme);
        setContentView(R.layout.activity_menu);

        setupActionBar();
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

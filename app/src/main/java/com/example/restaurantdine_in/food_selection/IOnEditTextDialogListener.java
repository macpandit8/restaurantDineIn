package com.example.restaurantdine_in.food_selection;

import android.content.Context;

import androidx.fragment.app.Fragment;

public interface IOnEditTextDialogListener {

    void onDoneClicked(Context context, int position, String aText, Fragment fragment, boolean fromOrderItemList);

}

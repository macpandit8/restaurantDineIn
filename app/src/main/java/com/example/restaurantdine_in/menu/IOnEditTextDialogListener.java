package com.example.restaurantdine_in.menu;

import android.app.Dialog;
import android.content.Context;

import androidx.fragment.app.Fragment;

public interface IOnEditTextDialogListener {

    void onDoneClicked(Context context, int position, String aText, Fragment fragment, boolean fromOrderItemList);

}

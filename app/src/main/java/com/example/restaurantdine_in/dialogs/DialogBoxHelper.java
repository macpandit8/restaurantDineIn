package com.example.restaurantdine_in.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.ContextThemeWrapper;

import com.example.restaurantdine_in.R;

public class DialogBoxHelper {
    public static AlertDialog showDialog(Context context, String positiveBtn, String negativeBtn, String message, Boolean cancelable,
                                         DialogInterface.OnClickListener listener1, DialogInterface.OnClickListener listener2) {

        ContextThemeWrapper themedContext;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            themedContext = new ContextThemeWrapper(context, android.R.style.Theme_Holo_Light_Dialog);
        } else {
            themedContext = new ContextThemeWrapper(context, android.R.style.Theme_Light);
        }
        AlertDialog.Builder ab = new AlertDialog.Builder(themedContext);
        ab.setTitle("Alert");
        ab.setMessage(message);
        ab.setPositiveButton(positiveBtn, listener1);
        ab.setNegativeButton(negativeBtn, listener2);
        ab.setCancelable(cancelable);
        ab.setIcon(R.drawable.notification);

        return ab.create();
    }
}

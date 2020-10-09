package com.example.restaurantdine_in;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.restaurantdine_in.dashboard.DashboardFragment;
import com.example.restaurantdine_in.dialogs.DialogBoxHelper;
import com.example.restaurantdine_in.printerLib.CommonAlertDialogFragment;
import com.example.restaurantdine_in.printerLib.Communication;
import com.example.restaurantdine_in.printerLib.ILocalizeReceipts;
import com.example.restaurantdine_in.printerLib.ModelCapability;
import com.example.restaurantdine_in.printerLib.PrinterFunctions;
import com.example.restaurantdine_in.printerLib.PrinterSettingManager;
import com.example.restaurantdine_in.printerLib.PrinterSettings;
import com.starmicronics.starioextension.StarIoExt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AboutActivity extends BaseActivity implements View.OnClickListener, CommonAlertDialogFragment.Callback{

    Button testPrinterBtn;
    TextView printerStatusTV;

    int tableNo;
    ArrayList<Integer> countList;
    ArrayList<String> nameList;
    ArrayList<String> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        printerStatusTV = findViewById(R.id.printerStatusTV);

        testPrinterBtn = findViewById(R.id.testPrinterBtn);
        testPrinterBtn.setOnClickListener(this);

        countList = new ArrayList<>();
        nameList = new ArrayList<>();
        commentList = new ArrayList<>();
        initializeTestPrintData();
    }

    private void initializeTestPrintData() {
        tableNo = 10;
        countList.add(1);
        countList.add(5);
        countList.add(10);

        nameList.add("Item1");
        nameList.add("Item no 2");
        nameList.add("item #3");

        commentList.add(" ");
        commentList.add("Comment 2");
        commentList.add("Comment number #3");
    }

    @Override
    public void onPause() {
        super.onPause();
        mIsForeground = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (DashboardFragment.hasKitchenPrinterConfiguration(this)) {
            printerStatusTV.setText(getString(R.string.connected));
            printerStatusTV.setTextColor(ContextCompat.getColor(this, R.color.green));
        } else {
            printerStatusTV.setText(getString(R.string.disconnected));
            printerStatusTV.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.testPrinterBtn:
                if (DashboardFragment.hasKitchenPrinterConfiguration(this)) {
                    DialogBoxHelper.showDialog(this, getString(R.string.yes_caps), getString(R.string.no_caps), getString(R.string.confirm_test_printing), true
                            , new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    progressDialog.show();
                                    mIsForeground = true;
                                    printTest();
                                }
                            }, null).show();
                } else {
                    DialogBoxHelper.showNotifyingDialog(this, getString(R.string.ok_caps), getString(R.string.no_printer_configuration), true, null).show();
                }
                break;

        }
    }

    private void printTest() {
        LocalPrinter.print(this, true, tableNo, countList, nameList, commentList, mCallback);
    }

    @Override
    public void onDialogResult(String tag, Intent data, String messageString) {
        //Do nothing
    }
}
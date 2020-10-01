package com.example.restaurantdine_in.printerLib;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.restaurantdine_in.BaseActivity;
import com.example.restaurantdine_in.R;
import com.starmicronics.stario.PortInfo;
import com.starmicronics.stario.StarIOPort;
import com.starmicronics.stario.StarIOPortException;

import java.util.ArrayList;
import java.util.List;

public class KitchenPrinterActivity extends BaseActivity implements CommonAlertDialogFragment.Callback {

    ListView printerListView;
    Button refreshPrinterSearch;
    private String mMacAddress;
    AlertDialog dialog;

    private static final String INTERFACE_SELECT_DIALOG = "InterfaceSelectDialog";
    private static final String PORT_NAME_INPUT_DIALOG = "PortNameInputDialog";

    private ArrayList<PrinterInfo> printersList;
    private PrinterListViewAdapter printerListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_printer);

        initializeProgressDialog();

        printersList = new ArrayList<>();

        printerListView = findViewById(R.id.printerListView);

        printerListViewAdapter = new PrinterListViewAdapter(this, printersList);
        printerListView.setAdapter(printerListViewAdapter);

        refreshPrinterSearch = findViewById(R.id.refreshPrinterSearch);
        refreshPrinterSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePrinterList();
            }
        });
        updatePrinterList();

    }

    private void initializeProgressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.progress_bar_dialog);
        dialog = builder.create();
    }

    private void updatePrinterList() {
//        adapter.clear();

        InterfaceSelectDialogFragment dialog = InterfaceSelectDialogFragment.newInstance(INTERFACE_SELECT_DIALOG);
        dialog.show(getSupportFragmentManager());
    }

    @Override
    public void onDialogResult(String tag, Intent data) {
        switch (tag) {
            case INTERFACE_SELECT_DIALOG: {
                String[] selectedInterfaces = data.getStringArrayExtra(PrinterSettingConstant.BUNDLE_KEY_INTERFACE);
                boolean isCanceled = data.hasExtra(CommonAlertDialogFragment.LABEL_NEGATIVE);

                if (selectedInterfaces != null) {

                    if (selectedInterfaces.length <= 1 && selectedInterfaces[0].equals(PrinterSettingConstant.IF_TYPE_MANUAL)) {
                        mMacAddress = "";

                        PrinterSettingManager settingManager = new PrinterSettingManager(this);
                        PrinterSettings settings = settingManager.getPrinterSettings(0);

                        String presentPortName = "";

                        if (settings != null) {
                            presentPortName = settings.getPortName();
                        }

                        PortNameDialogFragment dialog = PortNameDialogFragment.newInstance(PORT_NAME_INPUT_DIALOG, presentPortName);

                        dialog.show(getSupportFragmentManager());
                    } else {
                        for (String selectedInterface : selectedInterfaces) {
                            SearchTask searchTask = new SearchTask();
                            searchTask.execute(selectedInterface);
                        }

                        dialog.show();
                    }
                } else if (isCanceled) {
                    finish();
                }
                break;
            }
        }
    }

    /**
     * Printer search task.
     */
    private class SearchTask extends AsyncTask<String, Void, Void> {
        private List<PortInfo> mPortList;

        SearchTask() {
            super();
        }

        @Override
        protected Void doInBackground(String... interfaceType) {
            try {
                mPortList = StarIOPort.searchPrinter(interfaceType[0], KitchenPrinterActivity.this);
            }
            catch (StarIOPortException e) {
                mPortList = new ArrayList<>();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void doNotUse) {
            for (PortInfo info : mPortList) {
                addItem(info);
            }

            if (dialog != null) {
                dialog.dismiss();
            }

            printerListViewAdapter.notifyDataSetChanged();
        }
    }

    private void addItem(PortInfo info) {

        printersList.clear();

        String modelName;
        String portName;
        String macAddress;

        // --- Bluetooth ---
        // It can communication used device name(Ex.BT:Star Micronics) at bluetooth.
        // If android device has paired two same name device, can't choose destination target.
        // If used Mac Address(Ex. BT:00:12:3f:XX:XX:XX) at Bluetooth, can choose destination target.
        if (info.getPortName().startsWith(PrinterSettingConstant.IF_TYPE_BLUETOOTH)) {
            modelName  = info.getPortName().substring(PrinterSettingConstant.IF_TYPE_BLUETOOTH.length());
            portName   = PrinterSettingConstant.IF_TYPE_BLUETOOTH + info.getMacAddress();
            macAddress = info.getMacAddress();
        }
        else {
            modelName  = info.getModelName();
            portName   = info.getPortName();
            macAddress = info.getMacAddress();
        }



//        if (   info.getPortName().startsWith(PrinterSettingConstant.IF_TYPE_ETHERNET)
//                || info.getPortName().startsWith(PrinterSettingConstant.IF_TYPE_BLUETOOTH)) {
//            textList.add(new TextInfo("(" + macAddress + ")", R.id.macAddressTextView));
//        }

        PrinterSettingManager settingManager = new PrinterSettingManager(this);
        PrinterSettings       settings       = settingManager.getPrinterSettings(0);

        if (settings != null && settings.getPortName().equals(portName)) {
            printersList.add(new PrinterInfo(modelName, portName, macAddress, true));
        }
        else {
            printersList.add(new PrinterInfo(modelName, portName, macAddress, false));
        }

    }
}
package com.example.restaurantdine_in.printerLib;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.restaurantdine_in.BaseActivity;
import com.example.restaurantdine_in.R;
import com.example.restaurantdine_in.dialogs.DialogBoxHelper;
import com.starmicronics.stario.PortInfo;
import com.starmicronics.stario.StarIOPort;
import com.starmicronics.stario.StarIOPortException;

import java.util.ArrayList;
import java.util.List;

public class KitchenPrinterActivity extends BaseActivity implements CommonAlertDialogFragment.Callback {

    ListView printerListView;
    Button refreshPrinterSearch;
    private String mMacAddress;
    private String mModelName;
    private String mPortName;
    AlertDialog mProgressDialog;

    private int       mModelIndex;
    private String    mPortSettings;
    private int       mPrinterSettingIndex;
    private int       mPaperSize;
    private Boolean   mDrawerOpenStatus;

    private static final String INTERFACE_SELECT_DIALOG = "InterfaceSelectDialog";
    private static final String PORT_NAME_INPUT_DIALOG = "PortNameInputDialog";
    private static final String MODEL_CONFIRM_DIALOG             = "ModelConfirmDialog";
    private static final String MODEL_SELECT_DIALOG_0            = "ModelSelectDialog0";
    private static final String MODEL_SELECT_DIALOG_1            = "ModelSelectDialog1";
    private static final String DRAWER_OPEN_ACTIVE_SELECT_DIALOG = "DrawerOpenSelectDialog";
    private static final String PAPER_SIZE_SELECT_DIALOG         = "PaperSizeSelectDialog";

    private ArrayList<PrinterInfo> printersList;
    private PrinterListViewAdapter printerListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_printer);

        mProgressDialog = DialogBoxHelper.progressDialog(this);

        mPrinterSettingIndex = 0;

        printersList = new ArrayList<>();

        printerListView = findViewById(R.id.printerListView);

        printerListViewAdapter = new PrinterListViewAdapter(this, printersList);
        printerListView.setAdapter(printerListViewAdapter);
        printerListView.setOnItemClickListener(onPrinterSelectedListener());

        refreshPrinterSearch = findViewById(R.id.refreshPrinterSearch);
        refreshPrinterSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePrinterList();
            }
        });
        updatePrinterList();

    }

    private AdapterView.OnItemClickListener onPrinterSelectedListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int model = ModelCapability.getModel(printersList.get(position).getModelName().toString());

                mPortName = printersList.get(position).getPortName();
                mModelName = printersList.get(position).getModelName();
                mMacAddress = printersList.get(position).getMacAddress();

                if (model == ModelCapability.NONE) {
                    ModelSelectDialogFragment dialog = ModelSelectDialogFragment.newInstance(MODEL_SELECT_DIALOG_0);
                    dialog.show(getSupportFragmentManager());
                }
                else {
                    ModelConfirmDialogFragment dialog = ModelConfirmDialogFragment.newInstance(MODEL_CONFIRM_DIALOG, model);
                    dialog.show(getSupportFragmentManager());
                }
            }
        };
    }

    private void updatePrinterList() {
//        adapter.clear();

        InterfaceSelectDialogFragment dialog = InterfaceSelectDialogFragment.newInstance(INTERFACE_SELECT_DIALOG);
        dialog.show(getSupportFragmentManager());
    }

    @Override
    public void onDialogResult(String tag, Intent data, String messageString) {
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

                        mProgressDialog.show();
                    }
                } else if (isCanceled) {
                    finish();
                }
                break;
            }
            case MODEL_CONFIRM_DIALOG: {
                boolean isPressedYes = data.hasExtra(CommonAlertDialogFragment.LABEL_POSITIVE);

                if (isPressedYes) {
                    mModelIndex   = data.getIntExtra(PrinterSettingConstant.BUNDLE_KEY_MODEL_INDEX, ModelCapability.NONE);
                    mPortSettings = ModelCapability.getPortSettings(mModelIndex);

                    showPaperSizeOrDrawerOpenActiveSelectDialog();
                }
                else {
                    ModelSelectDialogFragment dialog = ModelSelectDialogFragment.newInstance(MODEL_SELECT_DIALOG_0);
                    dialog.show(getSupportFragmentManager());
                }
                break;
            }
            case MODEL_SELECT_DIALOG_0: {
                boolean isCanceled = data.hasExtra(CommonAlertDialogFragment.LABEL_NEGATIVE);

                if (isCanceled) {
                    return;
                }

                mModelIndex   = data.getIntExtra(PrinterSettingConstant.BUNDLE_KEY_MODEL_INDEX, ModelCapability.NONE);
                mPortSettings = ModelCapability.getPortSettings(mModelIndex);

                showPaperSizeOrDrawerOpenActiveSelectDialog();
                break;
            }
            case MODEL_SELECT_DIALOG_1: {
                boolean isCanceled = data.hasExtra(CommonAlertDialogFragment.LABEL_NEGATIVE);

                if (isCanceled) {
                    return;
                }

                mModelIndex = data.getIntExtra(PrinterSettingConstant.BUNDLE_KEY_MODEL_INDEX, ModelCapability.NONE);
                mModelName  = ModelCapability.getModelTitle(mModelIndex);

                showPaperSizeOrDrawerOpenActiveSelectDialog();
                break;
            }
            case PAPER_SIZE_SELECT_DIALOG: {
                boolean isCanceled = data.hasExtra(CommonAlertDialogFragment.LABEL_NEGATIVE);

                if (isCanceled) {
                    return;
                }

                mPaperSize = data.getIntExtra(PrinterSettingConstant.BUNDLE_KEY_PAPER_SIZE, PrinterSettingConstant.PAPER_SIZE_THREE_INCH);

                if (ModelCapability.canSetDrawerOpenStatus(mModelIndex)) {
                    DrawerOpenActiveSelectDialogFragment dialog = DrawerOpenActiveSelectDialogFragment.newInstance(DRAWER_OPEN_ACTIVE_SELECT_DIALOG);
                    dialog.show(getSupportFragmentManager());
                }
                else {
                    mDrawerOpenStatus = true;
                    registerPrinter();

                    finish();
                }
                break;
            }
            case DRAWER_OPEN_ACTIVE_SELECT_DIALOG: {
                boolean isCanceled = data.hasExtra(CommonAlertDialogFragment.LABEL_NEGATIVE);

                if (isCanceled) {
                    return;
                }

                mDrawerOpenStatus = data.getBooleanExtra(PrinterSettingConstant.BUNDLE_KEY_DRAWER_OPEN_STATUS, false);
                registerPrinter();

                finish();
                break;
            }
        }
    }

    private void showPaperSizeOrDrawerOpenActiveSelectDialog() {
        // In the SDK sample, when setting up a backup device, the paper size (dot) is the same as the destination device.
        int destinationDevicePaperSize = PrinterSettingConstant.PAPER_SIZE_THREE_INCH;
        if (mPrinterSettingIndex != 0) {
            PrinterSettingManager settingManager = new PrinterSettingManager(this);
            PrinterSettings       settings       = settingManager.getPrinterSettings();

            destinationDevicePaperSize = settings.getPaperSize();
        }

        if (mModelIndex == ModelCapability.SP700) {
            if (mPrinterSettingIndex == 0) {    // Destination device
                mPaperSize = PrinterSettingConstant.PAPER_SIZE_DOT_THREE_INCH;
            }
            else {                              // Backup device
                mPaperSize = destinationDevicePaperSize;
            }

            DrawerOpenActiveSelectDialogFragment dialog = DrawerOpenActiveSelectDialogFragment.newInstance(DRAWER_OPEN_ACTIVE_SELECT_DIALOG);
            dialog.show(getSupportFragmentManager());
        }
        else if (mModelIndex == ModelCapability.BSC10) {
            if (mPrinterSettingIndex == 0) {    // Destination device
                mPaperSize = PrinterSettingConstant.PAPER_SIZE_ESCPOS_THREE_INCH;
            }
            else {                              // Backup device
                mPaperSize = destinationDevicePaperSize;
            }

            DrawerOpenActiveSelectDialogFragment dialog = DrawerOpenActiveSelectDialogFragment.newInstance(DRAWER_OPEN_ACTIVE_SELECT_DIALOG);
            dialog.show(getSupportFragmentManager());
        }
        else if (mModelIndex == ModelCapability.SK1_211_221_V211 || mModelIndex == ModelCapability.SK1_211_221_V211_Presenter) {
            if (mPrinterSettingIndex == 0) {    // Destination device
                mPaperSize = PrinterSettingConstant.PAPER_SIZE_SK1_TWO_INCH;
            }
            else {                              // Backup device
                mPaperSize = destinationDevicePaperSize;
            }

            mDrawerOpenStatus = true;
            registerPrinter();

            finish();
        }
        else if (mModelIndex == ModelCapability.SK1_311_321_V311 || mModelIndex == ModelCapability.SK1_311_V311_Presenter) {
            if (mPrinterSettingIndex == 0) {    // Destination device
                mPaperSize = PrinterSettingConstant.PAPER_SIZE_THREE_INCH;
            }
            else {                              // Backup device
                mPaperSize = destinationDevicePaperSize;
            }

            mDrawerOpenStatus = true;
            registerPrinter();

            finish();
        }
        else {
            if (mPrinterSettingIndex == 0) {    // Destination device
                PaperSizeSelectDialogFragment dialog = PaperSizeSelectDialogFragment.newInstance(PAPER_SIZE_SELECT_DIALOG);
                dialog.show(getSupportFragmentManager());
            }
            else {                              // Backup device
                mPaperSize = destinationDevicePaperSize;

                if (ModelCapability.canSetDrawerOpenStatus(mModelIndex)) {
                    DrawerOpenActiveSelectDialogFragment dialog = DrawerOpenActiveSelectDialogFragment.newInstance(DRAWER_OPEN_ACTIVE_SELECT_DIALOG);
                    dialog.show(getSupportFragmentManager());
                }
                else {
                    mDrawerOpenStatus = true;
                    registerPrinter();

                    finish();
                }
            }
        }
    }

    /**
     * Register printer information to SharedPreference.
     */
    private void registerPrinter() {
        PrinterSettingManager settingManager = new PrinterSettingManager(this);

        settingManager.storePrinterSettings(
                mPrinterSettingIndex,
                new PrinterSettings(mModelIndex, mPortName, mPortSettings, mMacAddress, mModelName, mDrawerOpenStatus, mPaperSize)
        );
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

            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
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
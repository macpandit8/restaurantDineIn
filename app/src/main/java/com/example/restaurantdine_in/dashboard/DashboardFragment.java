package com.example.restaurantdine_in.dashboard;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restaurantdine_in.Constants;
import com.example.restaurantdine_in.dialogs.DialogBoxHelper;
import com.example.restaurantdine_in.printerLib.CommonAlertDialogFragment;
import com.example.restaurantdine_in.printerLib.Communication;
import com.example.restaurantdine_in.printerLib.ILocalizeReceipts;
import com.example.restaurantdine_in.printerLib.KitchenPrinterActivity;
import com.example.restaurantdine_in.food_selection.PlaceOrderActivity;
import com.example.restaurantdine_in.R;
import com.example.restaurantdine_in.printerLib.ModelCapability;
import com.example.restaurantdine_in.printerLib.PrinterFunctions;
import com.example.restaurantdine_in.printerLib.PrinterSettingManager;
import com.example.restaurantdine_in.printerLib.PrinterSettings;
import com.starmicronics.starioextension.ICommandBuilder;
import com.starmicronics.starioextension.StarIoExt;

import java.util.ArrayList;

public class DashboardFragment extends Fragment implements IDashboardActivityListener, CommonAlertDialogFragment.Callback {

    ImageView tb1, tb2, tb3, tb4, tb5, tb6, tb7, tb8, tb9;
    ImageView kitchen, cashRegister;

    AlertDialog mProgressDialog;

    private DashboardActivity dashboardActivity = null;

    private boolean mIsForeground;

    @Override
    public void onPause() {
        super.onPause();
        mIsForeground = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        getKitchenConfiguration();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View dashboardView = inflater.inflate(R.layout.dashboard_fragment, container, false);

        dashboardActivity = (DashboardActivity) getContext();
        mProgressDialog = DialogBoxHelper.progressDialog(getActivity());

        tb1 = dashboardView.findViewById(R.id.tb1);
        tb2 = dashboardView.findViewById(R.id.tb2);
        tb3 = dashboardView.findViewById(R.id.tb3);
        tb4 = dashboardView.findViewById(R.id.tb4);
        tb5 = dashboardView.findViewById(R.id.tb5);
        tb6 = dashboardView.findViewById(R.id.tb6);
        tb7 = dashboardView.findViewById(R.id.tb7);
        tb8 = dashboardView.findViewById(R.id.tb8);
        tb9 = dashboardView.findViewById(R.id.tb9);

        kitchen = dashboardView.findViewById(R.id.kitchen);
        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kitchenIntent = new Intent(getActivity(), KitchenPrinterActivity.class);
                getActivity().startActivity(kitchenIntent);
            }
        });

        cashRegister = dashboardView.findViewById(R.id.cashRegister);
        cashRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsForeground = true;
                printTest();
            }
        });

        Table table1 = new Table(tb1, Constants.SEAT_CAP_FOUR, Constants.SQUARE_TABLE, Constants.TABLE_NO_1);
        Table table2 = new Table(tb2, Constants.SEAT_CAP_FOUR, Constants.SQUARE_TABLE, Constants.TABLE_NO_2);
        Table table3 = new Table(tb3, Constants.SEAT_CAP_FOUR, Constants.SQUARE_TABLE, Constants.TABLE_NO_3);
        Table table4 = new Table(tb4, Constants.SEAT_CAP_TWO, Constants.ROUND_TABLE, Constants.TABLE_NO_4);
        Table table5 = new Table(tb5, Constants.SEAT_CAP_FOUR, Constants.ROUND_TABLE, Constants.TABLE_NO_5);
        Table table6 = new Table(tb6, Constants.SEAT_CAP_FOUR, Constants.ROUND_TABLE, Constants.TABLE_NO_6);
        Table table7 = new Table(tb7, Constants.SEAT_CAP_FOUR, Constants.ROUND_TABLE, Constants.TABLE_NO_7);
        Table table8 = new Table(tb8, Constants.SEAT_CAP_TWO, Constants.SQUARE_TABLE, Constants.TABLE_NO_8);
        Table table9 = new Table(tb9, Constants.SEAT_CAP_TEN, Constants.SQUARE_TABLE, Constants.TABLE_NO_9);

        ArrayList<Table> tables = new ArrayList<>();
        tables.add(table1);
        tables.add(table2);
        tables.add(table3);
        tables.add(table4);
        tables.add(table5);
        tables.add(table6);
        tables.add(table7);
        tables.add(table8);
        tables.add(table9);

        onClickListener(tables);

        return dashboardView;
    }

    private void printTest() {
        //TODO: Code Cleanup required

        mProgressDialog.show();

        byte[] commands;

        PrinterSettingManager settingManager = new PrinterSettingManager(getActivity());
        PrinterSettings       settings       = settingManager.getPrinterSettings();

        StarIoExt.Emulation emulation = ModelCapability.getEmulation(settings.getModelIndex());
        int paperSize = settings.getPaperSize();

        ILocalizeReceipts localizeReceipts = ILocalizeReceipts.createLocalizeReceipts(0, paperSize);

        switch (1) {
            default:
            case 1:
                commands = PrinterFunctions.createTextReceiptData(emulation, localizeReceipts, false);
                break;
//            case 2:
//                commands = PrinterFunctions.createTextReceiptData(emulation, localizeReceipts, true);
//                break;
//            case 3:
//                commands = PrinterFunctions.createRasterReceiptData(emulation, localizeReceipts, getResources());
//                break;
//            case 4:
//                commands = PrinterFunctions.createScaleRasterReceiptData(emulation, localizeReceipts, getResources(), paperSize, true);
//                break;
//            case 5:
//                commands = PrinterFunctions.createScaleRasterReceiptData(emulation, localizeReceipts, getResources(), paperSize, false);
//                break;
//            case 6:
//                commands = PrinterFunctions.createCouponData(emulation, localizeReceipts, getResources(), paperSize, ICommandBuilder.BitmapConverterRotation.Normal);
//                break;
//            case 7:
//                commands = PrinterFunctions.createCouponData(emulation, localizeReceipts, getResources(), paperSize, ICommandBuilder.BitmapConverterRotation.Right90);
//                break;
//            case 8:
//                if (mBitmap != null) {
//                    commands = PrinterFunctions.createRasterData(emulation, mBitmap, paperSize, true);
//                }
//                else {
//                    commands = new byte[0];
//                }
//                break;
        }

        Communication.sendCommands(this, commands, settings.getPortName(), settings.getPortSettings(), 10000, 30000, getActivity(), mCallback);     // 10000mS!!!
    }

    private final Communication.SendCallback mCallback = new Communication.SendCallback() {
        @Override
        public void onStatus(Communication.CommunicationResult communicationResult) {
            if (!mIsForeground) {
                return;
            }

            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }

            CommonAlertDialogFragment dialog = CommonAlertDialogFragment.newInstance("CommResultDialog");
            dialog.setTitle("Communication Result");
            dialog.setMessage(Communication.getCommunicationResultMessage(communicationResult));
            dialog.setPositiveButton("OK");
            dialog.show(getChildFragmentManager());
        }
    };

    private void getKitchenConfiguration() {
        PrinterSettingManager settingManager = new PrinterSettingManager(getActivity());
        PrinterSettings settings       = settingManager.getPrinterSettings();

        boolean isDeviceSelected     = false;
        int     modelIndex           = ModelCapability.NONE;
        String  modelName            = "";
        boolean isBluetoothInterface = false;
        boolean isUsbInterface       = false;

        if (settings != null) {
            isDeviceSelected     = true;
            modelIndex           = settings.getModelIndex();
            modelName            = settings.getModelName();
            isBluetoothInterface = settings.getPortName().toUpperCase().startsWith("BT:");
            isUsbInterface       = settings.getPortName().toUpperCase().startsWith("USB:");
        }
        if (isDeviceSelected) {
            kitchen.setImageResource(R.drawable.kitchen_connected);
        } else {
            kitchen.setImageResource(R.drawable.kitchen_disconnected);
        }
    }

    private void onClickListener(ArrayList<Table> tables) {

        for(final Table table : tables) {
            table.getAssociatedTable().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getActivity(), PlaceOrderActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.TABLE, table.getTableNumber());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    if (table.getSeatingCapacity() == Constants.SEAT_CAP_FOUR) {
                        if (table.getShape().equals(Constants.SQUARE_TABLE)) {
                            table.getAssociatedTable().setImageResource(R.drawable.tb4four_square_occupied);
                        } else {
                            table.getAssociatedTable().setImageResource(R.drawable.tb4four_round_occupied);
                        }
                    } else if (table.getSeatingCapacity() == Constants.SEAT_CAP_TWO) {
                        table.getAssociatedTable().setImageResource(R.drawable.tb4two_vertical_occupied);
                    } else if (table.getSeatingCapacity() == Constants.SEAT_CAP_TEN) {
                        table.getAssociatedTable().setImageResource(R.drawable.tb4ten_occupied);
                    }

                }
            });
        }
    }

    @Override
    public void onDialogResult(String tag, Intent data) {
        //Do nothing
    }
}

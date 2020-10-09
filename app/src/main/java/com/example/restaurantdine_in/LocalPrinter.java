package com.example.restaurantdine_in;

import android.content.Context;

import com.example.restaurantdine_in.printerLib.Communication;
import com.example.restaurantdine_in.printerLib.ILocalizeReceipts;
import com.example.restaurantdine_in.printerLib.ModelCapability;
import com.example.restaurantdine_in.printerLib.PrinterFunctions;
import com.example.restaurantdine_in.printerLib.PrinterSettingManager;
import com.example.restaurantdine_in.printerLib.PrinterSettings;
import com.starmicronics.starioextension.StarIoExt;

import java.util.ArrayList;

public class LocalPrinter {

    public LocalPrinter() {
    }

    public static void print(Context context,
                             boolean isTestReceipt,
                             int tableNo,
                             ArrayList<Integer> foodItemCountList,
                             ArrayList<String> foodItemNameList,
                             ArrayList<String> foodItemCommentList,
                             Communication.SendCallback mCallback) {

        byte[] commands;

        PrinterSettingManager settingManager = new PrinterSettingManager(context);
        PrinterSettings settings = settingManager.getPrinterSettings();

        StarIoExt.Emulation emulation = ModelCapability.getEmulation(settings.getModelIndex());
        int paperSize = settings.getPaperSize();

        ILocalizeReceipts localizeReceipts = ILocalizeReceipts.createLocalizeReceipts(isTestReceipt, paperSize);

        commands = PrinterFunctions.createTextReceiptData(emulation, localizeReceipts, false, tableNo, foodItemCountList, foodItemNameList, foodItemCommentList);

        if (settings != null) {
            Communication.sendCommands(context, commands, settings.getPortName(), settings.getPortSettings(), 10000, 30000, context, mCallback);
        }
    }
}

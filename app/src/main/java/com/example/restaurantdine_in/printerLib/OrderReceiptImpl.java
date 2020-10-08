package com.example.restaurantdine_in.printerLib;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.starmicronics.starioextension.ICommandBuilder;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class OrderReceiptImpl extends ILocalizeReceipts {
    @Override
    public void append2inchTextReceiptData(ICommandBuilder builder, boolean utf8) {

    }

    @Override
    public void append3inchTextReceiptData(ICommandBuilder builder, boolean utf8) {

    }

    @Override
    public void append4inchTextReceiptData(ICommandBuilder builder, boolean utf8) {

    }

    @Override
    public Bitmap create2inchRasterReceiptImage() {
        return null;
    }

    @Override
    public Bitmap create3inchRasterReceiptImage() {
        return null;
    }

    @Override
    public Bitmap create4inchRasterReceiptImage() {
        return null;
    }

    @Override
    public Bitmap createCouponImage(Resources resources) {
        return null;
    }

    @Override
    public Bitmap createEscPos3inchRasterReceiptImage() {
        return null;
    }

    @Override
    public void appendEscPos3inchTextReceiptData(ICommandBuilder builder, boolean utf8) {

    }

    @Override
    public void appendDotImpact3inchTextReceiptData(ICommandBuilder builder, boolean utf8,
                                                    int tableNo,
                                                    ArrayList<Integer> foodItemCountList,
                                                    ArrayList<String> foodItemNameList,
                                                    ArrayList<String> foodItemCommentList) {

        Charset encoding;

        if (utf8) {
            encoding = Charset.forName("UTF-8");

            builder.appendCodePage(ICommandBuilder.CodePageType.UTF8);
        }
        else {
            encoding = Charset.forName("US-ASCII");

            builder.appendCodePage(ICommandBuilder.CodePageType.CP998);
        }

        builder.appendInternational(ICommandBuilder.InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendFontStyle(ICommandBuilder.FontStyleType.A);
        builder.appendAlignment(ICommandBuilder.AlignmentPosition.Center);
        builder.append((
                "TABLE\n").getBytes(encoding));

        builder.appendAlignment(ICommandBuilder.AlignmentPosition.Center);
        builder.appendMultiple((String.valueOf(tableNo)).getBytes(encoding), 2, 2);
        builder.append(("\n").getBytes(encoding));

        builder.appendAlignment(ICommandBuilder.AlignmentPosition.Left);

        builder.append(("Date:").getBytes(encoding));
        builder.append(getCurrentDate().getBytes(encoding));
        builder.append(("              ").getBytes(encoding));
        builder.append(("Time:").getBytes(encoding));
        builder.append(getCurrentTime().getBytes(encoding));
        builder.append(("\n" +
                "------------------------------------------\n").getBytes(encoding));

        builder.appendFontStyle(ICommandBuilder.FontStyleType.B);
        for (int i = 0; i < foodItemCountList.size(); i++) {
            builder.appendAlignment(ICommandBuilder.AlignmentPosition.Left);
            builder.appendEmphasis(String.valueOf(foodItemCountList.get(i)).getBytes(encoding));
//            builder.appendMultipleWidth(String.valueOf(foodItemCountList.get(i)).getBytes(encoding), 2);
            builder.append((" X ").getBytes(encoding));
            builder.appendEmphasis(foodItemNameList.get(i).getBytes(encoding));
            if (!foodItemCommentList.get(i).equals(getOneCharSpace())) {
                builder.append(("(").getBytes(encoding));
                builder.appendInvert((foodItemCommentList.get(i)).toUpperCase().getBytes(encoding));
                builder.append((")").getBytes(encoding));
            }
            builder.append(("\n").getBytes(encoding));
        }

        builder.appendFontStyle(ICommandBuilder.FontStyleType.A);
        builder.append(("------------------------------------------\n").getBytes(encoding));

    }

    @Override
    public Bitmap createSk12inchRasterReceiptImage() {
        return null;
    }

    @Override
    public void appendSk12inchTextReceiptData(ICommandBuilder builder, boolean utf8) {

    }

    @Override
    public void appendTextLabelData(ICommandBuilder builder, boolean utf8) {

    }

    @Override
    public String createPasteTextLabelString() {
        return null;
    }

    @Override
    public void appendPasteTextLabelData(ICommandBuilder builder, String pasteText, boolean utf8) {

    }
}

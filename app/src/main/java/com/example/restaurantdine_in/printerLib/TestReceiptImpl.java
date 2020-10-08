package com.example.restaurantdine_in.printerLib;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;

import com.example.restaurantdine_in.R;
import com.starmicronics.starioextension.ICommandBuilder;
import com.starmicronics.starioextension.StarIoExt.CharacterCode;
import com.starmicronics.starioextension.ICommandBuilder.CodePageType;
import com.starmicronics.starioextension.ICommandBuilder.InternationalType;
import com.starmicronics.starioextension.ICommandBuilder.AlignmentPosition;
import com.starmicronics.starioextension.ICommandBuilder.BarcodeSymbology;
import com.starmicronics.starioextension.ICommandBuilder.BarcodeWidth;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class TestReceiptImpl extends ILocalizeReceipts {

    public TestReceiptImpl() {
        mLanguageCode = "En";

        mCharacterCode = CharacterCode.Standard;
    }

    @Override
    public void append2inchTextReceiptData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = Charset.forName("UTF-8");

            builder.appendCodePage(CodePageType.UTF8);
        }
        else {
            encoding = Charset.forName("US-ASCII");

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendAlignment(AlignmentPosition.Center);

        builder.append((
                "Star Clothing Boutique\n" +
                        "123 Star Road\n" +
                        "City, State 12345\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Left);

        builder.append((
                "Date:MM/DD/YYYY    Time:HH:MM PM\n" +
                        "--------------------------------\n" +
                        "\n").getBytes(encoding));

        builder.append((
                "SKU         Description    Total\n" +
                        "300678566   PLAIN T-SHIRT  10.99\n" +
                        "300692003   BLACK DENIM    29.99\n" +
                        "300651148   BLUE DENIM     29.99\n" +
                        "300642980   STRIPED DRESS  49.99\n" +
                        "300638471   BLACK BOOTS    35.99\n" +
                        "\n" +
                        "Subtotal                  156.95\n" +
                        "Tax                         0.00\n" +
                        "--------------------------------\n").getBytes(encoding));

        builder.append(("Total     ").getBytes(encoding));

        builder.appendMultiple(("   $156.95\n").getBytes(encoding), 2, 2);

        builder.append((
                "--------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "\n").getBytes(encoding));

        builder.appendInvert(("Refunds and Exchanges\n").getBytes(encoding));

        builder.append(("Within ").getBytes(encoding));

        builder.appendUnderLine(("30 days").getBytes(encoding));

        builder.append((" with receipt\n").getBytes(encoding));

        builder.append((
                "And tags attached\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Center);

        builder.appendBarcode(("{BStar.").getBytes(Charset.forName("US-ASCII")), BarcodeSymbology.Code128, BarcodeWidth.Mode2, 40, true);
    }

    @Override
    public void append3inchTextReceiptData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = Charset.forName("UTF-8");

            builder.appendCodePage(CodePageType.UTF8);
        }
        else {
            encoding = Charset.forName("US-ASCII");

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendAlignment(AlignmentPosition.Center);

        builder.append((
                "Star Clothing Boutique\n" +
                        "123 Star Road\n" +
                        "City, State 12345\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Left);

        builder.append((
                "Date:MM/DD/YYYY                    Time:HH:MM PM\n" +
                        "------------------------------------------------\n" +
                        "\n").getBytes(encoding));

        builder.appendEmphasis(("SALE\n").getBytes(encoding));

        builder.append((
                "SKU               Description              Total\n" +
                        "300678566         PLAIN T-SHIRT            10.99\n" +
                        "300692003         BLACK DENIM              29.99\n" +
                        "300651148         BLUE DENIM               29.99\n" +
                        "300642980         STRIPED DRESS            49.99\n" +
                        "300638471         BLACK BOOTS              35.99\n" +
                        "\n" +
                        "Subtotal                                  156.95\n" +
                        "Tax                                         0.00\n" +
                        "------------------------------------------------\n").getBytes(encoding));

        builder.append(("Total                       ").getBytes(encoding));

        builder.appendMultiple(("   $156.95\n").getBytes(encoding), 2, 2);

        builder.append((
                "------------------------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "\n").getBytes(encoding));

        builder.appendInvert(("Refunds and Exchanges\n").getBytes(encoding));

        builder.append(("Within ").getBytes(encoding));

        builder.appendUnderLine(("30 days").getBytes(encoding));

        builder.append((" with receipt\n").getBytes(encoding));

        builder.append((
                "And tags attached\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Center);

        builder.appendBarcode(("{BStar.").getBytes(Charset.forName("US-ASCII")), BarcodeSymbology.Code128, BarcodeWidth.Mode2, 40, true);
    }

    @Override
    public void append4inchTextReceiptData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = Charset.forName("UTF-8");

            builder.appendCodePage(CodePageType.UTF8);
        }
        else {
            encoding = Charset.forName("US-ASCII");

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendAlignment(AlignmentPosition.Center);

        builder.append((
                "Star Clothing Boutique\n" +
                        "123 Star Road\n" +
                        "City, State 12345\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Left);

        builder.append((
                "Date:MM/DD/YYYY                                         Time:HH:MM PM\n" +
                        "---------------------------------------------------------------------\n" +
                        "\n").getBytes(encoding));

        builder.appendEmphasis(("SALE\n").getBytes(encoding));

        builder.append((
                "SKU                        Description                          Total\n" +
                        "300678566                  PLAIN T-SHIRT                        10.99\n" +
                        "300692003                  BLACK DENIM                          29.99\n" +
                        "300651148                  BLUE DENIM                           29.99\n" +
                        "300642980                  STRIPED DRESS                        49.99\n" +
                        "300638471                  BLACK BOOTS                          35.99\n" +
                        "\n" +
                        "Subtotal                                                       156.95\n" +
                        "Tax                                                              0.00\n" +
                        "---------------------------------------------------------------------\n").getBytes(encoding));

        builder.append(("Total                                            ").getBytes(encoding));

        builder.appendMultiple(("   $156.95\n").getBytes(encoding), 2, 2);

        builder.append((
                "---------------------------------------------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "\n").getBytes(encoding));

        builder.appendInvert(("Refunds and Exchanges\n").getBytes(encoding));

        builder.append(("Within ").getBytes(encoding));

        builder.appendUnderLine(("30 days").getBytes(encoding));

        builder.append((" with receipt\n").getBytes(encoding));

        builder.append((
                "And tags attached\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Center);

        builder.appendBarcode(("{BStar.").getBytes(Charset.forName("US-ASCII")), BarcodeSymbology.Code128, BarcodeWidth.Mode2, 40, true);
    }

    @Override
    public Bitmap create2inchRasterReceiptImage() {
        String textToPrint =
                "   Star Clothing Boutique\n" +
                        "        123 Star Road\n" +
                        "      City, State 12345\n" +
                        "\n" +
                        "Date:MM/DD/YYYY Time:HH:MM PM\n" +
                        "-----------------------------\n" +
                        "SALE\n" +
                        "SKU       Description   Total\n" +
                        "300678566 PLAIN T-SHIRT 10.99\n" +
                        "300692003 BLACK DENIM   29.99\n" +
                        "300651148 BLUE DENIM    29.99\n" +
                        "300642980 STRIPED DRESS 49.99\n" +
                        "30063847  BLACK BOOTS   35.99\n" +
                        "\n" +
                        "Subtotal               156.95\n" +
                        "Tax                      0.00\n" +
                        "-----------------------------\n" +
                        "Total                 $156.95\n" +
                        "-----------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "Refunds and Exchanges\n" +
                        "Within 30 days with receipt\n" +
                        "And tags attached\n";

        int      textSize = 22;
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);

        return createBitmapFromText(textToPrint, textSize, PrinterSettingConstant.PAPER_SIZE_TWO_INCH, typeface);
    }

    @Override
    public Bitmap create3inchRasterReceiptImage() {
        String textToPrint =
                "        Star Clothing Boutique\n" +
                        "             123 Star Road\n" +
                        "           City, State 12345\n" +
                        "\n" +
                        "Date:MM/DD/YYYY          Time:HH:MM PM\n" +
                        "--------------------------------------\n" +
                        "SALE\n" +
                        "SKU            Description       Total\n" +
                        "300678566      PLAIN T-SHIRT     10.99\n" +
                        "300692003      BLACK DENIM       29.99\n" +
                        "300651148      BLUE DENIM        29.99\n" +
                        "300642980      STRIPED DRESS     49.99\n" +
                        "30063847       BLACK BOOTS       35.99\n" +
                        "\n" +
                        "Subtotal                        156.95\n" +
                        "Tax                               0.00\n" +
                        "--------------------------------------\n" +
                        "Total                          $156.95\n" +
                        "--------------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "Refunds and Exchanges\n" +
                        "Within 30 days with receipt\n" +
                        "And tags attached\n";

        int      textSize = 25;
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);

        return createBitmapFromText(textToPrint, textSize, PrinterSettingConstant.PAPER_SIZE_THREE_INCH, typeface);
    }

    @Override
    public Bitmap create4inchRasterReceiptImage() {
        String textToPrint =
                "                   Star Clothing Boutique\n" +
                        "                        123 Star Road\n" +
                        "                      City, State 12345\n" +
                        "\n" +
                        "Date:MM/DD/YYYY                             Time:HH:MM PM\n" +
                        "---------------------------------------------------------\n" +
                        "SALE\n" +
                        "SKU                     Description                 Total\n" +
                        "300678566               PLAIN T-SHIRT               10.99\n" +
                        "300692003               BLACK DENIM                 29.99\n" +
                        "300651148               BLUE DENIM                  29.99\n" +
                        "300642980               STRIPED DRESS               49.99\n" +
                        "300638471               BLACK BOOTS                 35.99\n" +
                        "\n" +
                        "Subtotal                                           156.95\n" +
                        "Tax                                                  0.00\n" +
                        "---------------------------------------------------------\n" +
                        "Total                                             $156.95\n" +
                        "---------------------------------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "Refunds and Exchanges\n" +
                        "Within 30 days with receipt\n" +
                        "And tags attached\n";

        int      textSize = 23;
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);

        return createBitmapFromText(textToPrint, textSize, PrinterSettingConstant.PAPER_SIZE_FOUR_INCH, typeface);
    }

    @Override
    public Bitmap createCouponImage(Resources resources) {
        return BitmapFactory.decodeResource(resources, R.drawable.cash_register);
    }

    @Override
    public Bitmap createEscPos3inchRasterReceiptImage() {
        String textToPrint =
                "\n" +
                        "      Star Clothing Boutique\n" +
                        "           123 Star Road\n" +
                        "         City, State 12345\n" +
                        "\n" +
                        "Date:MM/DD/YYYY       Time:HH:MM PM\n" +
                        "-----------------------------------\n" +
                        "SALE\n" +
                        "SKU          Description      Total\n" +
                        "300678566    PLAIN T-SHIRT    10.99\n" +
                        "300692003    BLACK DENIM      29.99\n" +
                        "300651148    BLUE DENIM       29.99\n" +
                        "300642980    STRIPED DRESS    49.99\n" +
                        "30063847     BLACK BOOTS      35.99\n" +
                        "\n" +
                        "Subtotal                     156.95\n" +
                        "Tax                            0.00\n" +
                        "-----------------------------------\n" +
                        "Total                       $156.95\n" +
                        "-----------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "Refunds and Exchanges\n" +
                        "Within 30 days with receipt\n" +
                        "And tags attached\n";

        int      textSize = 24;
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);

        return createBitmapFromText(textToPrint, textSize, PrinterSettingConstant.PAPER_SIZE_ESCPOS_THREE_INCH, typeface);
    }

    @Override
    public void appendEscPos3inchTextReceiptData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = Charset.forName("UTF-8");

            builder.appendCodePage(CodePageType.UTF8);
        }
        else {
            encoding = Charset.forName("US-ASCII");

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendAlignment(AlignmentPosition.Center);

        builder.append((
                "\n" +
                        "Star Clothing Boutique\n" +
                        "123 Star Road\n" +
                        "City, State 12345\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Left);

        builder.append((
                "Date:MM/DD/YYYY              Time:HH:MM PM\n" +
                        "------------------------------------------\n" +
                        "\n").getBytes(encoding));

        builder.appendEmphasis(("SALE \n").getBytes(encoding));

        builder.append((
                "SKU            Description           Total\n" +
                        "300678566      PLAIN T-SHIRT         10.99\n" +
                        "300692003      BLACK DENIM           29.99\n" +
                        "300651148      BLUE DENIM            29.99\n" +
                        "300642980      STRIPED DRESS         49.99\n" +
                        "300638471      BLACK BOOTS           35.99\n" +
                        "\n" +
                        "Subtotal                            156.95\n" +
                        "Tax                                   0.00\n" +
                        "------------------------------------------\n").getBytes(encoding));

        builder.append(("Total                 ").getBytes(encoding));

        builder.appendMultiple(("   $156.95\n").getBytes(encoding), 2, 2);

        builder.append((
                "------------------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "\n").getBytes(encoding));

        builder.appendInvert(("Refunds and Exchanges\n").getBytes(encoding));

        builder.append(("Within ").getBytes(encoding));

        builder.appendUnderLine(("30 days").getBytes(encoding));

        builder.append((" with receipt\n").getBytes(encoding));

        builder.append((
                "And tags attached\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Center);

        builder.appendBarcode(("{BStar.").getBytes(Charset.forName("US-ASCII")), BarcodeSymbology.Code128, BarcodeWidth.Mode2, 40, true);
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

            builder.appendCodePage(CodePageType.UTF8);
        }
        else {
            encoding = Charset.forName("US-ASCII");

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendFontStyle(ICommandBuilder.FontStyleType.A);
        builder.appendAlignment(AlignmentPosition.Center);
        builder.append((
                "TABLE\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Center);
        builder.appendMultiple((String.valueOf(tableNo)).getBytes(encoding), 2, 2);
        builder.append(("\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Left);

        builder.append(("Date:").getBytes(encoding));
        builder.append(getCurrentDate().getBytes(encoding));
        builder.append(("              ").getBytes(encoding));
        builder.append(("Time:").getBytes(encoding));
        builder.append(getCurrentTime().getBytes(encoding));
        builder.append(("\n" +
                "------------------------------------------\n").getBytes(encoding));

        builder.appendFontStyle(ICommandBuilder.FontStyleType.B);
        for (int i = 0; i < foodItemCountList.size(); i++) {
            builder.appendAlignment(AlignmentPosition.Left);
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
        String textToPrint =
                "     Star Clothing Boutique\n" +
                        "          123 Star Road\n" +
                        "        City, State 12345\n" +
                        "\n" +
                        "Date:MM/DD/YYYY     Time:HH:MM PM\n" +
                        "---------------------------------\n" +
                        "SALE\n" +
                        "SKU         Description     Total\n" +
                        "300678566   PLAIN T-SHIRT   10.99\n" +
                        "300692003   BLACK DENIM     29.99\n" +
                        "300651148   BLUE DENIM      29.99\n" +
                        "300642980   STRIPED DRESS   49.99\n" +
                        "30063847    BLACK BOOTS     35.99\n" +
                        "\n" +
                        "Subtotal                   156.95\n" +
                        "Tax                          0.00\n" +
                        "---------------------------------\n" +
                        "Total                     $156.95\n" +
                        "---------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "Refunds and Exchanges\n" +
                        "Within 30 days with receipt\n" +
                        "And tags attached\n";

        int      textSize = 22;
        Typeface typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);

        return createBitmapFromText(textToPrint, textSize, PrinterSettingConstant.PAPER_SIZE_SK1_TWO_INCH, typeface);
    }

    @Override
    public void appendSk12inchTextReceiptData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = Charset.forName("UTF-8");

            builder.appendCodePage(CodePageType.UTF8);
        }
        else {
            encoding = Charset.forName("US-ASCII");

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendAlignment(AlignmentPosition.Center);

        builder.append((
                "Star Clothing Boutique\n" +
                        "123 Star Road\n" +
                        "City, State 12345\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Left);

        builder.append((
                "Date:MM/DD/YYYY        Time:HH:MM PM\n" +
                        "------------------------------------\n" +
                        "\n").getBytes(encoding));

        builder.append((
                "SKU           Description      Total\n" +
                        "300678566     PLAIN T-SHIRT    10.99\n" +
                        "300692003     BLACK DENIM      29.99\n" +
                        "300651148     BLUE DENIM       29.99\n" +
                        "300642980     STRIPED DRESS    49.99\n" +
                        "300638471     BLACK BOOTS      35.99\n" +
                        "\n" +
                        "Subtotal                      156.95\n" +
                        "Tax                             0.00\n" +
                        "------------------------------------\n").getBytes(encoding));

        builder.append(("Total     ").getBytes(encoding));

        builder.appendMultiple(("      $156.95\n").getBytes(encoding), 2, 2);

        builder.append((
                "------------------------------------\n" +
                        "\n" +
                        "Charge\n" +
                        "156.95\n" +
                        "Visa XXXX-XXXX-XXXX-0123\n" +
                        "\n").getBytes(encoding));

        builder.appendInvert(("Refunds and Exchanges\n").getBytes(encoding));

        builder.append(("Within ").getBytes(encoding));

        builder.appendUnderLine(("30 days").getBytes(encoding));

        builder.append((" with receipt\n").getBytes(encoding));

        builder.append((
                "And tags attached\n" +
                        "\n").getBytes(encoding));

        builder.appendAlignment(AlignmentPosition.Center);

        builder.appendBarcode(("{BStar.").getBytes(Charset.forName("US-ASCII")), BarcodeSymbology.Code128, BarcodeWidth.Mode2, 40, true);
    }

    @Override
    public void appendTextLabelData(ICommandBuilder builder, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = Charset.forName("UTF-8");

            builder.appendCodePage(CodePageType.UTF8);
        }
        else {
            encoding = Charset.forName("US-ASCII");

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.appendUnitFeed(20 * 2);

        builder.appendMultipleHeight(2);

        builder.append(("Star Micronics America, Inc.").getBytes(encoding));

        builder.appendUnitFeed(64);

        builder.append(("65 Clyde Road Suite G").getBytes(encoding));

        builder.appendUnitFeed(64);

        builder.append(("Somerset, NJ 08873-3485 U.S.A").getBytes(encoding));

        builder.appendUnitFeed(64);

        builder.appendMultipleHeight(1);
    }

    @Override
    public String createPasteTextLabelString() {
        return "Star Micronics America, Inc.\n" +
                "65 Clyde Road Suite G\n" +
                "Somerset, NJ 08873-3485 U.S.A";
    }

    @Override
    public void appendPasteTextLabelData(ICommandBuilder builder, String pasteText, boolean utf8) {
        Charset encoding;

        if (utf8) {
            encoding = Charset.forName("UTF-8");

            builder.appendCodePage(CodePageType.UTF8);
        }
        else {
            encoding = Charset.forName("US-ASCII");

            builder.appendCodePage(CodePageType.CP998);
        }

        builder.appendInternational(InternationalType.USA);

        builder.appendCharacterSpace(0);

        builder.append(pasteText.getBytes(encoding));
    }
}

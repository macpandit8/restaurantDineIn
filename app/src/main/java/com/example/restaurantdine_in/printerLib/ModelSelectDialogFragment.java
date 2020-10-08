package com.example.restaurantdine_in.printerLib;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

public class ModelSelectDialogFragment extends CommonAlertDialogFragment {

    private Callback mCallbackTarget;

    public static ModelSelectDialogFragment newInstance(String tag) {
        ModelSelectDialogFragment dialogFragment = new ModelSelectDialogFragment();

        Bundle args = new Bundle();
        args.putString(DIALOG_TAG, tag);
        args.putBoolean(CANCEL, false);
        args.putBoolean(CALLBACK, true);
        args.putString(LABEL_NEGATIVE, "Cancel");

        dialogFragment.setArguments(args);

        return dialogFragment;
    }

    @Override
    public @NonNull
    Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm. What is your printer?");

        builder.setItems(new String[]{
                        "mC-Print2",
                        "mC-Print3",
                        "mPOP",
                        "FVP10",
                        "TSP100",
                        "TSP650II",
                        "TSP700II",
                        "TSP800II",
                        "TUP500",
                        "SP700",
                        "SM-S210i",
                        "SM-S220i",
                        "SM-S230i",
                        "SM-T300i/T300",
                        "SM-T400i",
                        "SM-L200",
                        "SM-L300",
                        "BSC10",
                        "SM-S210i StarPRNT",
                        "SM-S220i StarPRNT",
                        "SM-S230i StarPRNT",
                        "SM-T300i/T300 StarPRNT",
                        "SM-T400i StarPRNT",
                        "SK1-211/221/V211",
                        "SK1-211/221/V211 Presenter",
                        "SK1-311/321/V311",
                        "SK1-311/V311 Presenter"},
                mOnModelClickListener);

        setupNegativeButton(builder);

        mCallbackTarget = (Callback) getParentFragment();

        return builder.create();
    }

    private DialogInterface.OnClickListener mOnModelClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            int model = ModelCapability.NONE;

            switch (which) {
                case  0: model = ModelCapability.MC_PRINT2; break;
                case  1: model = ModelCapability.MC_PRINT3; break;
                case  2: model = ModelCapability.MPOP; break;
                case  3: model = ModelCapability.FVP10; break;
                case  4: model = ModelCapability.TSP100; break;
                case  5: model = ModelCapability.TSP650II; break;
                case  6: model = ModelCapability.TSP700II; break;
                case  7: model = ModelCapability.TSP800II; break;
                case  8: model = ModelCapability.TUP500; break;
                case  9: model = ModelCapability.SP700; break;
                case 10: model = ModelCapability.SM_S210I; break;
                case 11: model = ModelCapability.SM_S220I; break;
                case 12: model = ModelCapability.SM_S230I; break;
                case 13: model = ModelCapability.SM_T300I_T300; break;
                case 14: model = ModelCapability.SM_T400I; break;
                case 15: model = ModelCapability.SM_L200; break;
                case 16: model = ModelCapability.SM_L300; break;
                case 17: model = ModelCapability.BSC10; break;
                case 18: model = ModelCapability.SM_S210I_StarPRNT; break;
                case 19: model = ModelCapability.SM_S220I_StarPRNT; break;
                case 20: model = ModelCapability.SM_S230I_StarPRNT; break;
                case 21: model = ModelCapability.SM_T300I_T300_StarPRNT; break;
                case 22: model = ModelCapability.SM_T400I_StarPRNT; break;
                case 23: model = ModelCapability.SK1_211_221_V211; break;
                case 24: model = ModelCapability.SK1_211_221_V211_Presenter; break;
                case 25: model = ModelCapability.SK1_311_321_V311; break;
                case 26: model = ModelCapability.SK1_311_V311_Presenter; break;
            }

            Intent intentForPassingData = new Intent();
            intentForPassingData.putExtra(PrinterSettingConstant.BUNDLE_KEY_MODEL_INDEX, model);

            mCallbackTarget.onDialogResult(getArguments().getString(DIALOG_TAG), intentForPassingData);

            dismiss();
        }
    };
}

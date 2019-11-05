package com.example.restaurantdine_in.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.restaurantdine_in.R;
import com.example.restaurantdine_in.adapters.FoodItemListViewAdapter;
import com.example.restaurantdine_in.food_selection.IOnEditTextDialogListener;

public class EditTextDialogFragment extends DialogFragment {

    private static Context mContext;
    private static String mDialogTitle;
    private static String mPositiveBtnText;
    private static String mNegativeBtnText;
    private static boolean mFromOrderList;
    private static int mPosition;

    private TextView dialogTitleTV;
    private EditText dialogEditText;
    private Button cancelBtn, doneBtn;

    private static Fragment mFragment;

    IOnEditTextDialogListener iOnEditTextDialogListener;
    private FoodItemListViewAdapter foodItemListViewAdapter;

    public static EditTextDialogFragment newInstance(Context context, int position, String dialogTitle, String positiveBtnText, String negativeBtnText, Fragment fragment, boolean fromOrderList) {
        mContext = context;
        mDialogTitle = dialogTitle;
        mPositiveBtnText = positiveBtnText;
        mNegativeBtnText = negativeBtnText;
        mFromOrderList = fromOrderList;
        mFragment = fragment;
        mPosition = position;
        EditTextDialogFragment editTextDialogFragment = new EditTextDialogFragment();

        return editTextDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.edit_text_dialog_layout, container, false);

        iOnEditTextDialogListener = (IOnEditTextDialogListener) mContext;

        dialogTitleTV = rootView.findViewById(R.id.dialogTitleTV);
        dialogTitleTV.setText(mDialogTitle);

        dialogEditText = rootView.findViewById(R.id.dialogEditText);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        if(mFromOrderList) {
            dialogEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            dialogEditText.setHint(R.string.enter_comment);
        } else {
            dialogEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            dialogEditText.setHint(R.string.enter_quantity);
        }

        cancelBtn = rootView.findViewById(R.id.dialogCancelBtn);
        cancelBtn.setText(mNegativeBtnText);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        doneBtn = rootView.findViewById(R.id.dialogDoneBtn);
        doneBtn.setText(mPositiveBtnText);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnEditTextDialogListener.onDoneClicked(mContext, mPosition, dialogEditText.getText().toString(), mFragment, false);
                getDialog().dismiss();
            }
        });

        return rootView;
    }
}

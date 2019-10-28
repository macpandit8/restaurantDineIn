package com.example.restaurantdine_in.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.restaurantdine_in.R;

public class EditTextDialogFragment extends DialogFragment {

    private static Context mContext;
    private static String mDialogTitle;
    private static String mPositiveBtnText;
    private static String mNegativeBtnText;

    TextView dialogTitleTV;
    EditText dialogEditText;
    Button cancelBtn, doneBtn;

    public static EditTextDialogFragment newInstance(Context context, String dialogTitle, String positiveBtnText, String negativeBtnText) {
        mContext = context;
        mDialogTitle = dialogTitle;
        mPositiveBtnText = positiveBtnText;
        mNegativeBtnText = negativeBtnText;
        EditTextDialogFragment editTextDialogFragment = new EditTextDialogFragment();

        return editTextDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.edit_text_dialog_layout, container, false);

        dialogTitleTV = rootView.findViewById(R.id.dialogTitleTV);
        dialogTitleTV.setText(mDialogTitle);

        dialogEditText = rootView.findViewById(R.id.dialogEditText);
        dialogEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        dialogEditText.setHint(R.string.enter_quantity);

        cancelBtn.setText(mNegativeBtnText);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        doneBtn.setText(mPositiveBtnText);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Done button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}

package com.vine.vinemars.app.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.vine.vinemars.R;
import com.vine.vinemars.utils.Constant;

/**
 * Created by chengfei on 14/12/3.
 */
public class PhoneConfirmationDialogFragment extends DialogFragment {

    private String phoneNumber;
    private String country;

    public static PhoneConfirmationDialogFragment newInstance(String country, String phoneNumber) {
        PhoneConfirmationDialogFragment fragment = new PhoneConfirmationDialogFragment();
        Bundle args = new Bundle();
        args.putString(Constant.INTENT_KEY.PHONE_NUMBER, phoneNumber);
        args.putString(Constant.INTENT_KEY.COUNTRY, country);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.Dialog);
        phoneNumber = getArguments().getString(Constant.INTENT_KEY.PHONE_NUMBER);
        country = getArguments().getString(Constant.INTENT_KEY.COUNTRY);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle(R.string.title_phone_confirmation)
                .setMessage(getString(R.string.message_phone_confirmation, format()))
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
                //TODO: go to validate checkcode page
                VerificationCodeDialogFragment.newInstance(country, phoneNumber).show(getParentFragment().getChildFragmentManager(), null);
            }
        }).create();
    }

    protected String format() {
        return new StringBuilder("+").append(country).append(" ").append(phoneNumber).toString();
    }
}

package com.vine.vinemars.app.fragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.vine.vinemars.utils.Constant;

public class MessageDialogFragment extends DialogFragment {

    private String message;

    private String title;

    public static MessageDialogFragment newInstance(String message, String title) {
        MessageDialogFragment messageDialogFragment = new MessageDialogFragment();
        Bundle args = new Bundle();
        args.putString(Constant.INTENT_KEY.MSG, message);
        args.putString(Constant.INTENT_KEY.TITLE, title);
        messageDialogFragment.setArguments(args);
        return messageDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(Constant.INTENT_KEY.TITLE);
        message = getArguments().getString(Constant.INTENT_KEY.MSG);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder builder = new Builder(getActivity());
        builder.setMessage(message);
        builder.setTitle(title);
        return builder.create();
    }

}

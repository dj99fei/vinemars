package com.vine.vinemars.view;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;

/**
 * Created by chengfei on 14/12/1.
 */
public class CrossInEditTextWatcher implements TextWatcher {

    private EditText editText;
    private Drawable drawable;

    public CrossInEditTextWatcher(EditText editText) {
        this.editText = editText;
        int size = MyApplication.get().getResources().getDimensionPixelSize(R.dimen.edit_cross_size);
        this.drawable = MyApplication.get().getResources().getDrawable(R.drawable.ic_cross);
        drawable.setBounds(0, 0, size, size);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 0 && editText.getCompoundDrawables()[2] != null) {
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        if (s.length() > 0 && editText.getCompoundDrawables()[2] == null) {
            editText.setCompoundDrawables(null, null, drawable, null);
        }
        editText.setCompoundDrawablePadding(20);
    }
}

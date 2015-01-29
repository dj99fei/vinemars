package com.vine.vinemars.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by chengfei on 14/12/5.
 */
public class Utils {

    public static void hideKeyboard(EditText editText) {
        // Check if no view has focus:
        InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}

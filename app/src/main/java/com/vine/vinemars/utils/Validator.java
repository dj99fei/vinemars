
package com.vine.vinemars.utils;

import android.support.v4.app.FragmentManager;

import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;
import com.vine.vinemars.app.fragment.MessageDialogFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class is for form validation.It contains some common method to test if
 * the input data from user is valid
 * 
 * @author chengfei
 */
public class Validator {

    private MyApplication mApp;

    public static final int USERNAME_LENGTH_MAX = 20;
    public static final int USERNAME_LENGTH_MIN = 3;

    public Validator() {
        this.mApp = MyApplication.get();
    }

    /**
     * To test if the data is not empty.
     * 
     * @param data The data to be test.
     * @param resId The resId of the prompt when the test has not pass.
     * @return The validator itself.
     * @throws ValidateException If the data is empty a new ValidateException
     *             will be throw.
     */
    public Validator notEmpty(String data, int resId) throws ValidateException {
        if ("".equals(data)) {
            throw new ValidateException(mApp.getString(
                    R.string.verify_not_empty, mApp.getString(resId)));
        }
        return this;
    }

    /**
     * To test if the data has no whitespace.
     * 
     * @param data The data to be test.
     * @param resId The resId of the prompt when the test has not pass.
     * @return The validator itself.
     * @throws ValidateException If the data has whitespace a new
     *             ValidateException will be throw.
     */
    public Validator noWhiteSpace(String data, int resId)
            throws ValidateException {
        if (data != null && data.contains(" ")) {
            throw new ValidateException(mApp.getString(
                    R.string.verify_no_whitespace,
                    mApp.getString(resId)));
        }
        return this;
    }

    /**
     * To test if the data's length between min and max.
     * 
     * @param data The data to be test.
     * @param min The minimum length the data could be.
     * @param max The maximum length the data could be.
     * @param resId A custom string explaining what data is beyond the give range.
     * @return The validator itself.
     * @throws ValidateException If the data'length doesn't between the given
     *             range,a new ValidateException will be throw.
     */
    public Validator isLengthValid(String data, int min, int max, int resId)
            throws ValidateException {
        if (data != null && (data.length() < min || data.length() > max)) {
            throw new ValidateException(mApp.getString(
                    R.string.verify_length, mApp.getString(resId).toLowerCase(), min,
                    max));
        }
        return this;
    }

    /**
     * To test if the two data is consistent.
     * 
     * @param data The first data to be test.
     * @param data2 The second data to be test.
     * @param resId A custom string explaining what two data strings are inconsistent.
     * @return The validator itself.
     * @throws ValidateException If the two data is not consistent,a
     *             ValidateException will be throw.
     */
    public Validator isConsistent(String data, String data2, int resId)
            throws ValidateException {
        if (!data.equals(data2)) {
            throw new ValidateException(mApp.getString(
                    R.string.verify_not_consistent,
                    mApp.getString(resId)));
        }
        return this;
    }


    /**
     * To test if the data is email or phone number.
     * @param data The data to be test.
     * @return The validator itself;
     */
    public Validator isEmailOrPhoneNumber(String data) throws ValidateException {

        if (isEmailValid(data) || isPhoneNumberValid(data)) {
            return this;

        }
        throw new ValidateException(R.string.prompt_username_invalid);
    }

    /**
     * To test the data is a email form.
     * 
     * @param email The email string to be test.
     * @return The verify result.
     */
    private boolean isEmailValid(String email) {
        Pattern p = Pattern
                .compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            return false;
        }
        return true;
    }

    /**
     * To test the data is a phone number form.
     *
     * @param phoneNumber The phoneNumber string to be test.
     *
     * @return The verify result.
     */
    private boolean isPhoneNumberValid(String phoneNumber) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phoneNumber);
        if (!m.matches()) {
             return false;
        }
        return true;

    }

    /**
     * To handle the exception during the validation.A dialog implemented by a
     * fragment will be showed,so you need pass in a FragmentManager.
     * 
     * @param fm The FragmentManager by which a FragmentDialog will be showed.
     * @param e The exception occurred during the validation pass.
     */
    public void handleVerifyException(FragmentManager fm, ValidateException e) {
        MessageDialogFragment.newInstance(e.getMessage(), "Error").show(fm, null);
    }

    /**
     * This class is for validation. If any test condition is not met,this
     * exception will be throw.
     * 
     * @author chengfei
     */
    public static class ValidateException extends Exception {

        private static final long serialVersionUID = -7281950545855178907L;

        public ValidateException(String msg) {
            super(msg);
        }

        public ValidateException(int res) {
            super(MyApplication.get().getResources().getString(res));
        }

    }

}

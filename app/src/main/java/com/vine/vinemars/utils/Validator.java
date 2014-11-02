
package com.vine.vinemars.utils;

import android.content.res.Resources;
import android.widget.EditText;

import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;

/**
 * This class is for form validation.It contains some common method to test if
 * the input data from user is valid
 * 
 * @author chengfei
 */
public class Validator {

    private MyApplication mApplication;

    public static final int USERNAME_LENGTH_MAX = 20;
    public static final int USERNAME_LENGTH_MIN = 3;

    private static final String TAG = Validator.class.getSimpleName();

    public Validator() {
        this.mApplication = MyApplication.get();
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
            throw new ValidateException(mApplication.getString(
                    R.string.verify_not_empty, mApplication.getString(resId)));
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
            throw new ValidateException(mApplication.getString(
                    R.string.verify_no_whitespace,
                    mApplication.getString(resId)));
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
            throw new ValidateException(mApplication.getString(
                    R.string.verify_length, mApplication.getString(resId).toLowerCase(), min,
                    max));
        }
        return this;
    }
    
    public Validator isBeginWithLetter(String data) throws ValidateException {
        if (!data.matches("^[a-zA-Z].*")) {
            throw new ValidateException(mApplication.getString(
                    R.string.verify_begin_with_letter));
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
            throw new ValidateException(mApplication.getString(
                    R.string.verify_not_consistent,
                    mApplication.getString(resId)));
        }
        return this;
    }

    /**
     * This class is for validation. If any test condition is not met,this
     * exception will be throw.
     * 
     * @author chengfei
     */
    public static class ValidateException extends Exception {

        private static final long serialVersionUID = -7281950545855178907L;

        private int id;
        
        public ValidateException(String msg) {
            super(msg);
        }
        
        public ValidateException(int id, String msg) {
            super(msg);
            this.id = id;
        }
        
        public int getId() {
            return id;
        }
        
        public void setId(int id) {
            this.id = id;
        }
    }
    
    
    /**
     * To verify the legality of username.
     * @return The validator itself.
     * @throws ValidateException If the username is illegal, a ValidateException will be throw. 
     */
    public Validator verifyUsername(EditText usernameEdit) throws ValidateException {
        Resources resouce = MyApplication.get().getResources();
        String username = usernameEdit.getText().toString();
        int usernameMinLength = resouce.getInteger(R.integer.username_min_length);
        int usernameMaxLength = resouce.getInteger(R.integer.username_max_length);
        try {
            notEmpty(username, R.string.verify_label_username)
                    .noWhiteSpace(username, R.string.verify_label_username)
                    .isLengthValid(username, usernameMinLength, usernameMaxLength,
                            R.string.verify_label_username)
                    .isBeginWithLetter(username);
        } catch (ValidateException e) {
            e.setId(usernameEdit.getId());
            throw e;
        }
        return this;
    }
    /**
     * To verify the legality of password.
     * @return The validator itself.
     * @throws ValidateException If the password is illegal, a ValidateException will be throw.
     */
    public Validator verifyPassword(EditText passwordEdit) throws ValidateException {
        Resources resouce = MyApplication.get().getResources();
        String password = passwordEdit.getText().toString();
        int passwordMinLength = resouce.getInteger(R.integer.password_min_length);
        int passwordMaxLength = resouce.getInteger(R.integer.password_max_length);
        try {
            notEmpty(password, R.string.verify_label_password)
                    .noWhiteSpace(password, R.string.verify_label_password)
                    .isLengthValid(password, passwordMinLength, passwordMaxLength,
                            R.string.verify_label_password);
        } catch (ValidateException e) {
            e.setId(passwordEdit.getId());
            throw e;
        }
        return this;
    }
    /**
     * To verify the legality of nickname.
     * @return The validator itself.
     * @throws ValidateException If the nickname is illegal, a ValidateException will be throw.
     */
    public Validator verifyNickname(EditText nicknameEdit) throws ValidateException {
        Resources resouce = MyApplication.get().getResources();
        String nickname = nicknameEdit.getText().toString().trim();
        int passwordMinLength = resouce.getInteger(R.integer.nickname_min_length);
        int passwordMaxLength = resouce.getInteger(R.integer.nickname_max_length);
        try {
            notEmpty(nickname, R.string.verify_label_nickname)
                    .isLengthValid(nickname, passwordMinLength, passwordMaxLength,
                            R.string.verify_label_nickname);
        } catch (ValidateException e) {
            e.setId(nicknameEdit.getId());
            throw e;
        }
        return this;
    }
}

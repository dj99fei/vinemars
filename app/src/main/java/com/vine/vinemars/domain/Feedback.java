package com.vine.vinemars.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.vine.vinemars.R;
import com.vine.vinemars.utils.SharedPreferencesHelper;

/**
 * Created by lili on 14/12/10.
 */
public class Feedback implements Parcelable {
    public String feedback;
    public String contact;

    public Feedback(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.feedback);
        dest.writeString(this.contact);

    }
    private Feedback(Parcel in) {
        this.feedback = in.readString();
        this.contact = in.readString();
    }


    public static final Creator<Feedback> CREATOR = new Creator<Feedback>() {
        public Feedback createFromParcel(Parcel source) {
            return new Feedback(source);
        }

        public Feedback[] newArray(int size) {
            return new Feedback[size];
        }
    };


    public void save() {
        Gson gson = new Gson();
        String content = gson.toJson(this);
        SharedPreferencesHelper.getInstance()
                .withModule(R.string.module_feedback)
                .setData(String.class, content)
                .commit();
    }

    public static Feedback loadFromLocal() {
        String content = SharedPreferencesHelper
                .getInstance()
                .withModule(R.string.module_feedback)
                .get(String.class, "");
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(content, Feedback.class);
    }
}

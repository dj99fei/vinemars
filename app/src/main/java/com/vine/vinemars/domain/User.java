package com.vine.vinemars.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;
import com.vine.vinemars.utils.SharedPreferencesHelper;

/**
 * Created by chengfei on 14-10-25.
 */
public class User implements Parcelable {
    public String username;
    public String password;
    public String nickname;
    public String email;
    public String avatar;
    public String token;
    public String userId;
    public String sessionId;
    public int gender;
    public String signature;
    public int GENDER_FEMALE = 0;
    public int GENDER_MALE = 1;

    public User() {
    }

    //TODO: 加强用户登录条件
    public boolean isSigined() {
        return !TextUtils.isEmpty(username);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.nickname);
        dest.writeString(this.email);
        dest.writeString(this.avatar);
        dest.writeString(this.token);
        dest.writeString(this.userId);
        dest.writeInt(this.gender);
        dest.writeString(this.signature);
    }

    private User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.nickname = in.readString();
        this.email = in.readString();
        this.avatar = in.readString();
        this.token = in.readString();
        this.userId = in.readString();
        this.gender = in.readInt();
        this.signature = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };


    public String getGender() {
        if (gender == GENDER_FEMALE) {
            return MyApplication.get().getResources().getString(R.string.female);
        } else {
            return MyApplication.get().getResources().getString(R.string.male);
        }
    }


    public void save() {
        Gson gson = new Gson();
        String content = gson.toJson(this);
        SharedPreferencesHelper.getInstance()
                .withModule(R.ule_user)
                .withKey(R.string.key_user_info)
                .setData(String.class, content)
                .commit();
    }

    public static User loadFromLocal() {
        String content = SharedPreferencesHelper
                .getInstance()
                .withModule(R.string.module_user)
                .withKey(R.string.key_user_info)
                .get(String.class, "");
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(content, User.class);
    }
}

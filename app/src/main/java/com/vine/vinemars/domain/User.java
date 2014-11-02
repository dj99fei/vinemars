package com.vine.vinemars.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.nickname);
        dest.writeString(this.avatar);
    }

    public User() {
    }

    private User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.nickname = in.readString();
        this.avatar = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    //TODO: 加强用户登录条件
    public boolean isSigined() {
        return !TextUtils.isEmpty(username);
    }
}

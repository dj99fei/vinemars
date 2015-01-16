package com.vine.vinemars.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chengfei on 15/1/16.
 */
public class Topic implements Parcelable {

    /**
     * 话题标题
     */
    public String title;
    /**
     * 话题内容
     */
    public String content;
    /**
     * 话题缩略图
     */
    public String thumbnail;
    /**
     * 话题原图
     */
    public String picture;
    /**
     *
     */
    public String localPicture;
    /**
     * 可见范围
     */
    public int visibleCircle;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.thumbnail);
        dest.writeString(this.picture);
        dest.writeString(this.localPicture);
        dest.writeInt(this.visibleCircle);
    }

    public Topic() {
    }

    private Topic(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.thumbnail = in.readString();
        this.picture = in.readString();
        this.localPicture = in.readString();
        this.visibleCircle = in.readInt();
    }

    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        public Topic createFromParcel(Parcel source) {
            return new Topic(source);
        }

        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };
}

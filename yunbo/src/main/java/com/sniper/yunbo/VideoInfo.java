package com.sniper.yunbo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 视频信息类
 * @author baidu
 *
 */
public class VideoInfo implements Parcelable {
    
    private String title = "";
    private String url = "";
    private String imageUrl = "";
    private boolean canDelete = true;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    private String cookie = "";

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.imageUrl);
        dest.writeByte(this.canDelete ? (byte) 1 : (byte) 0);
        dest.writeString(this.cookie);
    }

    public VideoInfo() {
    }

    protected VideoInfo(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
        this.imageUrl = in.readString();
        this.canDelete = in.readByte() != 0;
        this.cookie = in.readString();
    }

    public static final Creator<VideoInfo> CREATOR = new Creator<VideoInfo>() {
        @Override
        public VideoInfo createFromParcel(Parcel source) {
            return new VideoInfo(source);
        }

        @Override
        public VideoInfo[] newArray(int size) {
            return new VideoInfo[size];
        }
    };
}

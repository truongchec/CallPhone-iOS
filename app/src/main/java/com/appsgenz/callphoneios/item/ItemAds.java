package com.appsgenz.callphoneios.item;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class ItemAds {
    @SerializedName("ban")
    String ban;
    @SerializedName("img")
    String img;
    @SerializedName("nam")
    String nam;
    @SerializedName("pkg")
    String pkg;

    public String getImg() {
        return this.img;
    }

    public String getPkg() {
        return this.pkg;
    }

    public String getNam() {
        return this.nam;
    }

    public String getBan() {
        return this.ban;
    }
}

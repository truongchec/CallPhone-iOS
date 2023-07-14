package com.appsgenz.callphoneios.item;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.ironsource.mediationsdk.impressionData.ImpressionData;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ItemRecentGroup {
    @SerializedName("arrRecent")
    public ArrayList<ItemRecent> arrRecent;
    @SerializedName(ImpressionData.IMPRESSION_DATA_KEY_COUNTRY)
    public String country;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    public String name;
    @SerializedName("nameType")
    public String nameType;
    @SerializedName("photo")
    public String photo;
    @SerializedName("time")
    public long time;

    public ItemRecentGroup() {
    }

    public ItemRecentGroup(ItemRecent itemRecent, String str, String str2) {
        this.name = str;
        this.photo = str2;
        ArrayList<ItemRecent> arrayList = new ArrayList<>();
        this.arrRecent = arrayList;
        arrayList.add(itemRecent);
        this.time = itemRecent.time;
        this.country = itemRecent.country;
        this.nameType = itemRecent.typeNumber;
    }

    public void addRecent(ItemRecent itemRecent) {
        this.time = Math.max(this.time, itemRecent.time);
        String str = this.country;
        if ((str == null || str.isEmpty()) && itemRecent.country != null && !itemRecent.country.isEmpty()) {
            this.country = itemRecent.country;
        }
        String str2 = this.nameType;
        if (str2 == null || str2.isEmpty()) {
            this.nameType = itemRecent.typeNumber;
        }
        this.arrRecent.add(itemRecent);
    }
}

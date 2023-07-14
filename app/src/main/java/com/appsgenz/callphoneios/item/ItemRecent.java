package com.appsgenz.callphoneios.item;

import com.google.ads.mediation.facebook.FacebookMediationAdapter;
import com.google.gson.annotations.SerializedName;
import com.ironsource.mediationsdk.impressionData.ImpressionData;

/* loaded from: classes.dex */
public class ItemRecent {
    @SerializedName(ImpressionData.IMPRESSION_DATA_KEY_COUNTRY)
    public String country;
    @SerializedName("dur")
    public long dur;
    @SerializedName(FacebookMediationAdapter.KEY_ID)
    public String id;
    @SerializedName("number")
    public String number;
    @SerializedName("simId")
    public String simId;
    @SerializedName("time")
    public long time;
    @SerializedName("type")
    public int type;
    @SerializedName("typeNumber")
    public String typeNumber;

    public ItemRecent(String str, String str2, String str3, long j, long j2, String str4, int i, String str5) {
        this.id = str;
        this.number = str2;
        this.simId = str3;
        this.dur = j;
        this.time = j2;
        this.country = str4;
        this.type = i;
        this.typeNumber = str5;
    }

    public ItemRecent() {
    }
}

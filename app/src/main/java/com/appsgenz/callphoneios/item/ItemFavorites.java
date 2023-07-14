package com.appsgenz.callphoneios.item;

import com.google.ads.mediation.facebook.FacebookMediationAdapter;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class ItemFavorites {
    @SerializedName(FacebookMediationAdapter.KEY_ID)
    public String id;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    public String name;
    @SerializedName("number")
    public String number;
    @SerializedName("photo")
    public String photo;
    @SerializedName("type")
    public int type;

    public ItemFavorites() {
    }

    public ItemFavorites(ItemFavorites itemFavorites) {
        this.id = itemFavorites.id;
        this.type = itemFavorites.type;
        this.number = itemFavorites.number;
    }
}

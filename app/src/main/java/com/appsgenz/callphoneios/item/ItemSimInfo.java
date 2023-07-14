package com.appsgenz.callphoneios.item;

import android.telecom.PhoneAccountHandle;

/* loaded from: classes.dex */
public class ItemSimInfo {
    public PhoneAccountHandle handle;
    public int id;
    public String label;
    public String phoneNumber;

    public ItemSimInfo(int i, PhoneAccountHandle phoneAccountHandle, String str, String str2) {
        this.id = i;
        this.handle = phoneAccountHandle;
        this.label = str;
        this.phoneNumber = str2;
    }
}

package com.appsgenz.callphoneios.fragment;

import com.appsgenz.callphoneios.item.ItemContact;
import com.appsgenz.callphoneios.item.ItemRecentGroup;

/* loaded from: classes.dex */
public interface ContactResult {
    void onAddNewContact(ItemRecentGroup itemRecentGroup, ItemContact itemContact);

    void onBack();

    void onContactChange();

    void onFavoritesChange();
}

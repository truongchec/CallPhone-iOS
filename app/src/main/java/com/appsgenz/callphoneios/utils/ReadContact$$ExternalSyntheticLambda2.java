package com.appsgenz.callphoneios.utils;

import com.appsgenz.callphoneios.item.ItemContact;
import java.util.Comparator;

public final /* synthetic */ class ReadContact$$ExternalSyntheticLambda2 implements Comparator {
    public static final /* synthetic */ ReadContact$$ExternalSyntheticLambda2 INSTANCE = new ReadContact$$ExternalSyntheticLambda2();

    private /* synthetic */ ReadContact$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareTo;
        compareTo = ((ItemContact) obj).getName().toLowerCase().compareTo(((ItemContact) obj2).getName().toLowerCase());
        return compareTo;
    }
}

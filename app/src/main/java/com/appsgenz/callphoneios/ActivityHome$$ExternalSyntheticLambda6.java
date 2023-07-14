package com.appsgenz.callphoneios;

import com.appsgenz.callphoneios.item.ItemContact;
import java.util.Comparator;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class ActivityHome$$ExternalSyntheticLambda6 implements Comparator {
    public static final /* synthetic */ ActivityHome$$ExternalSyntheticLambda6 INSTANCE = new ActivityHome$$ExternalSyntheticLambda6();

    private /* synthetic */ ActivityHome$$ExternalSyntheticLambda6() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareTo;
        compareTo = ((ItemContact) obj).getName().toLowerCase().compareTo(((ItemContact) obj2).getName().toLowerCase());
        return compareTo;
    }
}

package com.appsgenz.callphoneios.broadcase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.appsgenz.callphoneios.ActivityCall;
import com.appsgenz.callphoneios.service.CallManager;
import com.appsgenz.callphoneios.utils.MyConst;

/* loaded from: classes.dex */
public class MyCallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null || intent.getAction() == null) {
            return;
        }
        if (intent.getAction().equals(MyConst.ACCEPT_CALL)) {
            context.startActivity(ActivityCall.makeIntent(context));
            CallManager.getInstance().accept();
            return;
        }
        CallManager.getInstance().reject();
    }
}

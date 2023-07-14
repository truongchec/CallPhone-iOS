package com.appsgenz.callphoneios.service;

import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.os.Build;
import android.os.PowerManager;
import android.telecom.Call;
import android.telecom.InCallService;
import com.appsgenz.callphoneios.ActivityCall;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class CallService extends InCallService {
    private final Call.Callback callListener = new Call.Callback() { // from class: com.appsgenz.callphoneios.service.CallService.1
        @Override // android.telecom.Call.Callback
        public void onStateChanged(Call call, int i) {
            super.onStateChanged(call, i);
            CallService.this.callNotificationManager.setupNotification(false);
        }
    };
    private MyNotificationManager callNotificationManager;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.callNotificationManager = new MyNotificationManager(this);
    }

    @Override // android.telecom.InCallService
    public void onCallAdded(Call call) {
        super.onCallAdded(call);
        CallManager.getInstance().onAddCall(call, this);
        call.registerCallback(this.callListener);
        boolean isDeviceLocked = ((KeyguardManager) getSystemService("keyguard")).isDeviceLocked();
        if (!((PowerManager) getSystemService("power")).isInteractive() || isOutGoing(call) || isDeviceLocked) {
            try {
                this.callNotificationManager.setupNotification(false);
                startActivity(ActivityCall.makeIntent(this));
                return;
            } catch (ActivityNotFoundException unused) {
                this.callNotificationManager.setupNotification(true);
                return;
            }
        }
        this.callNotificationManager.setupNotification(true);
    }

    @Override // android.telecom.InCallService
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);
        CallManager.getInstance().onRemoveCall(call);
        this.callNotificationManager.cancelNotification();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.callNotificationManager.cancelNotification();
    }

    private boolean isOutGoing(Call call) {
        if (Build.VERSION.SDK_INT >= 29) {
            return call.getDetails().getCallDirection() == 1;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(9);
        arrayList.add(1);
        arrayList.add(8);
        return arrayList.contains(Integer.valueOf(CallManager.getState(call)));
    }
}

package com.appsgenz.callphoneios;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.appsgenz.callphoneios.screen.ActionScreenResult;
import com.appsgenz.callphoneios.screen.BaseScreen;
import com.appsgenz.callphoneios.screen.ios.ViewScreenIos;
import com.appsgenz.callphoneios.screen.ios2.ViewScreenIOS2;
import com.appsgenz.callphoneios.screen.mate.ViewScreenMate;
import com.appsgenz.callphoneios.screen.other.ViewScreenOther;
import com.appsgenz.callphoneios.screen.pixel.ViewScreenPixel;
import com.appsgenz.callphoneios.screen.samsung.ViewScreenSamsung;
import com.appsgenz.callphoneios.service.CallManager;
import com.appsgenz.callphoneios.service.CallManagerListener;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;

@SuppressLint("InvalidWakeLockTag")
public class ActivityCall extends AppCompatActivity {
    private BaseScreen baseScreen;
    private Sensor mSensor;
    private SensorManager mSensorManager;
    private PowerManager pm;
    private int status;
    private PowerManager.WakeLock wOff;
    private PowerManager.WakeLock wOn;
    private final CallManagerListener listener = new CallManagerListener() { // from class: com.appsgenz.callphoneios.ActivityCall.2
        @Override // com.appsgenz.callphoneios.service.CallManagerListener
        public void onStateChanged(int i) {
            ActivityCall.this.status = i;
            ActivityCall.this.baseScreen.updateStatus(i);
        }
    };
    private final SensorEventListener registerListener = new SensorEventListener() { // from class: com.appsgenz.callphoneios.ActivityCall.3
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (ActivityCall.this.isDestroyed() || ActivityCall.this.isFinishing() || ActivityCall.this.isChangingConfigurations()) {
                return;
            }
            if (sensorEvent.values[0] == 0.0f) {
                ActivityCall.this.turnOff();
            } else {
                ActivityCall.this.turnOn();
            }
        }
    };

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, ActivityCall.class);
        intent.setFlags(272760832);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int style = MyShare.getStyle(this);
        if (style == 0) {
            this.baseScreen = new ViewScreenIos(this);
        } else if (style == 1) {
            this.baseScreen = new ViewScreenPixel(this);
        } else if (style == 2) {
            this.baseScreen = new ViewScreenSamsung(this);
        } else if (style == 3) {
            this.baseScreen = new ViewScreenMate(this);
        } else if (style == 4) {
            this.baseScreen = new ViewScreenIOS2(this);
        } else {
            this.baseScreen = new ViewScreenOther(this);
        }
        setContentView(this.baseScreen);
        this.baseScreen.setActionScreenResult(new ActionScreenResult() { // from class: com.appsgenz.callphoneios.ActivityCall.1
            @Override // com.appsgenz.callphoneios.screen.ActionScreenResult
            public void onAccept() {
                CallManager.getInstance().accept();
            }

            @Override // com.appsgenz.callphoneios.screen.ActionScreenResult
            public void onReject() {
                CallManager.getInstance().reject();
            }

            @Override // com.appsgenz.callphoneios.screen.ActionScreenResult
            public void onHold() {
                ActivityCall.this.baseScreen.isHold = CallManager.getInstance().hold();
                ActivityCall.this.baseScreen.updateViewMode();
            }

            @Override // com.appsgenz.callphoneios.screen.ActionScreenResult
            public void onRecorder() {
                String[] strArr;
                if (Build.VERSION.SDK_INT >= 29) {
                    strArr = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"};
                } else {
                    strArr = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"};
                }
                int length = strArr.length;
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = true;
                        break;
                    }
                    if (!OtherUtils.checkPer(ActivityCall.this, strArr[i])) {
                        break;
                    }
                    i++;
                }
                if (z) {
                    ActivityCall.this.onRecorder();
                } else {
                    ActivityCompat.requestPermissions(ActivityCall.this, strArr, 1);
                }
            }

            @Override // com.appsgenz.callphoneios.screen.ActionScreenResult
            public void onMute() {
                ActivityCall.this.baseScreen.isMute = CallManager.getInstance().muteSpeaker();
                ActivityCall.this.baseScreen.updateViewMode();
            }

            @Override // com.appsgenz.callphoneios.screen.ActionScreenResult
            public void onSpeaker() {
                ActivityCall.this.baseScreen.isSpeaker = CallManager.getInstance().switchSpeaker();
                ActivityCall.this.baseScreen.updateViewMode();
            }

            @Override // com.appsgenz.callphoneios.screen.ActionScreenResult
            public void onOpenContact() {
                ActivityCall.this.baseScreen.onShowContact();
            }

            @Override // com.appsgenz.callphoneios.screen.ActionScreenResult
            public void onPadClick(String str) {
                CallManager.getInstance().onKeyPad(str);
            }
        });
        this.baseScreen.updateStatus(CallManager.getInstance().getState());
        CallManager.getInstance().addListener(this.listener);
        updateFlags();
        startSensor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRecorder() {
        if (this.baseScreen.isRec) {
            return;
        }
        this.baseScreen.isRec = true;
        CallManager.getInstance().startRecorder();
        this.baseScreen.updateViewMode();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        String[] strArr2;
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (Build.VERSION.SDK_INT >= 29) {
            strArr2 = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"};
        } else {
            strArr2 = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"};
        }
        int length = strArr2.length;
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = true;
                break;
            } else if (!OtherUtils.checkPer(this, strArr2[i2])) {
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            onRecorder();
        }
    }

    private void updateFlags() {
        int i;
        KeyguardManager keyguardManager = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
        if (Build.VERSION.SDK_INT >= 27) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
            if (keyguardManager != null) {
                keyguardManager.requestDismissKeyguard(this, null);
            }
            i = 512;
        } else {
            i = 6816384;
        }
        getWindow().getDecorView().setSystemUiVisibility(1280);
        getWindow().addFlags(i);
        getWindow().setNavigationBarColor(0);
        getWindow().setStatusBarColor(0);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        this.mSensorManager = sensorManager;
        if (sensorManager != null) {
            this.mSensor = sensorManager.getDefaultSensor(8);
        }
        this.pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        turnOn();
    }

    public void turnOn() {
        PowerManager powerManager = this.pm;
        if (powerManager != null) {
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(26, "tag");
            this.wOn = newWakeLock;
            newWakeLock.acquire(5000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void turnOff() {
        PowerManager powerManager = this.pm;
        if (powerManager != null) {
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(32, "tag");
            this.wOff = newWakeLock;
            newWakeLock.acquire();
        }
    }

    public void startSensor() {
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null) {
            sensorManager.registerListener(this.registerListener, this.mSensor, 3);
        }
    }

    public void stopSensor() {
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this.registerListener, this.mSensor);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.baseScreen.isBack()) {
            if ((this.baseScreen instanceof ViewScreenIos) && this.status == 2) {
                CallManager.getInstance().reject();
            }
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.baseScreen.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.baseScreen.onPause();
    }

    public void onReleaseData() {
        PowerManager.WakeLock wakeLock = this.wOn;
        if (wakeLock != null && wakeLock.isHeld()) {
            this.wOn.release();
        }
        PowerManager.WakeLock wakeLock2 = this.wOff;
        if (wakeLock2 != null && wakeLock2.isHeld()) {
            this.wOff.release();
        }
        CallManager.getInstance().removeListener();
        stopSensor();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.baseScreen.onDestroy();
        super.onDestroy();
    }
}

package com.appsgenz.callphoneios.screen;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.appsgenz.callphoneios.ActivityCall;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.custom.AvatarPeople;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.service.CallManager;
import com.appsgenz.callphoneios.utils.OtherUtils;
import com.appsgenz.callphoneios.utils.ReadContact;

/* loaded from: classes.dex */
public abstract class BaseScreen extends RelativeLayout {
    public ActionScreenResult actionScreenResult;
    public AppCompatActivity activityCall;
    private final BackgroundManager backgroundManager;
    public int callDuration;
    private final Handler handler;
    public final AvatarPeople imAvatar;
    public boolean isEndCall;
    public boolean isHold;
    public boolean isMute;
    public boolean isRec;
    public boolean isSpeaker;
    private final Runnable runnable;
    public final TextW tvName;
    public final TextW tvStatus;
    private ViewAllContact viewAllContact;

    public abstract void callRinging();

    public abstract void callStarted();

    public abstract void initOutgoingCallUI();

    public abstract void showPhoneAccountPicker();

    public abstract void updateViewMode();

    public BaseScreen(Context context) {
        super(context);
        this.runnable = new Runnable() { // from class: com.appsgenz.callphoneios.screen.BaseScreen.1
            @Override // java.lang.Runnable
            public void run() {
                if (BaseScreen.this.isEndCall) {
                    return;
                }
                BaseScreen.this.callDuration = CallManager.getInstance().getTimeCall();
                BaseScreen.this.tvStatus.setText(OtherUtils.longToDur(BaseScreen.this.callDuration));
                BaseScreen.this.handler.postDelayed(this, 1000L);
            }
        };
        setBackgroundColor(-16777216);
        if (context instanceof AppCompatActivity) {
            this.activityCall = (AppCompatActivity) context;
        }
        this.backgroundManager = new BackgroundManager(this);
        View view = new View(context);
        view.setBackgroundColor(Color.parseColor("#30000000"));
        addView(view, -1, -1);
        AvatarPeople avatarPeople = new AvatarPeople(context);
        this.imAvatar = avatarPeople;
        avatarPeople.setId(100);
        TextW textW = new TextW(context);
        this.tvName = textW;
        textW.setId(6666654);
        textW.setupText(600, 6.3f);
        textW.setTextColor(-1);
        TextW textW2 = new TextW(context);
        this.tvStatus = textW2;
        textW2.setId(6666655);
        textW2.setupText(400, 3.8f);
        textW2.setTextColor(-1);
        textW2.setText(R.string.app_name);
        setDataCallInfo();
        this.handler = new Handler();
    }

    public void setDataCallInfo() {
        String phoneCall = CallManager.getInstance().getPhoneCall();
        String[] namePhoto = ReadContact.getNamePhoto(getContext(), phoneCall);
        String str = namePhoto[0];
        if (!str.isEmpty()) {
            phoneCall = str;
        }
        this.tvName.setText(phoneCall);
        if (!namePhoto[1].isEmpty()) {
            this.imAvatar.setImage(namePhoto[1], phoneCall);
            return;
        }
        this.imAvatar.setVisibility(4);
        this.tvName.setGravity(1);
        this.tvStatus.setGravity(1);
    }

    public void setActionScreenResult(ActionScreenResult actionScreenResult) {
        this.actionScreenResult = actionScreenResult;
    }

    public void updateStatus(int i) {
        if (i != 1) {
            if (i == 2) {
                this.tvStatus.setText(R.string.is_calling);
                callRinging();
                return;
            } else if (i == 4) {
                this.isEndCall = false;
                this.handler.removeCallbacks(this.runnable);
                this.handler.post(this.runnable);
                callStarted();
                return;
            } else if (i == 7) {
                AppCompatActivity appCompatActivity = this.activityCall;
                if (appCompatActivity instanceof ActivityCall) {
                    ((ActivityCall) appCompatActivity).onReleaseData();
                }
                endCall();
                this.isEndCall = true;
                return;
            } else if (i == 8) {
                showPhoneAccountPicker();
                return;
            } else if (i != 9) {
                return;
            }
        }
        AppCompatActivity appCompatActivity2 = this.activityCall;
        if (appCompatActivity2 instanceof ActivityCall) {
            ((ActivityCall) appCompatActivity2).startSensor();
        }
        this.tvStatus.setText(R.string.dialing);
        initOutgoingCallUI();
    }

    public void onShowContact() {
        if (this.viewAllContact == null) {
            this.viewAllContact = new ViewAllContact(getContext());
        }
        addView(this.viewAllContact, -1, -1);
        this.viewAllContact.onShow();
    }

    public boolean isBack() {
        ViewAllContact viewAllContact = this.viewAllContact;
        if (viewAllContact == null || indexOfChild(viewAllContact) == -1) {
            return true;
        }
        this.viewAllContact.onHide();
        return false;
    }

    private void endCall() {
        AppCompatActivity appCompatActivity = this.activityCall;
        if (appCompatActivity == null) {
            return;
        }
        if (appCompatActivity instanceof ActivityCall) {
            ((ActivityCall) appCompatActivity).stopSensor();
        }
        if (this.isEndCall) {
            this.activityCall.finishAndRemoveTask();
        } else if (this.callDuration > 0) {
            this.activityCall.runOnUiThread(new Runnable() { // from class: com.appsgenz.callphoneios.screen.BaseScreen$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    BaseScreen.this.m181x6b07399e();
                }
            });
        } else {
            this.tvStatus.setText(R.string.call_ended);
            this.activityCall.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$endCall$1$com-callos14-callscreen-colorphone-screen-BaseScreen  reason: not valid java name */
    public /* synthetic */ void m181x6b07399e() {
        this.tvStatus.setText(OtherUtils.longToDur(this.callDuration) + " (" + getContext().getString(R.string.call_ended) + ")");
        new Handler().postDelayed(new Runnable() { // from class: com.appsgenz.callphoneios.screen.BaseScreen$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BaseScreen.this.m180x50ebbaff();
            }
        }, 1500L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$endCall$0$com-callos14-callscreen-colorphone-screen-BaseScreen  reason: not valid java name */
    public /* synthetic */ void m180x50ebbaff() {
        this.activityCall.finishAndRemoveTask();
    }

    public void onResume() {
        this.backgroundManager.onResume();
    }

    public void onPause() {
        this.backgroundManager.onPause();
    }

    public void onDestroy() {
        this.backgroundManager.onDestroy();
    }
}

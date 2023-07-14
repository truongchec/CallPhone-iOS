package com.appsgenz.callphoneios.screen.ios2;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.appsgenz.callphoneios.screen.ActionAcceptResult;
import com.appsgenz.callphoneios.screen.ActionScreenResult;
import com.appsgenz.callphoneios.screen.BaseScreen;
import com.appsgenz.callphoneios.screen.ios.ViewCall;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class ViewScreenIOS2 extends BaseScreen {
    private final ViewAddCallIOS viewAddCallIOS;
    private final ViewCall viewCall;

    public ViewScreenIOS2(Context context) {
        super(context);
        int widthScreen = OtherUtils.getWidthScreen(context);
        int i = widthScreen / 25;
        int i2 = (widthScreen * 18) / 100;
        LayoutParams layoutParams = new LayoutParams(i2, i2);
        layoutParams.addRule(21);
        int i3 = (widthScreen * 13) / 100;
        layoutParams.setMargins(0, MyShare.getSizeNotification(context) + i3, i, 0);
        addView(this.imAvatar, layoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(456456);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(16);
        linearLayout.setPadding(i, 0, i, 0);
        LayoutParams layoutParams2 = new LayoutParams(-1, i2);
        layoutParams2.setMargins(0, MyShare.getSizeNotification(context) + i3, 0, 0);
        layoutParams2.addRule(16, this.imAvatar.getId());
        addView(linearLayout, layoutParams2);
        linearLayout.addView(this.tvName, -1, -2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.setMargins(0, widthScreen / 200, 0, 0);
        linearLayout.addView(this.tvStatus, layoutParams3);
        ViewAddCallIOS viewAddCallIOS = new ViewAddCallIOS(context, this);
        this.viewAddCallIOS = viewAddCallIOS;
        viewAddCallIOS.setActionScreenResult(new ActionAcceptResult() { // from class: com.appsgenz.callphoneios.screen.ios2.ViewScreenIOS2.1
            @Override // com.appsgenz.callphoneios.screen.ActionAcceptResult
            public void onAccept() {
                ViewScreenIOS2.this.actionScreenResult.onAccept();
                ViewScreenIOS2.this.viewAddCallIOS.onRemove();
                ViewScreenIOS2.this.viewCall.onShow();
            }

            @Override // com.appsgenz.callphoneios.screen.ActionAcceptResult
            public void onReject() {
                ViewScreenIOS2.this.actionScreenResult.onReject();
            }
        });
        ViewCall viewCall = new ViewCall(context);
        this.viewCall = viewCall;
        LayoutParams layoutParams4 = new LayoutParams(-1, -1);
        layoutParams4.addRule(3, linearLayout.getId());
        addView(viewCall, layoutParams4);
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void setActionScreenResult(ActionScreenResult actionScreenResult) {
        super.setActionScreenResult(actionScreenResult);
        this.viewCall.setActionScreenResult(actionScreenResult);
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void callRinging() {
        this.viewAddCallIOS.onShow();
        this.viewCall.onStart();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void callStarted() {
        this.viewAddCallIOS.onRemove();
        this.viewCall.onShow();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void initOutgoingCallUI() {
        this.viewAddCallIOS.onRemove();
        this.viewCall.onShow();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void showPhoneAccountPicker() {
        this.viewCall.onPadClick();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void updateViewMode() {
        this.viewCall.updateUI(this.isMute, this.isSpeaker, this.isHold, this.isRec);
    }
}

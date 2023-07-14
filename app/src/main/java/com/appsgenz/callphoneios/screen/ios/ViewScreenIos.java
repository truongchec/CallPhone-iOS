package com.appsgenz.callphoneios.screen.ios;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.appsgenz.callphoneios.screen.ActionAcceptResult;
import com.appsgenz.callphoneios.screen.ActionScreenResult;
import com.appsgenz.callphoneios.screen.BaseScreen;
import com.appsgenz.callphoneios.service.CallManager;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;
import com.appsgenz.callphoneios.utils.ReadContact;

/* loaded from: classes.dex */
public class ViewScreenIos extends BaseScreen {
    private final ViewCall viewCall;
    private final ViewInComingIOS viewInComing;

    public ViewScreenIos(Context context) {
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
        ViewInComingIOS viewInComingIOS = new ViewInComingIOS(context);
        this.viewInComing = viewInComingIOS;
        viewInComingIOS.setViewRoot(this);
        viewInComingIOS.setVisibility(8);
        viewInComingIOS.setActionScreenResult(new ActionAcceptResult() { // from class: com.appsgenz.callphoneios.screen.ios.ViewScreenIos.1
            @Override // com.appsgenz.callphoneios.screen.ActionAcceptResult
            public void onReject() {
            }

            @Override // com.appsgenz.callphoneios.screen.ActionAcceptResult
            public void onAccept() {
                ViewScreenIos.this.actionScreenResult.onAccept();
                ViewScreenIos.this.viewInComing.onRemove();
                ViewScreenIos.this.viewCall.onShow();
            }
        });
        LayoutParams layoutParams4 = new LayoutParams(-1, (int) ((widthScreen * 21.2f) / 100.0f));
        layoutParams4.addRule(12);
        int i4 = widthScreen / 8;
        layoutParams4.setMargins(i4, 0, i4, MyShare.getSizeNavigation(context) + (widthScreen / 7));
        addView(viewInComingIOS, layoutParams4);
        ViewCall viewCall = new ViewCall(context);
        this.viewCall = viewCall;
        LayoutParams layoutParams5 = new LayoutParams(-1, -1);
        layoutParams5.addRule(3, linearLayout.getId());
        addView(viewCall, layoutParams5);
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void setActionScreenResult(ActionScreenResult actionScreenResult) {
        super.setActionScreenResult(actionScreenResult);
        this.viewCall.setActionScreenResult(actionScreenResult);
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
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
        this.imAvatar.setVisibility(8);
        this.tvName.setGravity(1);
        this.tvStatus.setGravity(1);
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void callRinging() {
        this.viewInComing.setVisibility(0);
        this.viewCall.onStart();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void callStarted() {
        this.viewInComing.onRemove();
        this.viewCall.onShow();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void initOutgoingCallUI() {
        this.viewInComing.onRemove();
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

package com.appsgenz.callphoneios.screen.samsung;

import android.content.Context;
import android.widget.RelativeLayout;
import com.appsgenz.callphoneios.screen.ActionAcceptResult;
import com.appsgenz.callphoneios.screen.ActionScreenResult;
import com.appsgenz.callphoneios.screen.BaseScreen;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class ViewScreenSamsung extends BaseScreen {
    private final ViewAddCallGalaxy viewAddCallGalaxy;
    private final ViewCallGalaxy viewCallGalaxy;

    public ViewScreenSamsung(Context context) {
        super(context);
        int widthScreen = OtherUtils.getWidthScreen(context);
        int i = (widthScreen * 38) / 100;
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(14);
        layoutParams.setMargins(0, MyShare.getSizeNotification(context) + ((widthScreen * 5) / 100), 0, 0);
        addView(this.tvName, layoutParams);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(14);
        layoutParams2.addRule(3, this.tvName.getId());
        layoutParams2.setMargins(0, 0, 0, widthScreen / 30);
        addView(this.tvStatus, layoutParams2);
        LayoutParams layoutParams3 = new LayoutParams(i, i);
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, this.tvStatus.getId());
        addView(this.imAvatar, layoutParams3);
        this.imAvatar.addStroke(widthScreen / 200, -1);
        ViewAddCallGalaxy viewAddCallGalaxy = new ViewAddCallGalaxy(context);
        this.viewAddCallGalaxy = viewAddCallGalaxy;
        viewAddCallGalaxy.setVisibility(8);
        viewAddCallGalaxy.setAddCallGalaxyResult(new ActionAcceptResult() { // from class: com.appsgenz.callphoneios.screen.samsung.ViewScreenSamsung.1
            @Override // com.appsgenz.callphoneios.screen.ActionAcceptResult
            public void onAccept() {
                ViewScreenSamsung.this.actionScreenResult.onAccept();
                ViewScreenSamsung.this.viewAddCallGalaxy.onRemove();
                ViewScreenSamsung.this.viewCallGalaxy.onShow();
            }

            @Override // com.appsgenz.callphoneios.screen.ActionAcceptResult
            public void onReject() {
                ViewScreenSamsung.this.actionScreenResult.onReject();
            }
        });
        LayoutParams layoutParams4 = new LayoutParams(-1, getResources().getDisplayMetrics().heightPixels / 2);
        layoutParams4.addRule(12);
        addView(viewAddCallGalaxy, layoutParams4);
        ViewCallGalaxy viewCallGalaxy = new ViewCallGalaxy(context);
        this.viewCallGalaxy = viewCallGalaxy;
        addView(viewCallGalaxy, -1, -1);
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void setActionScreenResult(ActionScreenResult actionScreenResult) {
        super.setActionScreenResult(actionScreenResult);
        this.viewCallGalaxy.setActionScreenResult(actionScreenResult);
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void callRinging() {
        this.viewAddCallGalaxy.setVisibility(0);
        this.viewCallGalaxy.onStart();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void callStarted() {
        this.viewAddCallGalaxy.onRemove();
        this.viewCallGalaxy.onShow();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void initOutgoingCallUI() {
        this.viewAddCallGalaxy.onRemove();
        this.viewCallGalaxy.onShow();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void showPhoneAccountPicker() {
        this.viewCallGalaxy.onPadClick();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void updateViewMode() {
        this.viewCallGalaxy.updateUI(this.isMute, this.isSpeaker, this.isHold, this.isRec);
    }
}

package com.appsgenz.callphoneios.screen.other;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;
import com.appsgenz.callphoneios.screen.ActionAcceptResult;
import com.appsgenz.callphoneios.screen.ActionScreenResult;
import com.appsgenz.callphoneios.screen.BaseScreen;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class ViewScreenOther extends BaseScreen {
    private final ViewAddCallOther viewAddCallOther;
    private final ViewCallOther viewCallOther;

    public ViewScreenOther(Context context) {
        super(context);
        int widthScreen = OtherUtils.getWidthScreen(context);
        int i = (widthScreen * 38) / 100;
        LayoutParams layoutParams = new LayoutParams(i, i);
        layoutParams.addRule(14);
        layoutParams.setMargins(0, MyShare.getSizeNotification(context) + (i / 3), 0, 0);
        addView(this.imAvatar, layoutParams);
        this.imAvatar.addStroke(widthScreen / 200, Color.parseColor("#90ffffff"));
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(14);
        layoutParams2.addRule(3, this.imAvatar.getId());
        int i2 = widthScreen / 100;
        layoutParams2.setMargins(0, i2, 0, i2);
        addView(this.tvName, layoutParams2);
        LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, this.tvName.getId());
        addView(this.tvStatus, layoutParams3);
        ViewAddCallOther viewAddCallOther = new ViewAddCallOther(context);
        this.viewAddCallOther = viewAddCallOther;
        viewAddCallOther.setVisibility(8);
        LayoutParams layoutParams4 = new LayoutParams(-1, widthScreen / 2);
        layoutParams4.addRule(12);
        layoutParams4.setMargins(0, 0, 0, MyShare.getSizeNavigation(context));
        addView(viewAddCallOther, layoutParams4);
        viewAddCallOther.setActionScreenResult(new ActionAcceptResult() { // from class: com.appsgenz.callphoneios.screen.other.ViewScreenOther.1
            @Override // com.appsgenz.callphoneios.screen.ActionAcceptResult
            public void onAccept() {
                ViewScreenOther.this.actionScreenResult.onAccept();
                ViewScreenOther.this.viewAddCallOther.onRemove();
                ViewScreenOther.this.viewCallOther.onShow();
            }

            @Override // com.appsgenz.callphoneios.screen.ActionAcceptResult
            public void onReject() {
                ViewScreenOther.this.actionScreenResult.onReject();
            }
        });
        ViewCallOther viewCallOther = new ViewCallOther(context);
        this.viewCallOther = viewCallOther;
        addView(viewCallOther, -1, -1);
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void setActionScreenResult(ActionScreenResult actionScreenResult) {
        super.setActionScreenResult(actionScreenResult);
        this.viewCallOther.setActionScreenResult(actionScreenResult);
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void callRinging() {
        this.viewAddCallOther.setVisibility(0);
        this.viewCallOther.onStart();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void callStarted() {
        this.viewAddCallOther.onRemove();
        this.viewCallOther.onShow();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void initOutgoingCallUI() {
        this.viewAddCallOther.onRemove();
        this.viewCallOther.onShow();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void showPhoneAccountPicker() {
        this.viewCallOther.onPadClick();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void updateViewMode() {
        this.viewCallOther.updateUI(this.isMute, this.isSpeaker, this.isHold, this.isRec);
    }
}

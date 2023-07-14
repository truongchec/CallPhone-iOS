package com.appsgenz.callphoneios.screen.mate;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.appsgenz.callphoneios.screen.ActionAcceptResult;
import com.appsgenz.callphoneios.screen.ActionScreenResult;
import com.appsgenz.callphoneios.screen.BaseScreen;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class ViewScreenMate extends BaseScreen {
    private final ViewAddCallMate viewAddCallMate;
    private final ViewCallMate viewCallMate;

    public ViewScreenMate(Context context) {
        super(context);
        int widthScreen = OtherUtils.getWidthScreen(context);
        int i = widthScreen / 70;
        int i2 = (widthScreen * 5) / 12;
        View view = new View(context);
        view.setId(65465);
        int i3 = i2 / 2;
        LayoutParams layoutParams = new LayoutParams(i3, i3);
        layoutParams.addRule(13);
        addView(view, layoutParams);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(14);
        layoutParams2.addRule(2, view.getId());
        addView(this.tvStatus, layoutParams2);
        LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        layoutParams3.addRule(14);
        layoutParams3.addRule(2, this.tvStatus.getId());
        layoutParams3.setMargins(i, i, i, i);
        addView(this.tvName, layoutParams3);
        LayoutParams layoutParams4 = new LayoutParams(i2, i2);
        layoutParams4.addRule(14);
        layoutParams4.addRule(2, this.tvName.getId());
        addView(this.imAvatar, layoutParams4);
        this.imAvatar.addStroke(widthScreen / 200, -16777216);
        int i4 = (widthScreen * 19) / 100;
        ViewAddCallMate viewAddCallMate = new ViewAddCallMate(context);
        this.viewAddCallMate = viewAddCallMate;
        viewAddCallMate.setVisibility(8);
        viewAddCallMate.setItfAddCall(new ActionAcceptResult() { // from class: com.appsgenz.callphoneios.screen.mate.ViewScreenMate.1
            @Override // com.appsgenz.callphoneios.screen.ActionAcceptResult
            public void onAccept() {
                ViewScreenMate.this.actionScreenResult.onAccept();
                ViewScreenMate.this.viewAddCallMate.onRemove();
                ViewScreenMate.this.viewCallMate.onShow();
            }

            @Override // com.appsgenz.callphoneios.screen.ActionAcceptResult
            public void onReject() {
                ViewScreenMate.this.actionScreenResult.onReject();
            }
        });
        LayoutParams layoutParams5 = new LayoutParams(-1, i4);
        layoutParams5.addRule(12);
        int i5 = i4 / 2;
        layoutParams5.setMargins(i5, 0, i5, i4);
        addView(viewAddCallMate, layoutParams5);
        ViewCallMate viewCallMate = new ViewCallMate(context);
        this.viewCallMate = viewCallMate;
        addView(viewCallMate, -1, -1);
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void setActionScreenResult(ActionScreenResult actionScreenResult) {
        super.setActionScreenResult(actionScreenResult);
        this.viewCallMate.setActionScreenResult(actionScreenResult);
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void callRinging() {
        this.viewAddCallMate.setVisibility(0);
        this.viewCallMate.onStart();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void callStarted() {
        this.viewAddCallMate.onRemove();
        this.viewCallMate.onShow();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void initOutgoingCallUI() {
        this.viewAddCallMate.onRemove();
        this.viewCallMate.onShow();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void showPhoneAccountPicker() {
        this.viewCallMate.onPadClick();
    }

    @Override // com.appsgenz.callphoneios.screen.BaseScreen
    public void updateViewMode() {
        this.viewCallMate.updateUI(this.isMute, this.isSpeaker, this.isHold, this.isRec);
    }
}

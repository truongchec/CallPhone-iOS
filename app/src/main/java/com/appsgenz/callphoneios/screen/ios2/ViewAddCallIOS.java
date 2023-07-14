package com.appsgenz.callphoneios.screen.ios2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.screen.ActionAcceptResult;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class ViewAddCallIOS {
    private ActionAcceptResult actionScreenResult;
    private boolean isRemove;
    private final LinearLayout llCall;
    private final LinearLayout llEnd;

    public void setActionScreenResult(ActionAcceptResult actionAcceptResult) {
        this.actionScreenResult = actionAcceptResult;
    }

    public ViewAddCallIOS(Context context, RelativeLayout relativeLayout) {
        int widthScreen = OtherUtils.getWidthScreen(context);
        int i = (int) ((widthScreen * 21.2f) / 100.0f);
        int i2 = widthScreen / 100;
        int sizeNavigation = MyShare.getSizeNavigation(context);
        int i3 = (widthScreen / 7) + i;
        LinearLayout linearLayout = new LinearLayout(context);
        this.llEnd = linearLayout;
        linearLayout.setVisibility(8);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        int i4 = (widthScreen * 45) / 100;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i4, i3);
        layoutParams.addRule(12);
        layoutParams.setMargins(0, 0, 0, sizeNavigation);
        relativeLayout.addView(linearLayout, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(context);
        this.llCall = linearLayout2;
        linearLayout2.setVisibility(8);
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i4, i3);
        layoutParams2.addRule(12);
        layoutParams2.addRule(21);
        layoutParams2.setMargins(0, 0, 0, sizeNavigation);
        relativeLayout.addView(linearLayout2, layoutParams2);
        ImageView imageView = new ImageView(context);
        imageView.setPadding(i2, i2, i2, i2);
        imageView.setImageResource(R.drawable.ic_end_call);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.screen.ios2.ViewAddCallIOS$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewAddCallIOS.this.m196xdab735e0(view);
            }
        });
        linearLayout.addView(imageView, i, i);
        TextW textW = new TextW(context);
        textW.setupText(300, 4.5f);
        textW.setSingleLine();
        textW.setGravity(17);
        int i5 = widthScreen / 50;
        textW.setPadding(i5, i5, i5, 0);
        textW.setText(R.string.decline);
        linearLayout.addView(textW, -1, -1);
        ImageView imageView2 = new ImageView(context);
        imageView2.setPadding(i2, i2, i2, i2);
        imageView2.setImageResource(R.drawable.ic_call_pad);
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.screen.ios2.ViewAddCallIOS$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewAddCallIOS.this.m197xce46ba21(view);
            }
        });
        linearLayout2.addView(imageView2, i, i);
        TextW textW2 = new TextW(context);
        textW2.setupText(300, 4.5f);
        textW2.setSingleLine();
        textW2.setGravity(17);
        textW2.setPadding(i5, i5, i5, 0);
        textW2.setText(R.string.accept);
        linearLayout2.addView(textW2, -1, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-screen-ios2-ViewAddCallIOS  reason: not valid java name */
    public /* synthetic */ void m196xdab735e0(View view) {
        this.actionScreenResult.onReject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-screen-ios2-ViewAddCallIOS  reason: not valid java name */
    public /* synthetic */ void m197xce46ba21(View view) {
        this.actionScreenResult.onAccept();
    }

    public void onShow() {
        this.llCall.setVisibility(0);
        this.llEnd.setVisibility(0);
    }

    public void onRemove() {
        if (this.llCall.getVisibility() == 8 && this.llCall.getParent() != null) {
            this.isRemove = true;
            ((ViewGroup) this.llCall.getParent()).removeView(this.llCall);
            ((ViewGroup) this.llEnd.getParent()).removeView(this.llEnd);
        } else if (this.isRemove) {
        } else {
            this.isRemove = true;
            this.llCall.animate().alpha(0.0f).setDuration(500L).withEndAction(new Runnable() { // from class: com.appsgenz.callphoneios.screen.ios2.ViewAddCallIOS$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    ViewAddCallIOS.this.m198x1aa1e9f();
                }
            }).start();
            this.llEnd.animate().alpha(0.0f).setDuration(500L).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onRemove$2$com-callos14-callscreen-colorphone-screen-ios2-ViewAddCallIOS  reason: not valid java name */
    public /* synthetic */ void m198x1aa1e9f() {
        if (this.llCall.getParent() != null) {
            ((ViewGroup) this.llCall.getParent()).removeView(this.llCall);
            ((ViewGroup) this.llEnd.getParent()).removeView(this.llEnd);
        }
    }
}

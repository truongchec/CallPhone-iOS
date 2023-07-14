package com.appsgenz.callphoneios.screen.ios;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.screen.ActionScreenResult;
import com.appsgenz.callphoneios.screen.PadResult;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class ViewCall extends RelativeLayout {
    private ActionScreenResult actionScreenResult;
    private final ImageView imEndCall;
    private boolean openPad;
    private final PadNum padNum;
    private final RelativeLayout rlMode;
    private final int size;
    private final ViewItemMode vHold;
    private final ViewItemMode vMute;
    private final ViewItemMode vRec;
    private final ViewItemMode vSpeaker;
    private final int w;

    public ViewCall(Context context) {
        super(context);
        int widthScreen = OtherUtils.getWidthScreen(context);
        this.w = widthScreen;
        int i = (int) ((widthScreen * 21.2f) / 100.0f);
        this.size = i;
        int i2 = widthScreen / 100;
        ImageView imageView = new ImageView(context);
        this.imEndCall = imageView;
        imageView.setId(99);
        imageView.setPadding(i2, i2, i2, i2);
        imageView.setImageResource(R.drawable.im_decline);
        imageView.setPivotX(i / 2.0f);
        imageView.setPivotY(i / 2.0f);
        imageView.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.screen.ios.ViewCall$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewCall.this.m186xa25788de(view);
            }
        });
        LayoutParams layoutParams = new LayoutParams(i, i);
        layoutParams.setMargins(0, 0, 0, MyShare.getSizeNavigation(context) + (widthScreen / 7));
        layoutParams.addRule(12);
        addView(imageView, layoutParams);
        imageView.setTranslationX((widthScreen - i) / 2.0f);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.rlMode = relativeLayout;
        LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        layoutParams2.addRule(2, imageView.getId());
        addView(relativeLayout, layoutParams2);
        PadNum padNum = new PadNum(context);
        this.padNum = padNum;
        padNum.setAlpha(0.0f);
        padNum.setScaleX(0.0f);
        padNum.setScaleY(0.0f);
        padNum.setVisibility(8);
        LayoutParams layoutParams3 = new LayoutParams(-1, -1);
        layoutParams3.addRule(8, imageView.getId());
        addView(padNum, layoutParams3);
        View view = new View(context);
        view.setId(100);
        LayoutParams layoutParams4 = new LayoutParams(i2, i2);
        layoutParams4.addRule(13);
        relativeLayout.addView(view, layoutParams4);
        ViewItemMode viewItemMode = new ViewItemMode(context);
        viewItemMode.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.screen.ios.ViewCall$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ViewCall.this.m187xa38ddbbd(view2);
            }
        });
        viewItemMode.setId(101);
        viewItemMode.setMode(R.drawable.im_mode_pad, R.string.keypad);
        LayoutParams layoutParams5 = new LayoutParams(-2, -2);
        layoutParams5.addRule(14);
        layoutParams5.addRule(2, view.getId());
        relativeLayout.addView(viewItemMode, layoutParams5);
        ViewItemMode viewItemMode2 = new ViewItemMode(context);
        this.vMute = viewItemMode2;
        viewItemMode2.setId(102);
        viewItemMode2.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.screen.ios.ViewCall$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ViewCall.this.m188xa4c42e9c(view2);
            }
        });
        viewItemMode2.setMode(R.drawable.im_mode_mute_on, R.string.mute);
        LayoutParams layoutParams6 = new LayoutParams(-2, -2);
        layoutParams6.addRule(6, viewItemMode.getId());
        layoutParams6.addRule(16, viewItemMode.getId());
        relativeLayout.addView(viewItemMode2, layoutParams6);
        ViewItemMode viewItemMode3 = new ViewItemMode(context);
        this.vSpeaker = viewItemMode3;
        viewItemMode3.setId(103);
        viewItemMode3.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.screen.ios.ViewCall$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ViewCall.this.m189xa5fa817b(view2);
            }
        });
        viewItemMode3.setMode(R.drawable.im_mode_speaker, R.string.speaker);
        LayoutParams layoutParams7 = new LayoutParams(-2, -2);
        layoutParams7.addRule(6, viewItemMode.getId());
        layoutParams7.addRule(17, viewItemMode.getId());
        relativeLayout.addView(viewItemMode3, layoutParams7);
        ViewItemMode viewItemMode4 = new ViewItemMode(context);
        this.vHold = viewItemMode4;
        viewItemMode4.setId(104);
        viewItemMode4.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.screen.ios.ViewCall$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ViewCall.this.m190xa730d45a(view2);
            }
        });
        viewItemMode4.setMode(R.drawable.im_mode_hold, R.string.hold);
        LayoutParams layoutParams8 = new LayoutParams(-2, -2);
        layoutParams8.addRule(14);
        layoutParams8.addRule(3, view.getId());
        relativeLayout.addView(viewItemMode4, layoutParams8);
        ViewItemMode viewItemMode5 = new ViewItemMode(context);
        viewItemMode5.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.screen.ios.ViewCall$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ViewCall.this.m191xa8672739(view2);
            }
        });
        viewItemMode5.setId(105);
        viewItemMode5.setMode(R.drawable.im_mode_contact, R.string.contacts);
        LayoutParams layoutParams9 = new LayoutParams(-2, -2);
        layoutParams9.addRule(3, view.getId());
        layoutParams9.addRule(17, viewItemMode.getId());
        relativeLayout.addView(viewItemMode5, layoutParams9);
        ViewItemMode viewItemMode6 = new ViewItemMode(context);
        this.vRec = viewItemMode6;
        viewItemMode6.setId(106);
        viewItemMode6.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.screen.ios.ViewCall$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ViewCall.this.m192xa99d7a18(view2);
            }
        });
        viewItemMode6.setMode(R.drawable.im_mode_rec, R.string.rec);
        LayoutParams layoutParams10 = new LayoutParams(-2, -2);
        layoutParams10.addRule(3, view.getId());
        layoutParams10.addRule(16, viewItemMode.getId());
        relativeLayout.addView(viewItemMode6, layoutParams10);
        padNum.setPadResult(new PadResult() { // from class: com.appsgenz.callphoneios.screen.ios.ViewCall$$ExternalSyntheticLambda7
            @Override // com.appsgenz.callphoneios.screen.PadResult
            public final void onViewClick(boolean z, String str) {
                ViewCall.this.m193xaad3ccf7(z, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-screen-ios-ViewCall  reason: not valid java name */
    public /* synthetic */ void m186xa25788de(View view) {
        this.actionScreenResult.onReject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-screen-ios-ViewCall  reason: not valid java name */
    public /* synthetic */ void m187xa38ddbbd(View view) {
        onPadClick();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$2$com-callos14-callscreen-colorphone-screen-ios-ViewCall  reason: not valid java name */
    public /* synthetic */ void m188xa4c42e9c(View view) {
        this.actionScreenResult.onMute();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$3$com-callos14-callscreen-colorphone-screen-ios-ViewCall  reason: not valid java name */
    public /* synthetic */ void m189xa5fa817b(View view) {
        this.actionScreenResult.onSpeaker();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$4$com-callos14-callscreen-colorphone-screen-ios-ViewCall  reason: not valid java name */
    public /* synthetic */ void m190xa730d45a(View view) {
        this.actionScreenResult.onHold();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$5$com-callos14-callscreen-colorphone-screen-ios-ViewCall  reason: not valid java name */
    public /* synthetic */ void m191xa8672739(View view) {
        this.actionScreenResult.onOpenContact();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$6$com-callos14-callscreen-colorphone-screen-ios-ViewCall  reason: not valid java name */
    public /* synthetic */ void m192xa99d7a18(View view) {
        this.actionScreenResult.onRecorder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$7$com-callos14-callscreen-colorphone-screen-ios-ViewCall  reason: not valid java name */
    public /* synthetic */ void m193xaad3ccf7(boolean z, String str) {
        if (z) {
            onPadClick();
            return;
        }
        ActionScreenResult actionScreenResult = this.actionScreenResult;
        if (actionScreenResult != null) {
            actionScreenResult.onPadClick(str);
        }
    }

    public void setActionScreenResult(ActionScreenResult actionScreenResult) {
        this.actionScreenResult = actionScreenResult;
    }

    public void onStart() {
        setVisibility(8);
        setAlpha(0.0f);
        ImageView imageView = this.imEndCall;
        int i = this.w;
        imageView.setTranslationX((i - this.size) - (i / 8.0f));
        this.imEndCall.setRotation(80.0f);
    }

    public void onShow() {
        if (getVisibility() == 8) {
            setVisibility(0);
            animate().alpha(1.0f).setDuration(500L).start();
            this.imEndCall.animate().translationX((this.w - this.size) / 2.0f).rotation(0.0f).setDuration(500L).start();
        }
    }

    public void onPadClick() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
        if (this.rlMode.getPivotX() == 0.0f) {
            this.rlMode.setPivotX(relativeLayout.getWidth() / 2.0f);
            this.rlMode.setPivotY(relativeLayout2.getHeight() / 2.0f);
            this.padNum.setPivotX(this.rlMode.getWidth() / 2.0f);
            this.padNum.setPivotY(this.rlMode.getHeight() / 2.0f);
        }
        boolean z = !this.openPad;
        this.openPad = z;
        if (z) {
            this.padNum.setVisibility(0);
            long j = 400;
            this.padNum.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(j).withEndAction(null).start();
            this.rlMode.animate().alpha(0.0f).scaleX(1.2f).scaleY(1.2f).setDuration(j).start();
            return;
        }
        long j2 = 400;
        this.padNum.animate().alpha(0.0f).scaleX(0.0f).scaleY(0.0f).setDuration(j2).withEndAction(new Runnable() { // from class: com.appsgenz.callphoneios.screen.ios.ViewCall$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                ViewCall.this.m194x38892628();
            }
        }).start();
        this.rlMode.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(j2).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onPadClick$8$com-callos14-callscreen-colorphone-screen-ios-ViewCall  reason: not valid java name */
    public /* synthetic */ void m194x38892628() {
        this.padNum.setVisibility(8);
    }

    public void updateUI(boolean z, boolean z2, boolean z3, boolean z4) {
        this.vMute.setEnable(z);
        this.vSpeaker.setEnable(z2);
        this.vHold.setEnable(z3);
        this.vRec.setEnable(z4);
    }
}

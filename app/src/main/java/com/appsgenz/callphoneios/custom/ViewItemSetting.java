package com.appsgenz.callphoneios.custom;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.custom.ViewSwitch;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class ViewItemSetting extends LinearLayout {
    private SwitchListener switchListener;
    private final TextW tv;
    private final int w;

    /* loaded from: classes.dex */
    public interface SwitchListener {
        void onSwitchChange(ViewItemSetting viewItemSetting, boolean z);
    }

    public ViewItemSetting(Context context) {
        super(context);
        setOrientation(0);
        setGravity(16);
        int widthScreen = OtherUtils.getWidthScreen(context);
        this.w = widthScreen;
        setBackgroundResource(R.drawable.sel_tran);
        TextW textW = new TextW(context);
        this.tv = textW;
        textW.setupText(400, 4.2f);
        int i = ((widthScreen / 25) * 3) / 2;
        textW.setPadding(i, 0, i, 0);
        addView(textW, new LayoutParams(0, -2, 1.0f));
    }

    public void setItem(int i, boolean z) {
        this.tv.setText(i);
        if (z) {
            this.tv.setTextColor(-16777216);
        } else {
            this.tv.setTextColor(-1);
        }
    }

    public void addNext() {
        int i = this.w / 25;
        int i2 = (int) (i * 3.2f);
        ImageView imageView = new ImageView(getContext());
        imageView.setPadding(i, i, i, i);
        imageView.setImageResource(R.drawable.im_next);
        addView(imageView, i2, i2);
    }

    public ImageView addMode(int i, OnClickListener onClickListener) {
        int i2 = this.w / 25;
        int i3 = (int) (i2 * 3.5f);
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(i);
        imageView.setOnClickListener(onClickListener);
        imageView.setPadding(i2, i2, i2, i2);
        addView(imageView, i3, i3);
        return imageView;
    }

    public void addSwitch(boolean z, SwitchListener switchListener) {
        this.switchListener = switchListener;
        ViewSwitch viewSwitch = new ViewSwitch(getContext());
        viewSwitch.setStatus(z);
        viewSwitch.setStatusResult(new ViewSwitch.StatusResult() { // from class: com.appsgenz.callphoneios.custom.ViewItemSetting$$ExternalSyntheticLambda0
            @Override // com.appsgenz.callphoneios.custom.ViewSwitch.StatusResult
            public final void onSwitchResult(boolean z2) {
                ViewItemSetting.this.m91x90cfe857(z2);
            }
        });
        int i = getResources().getDisplayMetrics().widthPixels;
        int i2 = (int) ((i * 6.3f) / 100.0f);
        LayoutParams layoutParams = new LayoutParams((int) ((i2 * 13.6f) / 8.3f), i2);
        layoutParams.setMargins(0, 0, (i / 25) / 2, 0);
        addView(viewSwitch, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$addSwitch$0$com-callos14-callscreen-colorphone-custom-ViewItemSetting  reason: not valid java name */
    public /* synthetic */ void m91x90cfe857(boolean z) {
        this.switchListener.onSwitchChange(this, z);
    }
}

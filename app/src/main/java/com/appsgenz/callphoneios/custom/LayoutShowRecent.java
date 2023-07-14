package com.appsgenz.callphoneios.custom;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.item.ItemRecent;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class LayoutShowRecent extends RelativeLayout {
    private final TextW tvDur;
    private final TextW tvStatus;
    private final TextW tvTime;

    public LayoutShowRecent(Context context) {
        super(context);
        int widthScreen = OtherUtils.getWidthScreen(context) / 50;
        TextW textW = new TextW(context);
        this.tvTime = textW;
        textW.setId(9898);
        textW.setupText(350, 3.0f);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        int i = widthScreen / 8;
        layoutParams.setMargins(widthScreen, widthScreen, widthScreen, i);
        addView(textW, layoutParams);
        TextW textW2 = new TextW(context);
        this.tvStatus = textW2;
        textW2.setId(9899);
        textW2.setupText(500, 3.0f);
        textW2.setGravity(16);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.addRule(17, textW.getId());
        layoutParams2.addRule(6, textW.getId());
        addView(textW2, layoutParams2);
        TextW textW3 = new TextW(context);
        this.tvDur = textW3;
        textW3.setupText(350, 2.9f);
        textW3.setTextColor(Color.parseColor("#a8a8a8"));
        textW3.setPadding(0, 0, 0, i);
        LayoutParams layoutParams3 = new LayoutParams(-1, -2);
        layoutParams3.addRule(3, textW2.getId());
        layoutParams3.addRule(18, textW2.getId());
        addView(textW3, layoutParams3);
    }

    public void setRecent(ItemRecent itemRecent, boolean z) {
        this.tvTime.setText(OtherUtils.longToTime(itemRecent.time));
        int i = itemRecent.type;
        if (i == 3) {
            this.tvDur.setVisibility(8);
            this.tvStatus.setText(R.string.missed_call);
        } else if (i == 4) {
            this.tvDur.setVisibility(0);
            this.tvStatus.setText(R.string.voicemails);
        } else if (i == 6) {
            this.tvDur.setVisibility(8);
            this.tvStatus.setText(R.string.call_is_blocked);
        } else if (i == 7) {
            this.tvDur.setVisibility(8);
            this.tvStatus.setText(R.string.call_another_device);
        } else if (itemRecent.dur <= 0) {
            this.tvDur.setVisibility(8);
            this.tvStatus.setText(R.string.call_is_canceled);
        } else {
            this.tvDur.setVisibility(0);
            if (itemRecent.type == 2) {
                this.tvStatus.setText(R.string.outgoing_call);
            } else {
                this.tvStatus.setText(R.string.incoming_call);
            }
        }
        if (this.tvDur.getVisibility() == 0) {
            this.tvDur.setText(OtherUtils.getDur(getContext(), itemRecent.dur));
        }
        if (z) {
            this.tvStatus.setTextColor(-16777216);
            this.tvTime.setTextColor(-16777216);
            return;
        }
        this.tvStatus.setTextColor(-1);
        this.tvTime.setTextColor(-1);
    }
}

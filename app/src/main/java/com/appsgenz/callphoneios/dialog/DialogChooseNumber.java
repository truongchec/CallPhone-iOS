package com.appsgenz.callphoneios.dialog;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.appsgenz.callphoneios.custom.ViewItemNumber;
import com.appsgenz.callphoneios.item.ItemContact;
import com.appsgenz.callphoneios.item.ItemPhone;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class DialogChooseNumber extends BaseDialog implements ViewItemNumber.OnItemNumberClick {
    private final NumberContactResult contactResult;
    private final ItemContact itemContact;
    private final boolean theme;

    /* loaded from: classes.dex */
    public interface NumberContactResult {
        void onNumberResult(ItemPhone itemPhone);
    }

    @Override // com.appsgenz.callphoneios.custom.ViewItemNumber.OnItemNumberClick
    public void onLongClickNumber(ItemPhone itemPhone) {
    }

    public DialogChooseNumber(Context context, ItemContact itemContact, boolean z, NumberContactResult numberContactResult) {
        super(context);
        this.itemContact = itemContact;
        this.theme = z;
        this.contactResult = numberContactResult;
        setCancelable(true);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int widthScreen = OtherUtils.getWidthScreen(getContext());
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(17);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setLayoutTransition(new LayoutTransition());
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(1);
        linearLayout.addView(linearLayout2, (widthScreen * 7) / 10, -2);
        setContentView(linearLayout);
        for (int i = 0; i < this.itemContact.getArrPhone().size(); i++) {
            ViewItemNumber viewItemNumber = new ViewItemNumber(getContext());
            viewItemNumber.setNumber(this.itemContact.getArrPhone().get(i), null, this.theme, this);
            linearLayout2.addView(viewItemNumber, -1, -2);
            if (i < this.itemContact.getArrPhone().size() - 1) {
                View view = new View(getContext());
                if (this.theme) {
                    view.setBackgroundColor(Color.parseColor("#dedede"));
                } else {
                    view.setBackgroundColor(Color.parseColor("#5c5c5c"));
                }
                linearLayout2.addView(view, -1, 1);
            }
        }
        if (this.theme) {
            linearLayout2.setBackground(OtherUtils.bgIcon(-1, (widthScreen * 4.0f) / 100.0f));
        } else {
            linearLayout2.setBackground(OtherUtils.bgIcon(Color.parseColor("#424141"), (widthScreen * 4.0f) / 100.0f));
        }
    }

    @Override // com.appsgenz.callphoneios.custom.ViewItemNumber.OnItemNumberClick
    public void onNumberResult(ItemPhone itemPhone) {
        this.contactResult.onNumberResult(itemPhone);
    }
}

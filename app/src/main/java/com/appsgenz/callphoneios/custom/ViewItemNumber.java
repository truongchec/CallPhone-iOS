package com.appsgenz.callphoneios.custom;

import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.item.ItemPhone;
import com.appsgenz.callphoneios.item.ItemRecentGroup;
import com.appsgenz.callphoneios.utils.OtherUtils;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.item.ItemFavorites;
import com.appsgenz.callphoneios.item.ItemPhone;
import com.appsgenz.callphoneios.item.ItemRecentGroup;
import com.appsgenz.callphoneios.utils.OtherUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ViewItemNumber extends RelativeLayout {
    private final ImageView imFav;
    private ItemPhone itemPhone;
    private OnItemNumberClick onItemNumberClick;
    private final TextW tvNumber;
    private final TextW tvRecent;
    private final TextW tvTitle;

    /* loaded from: classes.dex */
    public interface OnItemNumberClick {
        void onLongClickNumber(ItemPhone itemPhone);

        void onNumberResult(ItemPhone itemPhone);
    }

    public ViewItemNumber(Context context) {
        super(context);
        int widthScreen = OtherUtils.getWidthScreen(context) / 25;
        setPadding(widthScreen, widthScreen, widthScreen, widthScreen);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(20);
        linearLayout.setOrientation(0);
        addView(linearLayout, -1, -2);
        TextW textW = new TextW(context);
        this.tvTitle = textW;
        textW.setupText(350, 3.2f);
        linearLayout.addView(textW, -2, -2);
        ImageView imageView = new ImageView(context);
        this.imFav = imageView;
        imageView.setImageResource(R.drawable.ic_star_fav);
        int i = widthScreen / 16;
        imageView.setPadding(i, 0, i, i * 2);
        imageView.setVisibility(8);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(widthScreen, -1);
        int i2 = widthScreen / 3;
        layoutParams.setMargins(i2, 0, 0, 0);
        linearLayout.addView(imageView, layoutParams);
        TextW textW2 = new TextW(context);
        this.tvRecent = textW2;
        textW2.setTextColor(-1);
        textW2.setupText(450, 2.1f);
        textW2.setText(R.string.recent_up);
        int i3 = widthScreen / 7;
        textW2.setPadding(i3, 0, i3, 0);
        textW2.setVisibility(8);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(i2, 0, 0, 0);
        linearLayout.addView(textW2, layoutParams2);
        TextW textW3 = new TextW(context);
        this.tvNumber = textW3;
        textW3.setupText(400, 3.9f);
        textW3.setTextColor(Color.parseColor("#007AFF"));
        LayoutParams layoutParams3 = new LayoutParams(-1, -2);
        layoutParams3.addRule(3, linearLayout.getId());
        addView(textW3, layoutParams3);
        setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.custom.ViewItemNumber$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewItemNumber.this.m89xbfa5acd7(view);
            }
        });
        setOnLongClickListener(new OnLongClickListener() { // from class: com.appsgenz.callphoneios.custom.ViewItemNumber$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return ViewItemNumber.this.m90x4c92c3f6(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-custom-ViewItemNumber  reason: not valid java name */
    public /* synthetic */ void m89xbfa5acd7(View view) {
        OnItemNumberClick onItemNumberClick = this.onItemNumberClick;
        if (onItemNumberClick != null) {
            onItemNumberClick.onNumberResult(this.itemPhone);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-custom-ViewItemNumber  reason: not valid java name */
    public /* synthetic */ boolean m90x4c92c3f6(View view) {
        OnItemNumberClick onItemNumberClick = this.onItemNumberClick;
        if (onItemNumberClick != null) {
            onItemNumberClick.onLongClickNumber(this.itemPhone);
            return true;
        }
        return true;
    }

    public void setNumber(ItemPhone itemPhone, ItemRecentGroup itemRecentGroup, boolean z, OnItemNumberClick onItemNumberClick) {
        this.itemPhone = itemPhone;
        this.onItemNumberClick = onItemNumberClick;
        if (itemPhone.getNumber() != null) {
            this.tvNumber.setText(itemPhone.getNumber());
        } else {
            this.tvNumber.setText(R.string.unknown);
        }
        this.tvTitle.setText(ContactsContract.CommonDataKinds.Phone.getTypeLabelResource(itemPhone.getType()));
        if (z) {
            this.tvTitle.setTextColor(-16777216);
            this.imFav.clearColorFilter();
            this.tvRecent.setBackground(OtherUtils.bgIcon(Color.parseColor("#D1D1D1"), OtherUtils.getWidthScreen(getContext()) / 150.0f));
        } else {
            this.tvTitle.setTextColor(-1);
            this.imFav.setColorFilter(Color.parseColor("#9B9B9B"));
            this.tvRecent.setBackground(OtherUtils.bgIcon(Color.parseColor("#9B9B9B"), OtherUtils.getWidthScreen(getContext()) / 150.0f));
        }
        if (itemRecentGroup == null) {
            this.tvRecent.setVisibility(8);
            return;
        }
        String number = itemPhone.getNumber();
        String str = itemRecentGroup.arrRecent.get(0).number;
        if (number != null && str != null && PhoneNumberUtils.compare(number, str)) {
            this.tvRecent.setVisibility(0);
        } else {
            this.tvRecent.setVisibility(8);
        }
    }

    public void setNumberInDialog(ItemPhone itemPhone, boolean z, OnItemNumberClick onItemNumberClick) {
        this.itemPhone = itemPhone;
        this.onItemNumberClick = onItemNumberClick;
        if (itemPhone.getNumber() != null) {
            this.tvNumber.setText(itemPhone.getNumber());
        } else {
            this.tvNumber.setText(R.string.unknown);
        }
        this.tvTitle.setText(ContactsContract.CommonDataKinds.Phone.getTypeLabelResource(itemPhone.getType()));
        this.tvTitle.setupText(400, 3.9f);
        this.imFav.setVisibility(8);
        if (z) {
            this.tvTitle.setTextColor(-16777216);
            this.tvNumber.setTextColor(Color.parseColor("#a9a9a9"));
            return;
        }
        this.tvTitle.setTextColor(-1);
        this.tvNumber.setTextColor(Color.parseColor("#868686"));
    }

    public void setMissCall() {
        this.tvNumber.setTextColor(Color.parseColor("#FF2828"));
    }

    public void updateViewFav(ArrayList<ItemFavorites> arrayList) {
        boolean z;
        Iterator<ItemFavorites> it = arrayList.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                z = true;
                break;
            }
            ItemFavorites next = it.next();
            if (next.number != null && next.number.equals(this.itemPhone.getNumber())) {
                this.imFav.setVisibility(0);
                break;
            }
        }
        if (z) {
            this.imFav.setVisibility(8);
        }
    }
}

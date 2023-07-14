package com.appsgenz.callphoneios.custom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.custom.FavOnItemClick;
import com.appsgenz.callphoneios.item.ItemRecentGroup;
import com.appsgenz.callphoneios.utils.OtherUtils;
import com.ironsource.mediationsdk.utils.IronSourceConstants;

/* loaded from: classes.dex */
public class LayoutRecent extends RelativeLayout {
    private FavOnItemClick favOnItemClick;
    private ImageView imDel;
    private ImageView imInfo;
    private ImageView imSim;
    private ImageView imStatus;
    private TextW tvName;
    private TextW tvStatus;
    private TextW tvTime;

    public void setFavOnItemClick(FavOnItemClick favOnItemClick) {
        this.favOnItemClick = favOnItemClick;
    }

    public LayoutRecent(Context context) {
        super(context);
        init(context);
    }

    public LayoutRecent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public LayoutRecent(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        setLayoutTransition(OtherUtils.animLayoutItem());
        int widthScreen = OtherUtils.getWidthScreen(context) / 25;
        int i = (int) (widthScreen * 3.2f);
        setOnLongClickListener(new View.OnLongClickListener() { // from class: com.callos14.callscreen.colorphone.custom.LayoutRecent$$ExternalSyntheticLambda2
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return LayoutRecent.this.m85x48749e71(view);
            }
        });
        TextW textW = new TextW(context);
        this.tvName = textW;
        textW.setId(IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED);
        this.tvName.setupText(600, 4.2f);
        int i2 = widthScreen / 2;
        this.tvName.setPadding(0, i2, 0, 0);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setId(955);
        TextW textW2 = new TextW(context);
        this.tvTime = textW2;
        textW2.setId(IronSourceConstants.USING_CACHE_FOR_INIT_EVENT);
        this.tvTime.setupText(400, 3.6f);
        this.tvTime.setTextColor(Color.parseColor("#a8a8a8"));
        this.tvTime.setGravity(16);
        this.tvTime.setPadding(widthScreen, 0, 0, 0);
        View view = new View(context);
        view.setId(152);
        view.setBackgroundColor(Color.parseColor("#8A8A8E"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 1);
        layoutParams.addRule(3, relativeLayout.getId());
        layoutParams.setMargins(i, i2, 0, 0);
        addView(view, layoutParams);
        ImageView imageView = new ImageView(context);
        this.imStatus = imageView;
        imageView.setPadding(0, (widthScreen * 75) / 100, 0, widthScreen / 8);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i, -1);
        layoutParams2.addRule(6, this.tvName.getId());
        layoutParams2.addRule(8, this.tvName.getId());
        addView(this.imStatus, layoutParams2);
        ImageView imageView2 = new ImageView(context);
        this.imDel = imageView2;
        imageView2.setId(153);
        this.imDel.setImageResource(R.drawable.ic_del);
        this.imDel.setPadding(widthScreen, 0, widthScreen, 0);
        this.imDel.setOnClickListener(new View.OnClickListener() { // from class: com.callos14.callscreen.colorphone.custom.LayoutRecent$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LayoutRecent.this.m86x49aaf150(view2);
            }
        });
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i, -1);
        layoutParams3.addRule(8, view.getId());
        layoutParams3.addRule(6, this.tvName.getId());
        addView(this.imDel, layoutParams3);
        ImageView imageView3 = new ImageView(context);
        this.imInfo = imageView3;
        imageView3.setId(154);
        this.imInfo.setImageResource(R.drawable.ic_info_fav);
        this.imInfo.setPadding(i2, 0, widthScreen, 0);
        this.imInfo.setOnClickListener(new View.OnClickListener() { // from class: com.callos14.callscreen.colorphone.custom.LayoutRecent$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LayoutRecent.this.m87x4ae1442f(view2);
            }
        });
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(i - (widthScreen / 4), -1);
        layoutParams4.addRule(6, this.imDel.getId());
        layoutParams4.addRule(8, this.imDel.getId());
        layoutParams4.addRule(21);
        addView(this.imInfo, layoutParams4);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams5.addRule(16, this.imInfo.getId());
        layoutParams5.addRule(6, this.imDel.getId());
        layoutParams5.addRule(8, this.imDel.getId());
        addView(this.tvTime, layoutParams5);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams6.addRule(17, this.imDel.getId());
        layoutParams6.addRule(16, this.tvTime.getId());
        addView(this.tvName, layoutParams6);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams7.addRule(3, this.tvName.getId());
        layoutParams7.addRule(18, this.tvName.getId());
        layoutParams7.addRule(19, this.tvName.getId());
        addView(relativeLayout, layoutParams7);
        TextW textW3 = new TextW(context);
        this.tvStatus = textW3;
        textW3.setId(151);
        this.tvStatus.setupText(400, 3.4f);
        this.tvStatus.setTextColor(Color.parseColor("#a8a8a8"));
        int i3 = widthScreen / 10;
        ImageView imageView4 = new ImageView(context);
        this.imSim = imageView4;
        imageView4.setId(655);
        this.imSim.setPadding(i3, i3, i3, i3);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(widthScreen, -1);
        layoutParams8.addRule(6, this.tvStatus.getId());
        layoutParams8.addRule(8, this.tvStatus.getId());
        layoutParams8.setMargins(0, 0, widthScreen / 6, 0);
        relativeLayout.addView(this.imSim, layoutParams8);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams9.addRule(17, this.imSim.getId());
        relativeLayout.addView(this.tvStatus, layoutParams9);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$init$0$com-callos14-callscreen-colorphone-custom-LayoutRecent  reason: not valid java name */
    public /* synthetic */ boolean m85x48749e71(View view) {
        this.favOnItemClick.onLongClick();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$init$1$com-callos14-callscreen-colorphone-custom-LayoutRecent  reason: not valid java name */
    public /* synthetic */ void m86x49aaf150(View view) {
        this.favOnItemClick.onDel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$init$2$com-callos14-callscreen-colorphone-custom-LayoutRecent  reason: not valid java name */
    public /* synthetic */ void m87x4ae1442f(View view) {
        this.favOnItemClick.onInfo();
    }

    public void setItemRecent(ItemRecentGroup itemRecentGroup, int i, boolean z, boolean z2) {
        if (z) {
            this.imDel.setVisibility(0);
            this.imInfo.setVisibility(4);
            this.imStatus.setVisibility(8);
        } else {
            this.imDel.setVisibility(4);
            this.imInfo.setVisibility(0);
            this.imStatus.setVisibility(0);
        }
        int i2 = itemRecentGroup.arrRecent.get(0).type;
        if (i2 == 2) {
            this.imStatus.setImageResource(R.drawable.ic_status_out_call);
        } else {
            this.imStatus.setImageResource(0);
        }
        if (i2 == 3) {
            this.tvName.setTextColor(Color.parseColor("#FF2828"));
        } else if (z2) {
            this.tvName.setTextColor(-16777216);
        } else {
            this.tvName.setTextColor(-1);
        }
        String str = itemRecentGroup.name;
        if (str == null || str.isEmpty()) {
            str = itemRecentGroup.arrRecent.get(0).number;
        }
        if (str == null) {
            str = "";
        }
        if (itemRecentGroup.arrRecent.size() > 1) {
            str = str + " (" + itemRecentGroup.arrRecent.size() + ")";
        }
        this.tvName.setText(str);
        if (itemRecentGroup.nameType != null && !itemRecentGroup.nameType.isEmpty()) {
            this.tvStatus.setText(itemRecentGroup.nameType);
        } else {
            String str2 = itemRecentGroup.country;
            this.tvStatus.setText(str2 != null ? str2 : "");
        }
        this.tvTime.setText(OtherUtils.longToTime(getContext(), itemRecentGroup.time));
        if (i == 0) {
            this.imSim.setVisibility(0);
            this.imSim.setImageResource(R.drawable.ic_num_1);
        } else if (i == 1) {
            this.imSim.setVisibility(0);
            this.imSim.setImageResource(R.drawable.ic_num_2);
        } else {
            this.imSim.setVisibility(8);
        }
    }
}

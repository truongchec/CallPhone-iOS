package com.appsgenz.callphoneios.screen;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.adapter.AdapterContact;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.custom.ViewSearch;
import com.appsgenz.callphoneios.item.ItemContact;
import com.appsgenz.callphoneios.utils.ActionUtils;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;
import com.appsgenz.callphoneios.utils.ReadContact;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ViewAllContact extends RelativeLayout implements View.OnClickListener, AdapterContact.ContactResult {
    private final AdapterContact adapterContact;
    private final ArrayList<ItemContact> arrContact;
    private final Handler handler;
    private final int height;
    private boolean isAction;
    private boolean isLoaded;
    private final RelativeLayout rlRun;
    private final Runnable runnable;
    private String strSearch;
    private final View vBg;

    public ViewAllContact(final Context context) {
        super(context);
        this.runnable = new Runnable() { // from class: com.appsgenz.callphoneios.screen.ViewAllContact.2
            @Override // java.lang.Runnable
            public void run() {
                ViewAllContact.this.adapterContact.filter(ViewAllContact.this.strSearch.toLowerCase());
            }
        };
        int widthScreen = OtherUtils.getWidthScreen(context);
        int i = widthScreen / 30;
        boolean theme = MyShare.getTheme(context);
        int sizeNavigation = context.getResources().getDisplayMetrics().heightPixels + MyShare.getSizeNavigation(context);
        this.height = sizeNavigation;
        View view = new View(context);
        this.vBg = view;
        view.setAlpha(0.0f);
        view.setBackgroundColor(Color.parseColor("#55000000"));
        view.setOnClickListener(this);
        addView(view, -1, -1);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.rlRun = relativeLayout;
        relativeLayout.setTranslationY(sizeNavigation);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(0, MyShare.getSizeNotification(context) + (widthScreen / 10), 0, 0);
        addView(relativeLayout, layoutParams);
        ImageView imageView = new ImageView(context);
        imageView.setId(200);
        imageView.setImageResource(R.drawable.ic_close);
        imageView.setPadding(i, i, i, i);
        imageView.setOnClickListener(this);
        int i2 = i * 4;
        LayoutParams layoutParams2 = new LayoutParams(i2, i2);
        layoutParams2.addRule(21);
        relativeLayout.addView(imageView, layoutParams2);
        TextW textW = new TextW(context);
        textW.setupText(600, 3.8f);
        textW.setText(R.string.contacts);
        textW.setGravity(17);
        LayoutParams layoutParams3 = new LayoutParams(-1, -1);
        layoutParams3.addRule(6, imageView.getId());
        layoutParams3.addRule(8, imageView.getId());
        relativeLayout.addView(textW, layoutParams3);
        ArrayList<ItemContact> arrayList = new ArrayList<>();
        this.arrContact = arrayList;
        AdapterContact adapterContact = new AdapterContact(arrayList, theme, this);
        this.adapterContact = adapterContact;
        ViewSearch viewSearch = new ViewSearch(context);
        viewSearch.setId(1235);
        viewSearch.setVisibility(0);
        viewSearch.setListenerTextChange(new ViewSearch.TextResult() { // from class: com.appsgenz.callphoneios.screen.ViewAllContact.1
            @Override // com.appsgenz.callphoneios.custom.ViewSearch.TextResult
            public void onTextChange(String str) {
                ViewAllContact.this.strSearch = str;
                if (ViewAllContact.this.strSearch.isEmpty()) {
                    ViewAllContact.this.adapterContact.filter("");
                    return;
                }
                ViewAllContact.this.handler.removeCallbacks(ViewAllContact.this.runnable);
                ViewAllContact.this.handler.postDelayed(ViewAllContact.this.runnable, 400L);
            }
        });
        LayoutParams layoutParams4 = new LayoutParams(-1, -2);
        layoutParams4.addRule(3, imageView.getId());
        layoutParams4.setMargins(i, 0, i, i);
        relativeLayout.addView(viewSearch, layoutParams4);
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setAdapter(adapterContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        LayoutParams layoutParams5 = new LayoutParams(-1, -1);
        layoutParams5.addRule(3, viewSearch.getId());
        layoutParams5.setMargins(0, 0, 0, MyShare.getSizeNavigation(context));
        relativeLayout.addView(recyclerView, layoutParams5);
        this.handler = new Handler(new Handler.Callback() { // from class: com.appsgenz.callphoneios.screen.ViewAllContact$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return ViewAllContact.this.m182xc570757f(message);
            }
        });
        new Thread(new Runnable() { // from class: com.appsgenz.callphoneios.screen.ViewAllContact$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ViewAllContact.this.m183x525d8c9e(context);
            }
        }).start();
        if (theme) {
            relativeLayout.setBackgroundResource(R.drawable.bg_view_run);
            textW.setTextColor(Color.parseColor("#333333"));
            return;
        }
        relativeLayout.setBackgroundResource(R.drawable.bg_view_run_dark);
        textW.setTextColor(-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-screen-ViewAllContact  reason: not valid java name */
    public /* synthetic */ boolean m182xc570757f(Message message) {
        this.isLoaded = true;
        if (!this.isAction) {
            this.adapterContact.updateData();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-screen-ViewAllContact  reason: not valid java name */
    public /* synthetic */ void m183x525d8c9e(Context context) {
        this.arrContact.addAll(ReadContact.getAllContact(context));
        this.handler.sendEmptyMessage(1);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.isAction) {
            return;
        }
        this.isAction = true;
        onHide();
    }

    public void onShow() {
        if (this.isAction) {
            return;
        }
        this.isAction = true;
        this.vBg.animate().alpha(1.0f).setDuration(400L).start();
        this.rlRun.animate().translationY(0.0f).setDuration(420L).withEndAction(new Runnable() { // from class: com.appsgenz.callphoneios.screen.ViewAllContact$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ViewAllContact.this.m185xce0ee047();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onShow$2$com-callos14-callscreen-colorphone-screen-ViewAllContact  reason: not valid java name */
    public /* synthetic */ void m185xce0ee047() {
        this.isAction = false;
        if (this.isLoaded) {
            this.isLoaded = false;
            this.adapterContact.updateData();
        }
    }

    public void onHide() {
        this.vBg.animate().alpha(0.0f).setDuration(400L).start();
        this.rlRun.animate().translationY(this.height).setDuration(420L).withEndAction(new Runnable() { // from class: com.appsgenz.callphoneios.screen.ViewAllContact$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ViewAllContact.this.m184x8bd8601();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onHide$3$com-callos14-callscreen-colorphone-screen-ViewAllContact  reason: not valid java name */
    public /* synthetic */ void m184x8bd8601() {
        this.isAction = false;
        ViewParent parent = getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this);
        }
    }

    @Override // com.appsgenz.callphoneios.adapter.AdapterContact.ContactResult
    public void onItemContactClick(ItemContact itemContact) {
        ActionUtils.actionCall(getContext(), itemContact.getId());
    }
}

package com.appsgenz.callphoneios.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.utils.ActionUtils;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class ViewSearch extends LinearLayout {
    private final EditW edtSearch;
    private final ImageView imDel;

    /* loaded from: classes.dex */
    public interface TextResult {
        void onTextChange(String str);
    }

    public ViewSearch(Context context) {
        super(context);
        int widthScreen = OtherUtils.getWidthScreen(context);
        int i = widthScreen / 50;
        setOrientation(0);
        setGravity(16);
        setBackground(OtherUtils.bgIcon(Color.parseColor("#1f767680"), i * 1.6f));
        EditW editW = new EditW(context);
        this.edtSearch = editW;
        editW.setBackgroundColor(0);
        editW.setupText(400, 4.2f);
        editW.setHint(R.string.search);
        editW.setHintTextColor(Color.parseColor("#878789"));
        editW.setSingleLine();
        int i2 = widthScreen / 40;
        editW.setPadding(i, i2, i, i2);
        if (Build.VERSION.SDK_INT >= 28) {
            editW.setTypeface(Typeface.create(Typeface.SANS_SERIF, 400, false));
        } else {
            editW.setTypeface(Typeface.create(Typeface.SANS_SERIF, 0));
        }
        ImageView imageView = new ImageView(context);
        imageView.setPadding(i, i, 0, i);
        imageView.setImageResource(R.drawable.im_search);
        int i3 = (widthScreen * 8) / 100;
        addView(imageView, i3, i3);
        addView(editW, new LayoutParams(0, -2, 1.0f));
        ImageView imageView2 = new ImageView(context);
        this.imDel = imageView2;
        imageView2.setImageResource(R.drawable.ic_close);
        imageView2.setVisibility(4);
        imageView2.setPadding(i, 0, i, 0);
        imageView2.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.custom.ViewSearch$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewSearch.this.m95x6adb2fc3(view);
            }
        });
        int i4 = (widthScreen * 9) / 100;
        addView(imageView2, i4, i4);
        if (MyShare.getTheme(context)) {
            editW.setTextColor(Color.parseColor("#333333"));
        } else {
            editW.setTextColor(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-custom-ViewSearch  reason: not valid java name */
    public /* synthetic */ void m95x6adb2fc3(View view) {
        clearText();
    }

    public void clearText() {
        this.edtSearch.setText("");
    }

    public void setListenerTextChange(final TextResult textResult) {
        this.edtSearch.addTextChangedListener(new TextWatcher() { // from class: com.appsgenz.callphoneios.custom.ViewSearch.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() == 0) {
                    ViewSearch.this.imDel.setVisibility(8);
                } else {
                    ViewSearch.this.imDel.setVisibility(0);
                }
                textResult.onTextChange(charSequence.toString().toLowerCase());
            }
        });
    }

    public void onShow(boolean z) {
        if (z) {
            setVisibility(0);
            this.edtSearch.requestFocus();
            new Handler().postDelayed(new Runnable() { // from class: com.appsgenz.callphoneios.custom.ViewSearch$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ViewSearch.this.m96xaa92bfec();
                }
            }, 500L);
            return;
        }
        setVisibility(8);
        ActionUtils.hideKeyboard(getContext(), this.edtSearch);
        this.edtSearch.clearFocus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onShow$1$com-callos14-callscreen-colorphone-custom-ViewSearch  reason: not valid java name */
    public /* synthetic */ void m96xaa92bfec() {
        ActionUtils.showKeyboard(getContext(), this.edtSearch);
    }
}

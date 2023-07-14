package com.appsgenz.callphoneios.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsgenz.callphoneios.ActivityPreview;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.adapter.AdapterStyle;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class FragmentStyle extends Fragment {
    private AdapterStyle adapterStyle;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new ViewStyle(layoutInflater.getContext());
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AdapterStyle adapterStyle = this.adapterStyle;
//        if (adapterStyle != null) {
//            adapterStyle.onResume(RmSave.getPay(getContext()));
//        }
    }

    /* loaded from: classes.dex */
    class ViewStyle extends LinearLayout implements AdapterStyle.StyleItemClick {
        public ViewStyle(Context context) {
            super(context);
            setOrientation(1);
            int widthScreen = OtherUtils.getWidthScreen(context);
            int i = widthScreen / 25;
            TextW textW = new TextW(getContext());
            textW.setId(6666);
            textW.setupText(600, 8.0f);
            textW.setText(R.string.wallpaper);
            textW.setPadding(i, MyShare.getSizeNotification(context), 0, 0);
            addView(textW, -2, -2);
            //FragmentStyle.this.adapterStyle = new AdapterStyle(MyShare.getStyle(context), RmSave.getPay(context), this);
            FragmentStyle.this.adapterStyle = new AdapterStyle(MyShare.getStyle(context), true, this);
            RecyclerView recyclerView = new RecyclerView(context);
            recyclerView.setAdapter(FragmentStyle.this.adapterStyle);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 2, 1, false));
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            int i2 = widthScreen / 50;
            layoutParams.setMargins(i2, i / 2, i2, 0);
            addView(recyclerView, layoutParams);
            if (MyShare.getTheme(context)) {
                setBackgroundColor(-1);
                textW.setTextColor(-16777216);
                return;
            }
            setBackgroundColor(Color.parseColor("#2C2C2C"));
            textW.setTextColor(-1);
        }

        @Override // com.appsgenz.callphoneios.adapter.AdapterStyle.StyleItemClick
        public void goPremium() {
            FragmentStyle.this.startActivity(new Intent(getContext(), ActivityPreview.class));
        }
    }
}

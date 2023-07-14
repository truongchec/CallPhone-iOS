package com.appsgenz.callphoneios;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes.dex */
public class BaseActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setNavigationBarColor(Color.parseColor("#EFEFEF"));
        getWindow().setStatusBarColor(-1);
        getWindow().getDecorView().setSystemUiVisibility(Build.VERSION.SDK_INT >= 26 ? 8208 : 8192);
    }
}

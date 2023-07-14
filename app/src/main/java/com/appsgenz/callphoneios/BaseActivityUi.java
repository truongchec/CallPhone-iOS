package com.appsgenz.callphoneios;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes.dex */
public class BaseActivityUi extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(Build.VERSION.SDK_INT >= 26 ? 8208 : 8192);
        window.setNavigationBarColor(-1);
        window.setStatusBarColor(-1);
    }
}

package com.appsgenz.callphoneios;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;

import com.appsgenz.callphoneios.utils.MyShare;

public class ActivityApplyTheme extends AppCompatActivity {
    private final Runnable rEnd = new Runnable() { // from class: com.appsgenz.callphoneios.ActivityApplyTheme$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            ActivityApplyTheme.this.m42xc71b07b0();
        }
    };

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(1280);
        window.addFlags(512);
        window.setNavigationBarColor(0);
        window.setStatusBarColor(0);
        setContentView(R.layout.activity_apply_theme);
        boolean theme = MyShare.getTheme(this);
        View findViewById = findViewById(R.id.v_while);
        if (theme) {
            findViewById.setAlpha(0.0f);
            findViewById.animate().setDuration(2500L).alpha(1.0f).withEndAction(this.rEnd).start();
            return;
        }
        findViewById.setAlpha(1.0f);
        findViewById.animate().setDuration(2500L).alpha(0.0f).withEndAction(this.rEnd).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-ActivityApplyTheme  reason: not valid java name */
    public /* synthetic */ void m42xc71b07b0() {
        new Handler().postDelayed(new Runnable() { // from class: com.appsgenz.callphoneios.ActivityApplyTheme$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ActivityApplyTheme.this.startAc();
            }
        }, 1500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAc() {
        startActivity(new Intent(this, ActivityHome.class));
        finish();
    }
}

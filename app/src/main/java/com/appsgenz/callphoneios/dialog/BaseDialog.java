package com.appsgenz.callphoneios.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

/* loaded from: classes.dex */
public class BaseDialog extends Dialog {
    public BaseDialog(Context context) {
        super(context);
        requestWindowFeature(1);
        getWindow().setBackgroundDrawableResource(17170445);
        theme();
        setCancelable(false);
    }

    private void theme() {
        getWindow().getDecorView().setSystemUiVisibility(1536);
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.appsgenz.callphoneios.dialog.BaseDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                BaseDialog.lambda$theme$0(decorView, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$theme$0(View view, int i) {
        if ((i & 4) == 0) {
            view.setSystemUiVisibility(1536);
        }
    }
}

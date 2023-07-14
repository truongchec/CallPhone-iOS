package com.appsgenz.callphoneios.dialog;

import android.content.Context;
import android.os.Bundle;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.custom.LayoutListSim;
import com.appsgenz.callphoneios.item.ItemSimInfo;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class DialogShowSim extends BaseDialog {
    private final ArrayList<ItemSimInfo> arrSim;
    private final int marTop;
    private final LayoutListSim.SimItemClick simItemClick;

    public DialogShowSim(Context context, ArrayList<ItemSimInfo> arrayList, int i, LayoutListSim.SimItemClick simItemClick) {
        super(context);
        this.arrSim = arrayList;
        this.simItemClick = simItemClick;
        this.marTop = i;
        setCancelable(true);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_choose_sim);
        LayoutListSim layoutListSim = (LayoutListSim) findViewById(R.id.ll_sim);
        layoutListSim.setSimItemClick(new LayoutListSim.SimItemClick() { // from class: com.appsgenz.callphoneios.dialog.DialogShowSim$$ExternalSyntheticLambda0
            @Override // com.appsgenz.callphoneios.custom.LayoutListSim.SimItemClick
            public final void onSimClick(int i) {
                DialogShowSim.this.m106xfdce9568(i);
            }
        });
        layoutListSim.setSimInfo(this.arrSim);
        layoutListSim.showView(this.marTop);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-callos14-callscreen-colorphone-dialog-DialogShowSim  reason: not valid java name */
    public /* synthetic */ void m106xfdce9568(int i) {
        cancel();
        if (i != -1) {
            this.simItemClick.onSimClick(i);
        }
    }
}

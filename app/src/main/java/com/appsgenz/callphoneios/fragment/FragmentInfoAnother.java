package com.appsgenz.callphoneios.fragment;

import android.app.role.RoleManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telecom.PhoneAccountHandle;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import com.appsgenz.callphoneios.ActivityHome;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.dialog.DialogNotification;
import com.appsgenz.callphoneios.dialog.DialogResult;
import com.appsgenz.callphoneios.fragment.FragmentInfoAnother;
import com.appsgenz.callphoneios.item.ItemContact;
import com.appsgenz.callphoneios.item.ItemRecentGroup;
import com.appsgenz.callphoneios.item.ItemSimInfo;
import com.appsgenz.callphoneios.utils.ActionUtils;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;
import com.appsgenz.callphoneios.utils.ReadContact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class FragmentInfoAnother extends Fragment {
    private ContactResult contactResult;
    private ItemRecentGroup itemRecentGroup;
    private ViewInfoAnother viewInfoAnother;
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.appsgenz.callphoneios.fragment.FragmentInfoAnother$$ExternalSyntheticLambda0
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            FragmentInfoAnother.this.m136x282fd38e((ActivityResult) obj);
        }
    });
    private final ActivityResultLauncher<Intent> lPer = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.appsgenz.callphoneios.fragment.FragmentInfoAnother$$ExternalSyntheticLambda1
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            FragmentInfoAnother.this.m137x75ef4b8f((ActivityResult) obj);
        }
    });

    public void setContactResult(ContactResult contactResult) {
        this.contactResult = contactResult;
    }

    public static FragmentInfoAnother newInstance(ItemRecentGroup itemRecentGroup) {
        FragmentInfoAnother fragmentInfoAnother = new FragmentInfoAnother();
        Bundle bundle = new Bundle();
        bundle.putString("dataG", new Gson().toJson(itemRecentGroup));
        fragmentInfoAnother.setArguments(bundle);
        return fragmentInfoAnother;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        String string;
        super.onCreate(bundle);
        if (getArguments() == null || (string = getArguments().getString("dataG")) == null || string.isEmpty()) {
            return;
        }
        this.itemRecentGroup = (ItemRecentGroup) new Gson().fromJson(string, new TypeToken<ItemRecentGroup>() { // from class: com.appsgenz.callphoneios.fragment.FragmentInfoAnother.1
        }.getType());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewInfoAnother viewInfoAnother = new ViewInfoAnother(layoutInflater.getContext());
        this.viewInfoAnother = viewInfoAnother;
        return viewInfoAnother;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBack() {
        try {
            ContactResult contactResult = this.contactResult;
            if (contactResult != null) {
                contactResult.onBack();
            }
        } catch (IllegalStateException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-fragment-FragmentInfoAnother  reason: not valid java name */
    public /* synthetic */ void m136x282fd38e(ActivityResult activityResult) {
        if (getContext() == null || activityResult.getResultCode() != -1 || activityResult.getData() == null || activityResult.getData().getData() == null) {
            return;
        }
        Cursor query = getContext().getContentResolver().query(activityResult.getData().getData(), null, null, null, null);
        if (query != null && query.moveToFirst()) {
            ItemContact contact = ReadContact.getContact(getContext(), query.getString(query.getColumnIndex("_id")));
            if (contact != null) {
                if (getActivity() instanceof ActivityHome) {
                    ((ActivityHome) getActivity()).addNewContact(contact);
                }
                ContactResult contactResult = this.contactResult;
                if (contactResult != null) {
                    contactResult.onAddNewContact(this.itemRecentGroup, contact);
                }
            }
        }
        if (query != null) {
            query.close();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.appsgenz.callphoneios.fragment.FragmentInfoAnother$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FragmentInfoAnother.this.onBack();
            }
        }, 300L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-fragment-FragmentInfoAnother  reason: not valid java name */
    public /* synthetic */ void m137x75ef4b8f(ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            this.viewInfoAnother.showDialogBlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewInfoAnother extends RelativeLayout {
        private ArrayList<ItemContact> arrBlock;
        private ArrayList<ItemSimInfo> arrSim;
        private  int posSim;
        private  boolean theme;
        private  TextW tvBlock;

        /* JADX WARN: Removed duplicated region for block: B:29:0x02d9 A[LOOP:1: B:27:0x02d3->B:29:0x02d9, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x039c  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x03e8  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public ViewInfoAnother(Context context) {
            super(context);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-fragment-FragmentInfoAnother$ViewInfoAnother  reason: not valid java name */
        public /* synthetic */ void m138x9f01e15a(View view) {
            FragmentInfoAnother.this.onBack();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-fragment-FragmentInfoAnother$ViewInfoAnother  reason: not valid java name */
        public /* synthetic */ void m139x7f7b375b(View view) {
            FragmentInfoAnother.this.onBack();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$2$com-callos14-callscreen-colorphone-fragment-FragmentInfoAnother$ViewInfoAnother  reason: not valid java name */
        public /* synthetic */ boolean m140x5ff48d5c(View view) {
            OtherUtils.copyToClip(getContext(), FragmentInfoAnother.this.itemRecentGroup.arrRecent.get(0).number);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$3$com-callos14-callscreen-colorphone-fragment-FragmentInfoAnother$ViewInfoAnother  reason: not valid java name */
        public /* synthetic */ void m141x406de35d(View view) {
            OtherUtils.sendMessage(getContext(), FragmentInfoAnother.this.itemRecentGroup.arrRecent.get(0).number);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$4$com-callos14-callscreen-colorphone-fragment-FragmentInfoAnother$ViewInfoAnother  reason: not valid java name */
        public /* synthetic */ void m142x20e7395e(View view) {
            onCallClick();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$5$com-callos14-callscreen-colorphone-fragment-FragmentInfoAnother$ViewInfoAnother  reason: not valid java name */
        public /* synthetic */ void m143x1608f5f(View view) {
            ActionUtils.shareText(getContext(), FragmentInfoAnother.this.itemRecentGroup.arrRecent.get(0).number);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$6$com-callos14-callscreen-colorphone-fragment-FragmentInfoAnother$ViewInfoAnother  reason: not valid java name */
        public /* synthetic */ void m144xe1d9e560(View view) {
            ActionUtils.onNewContact(FragmentInfoAnother.this.launcher, FragmentInfoAnother.this.itemRecentGroup.arrRecent.get(0).number);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$7$com-callos14-callscreen-colorphone-fragment-FragmentInfoAnother$ViewInfoAnother  reason: not valid java name */
        public /* synthetic */ void m145xc2533b61(View view) {
            onBlock();
        }

        public void onCallClick() {
            PhoneAccountHandle phoneAccountHandle;
            String str = FragmentInfoAnother.this.itemRecentGroup.arrRecent.get(0).number;
            if (str == null || str.isEmpty() || this.arrSim.size() == 0) {
                return;
            }
            if (this.posSim < this.arrSim.size()) {
                phoneAccountHandle = this.arrSim.get(this.posSim).handle;
            } else {
                phoneAccountHandle = this.arrSim.get(0).handle;
            }
            OtherUtils.call(getContext(), str, phoneAccountHandle);
        }

        private void onBlock() {
            boolean z = false;
            String str = FragmentInfoAnother.this.itemRecentGroup.arrRecent.get(0).number;
            Iterator<ItemContact> it = this.arrBlock.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = true;
                    break;
                }
                ItemContact next = it.next();
                if (str != null && !next.getArrPhone().isEmpty() && next.getArrPhone().get(0).getNumber() != null && PhoneNumberUtils.compare(str, next.getArrPhone().get(0).getNumber())) {
                    this.arrBlock.remove(next);
                    MyShare.putBlockNumber(getContext(), this.arrBlock);
                    updateBlock();
                    break;
                }
            }
            if (z) {
                if (Build.VERSION.SDK_INT >= 29) {
                    RoleManager roleManager = (RoleManager) getContext().getSystemService("role");
                    if (roleManager.isRoleHeld("android.app.role.CALL_SCREENING")) {
                        showDialogBlock();
                        return;
                    }
                    FragmentInfoAnother.this.lPer.launch(roleManager.createRequestRoleIntent("android.app.role.CALL_SCREENING"));
                    return;
                }
                showDialogBlock();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showDialogBlock() {
            new DialogNotification(getContext(), this.theme, new DialogResult() { // from class: com.appsgenz.callphoneios.fragment.FragmentInfoAnother$ViewInfoAnother$$ExternalSyntheticLambda8
                @Override // com.appsgenz.callphoneios.dialog.DialogResult
                public final void onActionClick() {
                    ViewInfoAnother.this.m146xbf660a2a();
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$showDialogBlock$8$com-callos14-callscreen-colorphone-fragment-FragmentInfoAnother$ViewInfoAnother  reason: not valid java name */
        public /* synthetic */ void m146xbf660a2a() {
            this.arrBlock.add(new ItemContact(FragmentInfoAnother.this.itemRecentGroup.arrRecent.get(0).number));
            MyShare.putBlockNumber(getContext(), this.arrBlock);
            updateBlock();
        }

        private void updateBlock() {
            boolean z = false;
            String str = FragmentInfoAnother.this.itemRecentGroup.arrRecent.get(0).number;
            Iterator<ItemContact> it = this.arrBlock.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ItemContact next = it.next();
                if (str != null && !next.getArrPhone().isEmpty() && next.getArrPhone().get(0).getNumber() != null && PhoneNumberUtils.compare(str, next.getArrPhone().get(0).getNumber())) {
                    z = true;
                    break;
                }
            }
            if (z) {
                this.tvBlock.setTextColor(Color.parseColor("#007AFF"));
                this.tvBlock.setText(R.string.unblock_this_caller);
                return;
            }
            this.tvBlock.setTextColor(Color.parseColor("#FF2828"));
            this.tvBlock.setText(R.string.block_this_caller);
        }
    }
}

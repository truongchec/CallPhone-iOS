package com.appsgenz.callphoneios.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telecom.PhoneAccountHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.aitsuki.swipe.SwipeMenuRecyclerView;
import com.appsgenz.callphoneios.ActivityHome;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.adapter.AdapterRecent;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.custom.ViewModeRecent;
import com.appsgenz.callphoneios.dialog.DialogAddFav;
import com.appsgenz.callphoneios.dialog.DialogNotification;
import com.appsgenz.callphoneios.dialog.DialogResult;
import com.appsgenz.callphoneios.dialog.FavResult;
import com.appsgenz.callphoneios.fragment.FragmentRecents;
import com.appsgenz.callphoneios.item.ItemContact;
import com.appsgenz.callphoneios.item.ItemFavorites;
import com.appsgenz.callphoneios.item.ItemRecentGroup;
import com.appsgenz.callphoneios.item.ItemSimInfo;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;
import com.appsgenz.callphoneios.utils.ReadContact;
import com.appsgenz.callphoneios.utils.SimUtils;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class FragmentRecents extends BaseFragment {
    private ViewFragmentRecents viewFragmentRecents;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.viewFragmentRecents == null) {
            this.viewFragmentRecents = new ViewFragmentRecents(layoutInflater.getContext());
        }
        return this.viewFragmentRecents;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ViewFragmentRecents viewFragmentRecents = this.viewFragmentRecents;
        if (viewFragmentRecents != null) {
            viewFragmentRecents.loadAllRecent();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewFragmentRecents extends RelativeLayout implements AdapterRecent.RecentItemClick {
        private final AdapterRecent adapterRecent;
        private final ArrayList<ItemRecentGroup> arrRecent;
        private final ArrayList<ItemSimInfo> arrSim;
        private final int posSim;
        private final boolean theme;
        private final TextW tvEdit;
        private final TextW tvEmpty;
        private final TextW tvRemoveAll;

        public ViewFragmentRecents(Context context) {
            super(context);
            ArrayList<ItemSimInfo> availableSIMCardLabels = SimUtils.getAvailableSIMCardLabels(context);
            this.arrSim = availableSIMCardLabels;
            this.posSim = MyShare.getPosSim(context);
            int widthScreen = OtherUtils.getWidthScreen(context) / 25;
            boolean theme = MyShare.getTheme(context);
            this.theme = theme;
            ArrayList<ItemRecentGroup> arrayList = new ArrayList<>();
            this.arrRecent = arrayList;
            AdapterRecent adapterRecent = new AdapterRecent(arrayList, availableSIMCardLabels, theme, this);
            this.adapterRecent = adapterRecent;
            TextW textW = new TextW(context);
            this.tvEdit = textW;
            textW.setId(100);
            textW.setTextColor(Color.parseColor("#007AFF"));
            textW.setPadding(widthScreen, widthScreen, widthScreen, widthScreen);
            textW.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.fragment.FragmentRecents$ViewFragmentRecents$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewFragmentRecents.this.m149x69848d70(view);
                }
            });
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(21);
            layoutParams.setMargins(0, MyShare.getSizeNotification(context), 0, 0);
            addView(textW, layoutParams);
            TextW textW2 = new TextW(context);
            this.tvRemoveAll = textW2;
            textW2.setId(654982);
            textW2.setTextColor(Color.parseColor("#007AFF"));
            textW2.setupText(400, 4.0f);
            textW2.setPadding(widthScreen, widthScreen, widthScreen, widthScreen);
            textW2.setText(R.string.delete);
            textW2.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.fragment.FragmentRecents$ViewFragmentRecents$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewFragmentRecents.this.m150x49fde371(view);
                }
            });
            LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            layoutParams2.setMargins(0, MyShare.getSizeNotification(context), 0, 0);
            addView(textW2, layoutParams2);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            LayoutParams layoutParams3 = new LayoutParams(-1, -1);
            layoutParams3.addRule(6, textW.getId());
            layoutParams3.addRule(8, textW.getId());
            addView(linearLayout, layoutParams3);
            ViewModeRecent viewModeRecent = new ViewModeRecent(context);
            viewModeRecent.setModeResult(new ViewModeRecent.ModeResult() { // from class: com.appsgenz.callphoneios.fragment.FragmentRecents$ViewFragmentRecents$$ExternalSyntheticLambda3
                @Override // com.appsgenz.callphoneios.custom.ViewModeRecent.ModeResult
                public final void onMode(boolean z) {
                    ViewFragmentRecents.this.m151x2a773972(z);
                }
            });
            linearLayout.addView(viewModeRecent, -2, -2);
            TextW textW3 = new TextW(context);
            this.tvEmpty = textW3;
            textW3.setupText(400, 3.8f);
            textW3.setText(R.string.empty_recent);
            textW3.setGravity(17);
            textW3.setTextColor(Color.parseColor("#aaaaaa"));
            textW3.setVisibility(8);
            LayoutParams layoutParams4 = new LayoutParams(-1, -1);
            layoutParams4.setMargins(widthScreen, 0, widthScreen, 0);
            layoutParams4.addRule(3, textW.getId());
            addView(textW3, layoutParams4);
            SwipeMenuRecyclerView swipeMenuRecyclerView = new SwipeMenuRecyclerView(context);
            swipeMenuRecyclerView.setAdapter(adapterRecent);
            swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
            LayoutParams layoutParams5 = new LayoutParams(-1, -1);
            layoutParams5.addRule(3, textW.getId());
            addView(swipeMenuRecyclerView, layoutParams5);
            if (theme) {
                setBackgroundColor(-1);
            } else {
                setBackgroundColor(Color.parseColor("#2C2C2C"));
            }
            updateEdit();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-fragment-FragmentRecents$ViewFragmentRecents  reason: not valid java name */
        public /* synthetic */ void m149x69848d70(View view) {
            AdapterRecent adapterRecent = this.adapterRecent;
            adapterRecent.setChoose(!adapterRecent.isChoose());
            updateEdit();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-fragment-FragmentRecents$ViewFragmentRecents  reason: not valid java name */
        public /* synthetic */ void m150x49fde371(View view) {
            onRemoveAll();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$2$com-callos14-callscreen-colorphone-fragment-FragmentRecents$ViewFragmentRecents  reason: not valid java name */
        public /* synthetic */ void m151x2a773972(boolean z) {
            this.adapterRecent.showMiss(!z);
        }

        private void updateEdit() {
            if (this.adapterRecent.isChoose()) {
                this.tvEdit.setText(R.string.done);
                this.tvRemoveAll.setVisibility(0);
                this.tvEdit.setupText(600, 4.0f);
                return;
            }
            this.tvEdit.setText(R.string.edit);
            this.tvRemoveAll.setVisibility(8);
            this.tvEdit.setupText(400, 4.0f);
        }

        private void checkList() {
            if (this.arrRecent.isEmpty()) {
                this.tvEmpty.setVisibility(0);
                this.tvEdit.setVisibility(4);
                this.tvRemoveAll.setVisibility(8);
                if (this.adapterRecent.isChoose()) {
                    this.adapterRecent.setChoose(false);
                    updateEdit();
                    return;
                }
                return;
            }
            this.tvEmpty.setVisibility(8);
            this.tvEdit.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void loadAllRecent() {
            final Handler handler = new Handler(new Handler.Callback() { // from class: com.appsgenz.callphoneios.fragment.FragmentRecents$ViewFragmentRecents$$ExternalSyntheticLambda0
                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    return ViewFragmentRecents.this.m147x64a531a9(message);
                }
            });
            new Thread(new Runnable() { // from class: com.appsgenz.callphoneios.fragment.FragmentRecents$ViewFragmentRecents$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    ViewFragmentRecents.this.m148x451e87aa(handler);
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$loadAllRecent$3$com-callos14-callscreen-colorphone-fragment-FragmentRecents$ViewFragmentRecents  reason: not valid java name */
        public /* synthetic */ boolean m147x64a531a9(Message message) {
            checkList();
            this.adapterRecent.addNewData();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$loadAllRecent$4$com-callos14-callscreen-colorphone-fragment-FragmentRecents$ViewFragmentRecents  reason: not valid java name */
        public /* synthetic */ void m148x451e87aa(Handler handler) {
            this.arrRecent.clear();
            this.arrRecent.addAll(ReadContact.getAllRecents(getContext()));
            handler.sendEmptyMessage(1);
        }

        @Override // com.appsgenz.callphoneios.adapter.AdapterRecent.RecentItemClick
        public void onLongClick(final ItemRecentGroup itemRecentGroup) {
            ItemContact contactWithNumber = ReadContact.getContactWithNumber(getContext(), itemRecentGroup.arrRecent.get(0).number);
            if (contactWithNumber == null) {
                contactWithNumber = new ItemContact(itemRecentGroup.arrRecent.get(0).number);
            }
            new DialogAddFav(getContext(), this.theme, false, contactWithNumber, new FavResult() { // from class: com.appsgenz.callphoneios.fragment.FragmentRecents$ViewFragmentRecents$$ExternalSyntheticLambda5
                @Override // com.appsgenz.callphoneios.dialog.FavResult
                public final void onFavResult(ItemFavorites itemFavorites) {
                    ViewFragmentRecents.this.m152xf95ee2(itemRecentGroup, itemFavorites);
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onLongClick$5$com-callos14-callscreen-colorphone-fragment-FragmentRecents$ViewFragmentRecents  reason: not valid java name */
        public /* synthetic */ void m152xf95ee2(ItemRecentGroup itemRecentGroup, ItemFavorites itemFavorites) {
            if (itemFavorites.type == 0) {
                OtherUtils.sendMessage(getContext(), itemFavorites.number);
            } else {
                onItemClick(itemRecentGroup);
            }
        }

        @Override // com.appsgenz.callphoneios.adapter.AdapterRecent.RecentItemClick
        public void onItemClick(ItemRecentGroup itemRecentGroup) {
            PhoneAccountHandle phoneAccountHandle;
            if (this.arrSim.size() > 0) {
                if (this.posSim < this.arrSim.size()) {
                    phoneAccountHandle = this.arrSim.get(this.posSim).handle;
                } else {
                    phoneAccountHandle = this.arrSim.get(0).handle;
                }
                OtherUtils.call(getContext(), itemRecentGroup.arrRecent.get(0).number, phoneAccountHandle);
            }
        }

        @Override // com.appsgenz.callphoneios.adapter.AdapterRecent.RecentItemClick
        public void onDel(ItemRecentGroup itemRecentGroup) {
            if (!OtherUtils.checkPer(getContext(), "android.permission.WRITE_CALL_LOG")) {
                FragmentRecents.this.requestPermissions(new String[]{"android.permission.WRITE_CALL_LOG"}, 1);
                return;
            }
            String[] strArr = new String[itemRecentGroup.arrRecent.size()];
            for (int i = 0; i < itemRecentGroup.arrRecent.size(); i++) {
                strArr[i] = itemRecentGroup.arrRecent.get(i).id;
            }
            ReadContact.removeRecents(getContext(), strArr);
            this.adapterRecent.removeRecent(itemRecentGroup);
            checkList();
        }

        @Override // com.appsgenz.callphoneios.adapter.AdapterRecent.RecentItemClick
        public void onInfo(ItemRecentGroup itemRecentGroup) {
            if (FragmentRecents.this.getActivity() instanceof ActivityHome) {
                ItemContact contactWithNumber = ReadContact.getContactWithNumber(getContext(), itemRecentGroup.arrRecent.get(0).number);
                if (contactWithNumber != null) {
                    FragmentInfo newInstance = FragmentInfo.newInstance(contactWithNumber, itemRecentGroup, R.string.recents);
                    newInstance.setContactResult(FragmentRecents.this.contactResult);
                    ((ActivityHome) FragmentRecents.this.getActivity()).showFragment(newInstance, true);
                    return;
                }
                FragmentInfoAnother newInstance2 = FragmentInfoAnother.newInstance(itemRecentGroup);
                newInstance2.setContactResult(FragmentRecents.this.contactResult);
                ((ActivityHome) FragmentRecents.this.getActivity()).showFragment(newInstance2, true);
            }
        }

        private void onRemoveAll() {
            new DialogNotification(getContext(), R.string.delete_all_recents, R.string.delete_up, this.theme, new DialogResult() { // from class: com.appsgenz.callphoneios.fragment.FragmentRecents$ViewFragmentRecents$$ExternalSyntheticLambda4
                @Override // com.appsgenz.callphoneios.dialog.DialogResult
                public final void onActionClick() {
                    ViewFragmentRecents.this.m153x10def8b4();
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onRemoveAll$6$com-callos14-callscreen-colorphone-fragment-FragmentRecents$ViewFragmentRecents  reason: not valid java name */
        public /* synthetic */ void m153x10def8b4() {
            ReadContact.removeAllRecents(getContext());
            this.adapterRecent.removeAll();
            checkList();
            updateEdit();
        }
    }
}

package com.appsgenz.callphoneios.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsgenz.callphoneios.ActivityHome;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.adapter.AdapterContactHome;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.custom.ViewScrollAlphaB;
import com.appsgenz.callphoneios.custom.ViewSearch;
import com.appsgenz.callphoneios.dialog.DialogAddFav;
import com.appsgenz.callphoneios.dialog.FavResult;
import com.appsgenz.callphoneios.fragment.FragmentContact;
import com.appsgenz.callphoneios.item.ItemContact;
import com.appsgenz.callphoneios.item.ItemFavorites;
import com.appsgenz.callphoneios.item.ItemSimInfo;
import com.appsgenz.callphoneios.utils.ActionUtils;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;
import com.appsgenz.callphoneios.utils.ReadContact;
import com.appsgenz.callphoneios.utils.SimUtils;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class FragmentContact extends BaseFragment {
    private ViewContact viewContact;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getArrContact();
        if (this.viewContact == null) {
            this.viewContact = new ViewContact(layoutInflater.getContext());
        }
        return this.viewContact;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (getContext() == null || i2 != -1 || intent == null || intent.getData() == null) {
            return;
        }
        Cursor query = getContext().getContentResolver().query(intent.getData(), null, null, null, null);
        if (query != null && query.moveToFirst()) {
            int columnIndex = query.getColumnIndex("contact_id");
            if (columnIndex < 0) {
                columnIndex = query.getColumnIndex("_id");
            }
            ItemContact contact = ReadContact.getContact(getContext(), query.getString(columnIndex));
            if (contact != null) {
                if (getActivity() instanceof ActivityHome) {
                    ((ActivityHome) getActivity()).addNewContact(contact);
                }
                if (this.contactResult != null) {
                    this.contactResult.onContactChange();
                }
            }
        }
        if (query != null) {
            query.close();
        }
    }

    public void updateList() {
        ViewContact viewContact = this.viewContact;
        if (viewContact != null) {
            viewContact.loadContactDone();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewContact extends RelativeLayout implements AdapterContactHome.ContactResult {
        private final AdapterContactHome adapterContactHome;
        private final boolean theme;
        private final TextW tvEmpty;
        private final ViewScrollAlphaB viewScrollAlphaB;

        public ViewContact(Context context) {
            super(context);
            int widthScreen = OtherUtils.getWidthScreen(context);
            int i = widthScreen / 25;
            boolean theme = MyShare.getTheme(context);
            this.theme = theme;
            ImageView imageView = new ImageView(context);
            imageView.setId(IronSourceConstants.RV_CALLBACK_AVAILABILITY_TRUE);
            imageView.setImageResource(R.drawable.ic_add_contact);
            imageView.setPadding(i, i, i, i);
            imageView.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.fragment.FragmentContact$ViewContact$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewContact.this.m107x8d827b30(view);
                }
            });
            int i2 = (int) (i * 3.2f);
            LayoutParams layoutParams = new LayoutParams(i2, i2);
            layoutParams.addRule(21);
            layoutParams.setMargins(0, MyShare.getSizeNotification(context), 0, 0);
            addView(imageView, layoutParams);
            TextW textW = new TextW(context);
            textW.setupText(600, 4.3f);
            textW.setText(R.string.contacts);
            textW.setGravity(17);
            LayoutParams layoutParams2 = new LayoutParams(-1, -1);
            layoutParams2.addRule(6, imageView.getId());
            layoutParams2.addRule(8, imageView.getId());
            addView(textW, layoutParams2);
            ViewSearch viewSearch = new ViewSearch(context);
            viewSearch.setId(IronSourceConstants.RV_CALLBACK_AVAILABILITY_FALSE);
            viewSearch.setListenerTextChange(new ViewSearch.TextResult() { // from class: com.appsgenz.callphoneios.fragment.FragmentContact.ViewContact.1
                @Override // com.appsgenz.callphoneios.custom.ViewSearch.TextResult
                public void onTextChange(String str) {
                    ViewContact.this.adapterContactHome.filter(str);
                }
            });
            LayoutParams layoutParams3 = new LayoutParams(-1, -2);
            layoutParams3.setMargins(i, 0, i, i);
            layoutParams3.addRule(3, imageView.getId());
            addView(viewSearch, layoutParams3);
            RelativeLayout relativeLayout = new RelativeLayout(context);
            LayoutParams layoutParams4 = new LayoutParams(-1, -1);
            layoutParams4.addRule(3, viewSearch.getId());
            addView(relativeLayout, layoutParams4);
            TextW textW2 = new TextW(context);
            this.tvEmpty = textW2;
            textW2.setupText(400, 3.8f);
            textW2.setText(R.string.empty_contact);
            textW2.setVisibility(8);
            textW2.setGravity(1);
            textW2.setTextColor(Color.parseColor("#aaaaaa"));
            LayoutParams layoutParams5 = new LayoutParams(-2, -2);
            layoutParams5.setMargins(i, 0, i, 0);
            layoutParams5.addRule(13);
            relativeLayout.addView(textW2, layoutParams5);
            final RecyclerView recyclerView = new RecyclerView(context);
            AdapterContactHome adapterContactHome = new AdapterContactHome(FragmentContact.this.arrAllContact, theme, this);
            this.adapterContactHome = adapterContactHome;
            recyclerView.setAdapter(adapterContactHome);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
            LayoutParams layoutParams6 = new LayoutParams(-1, -1);
            layoutParams6.addRule(3, imageView.getId());
            relativeLayout.addView(recyclerView, layoutParams6);
            ViewScrollAlphaB viewScrollAlphaB = new ViewScrollAlphaB(context);
            this.viewScrollAlphaB = viewScrollAlphaB;
            viewScrollAlphaB.setAlphaBResult(new ViewScrollAlphaB.AlphaBResult() { // from class: com.appsgenz.callphoneios.fragment.FragmentContact$ViewContact$$ExternalSyntheticLambda1
                @Override // com.appsgenz.callphoneios.custom.ViewScrollAlphaB.AlphaBResult
                public final void onAlphaBResult(String str) {
                    ViewContact.this.m108x548e6231(recyclerView, str);
                }
            });
            LayoutParams layoutParams7 = new LayoutParams((widthScreen * 8) / 100, viewScrollAlphaB.makeHeightView());
            layoutParams7.addRule(15);
            layoutParams7.addRule(21);
            relativeLayout.addView(viewScrollAlphaB, layoutParams7);
            if (theme) {
                textW.setTextColor(-16777216);
                setBackgroundColor(-1);
            } else {
                textW.setTextColor(-1);
                setBackgroundColor(Color.parseColor("#2C2C2C"));
            }
            onCheckList();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-fragment-FragmentContact$ViewContact  reason: not valid java name */
        public /* synthetic */ void m107x8d827b30(View view) {
            ActionUtils.onNewContact(FragmentContact.this, "");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-fragment-FragmentContact$ViewContact  reason: not valid java name */
        public /* synthetic */ void m108x548e6231(RecyclerView recyclerView, String str) {
            int locationAlphaB = this.adapterContactHome.getLocationAlphaB(str);
            if (locationAlphaB != -1) {
                recyclerView.scrollToPosition(locationAlphaB);
            }
        }

        private void onCheckList() {
            if (FragmentContact.this.arrAllContact.size() == 0) {
                this.tvEmpty.setVisibility(0);
                this.viewScrollAlphaB.setVisibility(8);
                return;
            }
            this.tvEmpty.setVisibility(8);
            this.viewScrollAlphaB.setVisibility(0);
        }

        void loadContactDone() {
            this.adapterContactHome.filter("");
            onCheckList();
        }

        @Override // com.appsgenz.callphoneios.adapter.AdapterContactHome.ContactResult
        public void onItemClick(ItemContact itemContact) {
            if (FragmentContact.this.getActivity() instanceof ActivityHome) {
                FragmentInfo newInstance = FragmentInfo.newInstance(itemContact, R.string.contacts);
                newInstance.setContactResult(FragmentContact.this.contactResult);
                ((ActivityHome) FragmentContact.this.getActivity()).showFragment(newInstance, true);
            }
        }

        @Override // com.appsgenz.callphoneios.adapter.AdapterContactHome.ContactResult
        public void onLongClick(ItemContact itemContact) {
            new DialogAddFav(getContext(), this.theme, false, itemContact, new FavResult() { // from class: com.appsgenz.callphoneios.fragment.FragmentContact$ViewContact$$ExternalSyntheticLambda2
                @Override // com.appsgenz.callphoneios.dialog.FavResult
                public final void onFavResult(ItemFavorites itemFavorites) {
                    ViewContact.this.m109x406d299f(itemFavorites);
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onLongClick$2$com-callos14-callscreen-colorphone-fragment-FragmentContact$ViewContact  reason: not valid java name */
        public /* synthetic */ void m109x406d299f(ItemFavorites itemFavorites) {
            PhoneAccountHandle phoneAccountHandle;
            if (itemFavorites.type == 0) {
                OtherUtils.sendMessage(getContext(), itemFavorites.number);
                return;
            }
            ArrayList<ItemSimInfo> availableSIMCardLabels = SimUtils.getAvailableSIMCardLabels(getContext());
            if (availableSIMCardLabels.size() == 0) {
                return;
            }
            int posSim = MyShare.getPosSim(getContext());
            if (posSim < availableSIMCardLabels.size()) {
                phoneAccountHandle = availableSIMCardLabels.get(posSim).handle;
            } else {
                phoneAccountHandle = availableSIMCardLabels.get(0).handle;
            }
            OtherUtils.call(getContext(), itemFavorites.number, phoneAccountHandle);
        }
    }
}

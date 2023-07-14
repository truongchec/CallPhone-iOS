package com.appsgenz.callphoneios.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.appsgenz.callphoneios.custom.ViewAlphaB;
import com.appsgenz.callphoneios.custom.ViewListContact;
import com.appsgenz.callphoneios.item.ItemContact;
import com.appsgenz.callphoneios.item.ItemPhone;
import com.appsgenz.callphoneios.item.ItemShowContact;
import com.appsgenz.callphoneios.utils.OtherUtils;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class AdapterContactHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<ItemContact> arrContact;
    private final ArrayList<ItemShowContact> arrFilter = new ArrayList<>();
    private final ContactResult contactResult;
    private final boolean theme;

    /* loaded from: classes.dex */
    public interface ContactResult {
        void onItemClick(ItemContact itemContact);

        void onLongClick(ItemContact itemContact);
    }

    public AdapterContactHome(ArrayList<ItemContact> arrayList, boolean z, ContactResult contactResult) {
        this.arrContact = arrayList;
        this.contactResult = contactResult;
        this.theme = z;
        makeArr("");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.arrFilter.get(i).alphaB != null ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new HolderAlphaB(new ViewAlphaB(viewGroup.getContext()));
        }
        return new HolderContact(new ViewListContact(viewGroup.getContext()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof HolderAlphaB) {
            ((HolderAlphaB) viewHolder).viewAlphaB.setAlphaB(this.arrFilter.get(i).alphaB);
        } else {
            ((HolderContact) viewHolder).vContact.setContact(this.arrFilter.get(i).itemContact, this.theme);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.arrFilter.size();
    }

    public void filter(String str) {
        makeArr(str);
        notifyDataSetChanged();
    }

    private void makeArr(String str) {
        if (str.isEmpty()) {
            addData(this.arrContact);
            return;
        }
        ArrayList<ItemContact> arrayList = new ArrayList<>();
        Iterator<ItemContact> it = this.arrContact.iterator();
        while (it.hasNext()) {
            ItemContact next = it.next();
            if (next.getName() != null && next.getName().toLowerCase().contains(str)) {
                arrayList.add(next);
            } else if (next.getArrPhone() != null && !next.getArrPhone().isEmpty()) {
                Iterator<ItemPhone> it2 = next.getArrPhone().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        ItemPhone next2 = it2.next();
                        if (next2.getNumber() != null && next2.getNumber().toLowerCase().contains(str)) {
                            arrayList.add(next);
                            break;
                        }
                    }
                }
            }
        }
        addData(arrayList);
    }

    private void addData(ArrayList<ItemContact> arrayList) {
        this.arrFilter.clear();
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        String[] arrAlphaB = OtherUtils.arrAlphaB();
        int i = -1;
        Iterator<ItemContact> it = arrayList.iterator();
        while (it.hasNext()) {
            ItemContact next = it.next();
            int posAlphaB = getPosAlphaB(arrAlphaB, next.getName());
            if (i != posAlphaB) {
                this.arrFilter.add(new ItemShowContact(arrAlphaB[posAlphaB]));
                i = posAlphaB;
            }
            this.arrFilter.add(new ItemShowContact(next));
        }
    }

    private int getPosAlphaB(String[] strArr, String str) {
        String substring = (str == null || str.isEmpty()) ? "#" : str.substring(0, 1);
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equalsIgnoreCase(substring)) {
                return i;
            }
        }
        return strArr.length - 1;
    }

    public int getLocationAlphaB(String str) {
        Iterator<ItemShowContact> it = this.arrFilter.iterator();
        while (it.hasNext()) {
            ItemShowContact next = it.next();
            if (next.alphaB != null && next.alphaB.equalsIgnoreCase(str)) {
                return this.arrFilter.indexOf(next);
            }
        }
        return -1;
    }

    /* loaded from: classes.dex */
    class HolderAlphaB extends RecyclerView.ViewHolder {
        ViewAlphaB viewAlphaB;

        public HolderAlphaB(ViewAlphaB viewAlphaB) {
            super(viewAlphaB);
            this.viewAlphaB = viewAlphaB;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class HolderContact extends RecyclerView.ViewHolder {
        ViewListContact vContact;

        public HolderContact(ViewListContact viewListContact) {
            super(viewListContact);
            this.vContact = viewListContact;
            viewListContact.setOnClickListener(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.adapter.AdapterContactHome$HolderContact$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HolderContact.this.m55x8cab3be4(view);
                }
            });
            viewListContact.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.appsgenz.callphoneios.adapter.AdapterContactHome$HolderContact$$ExternalSyntheticLambda1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return HolderContact.this.m56x8d79ba65(view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-adapter-AdapterContactHome$HolderContact  reason: not valid java name */
        public /* synthetic */ void m55x8cab3be4(View view) {
            AdapterContactHome.this.contactResult.onItemClick(((ItemShowContact) AdapterContactHome.this.arrFilter.get(getLayoutPosition())).itemContact);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-adapter-AdapterContactHome$HolderContact  reason: not valid java name */
        public /* synthetic */ boolean m56x8d79ba65(View view) {
            AdapterContactHome.this.contactResult.onLongClick(((ItemShowContact) AdapterContactHome.this.arrFilter.get(getLayoutPosition())).itemContact);
            return true;
        }
    }
}

package com.appsgenz.callphoneios.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.appsgenz.callphoneios.custom.LayoutItemBlock;
import com.appsgenz.callphoneios.item.ItemContact;
import com.appsgenz.callphoneios.item.ItemPhone;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class AdapterBlock extends RecyclerView.Adapter<AdapterBlock.Holder> {
    private final ArrayList<ItemContact> arrBlock;
    private final ArrayList<ItemContact> arrFilter;
    private final OnItemBlockClick onItemBlockClick;
    private final boolean theme;

    /* loaded from: classes.dex */
    public interface OnItemBlockClick {
        void onItemClick(ItemContact itemContact);
    }

    public AdapterBlock(ArrayList<ItemContact> arrayList, boolean z, OnItemBlockClick onItemBlockClick) {
        this.arrBlock = arrayList;
        this.arrFilter = new ArrayList<>(arrayList);
        this.onItemBlockClick = onItemBlockClick;
        this.theme = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Holder(new LayoutItemBlock(viewGroup.getContext()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        holder.v.setContact(this.arrFilter.get(i), this.theme);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.arrFilter.size();
    }

    public void onFilter(String str) {
        this.arrFilter.clear();
        if (str == null || str.isEmpty()) {
            this.arrFilter.addAll(this.arrBlock);
        } else {
            Iterator<ItemContact> it = this.arrBlock.iterator();
            while (it.hasNext()) {
                ItemContact next = it.next();
                if (next.getName() != null && !next.getName().isEmpty() && next.getName().toLowerCase().contains(str)) {
                    this.arrFilter.add(next);
                } else if (next.getArrPhone() != null && !next.getArrPhone().isEmpty()) {
                    Iterator<ItemPhone> it2 = next.getArrPhone().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            ItemPhone next2 = it2.next();
                            if (next2.getNumber() != null && !next2.getNumber().isEmpty() && next2.getNumber().toLowerCase().contains(str)) {
                                this.arrFilter.add(next);
                                break;
                            }
                        }
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Holder extends RecyclerView.ViewHolder {
        LayoutItemBlock v;

        public Holder(LayoutItemBlock layoutItemBlock) {
            super(layoutItemBlock);
            this.v = layoutItemBlock;
            layoutItemBlock.getImDel().setOnClickListener(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.adapter.AdapterBlock$Holder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Holder.this.m53x7170d5ae(view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-adapter-AdapterBlock$Holder  reason: not valid java name */
        public /* synthetic */ void m53x7170d5ae(View view) {
            int layoutPosition = getLayoutPosition();
            AdapterBlock.this.onItemBlockClick.onItemClick((ItemContact) AdapterBlock.this.arrFilter.get(layoutPosition));
            AdapterBlock.this.arrFilter.remove(layoutPosition);
            AdapterBlock.this.notifyItemRemoved(layoutPosition);
        }
    }
}

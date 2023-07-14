package com.appsgenz.callphoneios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.aitsuki.swipe.SwipeLayout;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.custom.FavOnItemClick;
import com.appsgenz.callphoneios.custom.LayoutItemTopRecent;
import com.appsgenz.callphoneios.custom.LayoutRecent;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.item.ItemRecentGroup;
import com.appsgenz.callphoneios.item.ItemSimInfo;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class AdapterRecent extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<ItemRecentGroup> arrGroup;
    private final ArrayList<ItemRecentGroup> arrShow;
    private final ArrayList<ItemSimInfo> arrSim;
    private boolean isChoose;
    private boolean isMiss;
    private final RecentItemClick recentItemClick;
    private final boolean theme;

    /* loaded from: classes.dex */
    public interface RecentItemClick {
        void onDel(ItemRecentGroup itemRecentGroup);

        void onInfo(ItemRecentGroup itemRecentGroup);

        void onItemClick(ItemRecentGroup itemRecentGroup);

        void onLongClick(ItemRecentGroup itemRecentGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    public AdapterRecent(ArrayList<ItemRecentGroup> arrayList, ArrayList<ItemSimInfo> arrayList2, boolean z, RecentItemClick recentItemClick) {
        this.arrGroup = arrayList;
        this.arrShow = new ArrayList<>(arrayList);
        this.recentItemClick = recentItemClick;
        this.theme = z;
        this.arrSim = arrayList2;
    }

    public void addNewData() {
        if (this.isMiss) {
            showMiss(true);
            return;
        }
        this.arrShow.clear();
        this.arrShow.addAll(this.arrGroup);
        notifyDataSetChanged();
    }

    public void setChoose(boolean z) {
        this.isChoose = z;
        notifyItemRangeChanged(0, getItemCount(), true);
    }

    public void showMiss(boolean z) {
        this.isMiss = z;
        this.arrShow.clear();
        if (z) {
            Iterator<ItemRecentGroup> it = this.arrGroup.iterator();
            while (it.hasNext()) {
                ItemRecentGroup next = it.next();
                if (next.arrRecent.get(0).type == 3) {
                    this.arrShow.add(next);
                }
            }
        } else {
            this.arrShow.addAll(this.arrGroup);
        }
        notifyDataSetChanged();
    }

    public void removeRecent(ItemRecentGroup itemRecentGroup) {
        this.arrGroup.remove(itemRecentGroup);
        this.arrShow.remove(itemRecentGroup);
        notifyItemRemoved(this.arrShow.indexOf(itemRecentGroup) + 1);
    }

    public void removeAll() {
        this.arrShow.clear();
        this.arrGroup.clear();
        this.isChoose = false;
        notifyDataSetChanged();
    }

    public boolean isChoose() {
        return this.isChoose;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new HolderTop(new LayoutItemTopRecent(viewGroup.getContext()));
        }
        //return new HolderItem(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recent, viewGroup, false));
        return new HolderTop(new LayoutItemTopRecent(viewGroup.getContext()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof HolderItem) {
            HolderItem holderItem = (HolderItem) viewHolder;
            int i2 = -1;
            ItemRecentGroup itemRecentGroup = this.arrShow.get(i - 1);
            String str = itemRecentGroup.arrRecent.get(0).simId;
            if (this.arrSim.size() > 1 && str != null && !str.isEmpty()) {
                int i3 = 0;
                while (true) {
                    if (i3 >= this.arrSim.size()) {
                        break;
                    } else if (str.equals(this.arrSim.get(i3).handle.getId())) {
                        i2 = i3;
                        break;
                    } else {
                        i3++;
                    }
                }
            }
            holderItem.layoutRecent.setItemRecent(itemRecentGroup, i2, this.isChoose, this.theme);
            if (this.isChoose) {
                holderItem.sw.setSwipeFlags(0);
                holderItem.sw.closeRightMenu(true);
                return;
            }
            holderItem.sw.setSwipeFlags(1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.arrShow.size() + 1;
    }

    /* loaded from: classes.dex */
    class HolderTop extends RecyclerView.ViewHolder {
        public HolderTop(LayoutItemTopRecent layoutItemTopRecent) {
            super(layoutItemTopRecent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class HolderItem extends RecyclerView.ViewHolder {
        LayoutRecent layoutRecent;
        SwipeLayout sw;

        public HolderItem(View view) {
            super(view);
            this.sw = (SwipeLayout) view;
            LayoutRecent layoutRecent = (LayoutRecent) view.findViewById(R.id.content);
            this.layoutRecent = layoutRecent;
            layoutRecent.setFavOnItemClick(new FavOnItemClick() { // from class: com.appsgenz.callphoneios.adapter.AdapterRecent.HolderItem.1
                @Override // com.appsgenz.callphoneios.custom.FavOnItemClick
                public void onLongClick() {
                    if (AdapterRecent.this.isChoose) {
                        return;
                    }
                    AdapterRecent.this.recentItemClick.onLongClick((ItemRecentGroup) AdapterRecent.this.arrShow.get(HolderItem.this.getLayoutPosition() - 1));
                }

                @Override // com.appsgenz.callphoneios.custom.FavOnItemClick
                public void onItemClick() {
                    if (AdapterRecent.this.isChoose) {
                        return;
                    }
                    AdapterRecent.this.recentItemClick.onItemClick((ItemRecentGroup) AdapterRecent.this.arrShow.get(HolderItem.this.getLayoutPosition() - 1));
                }

                @Override // com.appsgenz.callphoneios.custom.FavOnItemClick
                public void onDel() {
                    AdapterRecent.this.recentItemClick.onDel((ItemRecentGroup) AdapterRecent.this.arrShow.get(HolderItem.this.getLayoutPosition() - 1));
                }

                @Override // com.appsgenz.callphoneios.custom.FavOnItemClick
                public void onInfo() {
                    AdapterRecent.this.recentItemClick.onInfo((ItemRecentGroup) AdapterRecent.this.arrShow.get(HolderItem.this.getLayoutPosition() - 1));
                }
            });
            TextW textW = (TextW) view.findViewById(R.id.right_menu);
            textW.setupText(400, 4.0f);
            textW.setTextColor(-1);
            textW.setOnClickListener(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.adapter.AdapterRecent$HolderItem$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HolderItem.this.m59xc5a92fdd(view2);
                }
            });
            this.layoutRecent.setOnClickListener(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.adapter.AdapterRecent$HolderItem$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HolderItem.this.m60xb6fabf5e(view2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-adapter-AdapterRecent$HolderItem  reason: not valid java name */
        public /* synthetic */ void m59xc5a92fdd(View view) {
            AdapterRecent.this.recentItemClick.onDel((ItemRecentGroup) AdapterRecent.this.arrShow.get(getLayoutPosition() - 1));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-adapter-AdapterRecent$HolderItem  reason: not valid java name */
        public /* synthetic */ void m60xb6fabf5e(View view) {
            if (this.sw.isRightMenuOpened()) {
                return;
            }
            AdapterRecent.this.recentItemClick.onItemClick((ItemRecentGroup) AdapterRecent.this.arrShow.get(getLayoutPosition() - 1));
        }
    }
}

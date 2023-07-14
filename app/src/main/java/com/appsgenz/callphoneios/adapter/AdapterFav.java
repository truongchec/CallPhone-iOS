package com.appsgenz.callphoneios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.aitsuki.swipe.SwipeLayout;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.custom.FavOnItemClick;
import com.appsgenz.callphoneios.custom.LayoutItemFav;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.item.ItemFavorites;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class AdapterFav extends RecyclerView.Adapter<AdapterFav.Holder> {
    private final ArrayList<ItemFavorites> arrFav;
    private final FavItemClick favItemClick;
    private boolean isChoose = false;
    private final boolean theme;

    /* loaded from: classes.dex */
    public interface FavItemClick {
        void onDel(ItemFavorites itemFavorites);

        void onInfo(ItemFavorites itemFavorites);

        void onItemClick(ItemFavorites itemFavorites);

        void onLongClick(ItemFavorites itemFavorites);
    }

    public AdapterFav(ArrayList<ItemFavorites> arrayList, boolean z, FavItemClick favItemClick) {
        this.arrFav = arrayList;
        this.favItemClick = favItemClick;
        this.theme = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fav, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int i) {
        holder.layoutItemFav.setFav(this.arrFav.get(i), this.isChoose, this.theme);
        if (this.isChoose) {
            holder.sw.setSwipeFlags(0);
            holder.sw.closeRightMenu(true);
            return;
        }
        holder.sw.setSwipeFlags(1);
    }

    public void setChoose(boolean z) {
        this.isChoose = z;
        notifyItemRangeChanged(0, getItemCount(), true);
    }

    public boolean isChoose() {
        return this.isChoose;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.arrFav.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Holder extends RecyclerView.ViewHolder {
        LayoutItemFav layoutItemFav;
        SwipeLayout sw;

        public Holder(View view) {
            super(view);
            this.sw = (SwipeLayout) view;
            LayoutItemFav layoutItemFav = (LayoutItemFav) view.findViewById(R.id.content);
            this.layoutItemFav = layoutItemFav;
            layoutItemFav.setFavOnItemClick(new FavOnItemClick() { // from class: com.appsgenz.callphoneios.adapter.AdapterFav.Holder.1
                @Override // com.appsgenz.callphoneios.custom.FavOnItemClick
                public void onLongClick() {
                    if (AdapterFav.this.isChoose) {
                        return;
                    }
                    AdapterFav.this.favItemClick.onLongClick((ItemFavorites) AdapterFav.this.arrFav.get(Holder.this.getLayoutPosition()));
                }

                @Override // com.appsgenz.callphoneios.custom.FavOnItemClick
                public void onItemClick() {
                    if (AdapterFav.this.isChoose) {
                        return;
                    }
                    AdapterFav.this.favItemClick.onItemClick((ItemFavorites) AdapterFav.this.arrFav.get(Holder.this.getLayoutPosition()));
                }

                @Override // com.appsgenz.callphoneios.custom.FavOnItemClick
                public void onDel() {
                    AdapterFav.this.favItemClick.onDel((ItemFavorites) AdapterFav.this.arrFav.get(Holder.this.getLayoutPosition()));
                }

                @Override // com.appsgenz.callphoneios.custom.FavOnItemClick
                public void onInfo() {
                    AdapterFav.this.favItemClick.onInfo((ItemFavorites) AdapterFav.this.arrFav.get(Holder.this.getLayoutPosition()));
                }
            });
            TextW textW = (TextW) view.findViewById(R.id.right_menu);
            textW.setupText(400, 4.0f);
            textW.setTextColor(-1);
            textW.setOnClickListener(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.adapter.AdapterFav$Holder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    Holder.this.m57x528d3220(view2);
                }
            });
            this.layoutItemFav.setOnClickListener(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.adapter.AdapterFav$Holder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    Holder.this.m58xe6cba1bf(view2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-adapter-AdapterFav$Holder  reason: not valid java name */
        public /* synthetic */ void m57x528d3220(View view) {
            AdapterFav.this.favItemClick.onDel((ItemFavorites) AdapterFav.this.arrFav.get(getLayoutPosition()));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-adapter-AdapterFav$Holder  reason: not valid java name */
        public /* synthetic */ void m58xe6cba1bf(View view) {
            if (this.sw.isRightMenuOpened()) {
                return;
            }
            AdapterFav.this.favItemClick.onItemClick((ItemFavorites) AdapterFav.this.arrFav.get(getLayoutPosition()));
        }
    }
}

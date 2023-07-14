package com.appsgenz.callphoneios.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.appsgenz.callphoneios.custom.ViewItemTheme;
import com.appsgenz.callphoneios.item.ItemTheme;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class AdapterTheme extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<ItemTheme> arrTheme;
    private final OnThemeItemClick onThemeItemClick;
    private String path;

    /* loaded from: classes.dex */
    public interface OnThemeItemClick {
        void onDownloadClick(ItemTheme itemTheme);

        void onItemClick(ItemTheme itemTheme);
    }

    public AdapterTheme(ArrayList<ItemTheme> arrayList, String str, OnThemeItemClick onThemeItemClick) {
        this.arrTheme = arrayList;
        this.onThemeItemClick = onThemeItemClick;
        this.path = str;
    }

    public void updatePath(String str) {
        if (this.path.equals(str)) {
            return;
        }
        this.path = str;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.arrTheme.get(i) == null ? 0 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            //return new HolderAds(new ViewNativeAds(viewGroup.getContext()));
            return new Holder(new ViewItemTheme(viewGroup.getContext()));
        }
        return new Holder(new ViewItemTheme(viewGroup.getContext()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof Holder) {
            ((Holder) viewHolder).v.setItemTheme(this.arrTheme.get(i), this.path);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.arrTheme.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Holder extends RecyclerView.ViewHolder {
        ViewItemTheme v;

        public Holder(ViewItemTheme viewItemTheme) {
            super(viewItemTheme);
            this.v = viewItemTheme;
            viewItemTheme.setDownloadClick(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Holder.this.m63x78e288f2(view);
                }
            });
            viewItemTheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Holder.this.m64xf7438cd1(view);
                }
            });
        }

        public /* synthetic */ void m63x78e288f2(View view) {
            AdapterTheme.this.onThemeItemClick.onDownloadClick((ItemTheme) AdapterTheme.this.arrTheme.get(getLayoutPosition()));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$1$com-callos14-callscreen-colorphone-adapter-AdapterTheme$Holder  reason: not valid java name */
        public /* synthetic */ void m64xf7438cd1(View view) {
            AdapterTheme.this.onThemeItemClick.onItemClick((ItemTheme) AdapterTheme.this.arrTheme.get(getLayoutPosition()));
        }
    }

    /* loaded from: classes.dex */
//    class HolderAds extends RecyclerView.ViewHolder {
//        public HolderAds(ViewNativeAds viewNativeAds) {
//            super(viewNativeAds);
//            viewNativeAds.addAds(null, false, R.string.na_big);
//        }
//    }
}

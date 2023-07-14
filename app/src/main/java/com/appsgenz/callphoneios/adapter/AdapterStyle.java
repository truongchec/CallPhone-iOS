package com.appsgenz.callphoneios.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.custom.ViewItemStyle;
import com.appsgenz.callphoneios.utils.MyShare;

/* loaded from: classes.dex */
public class AdapterStyle extends RecyclerView.Adapter<AdapterStyle.Holder> {
    private boolean isPro;
    private final StyleItemClick onItemClick;
    private int posApply;

    /* loaded from: classes.dex */
    public interface StyleItemClick {
        void goPremium();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 6;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (i == 0 || i == 1) ? 2 : 1;
    }

    public AdapterStyle(int i, boolean z, StyleItemClick styleItemClick) {
        this.onItemClick = styleItemClick;
        this.posApply = i;
        this.isPro = z;
    }

    public void onResume(boolean z) {
        if (this.isPro != z) {
            this.isPro = z;
            notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 2) {
            return new HolderTop(new ViewItemStyle(viewGroup.getContext()));
        }
        return new Holder(new ViewItemStyle(viewGroup.getContext()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Holder holder, int i) {
        holder.v.setItemTheme(i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? R.drawable.im_style_5 : R.drawable.im_style_4 : R.drawable.im_style_3 : R.drawable.im_style_2 : R.drawable.im_style_1 : R.drawable.im_style_0, i == this.posApply, this.isPro);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Holder extends RecyclerView.ViewHolder {
        ViewItemStyle v;

        public Holder(final ViewItemStyle viewItemStyle) {
            super(viewItemStyle);
            this.v = viewItemStyle;
            viewItemStyle.onApplyClick(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.adapter.AdapterStyle$Holder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Holder.this.m62xed61ac0a(viewItemStyle, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$com-callos14-callscreen-colorphone-adapter-AdapterStyle$Holder  reason: not valid java name */
        public /* synthetic */ void m62xed61ac0a(ViewItemStyle viewItemStyle, View view) {
            int layoutPosition = getLayoutPosition();
            if (AdapterStyle.this.isPro) {
                if (layoutPosition == AdapterStyle.this.posApply) {
                    return;
                }
                int i = AdapterStyle.this.posApply;
                AdapterStyle.this.posApply = layoutPosition;
                AdapterStyle.this.notifyItemChanged(i);
                AdapterStyle.this.notifyItemChanged(layoutPosition);
                MyShare.putStyle(viewItemStyle.getContext(), layoutPosition);
            } else if (layoutPosition != AdapterStyle.this.posApply) {
                AdapterStyle.this.onItemClick.goPremium();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class HolderTop extends Holder {
        public HolderTop(ViewItemStyle viewItemStyle) {
            super(viewItemStyle);
            viewItemStyle.onTop();
        }
    }
}

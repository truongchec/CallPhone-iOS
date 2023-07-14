package com.appsgenz.callphoneios.dialog;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;

import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.custom.ViewItemNumber;
import com.appsgenz.callphoneios.item.ItemContact;
import com.appsgenz.callphoneios.item.ItemFavorites;
import com.appsgenz.callphoneios.item.ItemPhone;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class DialogAddFav extends BaseDialog implements ViewItemNumber.OnItemNumberClick {
    private final FavResult favResult;
    private boolean isOpen;
    private final ItemContact itemContact;
    private final ItemFavorites itemFavorites;
    private LinearLayout llNumber;
    private final boolean showContent;
    private final boolean theme;
    private TextW tvTitle;
    private ViewItemDialog vCall;
    private View vD1;
    private View vD2;
    private ViewItemDialog vMessage;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onCreate$1(View view) {
    }

    @Override // com.appsgenz.callphoneios.custom.ViewItemNumber.OnItemNumberClick
    public void onLongClickNumber(ItemPhone itemPhone) {
    }

    public DialogAddFav(Context context, boolean z, ItemContact itemContact, FavResult favResult) {
        super(context);
        this.itemContact = itemContact;
        this.favResult = favResult;
        this.theme = z;
        setCancelable(true);
        ItemFavorites itemFavorites = new ItemFavorites();
        this.itemFavorites = itemFavorites;
        itemFavorites.id = itemContact.getId();
        this.showContent = true;
    }

    public DialogAddFav(Context context, boolean z, boolean z2, ItemContact itemContact, FavResult favResult) {
        super(context);
        this.itemContact = itemContact;
        this.favResult = favResult;
        this.theme = z;
        setCancelable(true);
        ItemFavorites itemFavorites = new ItemFavorites();
        this.itemFavorites = itemFavorites;
        itemFavorites.id = itemContact.getId();
        this.showContent = z2;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int widthScreen = OtherUtils.getWidthScreen(getContext());
        int i = widthScreen / 25;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(17);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.dialog.DialogAddFav$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogAddFav.this.m98x9ee8102c(view);
            }
        });
        CardView cardView = new CardView(getContext());
        cardView.setRadius(i);
        cardView.setCardElevation(i * 3);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) ((widthScreen * 6.5f) / 10.0f), -2);
        int i2 = (widthScreen * 3) / 10;
        layoutParams.setMargins(i2, i2, i2, i2);
        linearLayout.addView(cardView, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(1);
        linearLayout2.setOnClickListener(DialogAddFav$$ExternalSyntheticLambda3.INSTANCE);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.enableTransitionType(4);
        layoutTransition.enableTransitionType(2);
        layoutTransition.enableTransitionType(0);
        layoutTransition.enableTransitionType(3);
        layoutTransition.enableTransitionType(1);
        layoutTransition.setDuration(350L);
        linearLayout2.setLayoutTransition(layoutTransition);
        cardView.addView(linearLayout2, -1, -2);
        setContentView(linearLayout);
        TextW textW = new TextW(getContext());
        this.tvTitle = textW;
        textW.setupText(400, 3.2f);
        this.tvTitle.setText(R.string.add_fav);
        this.tvTitle.setTextColor(Color.parseColor("#868686"));
        this.tvTitle.setPadding(i * 2, i, i, i);
        linearLayout2.addView(this.tvTitle, -1, -2);
        this.vD1 = makeDivider(linearLayout2);
        if (!this.showContent) {
            this.tvTitle.setVisibility(8);
            this.vD1.setVisibility(8);
        }
        ViewItemDialog viewItemDialog = new ViewItemDialog(getContext());
        this.vMessage = viewItemDialog;
        viewItemDialog.setOnClickListener(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.dialog.DialogAddFav$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogAddFav.this.m99xa154b5ea(view);
            }
        });
        this.vMessage.setData(R.drawable.ic_message_fav, R.string.message_up);
        linearLayout2.addView(this.vMessage, -1, -2);
        this.vD2 = makeDivider(linearLayout2);
        ViewItemDialog viewItemDialog2 = new ViewItemDialog(getContext());
        this.vCall = viewItemDialog2;
        viewItemDialog2.setOnClickListener(new View.OnClickListener() { // from class: com.appsgenz.callphoneios.dialog.DialogAddFav$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogAddFav.this.m100xa28b08c9(view);
            }
        });
        this.vCall.setData(R.drawable.ic_call_fav, R.string.call_up);
        linearLayout2.addView(this.vCall, -1, -2);
        LinearLayout linearLayout3 = new LinearLayout(getContext());
        this.llNumber = linearLayout3;
        linearLayout3.setOrientation(1);
        this.llNumber.setVisibility(8);
        linearLayout2.addView(this.llNumber);
        makeDivider(this.llNumber);
        if (this.itemContact.getArrPhone().size() > 0) {
            for (int i3 = 0; i3 < this.itemContact.getArrPhone().size(); i3++) {
                ViewItemNumber viewItemNumber = new ViewItemNumber(getContext());
                viewItemNumber.setNumberInDialog(this.itemContact.getArrPhone().get(i3), this.theme, this);
                this.llNumber.addView(viewItemNumber, -1, -2);
                if (i3 < this.itemContact.getArrPhone().size() - 1) {
                    makeDivider(this.llNumber);
                }
            }
        }
        if (this.theme) {
            cardView.setCardBackgroundColor(-1);
        } else {
            cardView.setCardBackgroundColor(Color.parseColor("#424141"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-callos14-callscreen-colorphone-dialog-DialogAddFav  reason: not valid java name */
    public /* synthetic */ void m98x9ee8102c(View view) {
        cancel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$2$com-callos14-callscreen-colorphone-dialog-DialogAddFav  reason: not valid java name */
    public /* synthetic */ void m99xa154b5ea(View view) {
        onMessageClick();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$3$com-callos14-callscreen-colorphone-dialog-DialogAddFav  reason: not valid java name */
    public /* synthetic */ void m100xa28b08c9(View view) {
        onCallClick();
    }

    private View makeDivider(LinearLayout linearLayout) {
        View view = new View(getContext());
        if (this.theme) {
            view.setBackgroundColor(Color.parseColor("#b5b5b6"));
        } else {
            view.setBackgroundColor(Color.parseColor("#5c5c5c"));
        }
        linearLayout.addView(view, -1, 1);
        return view;
    }

    private void onMessageClick() {
        if (!this.isOpen) {
            this.itemFavorites.type = 0;
            this.isOpen = true;
            this.vMessage.setStatus(true);
            this.llNumber.setVisibility(0);
            this.vCall.setVisibility(8);
            if (this.showContent) {
                this.tvTitle.setVisibility(8);
                this.vD1.setVisibility(8);
            }
            this.vD2.setVisibility(8);
            return;
        }
        this.isOpen = false;
        this.vMessage.setStatus(false);
        this.vCall.setVisibility(0);
        if (this.showContent) {
            this.tvTitle.setVisibility(0);
            this.vD1.setVisibility(0);
        }
        this.vD2.setVisibility(0);
        this.llNumber.setVisibility(8);
    }

    private void onCallClick() {
        if (!this.isOpen) {
            this.itemFavorites.type = 1;
            this.isOpen = true;
            this.vCall.setStatus(true);
            this.llNumber.setVisibility(0);
            this.vMessage.setVisibility(8);
            if (this.showContent) {
                this.tvTitle.setVisibility(8);
                this.vD1.setVisibility(8);
            }
            this.vD2.setVisibility(8);
            return;
        }
        this.isOpen = false;
        this.vCall.setStatus(false);
        this.vMessage.setVisibility(0);
        if (this.showContent) {
            this.tvTitle.setVisibility(0);
            this.vD1.setVisibility(0);
        }
        this.vD2.setVisibility(0);
        this.llNumber.setVisibility(8);
    }

    @Override // com.appsgenz.callphoneios.custom.ViewItemNumber.OnItemNumberClick
    public void onNumberResult(ItemPhone itemPhone) {
        this.itemFavorites.number = itemPhone.getNumber();
        this.favResult.onFavResult(this.itemFavorites);
        cancel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewItemDialog extends LinearLayout {
        private final ImageView imNext;
        private final ImageView imType;
        private final TextW tv;

        public ViewItemDialog(Context context) {
            super(context);
            setOrientation(0);
            setGravity(16);
            int widthScreen = OtherUtils.getWidthScreen(getContext()) / 25;
            ImageView imageView = new ImageView(context);
            this.imNext = imageView;
            imageView.setImageResource(R.drawable.im_next);
            int i = widthScreen / 2;
            imageView.setPadding(i, widthScreen, i, widthScreen);
            addView(imageView, widthScreen * 2, widthScreen * 3);
            float f = widthScreen;
            imageView.setPivotX(f);
            imageView.setPivotY(1.5f * f);
            TextW textW = new TextW(context);
            this.tv = textW;
            textW.setupText(400, 4.0f);
            addView(textW, new LayoutParams(0, -2, 1.0f));
            ImageView imageView2 = new ImageView(context);
            this.imType = imageView2;
            imageView2.setPadding(widthScreen, 0, widthScreen, 0);
            int i2 = (int) (f * 3.2f);
            addView(imageView2, i2, i2);
            if (DialogAddFav.this.theme) {
                imageView.setColorFilter(-16777216);
                textW.setTextColor(-16777216);
                imageView2.setColorFilter(-16777216);
                return;
            }
            imageView.setColorFilter(-1);
            textW.setTextColor(-1);
            imageView2.setColorFilter(-1);
        }

        public void setData(int i, int i2) {
            this.imType.setImageResource(i);
            this.tv.setText(i2);
        }

        public void setStatus(boolean z) {
            if (z) {
                this.imNext.animate().setDuration(500L).rotation(90.0f).start();
            } else {
                this.imNext.animate().setDuration(500L).rotation(0.0f).start();
            }
        }
    }
}

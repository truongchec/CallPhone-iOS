package com.appsgenz.callphoneios.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsgenz.callphoneios.ActivityHome;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.adapter.AdapterBlock;
import com.appsgenz.callphoneios.custom.EditW;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.item.ItemContact;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class FragmentBlock extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new ViewBlock(layoutInflater.getContext());
    }

    /* loaded from: classes.dex */
    class ViewBlock extends LinearLayout implements AdapterBlock.OnItemBlockClick {
        private final AdapterBlock adapterBlock;
        private final ArrayList<ItemContact> arrBlock;
        private final ArrayList<ItemContact> arrShow;
        private final TextW tvEmpty;

        public ViewBlock(Context context) {
            super(context);
            setOrientation(1);
            int widthScreen = OtherUtils.getWidthScreen(context) / 25;
            boolean theme = MyShare.getTheme(context);
            ArrayList<ItemContact> arrBlock = MyShare.getArrBlock(context);
            this.arrBlock = arrBlock;
            this.arrShow = new ArrayList<>(arrBlock);
            if (FragmentBlock.this.getActivity() instanceof ActivityHome) {
                Iterator<ItemContact> it = ((ActivityHome) FragmentBlock.this.getActivity()).getArrAllContact().iterator();
                while (it.hasNext()) {
                    ItemContact next = it.next();
                    Iterator<ItemContact> it2 = this.arrShow.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            ItemContact next2 = it2.next();
                            if (next2.getId() != null && next2.getId().equals(next.getId())) {
                                next2.setName(next.getName());
                                next2.setPhoto(next.getPhoto());
                                break;
                            }
                        }
                    }
                }
            }
            AdapterBlock adapterBlock = new AdapterBlock(this.arrShow, theme, this);
            this.adapterBlock = adapterBlock;
            TextW textW = new TextW(context);
            textW.setupText(600, 8.0f);
            textW.setText(R.string.block_list);
            int i = widthScreen / 2;
            textW.setPadding(widthScreen, MyShare.getSizeNotification(context), widthScreen, i);
            addView(textW, -1, -2);
            TextW textW2 = new TextW(context);
            textW2.setupText(400, 3.0f);
            textW2.setText(R.string.content_block);
            textW2.setPadding(widthScreen, widthScreen / 4, widthScreen, i);
            textW2.setTextColor(Color.parseColor("#B8B8B8"));
            addView(textW2, -1, -2);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(0);
            linearLayout.setGravity(16);
            int i2 = widthScreen * 2;
            linearLayout.setBackground(OtherUtils.bgIcon(Color.parseColor("#1f767680"), i2 / 3.0f));
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            layoutParams.setMargins(widthScreen, i2 / 3, widthScreen, widthScreen);
            addView(linearLayout, layoutParams);
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.drawable.ic_search);
            LayoutParams layoutParams2 = new LayoutParams(widthScreen, widthScreen);
            layoutParams2.setMargins(i, i, i, i);
            linearLayout.addView(imageView, layoutParams2);
            EditW editW = new EditW(context);
            editW.setupText(400, 4.0f);
            editW.setBackgroundColor(0);
            editW.setSingleLine();
            editW.setPadding(0, i, i, i);
            editW.setHint(R.string.search);
            editW.setHintTextColor(Color.parseColor("#8A8A8E"));
            editW.addTextChangedListener(new TextWatcher() { // from class: com.appsgenz.callphoneios.fragment.FragmentBlock.ViewBlock.1
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
                    ViewBlock.this.adapterBlock.onFilter(charSequence.toString().toLowerCase());
                }
            });
            linearLayout.addView(editW, -1, -2);
            RelativeLayout relativeLayout = new RelativeLayout(context);
            addView(relativeLayout, -1, -1);
            TextW textW3 = new TextW(context);
            this.tvEmpty = textW3;
            textW3.setupText(500, 7.0f);
            textW3.setTextColor(Color.parseColor("#B8B8B8"));
            textW3.setGravity(17);
            textW3.setText(R.string.empty);
            relativeLayout.addView(textW3, -1, -1);
            checkList();
            RecyclerView recyclerView = new RecyclerView(context);
            recyclerView.setAdapter(adapterBlock);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
            relativeLayout.addView(recyclerView, -1, -1);
            if (theme) {
                textW.setTextColor(-16777216);
                editW.setTextColor(-16777216);
                setBackgroundColor(-1);
                return;
            }
            textW.setTextColor(-1);
            editW.setTextColor(-1);
            setBackgroundColor(Color.parseColor("#2C2C2C"));
        }

        @Override // com.appsgenz.callphoneios.adapter.AdapterBlock.OnItemBlockClick
        public void onItemClick(ItemContact itemContact) {
            int indexOf = this.arrShow.indexOf(itemContact);
            this.arrShow.remove(itemContact);
            this.arrBlock.remove(indexOf);
            MyShare.putBlockNumber(getContext(), this.arrBlock);
            checkList();
        }

        private void checkList() {
            if (this.arrBlock.size() == 0) {
                this.tvEmpty.setVisibility(0);
            } else {
                this.tvEmpty.setVisibility(8);
            }
        }
    }
}

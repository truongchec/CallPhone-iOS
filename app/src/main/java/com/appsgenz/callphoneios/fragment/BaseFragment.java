package com.appsgenz.callphoneios.fragment;

import androidx.fragment.app.Fragment;
import com.appsgenz.callphoneios.ActivityHome;
import com.appsgenz.callphoneios.item.ItemContact;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class BaseFragment extends Fragment {
    ArrayList<ItemContact> arrAllContact;
    ContactResult contactResult;

    public void getArrContact() {
        if (getActivity() instanceof ActivityHome) {
            this.arrAllContact = ((ActivityHome) getActivity()).getArrAllContact();
        } else {
            this.arrAllContact = new ArrayList<>();
        }
    }

    public void setContactResult(ContactResult contactResult) {
        this.contactResult = contactResult;
    }
}

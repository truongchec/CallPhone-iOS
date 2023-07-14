package com.appsgenz.callphoneios.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.adapter.AdapterRecorder;
import com.appsgenz.callphoneios.custom.TextW;
import com.appsgenz.callphoneios.dialog.DialogChooseGallery;
import com.appsgenz.callphoneios.fragment.FragmentRecorder;
import com.appsgenz.callphoneios.item.ItemRecorder;
import com.appsgenz.callphoneios.utils.FileUltils;
import com.appsgenz.callphoneios.utils.MyShare;
import com.appsgenz.callphoneios.utils.OtherUtils;
import com.appsgenz.callphoneios.utils.ReadContact;
import com.appsgenz.callphoneios.utils.SaveUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: classes.dex */
public class FragmentRecorder extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new ViewMainRecorder(layoutInflater.getContext());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewMainRecorder extends RelativeLayout implements AdapterRecorder.OnItemClick {
        private final AdapterRecorder adapterRecorder;
        private final ArrayList<ItemRecorder> arrRecorder;
        private final TextW tvEmpty;
        private final TextW tvStatus;

        @SuppressLint("ResourceType")
        public ViewMainRecorder(Context context) {
            super(context);
            int widthScreen = OtherUtils.getWidthScreen(context) / 25;
            boolean theme = MyShare.getTheme(context);
            int sizeNotification = MyShare.getSizeNotification(context);
            TextW textW = new TextW(getContext());
            textW.setId(6666);
            textW.setupText(600, 8.0f);
            textW.setText(R.string.recorder_list);
            textW.setPadding(widthScreen, sizeNotification, 0, 0);
            addView(textW, -2, -2);
            TextW textW2 = new TextW(context);
            this.tvStatus = textW2;
            textW2.setGravity(16);
            textW2.setTextColor(Color.parseColor("#007AFF"));
            textW2.setupText(400, 4.2f);
            textW2.setPadding(widthScreen, sizeNotification, widthScreen, 0);
            textW2.setOnClickListener(new OnClickListener() { // from class: com.appsgenz.callphoneios.fragment.FragmentRecorder$ViewMainRecorder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    onStatusClick();
                }
            });
            LayoutParams layoutParams = new LayoutParams(-2, -1);
            layoutParams.addRule(21);
            layoutParams.addRule(6, textW.getId());
            layoutParams.addRule(8, textW.getId());
            addView(textW2, layoutParams);
            ArrayList<ItemRecorder> arrayList = new ArrayList<>();
            this.arrRecorder = arrayList;
            loadAllRecorder();
            AdapterRecorder adapterRecorder = new AdapterRecorder(arrayList, theme, this);
            this.adapterRecorder = adapterRecorder;
            RecyclerView recyclerView = new RecyclerView(context);
            recyclerView.setAdapter(adapterRecorder);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
            LayoutParams layoutParams2 = new LayoutParams(-1, -1);
            layoutParams2.addRule(3, textW.getId());
            layoutParams2.setMargins(0, widthScreen, 0, 0);
            addView(recyclerView, layoutParams2);
            TextW textW3 = new TextW(context);
            this.tvEmpty = textW3;
            textW3.setText(R.string.empty);
            textW3.setTextColor(Color.parseColor("#B8B8B8"));
            textW3.setGravity(17);
            textW3.setupText(500, 7.0f);
            textW3.setVisibility(8);
            addView(textW3, -1, -1);
            if (theme) {
                textW.setTextColor(-16777216);
                setBackgroundColor(-1);
                return;
            }
            textW.setTextColor(-1);
            setBackgroundColor(Color.parseColor("#2C2C2C"));
        }


        private void loadAllRecorder() {
            final Handler handler = new Handler(new Handler.Callback() { // from class: com.appsgenz.callphoneios.fragment.FragmentRecorder$ViewMainRecorder$$ExternalSyntheticLambda0
                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    return ViewMainRecorder.this.m154x9cc97855(message);
                }
            });
            new Thread(new Runnable() { // from class: com.appsgenz.callphoneios.fragment.FragmentRecorder$ViewMainRecorder$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    ViewMainRecorder.this.m155xab1b3cd7(handler);
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$loadAllRecorder$1$com-callos14-callscreen-colorphone-fragment-FragmentRecorder$ViewMainRecorder  reason: not valid java name */
        public /* synthetic */ boolean m154x9cc97855(Message message) {
            if (this.arrRecorder.size() == 0) {
                this.tvEmpty.setVisibility(0);
                this.tvStatus.setVisibility(8);
                return true;
            }
            this.adapterRecorder.notifyDataSetChanged();
            statusUpdateUi();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$loadAllRecorder$3$com-callos14-callscreen-colorphone-fragment-FragmentRecorder$ViewMainRecorder  reason: not valid java name */
        public /* synthetic */ void m155xab1b3cd7(Handler handler) {
            File[] listFiles = new File(OtherUtils.getPathSave(getContext())).listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    try {
                        int indexOf = file.getName().indexOf("_");
                        String substring = file.getName().substring(0, indexOf);
                        int parseInt = Integer.parseInt(file.getName().substring(indexOf + 1, indexOf + 2));
                        String[] namePhoto = ReadContact.getNamePhoto(getContext(), substring);
                        this.arrRecorder.add(new ItemRecorder(file.getPath(), namePhoto[0], substring, namePhoto[1], file.length(), file.lastModified(), parseInt));
                    } catch (Exception unused) {
                    }
                }
                Collections.sort(this.arrRecorder, new Comparator<ItemRecorder>() {
                    @Override
                    public int compare(ItemRecorder o1, ItemRecorder o2) {
                        return ((int) o1.getTime()) - ((int) o2.getTime());
                    }
                });
            }
            handler.sendEmptyMessage(1);
        }


        private void onStatusClick() {
            if (this.adapterRecorder.isModeDefault()) {
                this.adapterRecorder.setModeDefault(false);
            } else {
                if (this.adapterRecorder.getArrChoose().size() != 0) {
                    final ArrayList arrayList = new ArrayList();
                    Iterator<Integer> it = this.adapterRecorder.getArrChoose().iterator();
                    while (it.hasNext()) {
                        arrayList.add(this.arrRecorder.get(it.next().intValue()));
                    }
                    this.arrRecorder.removeAll(arrayList);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                File file = new File(((ItemRecorder) it.next()).getData());
                                if (file.exists()) {
                                    file.delete();
                                }
                            }
                        }
                    }).start();
                }
                this.adapterRecorder.setModeDefault(true);
            }
            statusUpdateUi();
        }

        public class AnonymousClass1 implements DialogChooseGallery.GalleryResult {
            final int val$pos;

            AnonymousClass1(int i) {
                this.val$pos = i;
            }

            @Override // com.appsgenz.callphoneios.dialog.DialogChooseGallery.GalleryResult
            public void onImageClick() {
                File file = new File(((ItemRecorder) ViewMainRecorder.this.arrRecorder.get(this.val$pos)).getData());
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Context context = ViewMainRecorder.this.getContext();
                intent.setDataAndType(FileProvider.getUriForFile(context, ViewMainRecorder.this.getContext().getPackageName() + ".provider", file), "audio/*");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                startActivity(intent);
            }

            @Override // com.appsgenz.callphoneios.dialog.DialogChooseGallery.GalleryResult
            public void onVideoClick() {
                final Handler handler = new Handler(new Handler.Callback() { // from class: com.appsgenz.callphoneios.fragment.FragmentRecorder$ViewMainRecorder$1$$ExternalSyntheticLambda0
                    @Override // android.os.Handler.Callback
                    public final boolean handleMessage(Message message) {
                        return AnonymousClass1.this.m157x69b47d4e(message);
                    }
                });
                final int i = this.val$pos;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AnonymousClass1.this.m158x4a2dd34f(i, handler);
                    }
                }).start();
            }
            public /* synthetic */ boolean m157x69b47d4e(Message message) {
                if (message.what == 1) {
                    Toast.makeText(ViewMainRecorder.this.getContext(), (int) R.string.done, 0).show();
                } else {
                    Toast.makeText(ViewMainRecorder.this.getContext(), (int) R.string.error, 0).show();
                }
                return true;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: lambda$onVideoClick$1$com-callos14-callscreen-colorphone-fragment-FragmentRecorder$ViewMainRecorder$1  reason: not valid java name */
            public /* synthetic */ void m158x4a2dd34f(int i, Handler handler) {
                File file = new File(((ItemRecorder) ViewMainRecorder.this.arrRecorder.get(i)).getData());
                try {
                    byte[] bArr = new byte[(int) file.length()];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    fileInputStream.read(bArr);
                    fileInputStream.close();
                    SaveUtils.saveSound(ViewMainRecorder.this.getContext(), bArr, file.getName().substring(0, file.getName().lastIndexOf(FileUltils.HIDDEN_PREFIX)));
                    handler.sendEmptyMessage(1);
                } catch (Exception unused) {
                    handler.sendEmptyMessage(2);
                }
            }
        }

        @Override // com.appsgenz.callphoneios.adapter.AdapterRecorder.OnItemClick
        public void onItemClick(int i) {
            new DialogChooseGallery(getContext(), R.string.play, R.string.export_file, new AnonymousClass1(i)).show();
        }

        private void statusUpdateUi() {
            if (this.arrRecorder.size() == 0) {
                this.tvStatus.setText("");
            } else if (this.adapterRecorder.isModeDefault()) {
                this.tvStatus.setText(R.string.edit);
            } else {
                this.tvStatus.setText(R.string.delete);
            }
        }
    }
}

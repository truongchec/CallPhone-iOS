package com.appsgenz.callphoneios.custom;

import android.media.MediaPlayer;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class VideoLayout$$ExternalSyntheticLambda1 implements MediaPlayer.OnPreparedListener {
    public static final /* synthetic */ VideoLayout$$ExternalSyntheticLambda1 INSTANCE = new VideoLayout$$ExternalSyntheticLambda1();

    private /* synthetic */ VideoLayout$$ExternalSyntheticLambda1() {
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}

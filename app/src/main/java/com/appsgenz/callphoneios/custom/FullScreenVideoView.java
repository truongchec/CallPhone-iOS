package com.appsgenz.callphoneios.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/* loaded from: classes.dex */
public class FullScreenVideoView extends VideoView {
    public FullScreenVideoView(Context context) {
        super(context);
    }

    public FullScreenVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FullScreenVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(i, i2);
    }
}

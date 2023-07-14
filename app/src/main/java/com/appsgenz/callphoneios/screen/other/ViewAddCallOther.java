package com.appsgenz.callphoneios.screen.other;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import com.appsgenz.callphoneios.R;
import com.appsgenz.callphoneios.screen.ActionAcceptResult;
import com.appsgenz.callphoneios.utils.OtherUtils;

/* loaded from: classes.dex */
public class ViewAddCallOther extends View {
    private ActionAcceptResult actionScreenResult;
    private int alpha;
    private final Drawable dAdd;
    private final Drawable dEnd;
    private final Handler handler;
    private boolean isRemove;
    private boolean isTouch;
    private final Paint paint;
    private final Rect rAdd;
    private final Rect rEnd;
    private final Runnable runnable;
    private final int size;
    private int status;
    private boolean touchAdd;
    private boolean touchEnd;
    private final int w;
    private int xAdd;
    private int xEnd;
    private float xStart;

    static /* synthetic */ int access$208(ViewAddCallOther viewAddCallOther) {
        int i = viewAddCallOther.status;
        viewAddCallOther.status = i + 1;
        return i;
    }

    public void setActionScreenResult(ActionAcceptResult actionAcceptResult) {
        this.actionScreenResult = actionAcceptResult;
    }

    public ViewAddCallOther(Context context) {
        super(context);
        Runnable runnable = new Runnable() { // from class: com.appsgenz.callphoneios.screen.other.ViewAddCallOther.1
            @Override // java.lang.Runnable
            public void run() {
                ViewAddCallOther.this.handler.postDelayed(ViewAddCallOther.this.runnable, 500L);
                ViewAddCallOther.access$208(ViewAddCallOther.this);
                if (ViewAddCallOther.this.status == 3) {
                    ViewAddCallOther.this.status = 0;
                }
                ViewAddCallOther.this.invalidate();
            }
        };
        this.runnable = runnable;
        this.dAdd = getResources().getDrawable(R.drawable.im_add_call_other);
        this.dEnd = getResources().getDrawable(R.drawable.im_end_call_other);
        int widthScreen = OtherUtils.getWidthScreen(context);
        this.w = widthScreen;
        this.size = (widthScreen * 19) / 100;
        this.xAdd = widthScreen / 5;
        this.xEnd = widthScreen - (widthScreen / 5);
        Paint paint = new Paint(1);
        this.paint = paint;
        paint.setColor(-1);
        paint.setStrokeWidth((widthScreen * 0.83f) / 100.0f);
        this.rAdd = new Rect();
        this.rEnd = new Rect();
        Handler handler = new Handler();
        this.handler = handler;
        handler.post(runnable);
        this.isTouch = true;
        this.alpha = 255;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setAlpha(255);
        Rect rect = this.rAdd;
        int i = this.xAdd - (this.size / 2);
        int height = getHeight();
        int i2 = this.size;
        rect.set(i, (height - i2) / 2, this.xAdd + (i2 / 2), (getHeight() + this.size) / 2);
        Rect rect2 = this.rEnd;
        int i3 = this.xEnd - (this.size / 2);
        int height2 = getHeight();
        int i4 = this.size;
        rect2.set(i3, (height2 - i4) / 2, this.xEnd + (i4 / 2), (getHeight() + this.size) / 2);
        if (!this.touchAdd && !this.touchEnd) {
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setAlpha(100);
            int i5 = this.size;
            int i6 = (this.w / 5) + (i5 / 2) + (i5 / 4);
            canvas.drawCircle(i6, getHeight() / 2.0f, this.w / 150.0f, this.paint);
            canvas.drawCircle(this.w - i6, getHeight() / 2.0f, this.w / 150.0f, this.paint);
            int i7 = i6 + (this.size / 4);
            canvas.drawCircle(i7, getHeight() / 2.0f, this.w / 150.0f, this.paint);
            canvas.drawCircle(this.w - i7, getHeight() / 2.0f, this.w / 150.0f, this.paint);
            int i8 = this.status;
            if (i8 == 0) {
                canvas.drawCircle(this.xAdd, getHeight() / 2.0f, this.size / 2.0f, this.paint);
                canvas.drawCircle(this.xEnd, getHeight() / 2.0f, this.size / 2.0f, this.paint);
            } else if (i8 == 1) {
                this.paint.setAlpha(255);
                int i9 = this.size;
                int i10 = (this.w / 5) + (i9 / 2) + (i9 / 4);
                canvas.drawCircle(i10, getHeight() / 2.0f, this.w / 100.0f, this.paint);
                canvas.drawCircle(this.w - i10, getHeight() / 2.0f, this.w / 100.0f, this.paint);
            } else {
                this.paint.setAlpha(255);
                int i11 = (this.w / 5) + this.size;
                canvas.drawCircle(i11, getHeight() / 2.0f, this.w / 100.0f, this.paint);
                canvas.drawCircle(this.w - i11, getHeight() / 2.0f, this.w / 100.0f, this.paint);
            }
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setAlpha(255);
            canvas.drawCircle(this.xAdd, getHeight() / 2.0f, this.size / 2.0f, this.paint);
            canvas.drawCircle(this.xEnd, getHeight() / 2.0f, this.size / 2.0f, this.paint);
            this.dAdd.setAlpha(255);
            this.dEnd.setAlpha(255);
        }
        this.paint.setStyle(Paint.Style.STROKE);
        if (this.touchAdd) {
            this.dEnd.setAlpha(this.alpha);
            this.paint.setAlpha(255);
            canvas.drawCircle(this.xAdd, getHeight() / 2.0f, this.size / 2.0f, this.paint);
            this.paint.setAlpha(this.alpha);
            canvas.drawCircle(this.xEnd, getHeight() / 2.0f, this.size / 2.0f, this.paint);
        } else if (this.touchEnd) {
            this.dAdd.setAlpha(this.alpha);
            this.paint.setAlpha(this.alpha);
            canvas.drawCircle(this.xAdd, getHeight() / 2.0f, this.size / 2.0f, this.paint);
            this.paint.setAlpha(255);
            canvas.drawCircle(this.xEnd, getHeight() / 2.0f, this.size / 2.0f, this.paint);
        }
        this.dAdd.setBounds(this.rAdd);
        this.dAdd.draw(canvas);
        this.dEnd.setBounds(this.rEnd);
        this.dEnd.draw(canvas);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0015, code lost:
        if (r0 != 3) goto L13;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r9) {
        /*
            Method dump skipped, instructions count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgenz.callphoneios.screen.other.ViewAddCallOther.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void onRemove() {
        this.handler.removeCallbacks(this.runnable);
        if (getVisibility() == 8 && getParent() != null) {
            this.isRemove = true;
            ((ViewGroup) getParent()).removeView(this);
        } else if (this.isRemove) {
        } else {
            this.isRemove = true;
            animate().alpha(0.0f).setDuration(500L).withEndAction(new Runnable() { // from class: com.appsgenz.callphoneios.screen.other.ViewAddCallOther$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ViewAddCallOther.this.m208x47c7c971();
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onRemove$0$com-callos14-callscreen-colorphone-screen-other-ViewAddCallOther  reason: not valid java name */
    public /* synthetic */ void m208x47c7c971() {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
    }
}

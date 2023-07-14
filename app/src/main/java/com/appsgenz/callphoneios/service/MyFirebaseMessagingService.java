package com.appsgenz.callphoneios.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.bumptech.glide.Glide;
import com.appsgenz.callphoneios.ActivitySplash;
import com.appsgenz.callphoneios.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.Random;
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static final String CHANNEL_ID = "notify_channel";

    @Override
    public void onNewToken(String str) {
        super.onNewToken(str);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        final Uri uri;
        String str;
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        String str2 = "";
        if (notification != null) {
            str2 = notification.getTitle();
            str = notification.getBody();
            uri = notification.getImageUrl();
        } else {
            uri = null;
            str = "";
        }
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "My Firebase Notification", NotificationManager.IMPORTANCE_HIGH));
        }
        Intent intent = new Intent(this, ActivitySplash.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        final NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(this, CHANNEL_ID).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).setAutoCancel(true).setDefaults(3).setContentTitle(str2).setContentIntent(PendingIntent.getActivity(this, 222, intent, PendingIntent.FLAG_IMMUTABLE)).setContentText(str).setSmallIcon(R.drawable.ic_notification);
        if (uri == null || uri.toString().isEmpty()) {
            smallIcon.setStyle(new NotificationCompat.BigTextStyle());
            notificationManager.notify(new Random().nextInt(), smallIcon.build());
            return;
        }
        final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.appsgenz.callphoneios.service.MyFirebaseMessagingService$$ExternalSyntheticLambda0
            @Override
            public boolean handleMessage(Message message) {
                return MyFirebaseMessagingService.lambda$onMessageReceived$0(smallIcon, notificationManager, message);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyFirebaseMessagingService.this.m235x259ea5ec(uri, handler);
            }
        }).start();
    }

    public static boolean lambda$onMessageReceived$0(NotificationCompat.Builder builder, NotificationManager notificationManager, Message message) {
        Bitmap bitmap = (Bitmap) message.obj;
        if (bitmap != null) {
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
        } else {
            builder.setStyle(new NotificationCompat.BigTextStyle());
        }
        notificationManager.notify(IronSourceError.ERROR_RV_LOAD_FAIL_UNEXPECTED, builder.build());
        return true;
    }

    public void m235x259ea5ec(Uri uri, Handler handler) {
        Message message = new Message();
        try {
            Bitmap bitmap = Glide.with(this).asBitmap().load(uri).submit().get();
            if (bitmap != null) {
                message.obj = bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.sendMessage(message);
    }
}

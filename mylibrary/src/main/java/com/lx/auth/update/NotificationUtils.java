package com.lx.auth.update;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.lx.auth.R;

public class NotificationUtils {

    private static void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "其他通知";
            String description = "其他通知";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel("其他通知", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager mNotificationManager = Utils.getApp().getSystemService(NotificationManager.class);
            mNotificationManager.createNotificationChannel(channel);
        }
    }

    public static void showNotification(String content) {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Utils.getApp(), "其他通知")
                .setContentTitle("应用升级")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(content).setSound(null)
                .setPriority(NotificationCompat.PRIORITY_LOW);
        Notification build = builder.build();
        NotificationManagerCompat.from(Utils.getApp()).notify(1, build);
    }

    public static void cancel() {
        NotificationManagerCompat.from(Utils.getApp()).cancel(1);
    }
}

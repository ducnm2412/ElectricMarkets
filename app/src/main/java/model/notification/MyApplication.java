package model.notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createChannelNotification();
    }

    private void createChannelNotification() {
        // Kiểm tra xem hệ điều hành có hỗ trợ Notification Channel không (Android 8.0 trở lên)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Tạo một ID cho kênh thông báo
            String channelId = "default_channel";
            CharSequence channelName = "Default Notifications";
            String channelDescription = "This is the default notification channel for the app.";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            // Tạo NotificationChannel
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setDescription(channelDescription);

            // Lấy NotificationManager để tạo kênh
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}

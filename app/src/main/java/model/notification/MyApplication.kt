package model.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createChannelNotification()
    }

    private fun createChannelNotification() {
        // Kiểm tra xem hệ điều hành có hỗ trợ Notification Channel không (Android 8.0 trở lên)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Tạo một ID cho kênh thông báo
            val channelId = "default_channel"
            val channelName: CharSequence = "Default Notifications"
            val channelDescription = "This is the default notification channel for the app."
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // Tạo NotificationChannel
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }

            // Lấy NotificationManager để tạo kênh
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }
}

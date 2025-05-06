package model.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.example.electricmarkets.R

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Kiểm tra nếu thông báo có chứa dữ liệu
        if (remoteMessage.data.isNotEmpty()) {
            // Lấy dữ liệu từ thông báo
            val message = remoteMessage.data["message"]
            val orderId = remoteMessage.data["orderId"]

            // Hiển thị thông báo
            showNotification(message, orderId)
        }

        // Kiểm tra nếu thông báo có chứa thông tin notification
        remoteMessage.notification?.let {
            val message = it.body
            showNotification(message, null)
        }
    }

    // Phương thức hiển thị thông báo
    private fun showNotification(message: String?, orderId: String?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Tạo kênh thông báo cho Android 8.0 trở lên
        val channelId = "default_channel" // Đảm bảo bạn đã khai báo kênh trong MyApplication

        // Tạo thông báo
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_stat_ic_notification)
            .setContentTitle("Mua hàng thành công")
            .setContentText(message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Gửi thông báo
        notificationManager.notify(0, notification)
    }

    override fun onNewToken(token: String) {
        // Gửi token mới đến server hoặc sử dụng nếu cần
    }
}


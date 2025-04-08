package model.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.example.electricmarkets.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Kiểm tra nếu thông báo có chứa dữ liệu
        if (remoteMessage.getData().size() > 0) {
            // Lấy dữ liệu từ thông báo
            String message = remoteMessage.getData().get("message");
            String orderId = remoteMessage.getData().get("orderId"); // Giả sử dữ liệu bao gồm orderId

            // Hiển thị thông báo
            showNotification(message, orderId);
        }

        // Kiểm tra nếu thông báo có chứa thông tin notification
        if (remoteMessage.getNotification() != null) {
            String message = remoteMessage.getNotification().getBody();
            showNotification(message, null);
        }
    }

    // Phương thức hiển thị thông báo
    private void showNotification(String message, String orderId) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Tạo kênh thông báo cho Android 8.0 trở lên
        String channelId = "default_channel"; // Đảm bảo bạn đã khai báo kênh trong MyApplication

        // Tạo thông báo
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle("Mua hàng thành công")
                .setContentText(message)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        // Gửi thông báo
        if (notificationManager != null) {
            notificationManager.notify(0, notification);
        }
    }

    @Override
    public void onNewToken(String token) {
        // Gửi token mới đến server hoặc sử dụng nếu cần
    }
}

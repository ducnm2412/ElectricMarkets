package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.data.Order
import model.reponsitory.OrderRepository

class OrderViewModel : ViewModel() {

    private val orderRepository = OrderRepository()
    val orderList = MutableLiveData<List<Order>>()
    val errorMessage = MutableLiveData<String>()

    // ✅ Thêm đơn hàng vào Realtime Database và xử lý kết quả
    fun addOrder(order: Order, onResult: (Boolean, String?) -> Unit) {
        orderRepository.addOrder(order, onResult)
    }

    // ✅ Lấy tất cả đơn hàng của người dùng
    fun getOrdersByUser(userID: String) {
        orderRepository.getOrdersByUser(userID) { orders ->
            if (orders.isNotEmpty()) {
                orderList.value = orders
            } else {
                errorMessage.value = "Không tìm thấy đơn hàng"
            }
        }
    }

    // ✅ Cập nhật trạng thái đơn hàng
    fun updateOrderStatus(orderID: String, status: String) {
        orderRepository.updateOrderStatus(orderID, status)
    }
}

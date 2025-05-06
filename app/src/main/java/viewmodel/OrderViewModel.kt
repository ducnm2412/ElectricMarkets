    package viewmodel

    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import model.data.Cart
    import model.data.Order
    import model.data.OrderItem
    import model.reponsitory.OrderRepository
    import model.repository.CartRepository
    class OrderViewModel : ViewModel() {

        private val orderRepository = OrderRepository()
        val orderList = MutableLiveData<List<Order>>()
        val errorMessage = MutableLiveData<String>()

        // Lấy tất cả đơn hàng của người dùng
        fun getOrdersByUser(userID: String) {
            orderRepository.getOrdersByUser(userID) { orders ->
                if (orders.isNotEmpty()) {
                    orderList.value = orders
                } else {
                    errorMessage.value = "Không tìm thấy đơn hàng"
                }
            }
        }

        // Lấy lịch sử đơn hàng của người dùng
        fun getOrderHistory(userID: String) {
            orderRepository.getOrdersByUser(userID) { orders ->
                if (orders.isNotEmpty()) {
                    orderList.value = orders
                } else {
                    errorMessage.value = "Không có lịch sử đơn hàng"
                }
            }
        }

        // Cập nhật trạng thái đơn hàng
        fun updateOrderStatus(orderID: String, status: String) {
            orderRepository.updateOrderStatus(orderID, status)
        }

        fun addOrder(order: Order, onResult: (Boolean, String?) -> Unit) {
            orderRepository.addOrder(order) { success, orderId ->
                if (success) {
                    // Fetch the latest order after adding it
                    getOrdersByUser(order.userID)
                }
                onResult(success, orderId)
            }
        }
        fun getLatestOrder(userID: String) {
            orderRepository.getOrdersByUser(userID) { orders ->
                if (orders.isNotEmpty()) {
                    orderList.value = listOf(orders.first())
                } else {
                    errorMessage.value = "Không có đơn hàng"
                }
            }
        }
    }

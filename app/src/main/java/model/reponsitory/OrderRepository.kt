package model.reponsitory

import com.google.firebase.database.FirebaseDatabase
import model.data.Order

class OrderRepository {

    private val database = FirebaseDatabase.getInstance()
    private val orderRef = database.getReference("orders")

    // Thêm đơn hàng vào Realtime Database
    fun addOrder(order: Order) {
        val key = orderRef.push().key
        if (key != null) {
            orderRef.child(key).setValue(order)
                .addOnSuccessListener {
                    println(" Order added successfully!")
                }
                .addOnFailureListener { e ->
                    println(" Error adding order: $e")
                }
        }
    }

    // Lấy tất cả đơn hàng của người dùng
    fun getOrdersByUser(userID: String, callback: (List<Order>) -> Unit) {
        orderRef.get()
            .addOnSuccessListener { snapshot ->
                val orders = snapshot.children.mapNotNull {
                    it.getValue(Order::class.java)
                }.filter { it.userID == userID }

                callback(orders)
            }
            .addOnFailureListener { e ->
                println("Error getting orders: $e")
                callback(emptyList())
            }
    }

    // Cập nhật trạng thái đơn hàng
    fun updateOrderStatus(orderID: String, status: String) {
        orderRef.child(orderID).child("status").setValue(status)
            .addOnSuccessListener {
                println(" Order status updated successfully!")
            }
            .addOnFailureListener { e ->
                println(" Error updating order status: $e")
            }
    }
}

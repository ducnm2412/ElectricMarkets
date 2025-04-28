package model.data

data class Order(
    val orderID: String = "",
    val userID: String = "",
    val totalAmount: Double = 0.0,
    val status: String = "",
    val shippingAddress: String = "",
    val items: List<OrderItem> = listOf()
)

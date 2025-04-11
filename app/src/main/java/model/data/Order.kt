package model.data

data class Order(
    val userID: String = "",
    val totalAmount: Double = 0.0,
    val status: String = "",  // "Processing", "Shipped", "Cancelled", etc.
    val shippingAddress: String = "",
    val items: List<OrderItem> = listOf()
)
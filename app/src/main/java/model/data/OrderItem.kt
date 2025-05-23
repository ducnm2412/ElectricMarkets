package model.data

data class OrderItem(
    val productID: String = "",
    val productName: String = "",
    val quantity: Int = 0,
    val price: Double = 0.0
)
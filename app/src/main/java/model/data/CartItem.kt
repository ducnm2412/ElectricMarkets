    package model.data

    data class CartItem(
        val productID: String = "",
        val productName: String = "",
        val quantity: Int = 0,
        val price: Double = 0.0,
        val imageRes: Int = 0
    )
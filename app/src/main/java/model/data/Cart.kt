package model.data
data class Cart(
    val userID: String = "",
    val items: List<CartItem> = listOf()
)
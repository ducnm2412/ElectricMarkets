package model.data

data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val discountedPrice: Double = 0.0,
    val category: String = "",
    val stock: Int = 0,
    val image: Int = 0
)

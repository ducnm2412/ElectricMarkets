package model.data

data class Product(
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val discountedPrice: Double = 0.0,
    val category: String = "",
    val stock: Int = 0
)

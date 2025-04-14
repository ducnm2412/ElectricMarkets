package model.data

data class Contact(
    val message: String = "",
    val gender: String = "",  // "Nam" hoặc "Nữ"
    val email: String = "",
    val phone: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

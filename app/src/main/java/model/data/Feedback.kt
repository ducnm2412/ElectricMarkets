package model.data

data class Feedback(
    val userID: String = "",
    val rating: Int = 0, // ví dụ 5 sao
    val comment: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

package model.repository

import com.google.firebase.database.FirebaseDatabase
import model.data.CartItem
class CartRepository {

    private val db = FirebaseDatabase.getInstance().getReference("carts")

    // Thêm sản phẩm vào giỏ hàng
    fun addProductToCart(userId: String, productId: String, item: CartItem) {
        val userCartRef = db.child(userId).child(productId)

        userCartRef.get().addOnSuccessListener {
            if (it.exists()) {
                // Nếu sản phẩm đã có trong giỏ, cập nhật số lượng
                val currentQuantity = it.child("quantity").getValue(Int::class.java) ?: 0
                userCartRef.child("quantity").setValue(currentQuantity + item.quantity)
            } else {
                // Nếu sản phẩm chưa có trong giỏ, thêm mới
                userCartRef.setValue(item)
            }
        }
    }


    // Lấy tất cả sản phẩm trong giỏ hàng
    fun getCart(userId: String, onResult: (List<CartItem>) -> Unit) {
        db.child(userId).get()
            .addOnSuccessListener { snapshot ->
                val list = snapshot.children.mapNotNull {
                    val productId = it.key ?: return@mapNotNull null
                    val item = it.getValue(CartItem::class.java)
                    item?.copy(productID = productId)
                }
                onResult(list)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    // Xóa sản phẩm khỏi giỏ hàng
    fun removeProduct(userId: String, productId: String) {
        db.child(userId).child(productId).removeValue()
    }
    fun updateQuantity(userId: String, productId: String, newQuantity: Int) {
        db.child(userId).child(productId).child("quantity").setValue(newQuantity)
            .addOnSuccessListener {
                println("✅ Updated product quantity")
            }
            .addOnFailureListener {
                println("❌ Failed to update product quantity: ${it.message}")
            }
    }
    // Xóa toàn bộ giỏ hàng (hoặc có thể xóa khi đã thanh toán)
    fun clearCart(userId: String) {
        db.child(userId).removeValue()
    }
}

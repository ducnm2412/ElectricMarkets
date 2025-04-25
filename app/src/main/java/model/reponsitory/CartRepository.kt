package model.repository

import com.google.firebase.database.FirebaseDatabase
import model.data.CartItem
class CartRepository {

    private val db = FirebaseDatabase.getInstance().getReference("carts")

    // Thêm sản phẩm vào giỏ hàng
    fun addProductToCart(userId: String, productId: String, item: CartItem) {
        db.child(userId).child(productId).setValue(item)
            .addOnSuccessListener {
                println("✅ Added to cart")
            }
            .addOnFailureListener {
                println("❌ Failed to add to cart: ${it.message}")
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

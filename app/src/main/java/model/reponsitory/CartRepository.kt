package model.reponsitory


import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import model.data.Cart
import model.data.CartItem

class CartRepository {

    private val firestore = FirebaseFirestore.getInstance()

    // Thêm sản phẩm vào giỏ hàng của người dùng
    fun addProductToCart(userUID: String, cartItem: CartItem) {
        val cartRef = firestore.collection("carts").document(userUID)

        // Thêm sản phẩm vào giỏ hàng
        cartRef.update("items", FieldValue.arrayUnion(cartItem))
            .addOnSuccessListener {
                println("Product added to cart successfully!")
            }
            .addOnFailureListener { e ->
                println("Error adding product to cart: $e")
            }
    }

    // Lấy giỏ hàng của người dùng
    fun getCart(userUID: String, callback: (Cart?) -> Unit) {
        val cartRef = firestore.collection("carts").document(userUID)

        cartRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val cart = document.toObject(Cart::class.java)
                    callback(cart)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                println("Error getting cart: $e")
                callback(null)
            }
    }

    // Cập nhật giỏ hàng của người dùng
    fun updateCart(userUID: String, cart: Cart) {
        val cartRef = firestore.collection("carts").document(userUID)

        // Cập nhật giỏ hàng trong Firestore
        cartRef.set(cart)
            .addOnSuccessListener {
                println("Cart updated successfully!")
            }
            .addOnFailureListener { e ->
                println("Error updating cart: $e")
            }
    }
}
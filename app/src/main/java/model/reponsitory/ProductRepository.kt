package model.repository

import com.example.electricmarkets.R
import com.google.firebase.database.FirebaseDatabase
import model.data.Product

class ProductRepository {

    private val database = FirebaseDatabase.getInstance()
    private val productRef = database.getReference("products")
    fun getProducts(callback: (List<Product>) -> Unit) {
        productRef.get()
            .addOnSuccessListener { snapshot ->
                val products = snapshot.children.mapNotNull {
                    val product = it.getValue(Product::class.java)
                    product?.copy(id = it.key ?: "")
                }
                callback(products)
            }
            .addOnFailureListener { e ->
                println("Lỗi khi lấy sản phẩm: $e")
                callback(emptyList())
            }
    }
    fun getProductById(productId: String, callback: (Product?) -> Unit) {
        productRef.child(productId).get()
            .addOnSuccessListener { snapshot ->
                val product = snapshot.getValue(Product::class.java)
                callback(product)
            }
            .addOnFailureListener { e ->
                println("Error fetching product by ID: $e")
                callback(null)
            }
    }

}

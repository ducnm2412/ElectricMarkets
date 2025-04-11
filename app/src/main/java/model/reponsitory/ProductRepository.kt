package model.repository

import com.google.firebase.database.FirebaseDatabase
import model.data.Product

class ProductRepository {

    private val database = FirebaseDatabase.getInstance()
    private val productRef = database.getReference("products")

    // Thêm 10 sản phẩm vào Realtime Database
    fun addDefaultProducts() {
        val products = listOf(
            Product("Máy lạnh Panasonic Inverter WIFI 1.0 HP", "Tiết kiệm điện, làm lạnh nhanh", 12900000.0, 12290000.0, "Máy lạnh", 5),
            Product("Naga Inverter 1 HP", "Thiết kế nhỏ gọn, vận hành êm", 6680000.0, 5480000.0, "Máy lạnh", 10),
            Product("TCL Inverter 1.5 HP", "Công nghệ làm lạnh sâu", 7990000.0, 5990000.0, "Máy lạnh", 8),
            Product("Electrolux ECI-28D", "Tai nghe không dây chống ồn", 3290000.0, 2540000.0, "Phụ kiện", 20),
            Product("Tủ lạnh Samsung Inverter", "Tiết kiệm điện, dung tích lớn", 22490000.0, 19790000.0, "Tủ lạnh", 3),
            Product("Máy giặt Panasonic Inverter", "Công nghệ giặt tiết kiệm nước", 15149000.0, 12790000.0, "Máy giặt", 4),
            Product("Máy lọc nước RO nóng lạnh", "Lọc 9 lõi, nước uống trực tiếp", 9120000.0, 6590000.0, "Máy lọc nước", 7),
            Product("Thăng nhít Robot", "Xe máy điện trẻ em", 1990000.0, 1590000.0, "Khác", 12),
            Product("Tivi LG 55 inch 4K", "Hình ảnh sắc nét, hệ điều hành WebOS", 15000000.0, 13500000.0, "Tivi", 6),
            Product("Máy lạnh Casper Inverter 1 HP", "Giá rẻ, làm lạnh nhanh", 7490000.0, 5490000.0, "Máy lạnh", 9)
        )


        products.forEach { product ->
            val key = productRef.push().key
            if (key != null) {
                productRef.child(key).setValue(product)
            }
        }

        println("Thêm 10 sản phẩm thủ công thành công (Realtime DB)!")
    }

    // Lấy danh sách sản phẩm từ Realtime Database
    fun getProducts(callback: (List<Product>) -> Unit) {
        productRef.get()
            .addOnSuccessListener { snapshot ->
                val products = snapshot.children.mapNotNull { it.getValue(Product::class.java) }
                callback(products)
            }
            .addOnFailureListener { e ->
                println("Lỗi khi lấy sản phẩm: $e")
                callback(emptyList())
            }
    }
}

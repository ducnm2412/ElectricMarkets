package model.repository

import com.example.electricmarkets.R
import com.google.firebase.database.FirebaseDatabase
import model.data.Product

class ProductRepository {

    private val database = FirebaseDatabase.getInstance()
    private val productRef = database.getReference("products")

    // Thêm 10 sản phẩm vào Realtime Database
    fun addDefaultProducts() {
        val products = listOf(
            Product(id = "1", name = "Máy lạnh Panasonic Inverter WIFI 1.0 HP", description = "Tiết kiệm điện, làm lạnh nhanh", price = 12900000.0, discountedPrice = 12290000.0, category = "Máy lạnh", stock = 5, image = R.drawable.panasonic_1hp),
            Product(id = "2", name = "Naga Inverter 1 HP", description = "Thiết kế nhỏ gọn, vận hành êm", price = 6680000.0, discountedPrice = 5480000.0, category = "Máy lạnh", stock = 10, image = R.drawable.naga_1hp),
            Product(id = "3", name = "TCL Inverter 1.5 HP", description = "Công nghệ làm lạnh sâu", price = 7990000.0, discountedPrice = 5990000.0, category = "Máy lạnh", stock = 8, image = R.drawable.tcl_15hp),
            Product(id = "4", name = "Electrolux ECI-28D", description = "Tai nghe không dây chống ồn", price = 3290000.0, discountedPrice = 2540000.0, category = "Phụ kiện", stock = 20, image = R.drawable.electrolux_eci28d),
            Product(id = "5", name = "Tủ lạnh Samsung Inverter", description = "Tiết kiệm điện, dung tích lớn", price = 22490000.0, discountedPrice = 19790000.0, category = "Tủ lạnh", stock = 3, image = R.drawable.tu_lanh_samsung),
            Product(id = "6", name = "Máy giặt Panasonic Inverter", description = "Công nghệ giặt tiết kiệm nước", price = 15149000.0, discountedPrice = 12790000.0, category = "Máy giặt", stock = 4, image = R.drawable.may_giat_panasonic),
            Product(id = "7", name = "Máy lọc nước RO nóng lạnh", description = "Lọc 9 lõi, nước uống trực tiếp", price = 9120000.0, discountedPrice = 6590000.0, category = "Máy lọc nước", stock = 7, image = R.drawable.loc_nuoc_ro),
            Product(id = "8", name = "Thăng nhít Robot", description = "Xe máy điện trẻ em", price = 1990000.0, discountedPrice = 1590000.0, category = "Khác", stock = 12, image = R.drawable.xe_dap_robot),
            Product(id = "9", name = "Tivi LG 55 inch 4K", description = "Hình ảnh sắc nét, hệ điều hành WebOS", price = 15000000.0, discountedPrice = 13500000.0, category = "Tivi", stock = 6, image = R.drawable.tivi_lg_55),
            Product(id = "10", name = "Máy lạnh Casper Inverter 1 HP", description = "Giá rẻ, làm lạnh nhanh", price = 7490000.0, discountedPrice = 5490000.0, category = "Máy lạnh", stock = 9, image = R.drawable.casper_1hp)
        )

        products.forEach { product ->
            val key = productRef.push().key
            key?.let {
                productRef.child(it).setValue(product)
            }
        }

        println("✅ Đã thêm 10 sản phẩm có hình ảnh!")
    }


    fun getProducts(callback: (List<Product>) -> Unit) {
        productRef.get()
            .addOnSuccessListener { snapshot ->
                val products = snapshot.children.mapNotNull {
                    val product = it.getValue(Product::class.java)
                    product?.copy(id = it.key ?: "") // Thêm id cho mỗi sản phẩm
                }
                callback(products)
            }
            .addOnFailureListener { e ->
                println("Lỗi khi lấy sản phẩm: $e")
                callback(emptyList())
            }
    }


}

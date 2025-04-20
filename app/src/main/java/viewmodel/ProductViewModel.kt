package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.data.Product
import model.repository.CartRepository
import model.repository.ProductRepository

class ProductViewModel : ViewModel() {

    private val productRepository = ProductRepository()
    val productList: MutableLiveData<List<Product>> = MutableLiveData()
    val errorMessage = MutableLiveData<String>()
    private val cartRepository = CartRepository()
    val allProducts = MutableLiveData<List<Product>>() // toàn bộ
    val filteredProducts = MutableLiveData<List<Product>>() // hiển thị sau khi search/filter

    // Thêm 10 sản phẩm mặc định vào Realtime Database
    fun addDefaultProducts() {
        productRepository.addDefaultProducts()
    }

    // Lấy danh sách sản phẩm từ Realtime Database
    fun getProducts() {
        productRepository.getProducts { products ->
            if (products.isNotEmpty()) {
                productList.value = products
            } else {
                errorMessage.value = "Không có sản phẩm nào"
            }
        }
    }
    fun searchProductsByName(keyword: String) {
        val all = productList.value ?: return
        val filtered = all.filter { it.name.contains(keyword, ignoreCase = true) }
        productList.value = filtered
    }
    fun filterByCategory(category: String) {
        val all = productList.value ?: return
        val filtered = all.filter { it.category == category }
        productList.value = filtered
    }
}

package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.data.Product
import model.repository.ProductRepository

class ProductViewModel : ViewModel() {

    private val productRepository = ProductRepository()
    val productList = MutableLiveData<List<Product>>()
    val errorMessage = MutableLiveData<String>()

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
}

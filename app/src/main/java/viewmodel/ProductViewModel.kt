package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import model.data.CartItem
import model.data.Product
import model.repository.CartRepository
import model.repository.ProductRepository

class ProductViewModel : ViewModel() {
    private val repo = CartRepository()
    private val productRepository = ProductRepository()
    val productList: MutableLiveData<List<Product>> = MutableLiveData()
    val errorMessage = MutableLiveData<String>()
    private val cartRepository = CartRepository()
    val allProducts = MutableLiveData<List<Product>>()
    val filteredProducts = MutableLiveData<List<Product>>()
    val cartItems = MutableLiveData<List<CartItem>>()

    val productDetails: MutableLiveData<Product?> = MutableLiveData()
    fun loadCart(userId: String) {
        repo.getCart(userId) { list ->
            cartItems.value = list
        }
    }

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
        val filtered = all.filter { it.name.trim().contains(keyword.trim(), ignoreCase = true) }
        productList.value = filtered
    }


    fun getProductDetails(productId: String) {
        productRepository.getProductById(productId) { product ->
            if (product != null) {
                productDetails.value = product
            } else {
                errorMessage.value = "Không tìm thấy sản phẩm với ID $productId"
            }
        }
    }
}

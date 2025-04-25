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
    val allProducts = MutableLiveData<List<Product>>() // toàn bộ
    val filteredProducts = MutableLiveData<List<Product>>() // hiển thị sau khi search/filter
    val cartItems = MutableLiveData<List<CartItem>>()
    // Thêm 10 sản phẩm mặc định vào Realtime Database
    fun addDefaultProducts() {
        productRepository.addDefaultProducts()
    }
    fun loadCart(userId: String) {
        repo.getCart(userId) { list ->
            cartItems.value = list
        }
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

        val filtered = all.filter { it.name.trim().contains(keyword.trim(), ignoreCase = true) }

        productList.value = filtered
    }
    fun updateQuantity(productId: String, newQuantity: Int) {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        // Cập nhật số lượng trong giỏ hàng
        repo.updateQuantity(currentUserId, productId, newQuantity)

        loadCart(currentUserId) // Reload lại giỏ hàng sau khi cập nhật
    }
    fun filterByCategory(category: String) {
        val all = productList.value ?: return
        val filtered = all.filter { it.category == category }
        productList.value = filtered
    }
}

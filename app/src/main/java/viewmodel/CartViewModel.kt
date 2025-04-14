package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.data.CartItem
import model.repository.CartRepository

class CartViewModel : ViewModel() {

    private val repo = CartRepository()
    val cartItems = MutableLiveData<List<CartItem>>()
    val errorMessage = MutableLiveData<String>()

    fun loadCart(userId: String) {
        repo.getCart(userId) { list ->
            cartItems.value = list
        }
    }

    fun addToCart(userId: String, productId: String, item: CartItem) {
        repo.addProductToCart(userId, productId, item)
        loadCart(userId) // reload lại nếu cần
    }

    fun removeItem(userId: String, productId: String) {
        repo.removeProduct(userId, productId)
        loadCart(userId)
    }
}

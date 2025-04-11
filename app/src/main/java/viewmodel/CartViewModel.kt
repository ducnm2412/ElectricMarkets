package viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.data.Cart
import model.data.CartItem
import model.reponsitory.CartRepository

class CartViewModel : ViewModel() {

    private val cartRepository = CartRepository()
    val cart = MutableLiveData<Cart?>()
    val errorMessage = MutableLiveData<String>()

    // Thêm sản phẩm vào giỏ hàng
    fun addProductToCart(userUID: String, cartItem: CartItem) {
        cartRepository.addProductToCart(userUID, cartItem)
    }

    // Lấy giỏ hàng của người dùng
    fun getCart(userUID: String) {
        cartRepository.getCart(userUID) { fetchedCart ->
            if (fetchedCart != null) {
                cart.value = fetchedCart
            } else {
                errorMessage.value = "No cart found"
            }
        }
    }

    // Cập nhật giỏ hàng
    fun updateCart(userUID: String, cart: Cart) {
        cartRepository.updateCart(userUID, cart)
    }
}

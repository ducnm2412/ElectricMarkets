    package viewmodel

    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import com.google.firebase.auth.FirebaseAuth
    import model.data.CartItem
    import model.data.Order
    import model.data.OrderItem
    import model.reponsitory.OrderRepository
    import model.repository.CartRepository
    class CartViewModel : ViewModel() {

        private val repo = CartRepository()
        private val orderRepo = OrderRepository()
        val cartItems = MutableLiveData<List<CartItem>>()
        val errorMessage = MutableLiveData<String>()

        fun addToCart(userId: String, productId: String, item: CartItem) {
            repo.addProductToCart(userId, productId, item)
            loadCart(userId)
        }

        fun loadCart(userId: String) {
            repo.getCart(userId) { list ->
                cartItems.value = list
            }
        }

        fun removeItem(userId: String, productId: String) {
            repo.removeProduct(userId, productId)
            loadCart(userId)
        }
        fun updateQuantity(productId: String, newQuantity: Int) {
            val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: return

            repo.updateQuantity(currentUserId, productId, newQuantity)

            loadCart(currentUserId)
        }
        fun checkoutCart(userUID: String, shippingAddress: String, onResult: (Boolean, String?) -> Unit) {
            repo.getCart(userUID) { cartItems ->
                if (cartItems.isNotEmpty()) {
                    val totalAmount = cartItems.sumOf { it.price * it.quantity }

                    val order = Order(
                        orderID = "order_${System.currentTimeMillis()}",
                        userID = userUID,
                        totalAmount = totalAmount,
                        status = "Processing",
                        shippingAddress = shippingAddress,
                        items = cartItems.map { cartItem ->
                            OrderItem(
                                productID = cartItem.productID,
                                productName = cartItem.productName,
                                quantity = cartItem.quantity,
                                price = cartItem.price
                            )
                        }
                    )

                    println("Order being created: $order")

                    orderRepo.addOrder(order) { isSuccess, errorMessage ->
                        if (isSuccess) {
                            cartItems.forEach { item ->
                                repo.removeProduct(userUID, item.productID)
                            }
                            onResult(true, "Thanh toán thành công!")
                        } else {
                            onResult(false, errorMessage)
                        }
                    }
                } else {
                    errorMessage.value = "Giỏ hàng trống, không thể thanh toán."
                    onResult(false, "Giỏ hàng trống.")
                }
            }
        }
    }

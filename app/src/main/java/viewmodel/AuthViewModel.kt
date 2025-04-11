package com.example.electricmarkets.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.reponsitory.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import model.data.Product

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    private val auth = FirebaseAuth.getInstance()
    val productList = MutableLiveData<List<Product>>()
    // Expose error messages and success status as LiveData
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val successMessage: MutableLiveData<String> = MutableLiveData()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    // Đăng nhập người dùng
    fun login(email: String, password: String, onSuccess: () -> Unit) {
        authRepository.login(
            email,
            password,
            onSuccess = {
                val user = auth.currentUser
                user?.getIdToken(true)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        successMessage.value = "Đăng nhập thành công"
                        onSuccess()  // Điều hướng sau khi đăng nhập thành công
                    }
                }
                errorMessage.value = ""  // Clear any previous error
            },
            onFailure = { error ->
                errorMessage.value = error
            }
        )
    }

    private fun onSuccess() {
        TODO("Not yet implemented")
    }

    // Đăng ký người dùng
    fun register(email: String, password: String, onSuccess: () -> Unit) {
        authRepository.register(
            email,
            password,
            onSuccess = {
                // Đăng ký thành công
                successMessage.value = "Đăng ký thành công"
                errorMessage.value = ""  // Clear any previous error
                onSuccess() // Điều hướng về trang đăng nhập sau khi đăng ký thành công
            },
            onFailure = { error ->
                // Cập nhật thông báo lỗi
                errorMessage.value = error
            }
        )
    }

    // Phương thức để đặt thông báo lỗi thủ công (tuỳ chọn)
    fun setErrorMessage(message: String) {
        errorMessage.value = message
    }
    // Hàm đọc dữ liệu từ Firebase
    fun fetchProducts() {
        database.child("products").get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val products = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(Product::class.java)
                }.filterNotNull()

                productList.value = products // Đưa dữ liệu vào LiveData
            } else {
                errorMessage.value = "No products found"
            }
        }.addOnFailureListener {
            errorMessage.value = "Failed to fetch products: ${it.message}"
        }
    }
}



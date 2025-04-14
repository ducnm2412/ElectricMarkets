package com.example.electricmarkets.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.reponsitory.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import model.data.Product
import model.data.User  // Import User từ model.data

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    private val auth = FirebaseAuth.getInstance()
    val productList = MutableLiveData<List<Product>>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val successMessage: MutableLiveData<String> = MutableLiveData()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        authRepository.login(
            email,
            password,
            onSuccess = {
                val user = auth.currentUser
                user?.getIdToken(true)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        successMessage.value = "Đăng nhập thành công"
                        onSuccess()
                    }
                }
                errorMessage.value = ""
            },
            onFailure = { error ->
                errorMessage.value = error
            }
        )
    }

    fun register(
        email: String,
        password: String,
        fullName: String,
        phone: String,
        address: String,
        onSuccess: () -> Unit
    ) {
        authRepository.register(
            email,
            password,
            onSuccess = {
                val uid = auth.currentUser?.uid
                if (uid != null) {
                    val user = User(  // Tạo đối tượng User từ model.data
                        uid = uid,
                        email = email,
                        fullName = fullName,
                        phone = phone,
                        address = address
                    )
                    database.child("users").child(uid).setValue(user)
                        .addOnSuccessListener {
                            successMessage.value = "Đăng ký và lưu thông tin thành công"
                            errorMessage.value = ""
                            onSuccess()
                        }
                        .addOnFailureListener {
                            errorMessage.value = "Đăng ký thành công nhưng lưu thông tin thất bại"
                        }
                } else {
                    errorMessage.value = "Không lấy được UID người dùng"
                }
            },
            onFailure = { error ->
                errorMessage.value = error
            }
        )
    }

    fun setErrorMessage(message: String) {
        errorMessage.value = message
    }

    fun fetchProducts() {
        database.child("products").get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val products = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(Product::class.java)
                }.filterNotNull()

                productList.value = products
            } else {
                errorMessage.value = "No products found"
            }
        }.addOnFailureListener {
            errorMessage.value = "Failed to fetch products: ${it.message}"
        }
    }

    // Sửa lại phương thức này
    fun saveUserInfo(userId: String, user: User) {
        database.child("users").child(userId).setValue(user)
    }
}

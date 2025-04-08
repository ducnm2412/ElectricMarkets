package com.example.electricmarkets.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.electricmarkets.model.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    private val auth = FirebaseAuth.getInstance()

    // Expose error messages and success status as LiveData
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val successMessage: MutableLiveData<String> = MutableLiveData()

    // Đăng nhập người dùng
    fun login(email: String, password: String) {
        authRepository.login(
            email,
            password,
            onSuccess = {
                // Đăng nhập thành công, lấy user token và thông báo
                val user = auth.currentUser
                user?.getIdToken(true)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val token = task.result?.token
                        // Có thể lưu hoặc sử dụng token nếu cần
                        successMessage.value = "Đăng nhập thành công"
                    }
                }
                errorMessage.value = ""  // Clear any previous error
            },
            onFailure = { error ->
                // Cập nhật thông báo lỗi
                errorMessage.value = error
            }
        )
    }

    // Đăng ký người dùng
    fun register(email: String, password: String) {
        authRepository.register(
            email,
            password,
            onSuccess = {
                // Đăng ký thành công
                successMessage.value = "Đăng ký thành công"
                errorMessage.value = ""  // Clear any previous error
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
}

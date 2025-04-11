
package model.reponsitory

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    // Đăng nhập người dùng
    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Đăng nhập thành công
                onSuccess()
            }
            .addOnFailureListener { exception ->
                // Đăng nhập thất bại
                onFailure(exception.localizedMessage ?: "Đã có lỗi xảy ra")
            }
    }

    // Đăng ký người dùng
    fun register(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Đăng ký thành công
                onSuccess()
            }
            .addOnFailureListener { exception ->
                // Đăng ký thất bại
                onFailure(exception.localizedMessage ?: "Đã có lỗi xảy ra")
            }
    }
}

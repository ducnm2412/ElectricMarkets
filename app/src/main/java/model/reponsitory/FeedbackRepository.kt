package model.repository

import com.google.firebase.database.FirebaseDatabase
import model.data.Feedback

class FeedbackRepository {

    private val database = FirebaseDatabase.getInstance()
    private val feedbackRef = database.getReference("feedbacks")

    // Thêm hoặc cập nhật đánh giá cho sản phẩm
    fun addFeedback(productID: String, feedback: Feedback, onResult: (Boolean) -> Unit) {
        val userFeedbackRef = feedbackRef.child(productID).child(feedback.userID)
        userFeedbackRef.setValue(feedback)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    // Lấy toàn bộ feedback cho một sản phẩm
    fun getFeedbacksForProduct(productID: String, onResult: (List<Feedback>) -> Unit) {
        feedbackRef.child(productID).get()
            .addOnSuccessListener { snapshot ->
                val list = snapshot.children.mapNotNull {
                    it.getValue(Feedback::class.java)
                }
                onResult(list)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }
}

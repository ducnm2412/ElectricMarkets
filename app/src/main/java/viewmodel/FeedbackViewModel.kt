package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.data.Feedback
import model.repository.FeedbackRepository

class FeedbackViewModel : ViewModel() {

    private val feedbackRepo = FeedbackRepository()
    val feedbackList = MutableLiveData<List<Feedback>>()
    val errorMessage = MutableLiveData<String>()

    // Gửi feedback
    fun addFeedback(productID: String, feedback: Feedback, onResult: (Boolean) -> Unit) {
        feedbackRepo.addFeedback(productID, feedback) { success ->
            if (!success) errorMessage.value = "Không thể gửi đánh giá"
            onResult(success)
        }
    }

    // Lấy feedback theo productID
    fun getFeedbacks(productID: String) {
        feedbackRepo.getFeedbacksForProduct(productID) { list ->
            feedbackList.value = list
        }
    }
}

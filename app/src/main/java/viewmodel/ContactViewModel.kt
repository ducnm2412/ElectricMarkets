package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.data.Contact
import model.repository.ContactRepository

class ContactViewModel : ViewModel() {

    private val repository = ContactRepository()
    val contactData = MutableLiveData<Contact?>()
    val errorMessage = MutableLiveData<String>()

    // Gửi góp ý
    fun submitContact(userID: String, contact: Contact, onResult: (Boolean) -> Unit) {
        repository.submitContact(userID, contact) { success ->
            if (!success) errorMessage.value = "Không thể gửi góp ý"
            onResult(success)
        }
    }

    // Lấy góp ý (nếu cần xem lại)
    fun getContact(userID: String) {
        repository.getContact(userID) { contact ->
            contactData.value = contact
        }
    }
}

package model.repository

import com.google.firebase.database.FirebaseDatabase
import model.data.Contact

class ContactRepository {

    private val database = FirebaseDatabase.getInstance()
    private val contactRef = database.getReference("contact")

    // Gửi góp ý
    fun submitContact(userID: String, contact: Contact, onResult: (Boolean) -> Unit) {
        contactRef.child(userID).setValue(contact)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    // Lấy góp ý theo userID (nếu muốn)
    fun getContact(userID: String, onResult: (Contact?) -> Unit) {
        contactRef.child(userID).get()
            .addOnSuccessListener { snapshot ->
                val contact = snapshot.getValue(Contact::class.java)
                onResult(contact)
            }
            .addOnFailureListener {
                onResult(null)
            }
    }
}

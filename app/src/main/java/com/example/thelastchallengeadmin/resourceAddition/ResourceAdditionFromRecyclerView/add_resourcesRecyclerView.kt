package com.example.thelastchallengeadmin.resourceAddition.ResourceAdditionFromRecyclerView

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.thelastchallengeadmin.Objects.storeddatagame
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata.resName_eng1
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata.resName_sp1
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata.resdesc_en1
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata.resdesc_sp1
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.*

class add_resourcesRecyclerView : BottomSheetDialogFragment() {
    private var imageUri: Uri? = null
    private var pickImage = 100
    val db = Firebase.firestore
    var myfirebaseiconURL=""

    companion object { const val TAG = "CustomBottomSheetDialogFragment" }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {   getDialog()!!.getWindow()!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return inflater.inflate(R.layout.bottom_sheet_add_resource, container, false)}
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ed_username.setText(resName_eng1)
        ed_usernamesp.setText(resName_sp1)
        descriptionengtxt.setText(resdesc_en1)
        descriptionsptext.setText(resdesc_sp1)

        button9.setOnClickListener {
            getimg()
        }

        btn_save.setOnClickListener {
            val docid=getRandomString(12)

            val resourcce = HashMap<String, Any>()
            resourcce["resName_eng"]=ed_username.text.toString()
            resourcce["resName_sp"]=ed_usernamesp.text.toString()
            resourcce["resdesc_en"]=descriptionengtxt.text.toString()
            resourcce["resdesc_sp"]=descriptionsptext.text.toString()
            resourcce["resIconURL"]=myfirebaseiconURL
            resourcce["docID"]=docid
            db.collection("resources").document(docid)
                .set(resourcce, SetOptions.merge())
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    dismiss()
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e)
                }
        }
        btn_cancel.setOnClickListener {
            dismiss()
        }
    }

    fun getRandomString(length: Int) : String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    private fun getimg() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, 100)
    }

    override fun onActivityResult(requesCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(100, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && 100 == pickImage) {

            imageUri = data?.data
            uploadImageToFirebaseStorage()
        }
    }

    private fun uploadImageToFirebaseStorage() {
        val ref = FirebaseStorage.getInstance().reference.child("Resources")
        if (imageUri == null) return
        ref.putFile(imageUri!!)
            .addOnSuccessListener {

                Log.d(ContentValues.TAG, "Successfully uploaded image: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    myfirebaseiconURL =it.toString()
                    button9.text="Image Uploaded"
                    Log.d(ContentValues.TAG, "Successyully uploaded image: ${storeddatagame.gameiconurl}")


                }
                    .addOnFailureListener {
                        Log.d("TAG", "Failed to upload image to storage: ${it.message}")

                    }
            }
    }



}
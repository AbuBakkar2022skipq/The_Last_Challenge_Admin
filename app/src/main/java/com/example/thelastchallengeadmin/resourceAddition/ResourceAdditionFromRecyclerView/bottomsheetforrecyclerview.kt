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
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata.docID1
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.*
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.btn_cancel
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.btn_save
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.button9
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.descriptionengtxt
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.descriptionsptext
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.ed_username
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.ed_usernamesp
import kotlinx.android.synthetic.main.bottomsheetaddresourcerecycleview.*

class bottomsheetforrecyclerview : BottomSheetDialogFragment() {
    private var imageUri: Uri? = null
    private var pickImage = 100
    val db = Firebase.firestore
    var myfirebaseiconURL=""
    var oktoclose=false

    companion object { const val TAG = "CustomBottomSheetDialogFragment" }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {   getDialog()!!.getWindow()!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return inflater.inflate(R.layout.bottomsheetaddresourcerecycleview, container, false)}
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        button9.setOnClickListener {
            getimg()
        }

        val docid=getRandomString(12)

        btn_save.setOnClickListener {

            val resourcce = HashMap<String, Any>()
            resourcce["resName_eng"]=ed_username.text.toString()
            resourcce["resName_sp"]=ed_usernamesp.text.toString()
            resourcce["resdesc_en"]=descriptionengtxt.text.toString()
            resourcce["resdesc_sp"]=descriptionsptext.text.toString()
            resourcce["resIconURL"]=myfirebaseiconURL
            resourcce["docID"]= docID1
            resourcce["onlinestatus"]=true


            db.collection("resources").document(docID1)
                .set(resourcce, SetOptions.merge())
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    if (oktoclose==true) {
                        dismiss()
                    }
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e)
                }
        }

        btn_del.setOnClickListener {
            db.collection("resources").document(docID1)
                .delete()
                .addOnSuccessListener {
                    activity?.recreate()
                    dismiss()
                }
                .addOnFailureListener { }
        }

        btn_cancel.setOnClickListener {
            if (oktoclose==true) {
                dismiss()
            }
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
        button9.text="Image Uploading"
        if (imageUri == null) return
        ref.putFile(imageUri!!)
            .addOnSuccessListener {

                Log.d(ContentValues.TAG, "Successfully uploaded image: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    myfirebaseiconURL =it.toString()
                    button9.text="Image Uploaded"
                    oktoclose=true
                    Log.d(ContentValues.TAG, "Successyully uploaded image: ${storeddatagame.gameiconurl}")
                }
                    .addOnFailureListener {
                        Log.d("TAG", "Failed to upload image to storage: ${it.message}")
                        oktoclose=true
                    }
            }
    }

}
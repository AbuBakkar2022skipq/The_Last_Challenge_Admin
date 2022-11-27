package com.example.thelastchallengeadmin.Punishments.AddPunishments

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.thelastchallengeadmin.Objects.storeddatagame
import com.example.thelastchallengeadmin.R
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.add_punishments.*
import kotlinx.android.synthetic.main.addquestion_layout.*
import kotlinx.android.synthetic.main.addquestion_layout.button4
import kotlinx.android.synthetic.main.addquestion_layout.button5
import kotlinx.android.synthetic.main.addquestion_layout.imageView999
import kotlinx.android.synthetic.main.addquestion_layout.imageView9990
import kotlinx.android.synthetic.main.addquestion_layout.textView21
import kotlinx.android.synthetic.main.addquestion_layout.textView211
import kotlinx.android.synthetic.main.addquestion_layout.textView22
import kotlinx.android.synthetic.main.addquestion_layout.textView2225
import kotlinx.android.synthetic.main.addquestion_layout.textView2227
import kotlinx.android.synthetic.main.addquestion_layout.textView229
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.*

class AddPunishments : AppCompatActivity() {
    private var imageUri: Uri? = null
    private var pickImage = 100
    val db = Firebase.firestore
    var myfirebaseiconURL=""
    var oktoclose=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_punishments)
        var adultt=false
        var correct=0
        button56.visibility= View.VISIBLE

        val v1 = imageView999
        val v2 = imageView9990
        val v3 = check3
        val v4 = check4

        button5.setOnClickListener {
            getimg()
        }


        var docc=getRandomString(12)
        button4.setOnClickListener {
            adultt = eighteen.isChecked
            val resourcce = HashMap<String, Any>()
            resourcce["punishmentName_en"]=textView21.text.toString()
            resourcce["punishmentName_sp"]=textView211.text.toString()
            resourcce["punishment_en"]=textView22.text.toString()
            resourcce["punishment_sp"]=textView2225.text.toString()
            resourcce["imageURL"]=myfirebaseiconURL
            resourcce["adult"]=adultt
            resourcce["docID"]=docc
            resourcce["onlinestatus"]=true

            val db = Firebase.firestore
            db.collection("punishments").document(docc)
                .set(resourcce, SetOptions.merge())
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    finish()
                }
                .addOnFailureListener {
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
        val ref = FirebaseStorage.getInstance().reference.child("Punishment")
        button9.text="Image Uploading"
        if (imageUri == null) return
        ref.putFile(imageUri!!)
            .addOnSuccessListener {

                Log.d(ContentValues.TAG, "Successfully uploaded image: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    myfirebaseiconURL =it.toString()
                    Log.d(ContentValues.TAG, "Successyully uploaded image: ${storeddatagame.gameiconurl}")
                }
                    .addOnFailureListener {
                        Log.d("TAG", "Failed to upload image to storage: ${it.message}")
                        oktoclose=true
                    }
            }
    }

}
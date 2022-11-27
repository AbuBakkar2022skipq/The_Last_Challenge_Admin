package com.example.thelastchallengeadmin.vrgame.Mimicry.Words.BottomSheetAddWords

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
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata.modelsdata
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.*
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.btn_cancel
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.btn_save
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.ed_username
import kotlinx.android.synthetic.main.bottom_sheet_add_resource.ed_usernamesp
import kotlinx.android.synthetic.main.bottomsheet_add_words.*

class bottomSheetAddWords : BottomSheetDialogFragment() {
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
        return inflater.inflate(R.layout.bottomsheet_add_words, container, false)}
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

            var adult=false

                imageView12.setOnClickListener {
                    if(adult==false){
                        adult=true
                        imageView12.setImageResource(R.drawable.check)
                    }
                    else if (adult==true){
                        adult=false
                        imageView12.setImageResource(R.drawable.uncheck)
                    }
                }

                var docc=getRandomString(12)
        btn_save.setOnClickListener {

            val resourcce = HashMap<String, Any>()
            resourcce["resName_eng"]=ed_username.text.toString()
            resourcce["resName_sp"]=ed_usernamesp.text.toString()
            resourcce["docID"]="words"
            resourcce["adult"]=adult
            resourcce["docID"]=docc
            resourcce["onlinestatus"]=true


            db.collection("words").document(docc)
                .set(resourcce, SetOptions.merge())
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    dismiss()
                    activity?.recreate()
                    modelsdata.clear()
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
}
package com.example.thelastchallengeadmin.vrgame.DrawingGame.AddWords

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.bottosheet_addd_forbidden_words.*

class AddWords : BottomSheetDialogFragment() {
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
        return inflater.inflate(R.layout.bottosheet_addd_forbidden_words, container, false)}
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var adult=false

        qimageView12.setOnClickListener {
            if(adult==false){
                adult=true
                qimageView12.setImageResource(R.drawable.check)
            }
            else if (adult==true){
                adult=false
                qimageView12.setImageResource(R.drawable.uncheck)
            }
        }

        var docc=getRandomString(12)
        qbtn_save.setOnClickListener {

            val resourcce = HashMap<String, Any>()
            resourcce["resName_eng"]=qed_username.text.toString()
            resourcce["resName_sp"]=qed_usernamesp.text.toString()
            resourcce["adult"]=adult
            resourcce["docID"]=docc
            resourcce["onlinestatus"]=true

            db.collection("drawingwords").document(docc)
                .set(resourcce, SetOptions.merge())
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    dismiss()
                    activity?.recreate()
                    mimicryshareddata.modelsdata.clear()
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e)
                }
        }
        qbtn_cancel.setOnClickListener {
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
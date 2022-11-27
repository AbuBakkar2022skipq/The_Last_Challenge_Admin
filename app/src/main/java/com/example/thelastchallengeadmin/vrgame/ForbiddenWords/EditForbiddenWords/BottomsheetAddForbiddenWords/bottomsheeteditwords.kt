package com.example.thelastchallengeadmin.vrgame.ForbiddenWords.EditForbiddenWords.BottomsheetAddForbiddenWords

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.ForbiddenWords.forbiddenwordsshareddata
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_bottomsheet_edit_word.*

class bottomsheeteditwords : BottomSheetDialogFragment() {
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
        return inflater.inflate(R.layout.activity_bottomsheet_edit_word, container, false)}
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var adult= forbiddenwordsshareddata.adult2
        if(adult==true){
            aimageView12.setImageResource(R.drawable.check)
        }
        else if (adult==false){
            aimageView12.setImageResource(R.drawable.uncheck)
        }

        aimageView12.setOnClickListener {
            if(adult==false){
                adult=true
                aimageView12.setImageResource(R.drawable.check)
            }
            else if (adult==true){
                adult=false
                aimageView12.setImageResource(R.drawable.uncheck)
            }
        }


        aed_username.setText(forbiddenwordsshareddata.resName_eng2)
        aed_usernamesp.setText(forbiddenwordsshareddata.resName_sp2)

        abtn_save.setOnClickListener {

            val resourcce = HashMap<String, Any>()
            resourcce["resName_eng"]=aed_username.text.toString()
            resourcce["resName_sp"]=aed_usernamesp.text.toString()
            resourcce["docID"]= forbiddenwordsshareddata.docID2
            resourcce["adult"]=adult
            resourcce["onlinestatus"]=true

            db.collection("forbidden").document(forbiddenwordsshareddata.docID2)
                .set(resourcce)
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    activity?.recreate()
                    forbiddenwordsshareddata.reload =true
                    dismiss()
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e)
                }
        }


        abtn_del.setOnClickListener {
            db.collection("forbidden").document(forbiddenwordsshareddata.docID2)
                .delete()
                .addOnSuccessListener {
                    forbiddenwordsshareddata.modelsdata.clear()
                    activity?.recreate()
                    dismiss()
                }
                .addOnFailureListener { }
        }

        abtn_cancel.setOnClickListener {
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
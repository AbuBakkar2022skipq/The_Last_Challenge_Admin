package com.example.thelastchallengeadmin.vrgame.DrawingGame.EditWords

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata
import com.example.thelastchallengeadmin.vrgame.DrawingGame.DrawingGameWords.shareddatadrawingGame
import com.example.thelastchallengeadmin.vrgame.DrawingGame.DrawingGameWords.shareddatadrawingGame.dataa
import com.example.thelastchallengeadmin.vrgame.DrawingGame.DrawingGameWords.shareddatadrawingGame.positionnnn
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.bottom_sheet_edit_drawing_game_words.*
import kotlinx.android.synthetic.main.bottosheet_addd_forbidden_words.*

class EditWords: BottomSheetDialogFragment() {
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
        return inflater.inflate(R.layout.bottom_sheet_edit_drawing_game_words, container, false)}
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var adult=false
        if (dataa[positionnnn].adult==true){
            adult=true
        }

        if (adult==true){
            aimageView12.setImageResource(R.drawable.check)
        }

        else if (adult==false){
            aimageView12.setImageResource(R.drawable.uncheck)
        }

        aimageView12.setOnClickListener {
            if(adult==false){
                adult=true
                dataa[positionnnn].adult=true
                aimageView12.setImageResource(R.drawable.check)
            }
            else if (adult==true){
                adult=false
                dataa[positionnnn].adult=false
                aimageView12.setImageResource(R.drawable.uncheck)
            }
        }

        abtn_del.setOnClickListener {
            db.collection("drawingwords").document(dataa[positionnnn].docID)
                .delete()
                .addOnSuccessListener {
                    activity?.recreate()
                    dismiss()
                }
                .addOnFailureListener { }
        }

        aed_username.setText(shareddatadrawingGame.dataa[shareddatadrawingGame.positionnnn].resName_eng)
        aed_usernamesp.setText(shareddatadrawingGame.dataa[shareddatadrawingGame.positionnnn].resName_sp)


        var docc=getRandomString(12)
        abtn_save.setOnClickListener {

            val resourcce = HashMap<String, Any>()
            resourcce["resName_eng"]=aed_username.text.toString()
            resourcce["resName_sp"]=aed_usernamesp.text.toString()
            resourcce["adult"]=dataa[positionnnn].adult
            resourcce["docID"]=dataa[positionnnn].docID
            resourcce["onlinestatus"]=true

            db.collection("drawingwords").document(dataa[positionnnn].docID)
                .set(resourcce)
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    dismiss()
                    activity?.recreate()
                    mimicryshareddata.modelsdata.clear()
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e)
                }
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
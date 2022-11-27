package com.example.thelastchallengeadmin.vrgame.StrungWords.EditStrungWord

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
import com.example.thelastchallengeadmin.vrgame.Songs.sharedDataSongsGame
import com.example.thelastchallengeadmin.vrgame.StrungWords.SharedDataStrungWords.daytaw
import com.example.thelastchallengeadmin.vrgame.StrungWords.SharedDataStrungWords.indexpos
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.bottom_sheet_edit_drawing_game_words.*

class EditStrungWord  : BottomSheetDialogFragment() {
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
        aadultt.text= "18+ Song"
        var adult=false
        if (daytaw[indexpos].adult==true){
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
                daytaw[indexpos].adult=true
                aimageView12.setImageResource(R.drawable.check)
            }
            else if (adult==true){
                adult=false
                daytaw[indexpos].adult=false
                aimageView12.setImageResource(R.drawable.uncheck)
            }
        }

        abtn_del.setOnClickListener {
            db.collection("drawingwords").document(daytaw[indexpos].docID)
                .delete()
                .addOnSuccessListener {
                    activity?.recreate()
                    dismiss()
                }
                .addOnFailureListener { }
        }

        aed_username.setText(daytaw[indexpos].resName_eng)
        aed_usernamesp.setText(daytaw[indexpos].resName_sp)


        var docc=getRandomString(12)
        abtn_save.setOnClickListener {

            val resourcce = HashMap<String, Any>()
            resourcce["resName_eng"]=aed_username.text.toString()
            resourcce["resName_sp"]=aed_usernamesp.text.toString()
            resourcce["adult"]= daytaw[indexpos].adult
            resourcce["docID"]= daytaw[indexpos].docID
            resourcce["onlinestatus"]=true

            db.collection("songs").document(daytaw[indexpos].docID)
                .set(resourcce)
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    dismiss()
                    activity?.recreate()
                    daytaw.clear()
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
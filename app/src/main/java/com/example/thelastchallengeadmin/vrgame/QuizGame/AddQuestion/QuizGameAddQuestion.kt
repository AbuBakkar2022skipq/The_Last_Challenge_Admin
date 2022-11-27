package com.example.thelastchallengeadmin.vrgame.QuizGame.AddQuestion

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.thelastchallengeadmin.R
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.addquestion_layout.*


class QuizGameAddQuestion: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addquestion_layout)
        var adultt=false
        var correct=0

        val v1 = imageView999
        val v2 = imageView9990
        val v3 = check3
        val v4 = check4

        button4.visibility= INVISIBLE

        v1.setOnClickListener {
            correct=1
            v1.setImageResource(R.drawable.check)
            v2.setImageResource(R.drawable.uncheck)
            v3.setImageResource(R.drawable.uncheck)
            v4.setImageResource(R.drawable.uncheck)
        }

        v2.setOnClickListener {
            correct=2
            v1.setImageResource(R.drawable.uncheck)
            v2.setImageResource(R.drawable.check)
            v3.setImageResource(R.drawable.uncheck)
            v4.setImageResource(R.drawable.uncheck)
        }

        v3.setOnClickListener {
            correct=3
            v1.setImageResource(R.drawable.uncheck)
            v2.setImageResource(R.drawable.uncheck)
            v3.setImageResource(R.drawable.check)
            v4.setImageResource(R.drawable.uncheck)
        }

        v4.setOnClickListener {
            correct=4
            v1.setImageResource(R.drawable.uncheck)
            v2.setImageResource(R.drawable.uncheck)
            v3.setImageResource(R.drawable.uncheck)
            v4.setImageResource(R.drawable.check)
        }

        var docc=getRandomString(12)
        button5.setOnClickListener {
            adultt = (checkBox).isChecked

            val resourcce = HashMap<String, Any>()
            resourcce["question_en"]=textView21.text.toString()
            resourcce["question_sp"]=textView211.text.toString()
            resourcce["option1_en"]=textView22.text.toString()
            resourcce["option1_sp"]=textView2225.text.toString()
            resourcce["option2_en"]=textView229.text.toString()
            resourcce["option2_sp"]=textView2227.text.toString()
            resourcce["option3_en"]=textView22273.text.toString()
            resourcce["option3_sp"]=textView222709.text.toString()
            resourcce["option4_en"]=textView2227309.text.toString()
            resourcce["option4_sp"]=textView22273091.text.toString()
            resourcce["correct"]=correct
            resourcce["adult"]=adultt
            resourcce["docID"]=docc
            resourcce["onlinestatus"]=true

            val db = Firebase.firestore
            db.collection("quizgame").document(docc)
                .set(resourcce)
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

}
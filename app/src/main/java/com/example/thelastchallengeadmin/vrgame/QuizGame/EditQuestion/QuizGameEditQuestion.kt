package com.example.thelastchallengeadmin.vrgame.QuizGame.EditQuestion

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.thelastchallengeadmin.MainActivity.MainActivity
import com.example.thelastchallengeadmin.Objects.recdata
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.QuizGame.shareddataQuiz.modelsdataa
import com.example.thelastchallengeadmin.vrgame.QuizGame.shareddataQuiz.selectedposition
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.addquestion_layout.*

class QuizGameEditQuestion : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addquestion_layout)
        var adultt = false

        var correct = modelsdataa[selectedposition].correct

        val v1 = imageView999
        val v2 = imageView9990
        val v3 = check3
        val v4 = check4

        button5.visibility= View.VISIBLE


        if (correct == 1) {
            v1.setImageResource(R.drawable.check)
            v2.setImageResource(R.drawable.uncheck)
            v3.setImageResource(R.drawable.uncheck)
            v4.setImageResource(R.drawable.uncheck)
        } else if (correct == 2) {
            v1.setImageResource(R.drawable.uncheck)
            v2.setImageResource(R.drawable.check)
            v3.setImageResource(R.drawable.uncheck)
            v4.setImageResource(R.drawable.uncheck)
        } else if (correct == 3) {
            v1.setImageResource(R.drawable.uncheck)
            v2.setImageResource(R.drawable.uncheck)
            v3.setImageResource(R.drawable.check)
            v4.setImageResource(R.drawable.uncheck)
        } else if (correct == 4) {
            v1.setImageResource(R.drawable.uncheck)
            v2.setImageResource(R.drawable.uncheck)
            v3.setImageResource(R.drawable.uncheck)
            v4.setImageResource(R.drawable.check)
        }


        v1.setOnClickListener {
            correct = 1
            v1.setImageResource(R.drawable.check)
            v2.setImageResource(R.drawable.uncheck)
            v3.setImageResource(R.drawable.uncheck)
            v4.setImageResource(R.drawable.uncheck)
        }

        v2.setOnClickListener {
            correct = 2
            v1.setImageResource(R.drawable.uncheck)
            v2.setImageResource(R.drawable.check)
            v3.setImageResource(R.drawable.uncheck)
            v4.setImageResource(R.drawable.uncheck)
        }

        v3.setOnClickListener {
            correct = 3
            v1.setImageResource(R.drawable.uncheck)
            v2.setImageResource(R.drawable.uncheck)
            v3.setImageResource(R.drawable.check)
            v4.setImageResource(R.drawable.uncheck)
        }

        v4.setOnClickListener {
            correct = 4
            v1.setImageResource(R.drawable.uncheck)
            v2.setImageResource(R.drawable.uncheck)
            v3.setImageResource(R.drawable.uncheck)
            v4.setImageResource(R.drawable.check)
        }

        textView21.setText(modelsdataa[selectedposition].question_en)
        textView211.setText(modelsdataa[selectedposition].question_sp)
        textView22.setText(modelsdataa[selectedposition].option1_en)
        textView2225.setText(modelsdataa[selectedposition].option1_sp)
        textView229.setText(modelsdataa[selectedposition].option2_en)
        textView2227.setText(modelsdataa[selectedposition].option2_sp)
        textView22273.setText(modelsdataa[selectedposition].option3_en)
        textView222709.setText(modelsdataa[selectedposition].option3_sp)
        textView2227309.setText(modelsdataa[selectedposition].option4_en)
        textView22273091.setText(modelsdataa[selectedposition].option4_sp)
        checkBox.isChecked = modelsdataa[selectedposition].adult == true

        button4.setOnClickListener {
            db.collection("quizgame").document(modelsdataa[selectedposition].docID)
                .delete()
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully deleted!")
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show()

                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error deleting document", e)
                    Toast.makeText(this, "Error deleting document", Toast.LENGTH_LONG).show()}
        }


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
            resourcce["docID"]=modelsdataa[selectedposition].docID
            resourcce["onlinestatus"]=true

            db.collection("quizgame").document(modelsdataa[selectedposition].docID)
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
}
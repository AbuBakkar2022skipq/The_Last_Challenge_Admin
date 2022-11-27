package com.example.thelastchallengeadmin.addGame

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.thelastchallengeadmin.MainActivity.MainActivity
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.Objects.recdata
import com.example.thelastchallengeadmin.Objects.storeddatagame.baloons
import com.example.thelastchallengeadmin.Objects.storeddatagame.bottles
import com.example.thelastchallengeadmin.Objects.storeddatagame.cards
import com.example.thelastchallengeadmin.Objects.storeddatagame.cookies
import com.example.thelastchallengeadmin.Objects.storeddatagame.cups
import com.example.thelastchallengeadmin.Objects.storeddatagame.gameexplain
import com.example.thelastchallengeadmin.Objects.storeddatagame.gameiconurl
import com.example.thelastchallengeadmin.Objects.storeddatagame.gamename
import com.example.thelastchallengeadmin.Objects.storeddatagame.pingpongballs
import com.example.thelastchallengeadmin.Objects.storeddatagame.punishment
import com.example.thelastchallengeadmin.Objects.storeddatagame.timer
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class finalpage  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.finalpage)
        val db = Firebase.firestore

        val btn = findViewById<Button>(R.id.button4)
        btn.setOnClickListener {
            val txt = findViewById<TextView>(R.id.textView22)
            punishment = txt.text.toString()

            val docid = getRandomString(12)
            val gameinfo = HashMap<String, Any>()
            gameinfo["gamename"] = gamename
            gameinfo["gameexplain"] = gameexplain
            gameinfo["cups"] = cups
            gameinfo["pingpongballs"] = pingpongballs
            gameinfo["bottles"] = bottles
            gameinfo["baloons"] = baloons
            gameinfo["cards"] = cards
            gameinfo["cookies"] = cookies
            gameinfo["punishment"] = punishment
            gameinfo["timer"] = timer
            gameinfo["gameiconurl"] = gameiconurl
            gameinfo["onlinestatus"] = "yes"
            gameinfo["documentId"] = docid

            db.collection("games").document(docid)
                .set(gameinfo)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!")

                    Toast.makeText(this, "Game Saved", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e)
                    Toast.makeText(this, "Error Saving Game", Toast.LENGTH_LONG).show()
                }

            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


        val delete = findViewById<Button>(R.id.button3)
        delete.setOnClickListener {

            db.collection("games").document("${recdata.currentdocumentId}")
                .delete()
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!")
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    Toast.makeText(this, "Game Deleted", Toast.LENGTH_LONG).show()

                }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e)
                    Toast.makeText(this, "Error deleting document", Toast.LENGTH_LONG).show()}
        }

        val timerarray = resources.getStringArray(R.array.timer_array)

        val spinner = findViewById<Spinner>(R.id.button110)
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, timerarray)
        // Set layout to use when the list of choices appear
        spinner.adapter=aa
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //p0 = AdapterView
                //p1 = View
                //p2 = position (for identifying the array position) || Example: timerarray[p2]
                //p3 = ID

                Log.d(TAG, "indexx =" + timerarray[p2]);
                converttotime(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }



    }

    @SuppressLint("SetTextI18n")
    private fun converttotime(s: Int) {
        val txt = findViewById<TextView>(R.id.button10)

        when (s) {
            0 -> {
                timer=0
                txt.text="Timer Off"
            }
            1 -> {
                timer=10
                txt.text="10 Seconds"
            }
            2 -> {
                timer=20
                txt.text="20 Seconds"
            }
            3 -> {
                timer = 30
                txt.text="30 Seconds"
            }
            4 -> {
                timer=40
                txt.text="40 Seconds"
            }
            5 -> {
                timer=50
                txt.text="50 Seconds"
            }
            6 -> {
                timer=60
                txt.text="1 minute"
            }
            7 -> {
                timer=90
                txt.text="1.5 minutes"
            }
            8 -> {
                timer=120
                txt.text="2 minutes"
            }
            9 -> {
                timer=150
                txt.text="2.5 minutes"
            }
            10 -> {
                timer=180
                txt.text="3 minutes"
            }
            11 -> {
                timer=210
                txt.text="3.5 minutes"
            }
            12 -> {
                timer=240
                txt.text="4 minutes"
            }
            13 -> {
                timer=270
                txt.text="4.5 minutes"
            }
            14 -> {
                timer=300
                txt.text="5 minutes"
            }
            15 -> {
                timer=330
                txt.text="5.5 minutes"
            }
            16 -> {
                timer=360
                txt.text="6 minutes"
            }
            17 -> {
                timer=390
                txt.text="6.5 minutes"
            }
            18 -> {
                timer=420
                txt.text="7 minutes"
            }
            19 -> {
                timer=450
                txt.text="7.5 minutes"
            }
            20 -> {
                timer=480
                txt.text="8 minutes"
            }
            21 -> {
                timer=510
                txt.text="8.5 minutes"
            }
            22 -> {
                timer=540
                txt.text="9 minutes"
            }
            23 -> {
                timer=570
                txt.text="9.5 minutes"
            }
            24 -> {
                timer=600
                txt.text="10 minutes"
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



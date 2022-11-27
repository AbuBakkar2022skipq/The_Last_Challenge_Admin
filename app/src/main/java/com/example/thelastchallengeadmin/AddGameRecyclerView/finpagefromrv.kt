package com.example.thelastchallengeadmin.AddGameRecyclerView

import android.annotation.SuppressLint
import android.content.ContentValues
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
import com.example.thelastchallengeadmin.Objects.recdata.currentdocumentId
import com.example.thelastchallengeadmin.Objects.recdata.punishment
import com.example.thelastchallengeadmin.Objects.recdata.timer
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class finpagefromrv : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    var setvalue=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finpagefromrv)
        val db = Firebase.firestore
        Toast.makeText(this, timer.toString(), Toast.LENGTH_SHORT).show()
        Log.d(TAG, "DocumentSnapshot datay : ${recdata.punishment}")
        val edittxt = findViewById<EditText>(R.id.textView22)
        edittxt.setText(punishment)
        val btn = findViewById<Button>(R.id.button4)



        val txt = findViewById<TextView>(R.id.button10)


        btn.setOnClickListener {

            val gameinfo = HashMap<String, Any>()
            gameinfo["gamename"] = recdata.gamename
            gameinfo["gameexplain"] = recdata.gameexplain
            gameinfo["cups"] = recdata.cups
            gameinfo["pingpongballs"] = recdata.pingpongballs
            gameinfo["bottles"] = recdata.bottles
            gameinfo["baloons"] = recdata.baloons
            gameinfo["cards"] = recdata.cards
            gameinfo["cookies"] = recdata.cookies
            gameinfo["punishment"] = recdata.punishment
            gameinfo["timer"] = recdata.timer
            gameinfo["gameiconurl"] = recdata.gameiconurl
            gameinfo["onlinestatus"] = true
            gameinfo["documentId"] = recdata.currentdocumentId

            db.collection("games").document("${recdata.currentdocumentId}")
                .set(gameinfo)
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e) }
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            Toast.makeText(this, "Prefrences Updated", Toast.LENGTH_LONG).show()
        }


        val delete = findViewById<Button>(R.id.button3)
        delete.setOnClickListener {

                db.collection("games").document("$currentdocumentId")
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

                Log.d(ContentValues.TAG, "indexx =" + timerarray[p2]);
                converttotime(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val txtv = findViewById<TextView>(R.id.button10)
        txtv.setOnClickListener{
            spinner.performClick()
        }
    }

    private fun converttotime(s: Int) {
        val txt = findViewById<TextView>(R.id.button10)

        if (s==0){
            recdata.timer =0
            txt.text="Timer Off"
        }
        else if(s==1){
            recdata.timer =10
            txt.text="10 Seconds"
        }
        else if(s==2){
            recdata.timer =20
            txt.text="20 Seconds"
        }
        else if(s==3) {
            recdata.timer = 30
            txt.text="30 Seconds"
        }
        else if(s==4){
            recdata.timer =40
            txt.text="40 Seconds"
        }
        else if(s==5){
            recdata.timer =50
            txt.text="50 Seconds"
        }
        else if(s==6){
            recdata.timer =60
            txt.text="1 minute"
        }
        else if(s==7){
            recdata.timer =90
            txt.text="1.5 minutes"
        }
        else if(s==8){
            recdata.timer =120
            txt.text="2 minutes"
        }
        else if(s==9){
            recdata.timer =150
            txt.text="2.5 minutes"
        }
        else if(s==10){
            recdata.timer =180
            txt.text="3 minutes"
        }
        else if(s==11){
            recdata.timer =210
            txt.text="3.5 minutes"
        }
        else if(s==12){
            recdata.timer =240
            txt.text="4 minutes"
        }
        else if(s==13){
            recdata.timer =270
            txt.text="4.5 minutes"
        }
        else if(s==14){
            recdata.timer =300
            txt.text="5 minutes"
        }
        else if(s==15){
            recdata.timer =330
            txt.text="5.5 minutes"
        }
        else if(s==16){
            recdata.timer =360
            txt.text="6 minutes"
        }
        else if(s==17){
            recdata.timer =390
            txt.text="6.5 minutes"
        }
        else if(s==18){
            recdata.timer =420
            txt.text="7 minutes"
        }
        else if(s==19){
            recdata.timer =450
            txt.text="7.5 minutes"
        }
        else if(s==20){
            recdata.timer =480
            txt.text="8 minutes"
        }
        else if(s==21){
            recdata.timer =510
            txt.text="8.5 minutes"
        }
        else if(s==22){
            recdata.timer =540
            txt.text="9 minutes"
        }
        else if(s==23){
            recdata.timer =570
            txt.text="9.5 minutes"
        }
        else if(s==24){
            recdata.timer =600
            txt.text="10 minutes"
        }
        else {
        }
    }
}

package com.example.thelastchallengeadmin.AddGameRecyclerView

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.thelastchallengeadmin.AddGameRecyclerView.resources.addResourcess
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.GameManagement.game
import com.example.thelastchallengeadmin.GameManagement.gameManagementData.mylistofdata
import com.example.thelastchallengeadmin.GameManagement.gameManagementData.selectedd
import com.example.thelastchallengeadmin.GameManagement.managegamepagepunishment
import com.example.thelastchallengeadmin.MainActivity.MainActivity
import com.example.thelastchallengeadmin.Objects.recdata
import com.example.thelastchallengeadmin.Objects.recdata.baloons
import com.example.thelastchallengeadmin.Objects.recdata.bottles
import com.example.thelastchallengeadmin.Objects.recdata.cards
import com.example.thelastchallengeadmin.Objects.recdata.cookies
import com.example.thelastchallengeadmin.Objects.recdata.cups
import com.example.thelastchallengeadmin.Objects.recdata.currentdocumentId
import com.example.thelastchallengeadmin.Objects.recdata.docidforrv
import com.example.thelastchallengeadmin.Objects.recdata.gameexplain
import com.example.thelastchallengeadmin.Objects.recdata.gameiconurl
import com.example.thelastchallengeadmin.Objects.recdata.gamename
import com.example.thelastchallengeadmin.Objects.recdata.pingpongballs
import com.example.thelastchallengeadmin.Objects.recdata.punishment
import com.example.thelastchallengeadmin.Objects.recdata.timer
import com.example.thelastchallengeadmin.Objects.storeddatagame
import com.example.thelastchallengeadmin.SelectResources.selectedResourcesData
import com.example.thelastchallengeadmin.addGame.addpunishment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add_game_from_rv.*
import kotlinx.android.synthetic.main.activity_add_game_from_rv.button10
import kotlinx.android.synthetic.main.activity_add_game_from_rv.button100000
import kotlinx.android.synthetic.main.activity_add_game_from_rv.textView21
import kotlinx.android.synthetic.main.activity_add_game_from_rv.textView211
import kotlinx.android.synthetic.main.activity_add_game_from_rv.textView22
import kotlinx.android.synthetic.main.activity_add_game_from_rv.textView222
import kotlinx.android.synthetic.main.activity_add_game_from_rv.textView2227
import kotlinx.android.synthetic.main.activity_add_game_from_rv.textView229
import kotlinx.android.synthetic.main.addpunishment.*

class add_game_from_rv : AppCompatActivity() {
    private var pickImage = 100
    private var imageUri: Uri? = null
    
    var isFragmentLoaded=true
    val db = Firebase.firestore

    val mgr=supportFragmentManager


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game_from_rv)

        val gamenameen = findViewById<EditText>(R.id.textView21)
        val gamenamesp = findViewById<EditText>(R.id.textView211)
        val expEn = findViewById<EditText>(R.id.textView22)
        val expSp = findViewById<EditText>(R.id.textView2225)
        val expEn18 = findViewById<EditText>(R.id.textView229)
        val expSp18 = findViewById<EditText>(R.id.textView2227)

        if (mylistofdata[selectedd].onlinestatus==true) {
            gs.setImageResource(R.drawable.active)
        }

        else if (mylistofdata[selectedd].onlinestatus==false) {
            gs.setImageResource(R.drawable.inactive)
        }

        gs.setOnClickListener {
            if (mylistofdata[selectedd].onlinestatus==true) {
                mylistofdata[selectedd].onlinestatus=false
                gs.setImageResource(R.drawable.inactive)
            }

            else if (mylistofdata[selectedd].onlinestatus==false) {
                mylistofdata[selectedd].onlinestatus=true
                gs.setImageResource(R.drawable.active)
            }
        }

        gamenameen.setText(mylistofdata[selectedd].gameNameEn)
        gamenamesp.setText(mylistofdata[selectedd].gameNameSp)
        expEn.setText(mylistofdata[selectedd].gameExplainEn)
        expSp.setText(mylistofdata[selectedd].gameExplainSp)
        expEn18.setText(mylistofdata[selectedd].gameExplainEn18)
        expSp18.setText(mylistofdata[selectedd].gameExplainSp18)
        val v = mylistofdata[selectedd].timer.toString()
        button100000.text = "$v Seconds"

        val btn5= findViewById<Button>(R.id.button5)
        btn5.setOnClickListener {
            getimg()
        }

        val btn = findViewById<Button>(R.id.button4)
        btn.setOnClickListener{
            setdata()

        }

        button10.setOnClickListener {
            val intent = Intent(this, addResourcess::class.java)
            startActivity(intent)
        }
        val timerarray = resources.getStringArray(R.array.timer_array)

        val spinner = findViewById<Spinner>(R.id.button11100)
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, timerarray)

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
        val txtv = findViewById<TextView>(R.id.button100000)
        txtv.setOnClickListener{
            spinner.performClick()
        }
    }

    private fun setdata() {
        //set random string as doc ID to make it eas to fetch it later

        val gameinfo = HashMap<String, Any>()
        gameinfo["gameNameEn"] = textView21.text.toString()
        gameinfo["gameNameSp"] = textView211.text.toString()
        gameinfo["gameExplainEn"] = textView22.text.toString()
        gameinfo["gameExplainSp"] = textView222.text.toString()
        gameinfo["gameExplainEn18"] = textView229.text.toString()
        gameinfo["gameExplainSp18"] = textView2227.text.toString()
        gameinfo["timer"] = timer.toInt()
        gameinfo["get"] = true
        gameinfo["gameiconurl"] = recdata.gameiconurl
        gameinfo["onlinestatus"] = mylistofdata[selectedd].onlinestatus
        gameinfo["documentId"] = mylistofdata[selectedd].documentId
        gameinfo["resources"]= LocalData.listOfModelzzz

        //Uploading Data to Firestore
        db.collection("games").document(mylistofdata[selectedd].documentId)
            .set(gameinfo)
            .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                val intent = Intent(applicationContext, managegamepagepunishment::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                Toast.makeText(this, "Game Saved", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e)
                Toast.makeText(this, "Error Saving Game", Toast.LENGTH_LONG).show()
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
        startActivityForResult(photoPickerIntent, pickImage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            Toast.makeText(this, "Image Selected Sucessfully", Toast.LENGTH_LONG).show()
            uploadImageToFirebaseStorage()
        }
    }

    private fun uploadImageToFirebaseStorage() {
        val ref = FirebaseStorage.getInstance().reference.child("PhysicalGame")
        if (imageUri == null) return
        ref.putFile(imageUri!!)
            .addOnSuccessListener {
                Toast.makeText(this, "Starting Upload", Toast.LENGTH_SHORT).show()

                Log.d(ContentValues.TAG, "Successfully uploaded image: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    recdata.gameiconurl =it.toString()
                    Log.d(ContentValues.TAG, "Successyully uploaded image: ${storeddatagame.gameiconurl}")

                    Toast.makeText(this, "Image Uploaded Successfully", Toast.LENGTH_LONG).show()

                }
                    .addOnFailureListener {
                        Log.d("TAG", "Failed to upload image to storage: ${it.message}")
                        Toast.makeText(this, "Not Uploaded", Toast.LENGTH_LONG).show()

                    }
            }
        Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show()
    }

    private fun converttotime(s: Int) {
        val txt = findViewById<TextView>(R.id.button100000)

        if (s==0){
            storeddatagame.timer =0
            storeddatagame.timerenabled =false
            txt.text="Timer"
        }
        else if(s==1){
            storeddatagame.timer =10
            storeddatagame.timerenabled =true
            txt.text="10 Seconds"
        }
        else if(s==2){
            storeddatagame.timer =20
            storeddatagame.timerenabled =true
            txt.text="20 Seconds"
        }
        else if(s==3) {
            storeddatagame.timer = 30
            storeddatagame.timerenabled =true
            txt.text="30 Seconds"
        }
        else if(s==4){
            storeddatagame.timerenabled =true
            storeddatagame.timer =40
            txt.text="40 Seconds"
        }
        else if(s==5){
            storeddatagame.timerenabled =true
            storeddatagame.timer =50
            txt.text="50 Seconds"
        }
        else if(s==6){
            storeddatagame.timerenabled =true
            storeddatagame.timer =60
            txt.text="1 minute"
        }
        else if(s==7){
            storeddatagame.timer =90
            storeddatagame.timerenabled =true
            txt.text="1.5 minutes"
        }
        else if(s==8){
            storeddatagame.timer =120
            storeddatagame.timerenabled =true
            txt.text="2 minutes"
        }
        else if(s==9){
            storeddatagame.timer =150
            storeddatagame.timerenabled =true
            txt.text="2.5 minutes"
        }
        else if(s==10){
            storeddatagame.timer =180
            storeddatagame.timerenabled =true
            txt.text="3 minutes"
        }
        else if(s==11){
            storeddatagame.timerenabled =true
            storeddatagame.timer =210
            txt.text="3.5 minutes"
        }
        else if(s==12){
            storeddatagame.timerenabled =true
            storeddatagame.timer =240
            txt.text="4 minutes"
        }
        else if(s==13){
            storeddatagame.timerenabled =true
            storeddatagame.timer =270
            txt.text="4.5 minutes"
        }
        else if(s==14){
            storeddatagame.timerenabled =true
            storeddatagame.timer =300
            txt.text="5 minutes"
        }
        else if(s==15){
            storeddatagame.timerenabled =true
            storeddatagame.timer =330
            txt.text="5.5 minutes"
        }
        else if(s==16){
            storeddatagame.timerenabled =true
            storeddatagame.timer =360
            txt.text="6 minutes"
        }
        else if(s==17){
            storeddatagame.timerenabled =true
            storeddatagame.timer =390
            txt.text="6.5 minutes"
        }
        else if(s==18){
            storeddatagame.timerenabled =true
            storeddatagame.timer =420
            txt.text="7 minutes"
        }
        else if(s==19){
            storeddatagame.timerenabled =true
            storeddatagame.timer =450
            txt.text="7.5 minutes"
        }
        else if(s==20){
            storeddatagame.timerenabled =true
            storeddatagame.timer =480
            txt.text="8 minutes"
        }
        else if(s==21){
            storeddatagame.timer =510
            storeddatagame.timerenabled =true
            txt.text="8.5 minutes"
        }
        else if(s==22){
            storeddatagame.timer =540
            storeddatagame.timerenabled =true
            txt.text="9 minutes"
        }
        else if(s==23){
            storeddatagame.timerenabled =true
            storeddatagame.timer =570
            txt.text="9.5 minutes"
        }
        else if(s==24){
            storeddatagame.timerenabled =true
            storeddatagame.timer =600
            txt.text="10 minutes"
        }
        else {
        }
    }

}

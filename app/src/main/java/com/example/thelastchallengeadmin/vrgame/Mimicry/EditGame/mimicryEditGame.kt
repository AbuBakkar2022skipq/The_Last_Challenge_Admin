package com.example.thelastchallengeadmin.vrgame.Mimicry.EditGame

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.thelastchallengeadmin.MainActivity.MainActivity
import com.example.thelastchallengeadmin.Objects.storeddatagame
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.SelectResources.selectResources
import com.example.thelastchallengeadmin.SelectResources.selectedResourcesData
import com.example.thelastchallengeadmin.vrgame.Mimicry.EditGame.SelectResources.selectWords
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata.selectedwordss
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.addpunishment.*

class mimicryEditGame : AppCompatActivity() {
    private var pickImage = 100
    private var imageUri: Uri? = null

    var isFragmentLoaded=true
    val db = Firebase.firestore

    val mgr=supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mimicry_edit_game)
        val timerarray = resources.getStringArray(R.array.timer_array)

        val spinner = findViewById<Spinner>(R.id.button11100)
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, timerarray)

        //Add Resources from List
        /*val addresources= findViewById<Button>(R.id.button10)
        addresources.setOnClickListener {
            val intent = Intent(this, selectWords::class.java)
            startActivity(intent)
        }*/

        //Get Image Icon
        val btn5= findViewById<Button>(R.id.button5)
        btn5.setOnClickListener {
            getimg()
        }

        //set Data and Upload to Firestore
        val btn = findViewById<Button>(R.id.button4)
        btn.setOnClickListener{
            setvalues()
        }

        //Spinner Implementation for Timer
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

    private fun setvalues() {
        //set random string as doc ID to make it eas to fetch it later
        val docid = getRandomString(12)

        val gameinfo = HashMap<String, Any>()
        gameinfo["gameNameEn"] = textView21.text.toString()
        gameinfo["gameNameSp"] = textView211.text.toString()
        gameinfo["gameExplainEn"] = textView22.text.toString()
        gameinfo["gameExplainSp"] = textView222.text.toString()
        gameinfo["gameExplainEn18"] = textView229.text.toString()
        gameinfo["gameExplainSp18"] = textView2227.text.toString()
        gameinfo["timer"] = storeddatagame.timer.toInt()
        gameinfo["gameiconurl"] = storeddatagame.gameiconurl.toString()
        gameinfo["onlinestatus"] = true
        gameinfo["documentId"] = "mimicry"
        //gameinfo["resources"]= selectedwordss
        gameinfo["get"] = true

        //Uploading Data to Firestore
        db.collection("mimicry").document("mimicry")
            .set(gameinfo)
            .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")

                Toast.makeText(this, "Game Saved", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e)
                Toast.makeText(this, "Error Saving Game", Toast.LENGTH_LONG).show()
            }

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun getimg() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, 100)
    }

    @SuppressLint("SetTextI18n")
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

    override fun onActivityResult(requesCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(100, resultCode, data)
        if (resultCode == RESULT_OK && 100 == pickImage) {

            imageUri = data?.data
            uploadImageToFirebaseStorage()
        }
    }

    fun getRandomString(length: Int) : String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    private fun uploadImageToFirebaseStorage() {
        val ref = FirebaseStorage.getInstance().reference.child("Mimicry")
        if (imageUri == null) return
        ref.putFile(imageUri!!)
            .addOnSuccessListener {
                Toast.makeText(this, "Starting Upload", Toast.LENGTH_SHORT).show()

                Log.d(ContentValues.TAG, "Successfully uploaded image: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    storeddatagame.gameiconurl =it.toString()
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
}

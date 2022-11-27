package com.example.thelastchallengeadmin.Punishments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.Punishments.AddPunishments.AddPunishments
import com.example.thelastchallengeadmin.Punishments.EditPunishments.EditPunishments
import com.example.thelastchallengeadmin.Punishments.RecyclerView.PunishmentsDataModel
import com.example.thelastchallengeadmin.Punishments.RecyclerView.PunishmentsRecyclerViewAdapter
import com.example.thelastchallengeadmin.Punishments.sharedDataPunishments.datamodell
import com.example.thelastchallengeadmin.Punishments.sharedDataPunishments.indexxx
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.QuizGame.AddQuestion.QuizGameAddQuestion
import com.example.thelastchallengeadmin.vrgame.QuizGame.EditQuestion.QuizGameEditQuestion
import com.example.thelastchallengeadmin.vrgame.QuizGame.RecyclerView.QuizGameRecyclerViewAdapter
import com.example.thelastchallengeadmin.vrgame.QuizGame.RecyclerView.QuizGameRecyclerViewDataclasss
import com.example.thelastchallengeadmin.vrgame.QuizGame.shareddataQuiz
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.punishments_menu.*
import kotlinx.android.synthetic.main.quiz_game_view_questions.*
import kotlinx.android.synthetic.main.quiz_game_view_questions.button8
import java.util.ArrayList

class PunishmentsMenu : AppCompatActivity() , PunishmentsRecyclerViewAdapter.OnItemClickListener {
    val db = Firebase.firestore
    private val listOfModels = ArrayList<PunishmentsDataModel>()
    private var adapter: PunishmentsRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.punishments_menu)
        initrecyclerview()
        readPropertiesData()
        button8.setOnClickListener {
            val intent = Intent(this, AddPunishments::class.java)
            startActivity(intent)
        }

        button6.setOnClickListener {
            finish()
        }
    }


    private fun initrecyclerview(){
        adapter = PunishmentsRecyclerViewAdapter(listOfModels,this)
        val recyclerview : RecyclerView = findViewById(R.id.rvvv7701)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun readPropertiesData() {
        val db = FirebaseFirestore.getInstance()
        val citiesRef = db.collection("punishments").whereEqualTo("onlinestatus", true)
        citiesRef.addSnapshotListener { value, error ->
            if (error == null) {
                value?.let { snapshots ->
                    for (dc in snapshots.documentChanges) {
                        @Suppress("NON_EXHAUSTIVE_WHEN")
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> {
                                var dcc = dc.document.toObject(PunishmentsDataModel::class.java)
                                dcc.let {
                                    Log.i("tag", "$it")
                                    listOfModels.add(it)
                                    datamodell.add(it)

                                    dcc
                                }
                                adapter?.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        }
    }


    override fun onItemClick(position: Int, documentId: String?) {
        indexxx =position
        val intent = Intent(this, EditPunishments::class.java)
        startActivity(intent)
    }
}
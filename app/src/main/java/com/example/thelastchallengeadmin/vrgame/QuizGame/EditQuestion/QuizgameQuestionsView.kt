package com.example.thelastchallengeadmin.vrgame.QuizGame.EditQuestion

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.ForbiddenWords.forbiddenwordsmenu
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.BottomSheetAddWords.bottomSheetAddWords
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.RecyclerView.BottomSheetWrtParent.BottomsheetEditWord
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.RecyclerView.RecyclerViewAdapterForWords
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.RecyclerView.recyclerViewDataModel
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata
import com.example.thelastchallengeadmin.vrgame.QuizGame.AddQuestion.QuizGameAddQuestion
import com.example.thelastchallengeadmin.vrgame.QuizGame.EditDetails.QuizGameDetailsEdit
import com.example.thelastchallengeadmin.vrgame.QuizGame.RecyclerView.QuizGameRecyclerViewAdapter
import com.example.thelastchallengeadmin.vrgame.QuizGame.RecyclerView.QuizGameRecyclerViewDataclasss
import com.example.thelastchallengeadmin.vrgame.QuizGame.shareddataQuiz.modelsdataa
import com.example.thelastchallengeadmin.vrgame.QuizGame.shareddataQuiz.selectedposition
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_mimicry_menu.*
import kotlinx.android.synthetic.main.add_resources.*
import kotlinx.android.synthetic.main.quiz_game_view_questions.*
import kotlinx.android.synthetic.main.quiz_game_view_questions.button8
import java.util.ArrayList

class QuizgameQuestionsView : AppCompatActivity(), QuizGameRecyclerViewAdapter.OnItemClickListener {
    val db = Firebase.firestore
    private val listOfModels = ArrayList<QuizGameRecyclerViewDataclasss>()
    private var adapter: QuizGameRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_game_view_questions)
        initrecyclerview()
        readPropertiesData()
        button8.setOnClickListener {
            val intent = Intent(this, QuizGameAddQuestion::class.java)
            startActivity(intent)
        }

        button69.setOnClickListener {
            finish()
        }
    }


    private fun initrecyclerview(){
        adapter = QuizGameRecyclerViewAdapter(listOfModels,this)
        val recyclerview : RecyclerView = findViewById(R.id.rvvv77p)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun readPropertiesData() {
        val db = FirebaseFirestore.getInstance()
        val citiesRef = db.collection("quizgame").whereEqualTo("onlinestatus", true)
        citiesRef.addSnapshotListener { value, error ->
            if (error == null) {
                value?.let { snapshots ->
                    for (dc in snapshots.documentChanges) {
                        @Suppress("NON_EXHAUSTIVE_WHEN")
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> {
                                var dcc = dc.document.toObject(QuizGameRecyclerViewDataclasss::class.java)
                                dcc.let {
                                    Log.i("tag", "$it")
                                    listOfModels.add(it)
                                    modelsdataa.add(it)

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
        selectedposition=position
        val intent = Intent(this, QuizGameEditQuestion::class.java)
        startActivity(intent)
    }
}
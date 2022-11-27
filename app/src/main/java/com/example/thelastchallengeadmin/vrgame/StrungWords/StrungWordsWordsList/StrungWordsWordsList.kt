package com.example.thelastchallengeadmin.vrgame.StrungWords.StrungWordsWordsList

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.DrawingGame.DrawingGameWords.RecyclerView.recycleViewAdapterDrawingGame
import com.example.thelastchallengeadmin.vrgame.Mimicry.EditGame.SelectResources.RecyclerView.dataclassrecyclerViewwordss
import com.example.thelastchallengeadmin.vrgame.Songs.AddSong.AddSong
import com.example.thelastchallengeadmin.vrgame.Songs.EditSong.EditSong
import com.example.thelastchallengeadmin.vrgame.Songs.sharedDataSongsGame
import com.example.thelastchallengeadmin.vrgame.StrungWords.AddStrungWord.AddWordd
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.drawing_game_words_list.*
import java.util.ArrayList

class StrungWordsWordsList: AppCompatActivity() ,  recycleViewAdapterDrawingGame.OnItemClickListener {

    val db = Firebase.firestore
    private val listOfModels = ArrayList<dataclassrecyclerViewwordss>()
    private var adapter: recycleViewAdapterDrawingGame? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_list)
        textView20.text="Strung Words"
        button8.text="Add Word"
        initrecyclerview()
        readPropertiesData()

        button8.setOnClickListener {
            AddWordd().apply {
                show(supportFragmentManager, AddWordd.TAG)
            }
        }
    }

    override fun onItemClick(position: Int, documentId: String?, editt: ImageView) {
        val av = listOfModels[position].docID.toString()
        sharedDataSongsGame.indyx = position
        EditSong().apply {
            show(supportFragmentManager, EditSong.TAG)
        }
    }

    private fun initrecyclerview(){
        adapter = recycleViewAdapterDrawingGame(listOfModels,this)
        val recyclerview : RecyclerView = findViewById(R.id.rvvv77t)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun readPropertiesData() {
        val db = FirebaseFirestore.getInstance()
        val citiesRef = db.collection("strungWords").whereEqualTo("onlinestatus", true)
        citiesRef.addSnapshotListener { value, error ->
            if (error == null) {
                value?.let { snapshots ->
                    for (dc in snapshots.documentChanges) {
                        @Suppress("NON_EXHAUSTIVE_WHEN")
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> {
                                var dcc =
                                    dc.document.toObject(dataclassrecyclerViewwordss::class.java)
                                dcc.let {
                                    Log.i("tag", "$it")
                                    listOfModels.add(it)
                                    sharedDataSongsGame.dayta.add(it)
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
}
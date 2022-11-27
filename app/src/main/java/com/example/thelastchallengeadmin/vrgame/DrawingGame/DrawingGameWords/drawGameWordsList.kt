package com.example.thelastchallengeadmin.vrgame.DrawingGame.DrawingGameWords

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.DrawingGame.AddWords.AddWords
import com.example.thelastchallengeadmin.vrgame.DrawingGame.DrawingGameWords.RecyclerView.recycleViewAdapterDrawingGame
import com.example.thelastchallengeadmin.vrgame.DrawingGame.DrawingGameWords.shareddatadrawingGame.dataa
import com.example.thelastchallengeadmin.vrgame.DrawingGame.DrawingGameWords.shareddatadrawingGame.positionnnn
import com.example.thelastchallengeadmin.vrgame.DrawingGame.EditWords.EditWords
import com.example.thelastchallengeadmin.vrgame.ForbiddenWords.EditForbiddenWords.BottomsheetAddForbiddenWords.botomsheetaddforbiddenwords
import com.example.thelastchallengeadmin.vrgame.ForbiddenWords.forbiddenwordsshareddata
import com.example.thelastchallengeadmin.vrgame.Mimicry.EditGame.SelectResources.RecyclerView.RecyclerViewAdapterWordss
import com.example.thelastchallengeadmin.vrgame.Mimicry.EditGame.SelectResources.RecyclerView.dataclassrecyclerViewwordss
import com.example.thelastchallengeadmin.vrgame.Mimicry.MimicryMenu
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_select_words.*
import kotlinx.android.synthetic.main.drawing_game_words_list.*
import java.util.ArrayList


class drawGameWordsList : AppCompatActivity(),  recycleViewAdapterDrawingGame.OnItemClickListener {

    val db = Firebase.firestore
    private val listOfModels = ArrayList<dataclassrecyclerViewwordss>()
    private var adapter: recycleViewAdapterDrawingGame? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawing_game_words_list)
        initrecyclerview()
        readPropertiesData()

        button8.setOnClickListener {
            AddWords().apply {
                show(supportFragmentManager, AddWords.TAG)
            }
        }
    }

    override fun onItemClick(position: Int, documentId: String?, editt: ImageView) {
        val av = listOfModels[position].docID.toString()
        positionnnn= position
        EditWords().apply {
            show(supportFragmentManager, EditWords.TAG)
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
        val citiesRef = db.collection("drawingwords").whereEqualTo("onlinestatus", true)
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
                                    dataa.add(it)
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
package com.example.thelastchallengeadmin.vrgame.ForbiddenWords.EditForbiddenWords

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.ForbiddenWords.EditForbiddenWords.BottomsheetAddForbiddenWords.botomsheetaddforbiddenwords
import com.example.thelastchallengeadmin.vrgame.ForbiddenWords.EditForbiddenWords.BottomsheetAddForbiddenWords.bottomsheeteditwords
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.BottomSheetAddWords.bottomSheetAddWords
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.RecyclerView.BottomSheetWrtParent.BottomsheetEditWord
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.RecyclerView.RecyclerViewAdapterForWords
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.RecyclerView.recyclerViewDataModel
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_edit_forbidden_words.*
import kotlinx.android.synthetic.main.add_resources.*
import java.util.ArrayList

class editForbiddenWords : AppCompatActivity(), RecyclerViewAdapterForWords.OnItemClickListener {
    val db = Firebase.firestore
    private val listOfModels = ArrayList<recyclerViewDataModel>()
    private var adapter: RecyclerViewAdapterForWords? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_forbidden_words)
        initrecyclerview()
        readPropertiesData()
        qbutton8.setOnClickListener {
            botomsheetaddforbiddenwords().apply {
                show(supportFragmentManager, botomsheetaddforbiddenwords.TAG)
            }
        }
    }


    private fun initrecyclerview(){
        adapter = RecyclerViewAdapterForWords(listOfModels,this)
        val recyclerview : RecyclerView = findViewById(R.id.qrvvv77)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun readPropertiesData() {
        val db = FirebaseFirestore.getInstance()
        val citiesRef = db.collection("forbidden").whereEqualTo("onlinestatus", true)
        citiesRef.addSnapshotListener { value, error ->
            if (error == null) {
                value?.let { snapshots ->
                    for (dc in snapshots.documentChanges) {
                        @Suppress("NON_EXHAUSTIVE_WHEN")
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> {
                                var dcc = dc.document.toObject(recyclerViewDataModel::class.java)
                                dcc.let {
                                    Log.i("tag", "$it")
                                    listOfModels.add(it)
                                    mimicryshareddata.modelsdata.add(it)
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
        var v1 = mimicryshareddata.modelsdata[position].docID.toString()
        mimicryshareddata.docID2 = v1
        mimicryshareddata.resName_eng2 = mimicryshareddata.modelsdata[position].resName_eng
        mimicryshareddata.resName_sp2 = mimicryshareddata.modelsdata[position].resName_sp
        mimicryshareddata.adult2 = mimicryshareddata.modelsdata[position].adult
        mimicryshareddata.selectedposition =position

        bottomsheeteditwords().apply {
            show(supportFragmentManager, bottomsheeteditwords.TAG)
        }
    }
}
package com.example.thelastchallengeadmin.vrgame.Mimicry.Words

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.resourceAddition.ResourceAdditionFromRecyclerView.bottomsheetforrecyclerview
import com.example.thelastchallengeadmin.resourceAddition.bottomsheet_for_add_resource
import com.example.thelastchallengeadmin.resourceAddition.recyclerviewforresourcespage
import com.example.thelastchallengeadmin.resourceAddition.recyclerviewresourcesadapter
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.BottomSheetAddWords.bottomSheetAddWords
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.RecyclerView.BottomSheetWrtParent.BottomsheetEditWord
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.RecyclerView.RecyclerViewAdapterForWords
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.RecyclerView.recyclerViewDataModel
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata.adult2
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata.docID2
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata.modelsdata
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata.resName_eng2
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata.resName_sp2
import com.example.thelastchallengeadmin.vrgame.Mimicry.mimicryshareddata.selectedposition
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.add_resources.*
import kotlinx.android.synthetic.main.bottomsheet_add_words.*
import java.util.ArrayList

class words : AppCompatActivity() , RecyclerViewAdapterForWords.OnItemClickListener {
    val db = Firebase.firestore
    private val listOfModels = ArrayList<recyclerViewDataModel>()
    private var adapter: RecyclerViewAdapterForWords? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)
        initrecyclerview()
        readPropertiesData()
        button8.setOnClickListener {
            bottomSheetAddWords().apply {
                show(supportFragmentManager, bottomSheetAddWords.TAG)
            }
        }
    }


    private fun initrecyclerview(){
        adapter = RecyclerViewAdapterForWords(listOfModels,this)
        val recyclerview : RecyclerView = findViewById(R.id.rvvv77)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun readPropertiesData() {
        val db = FirebaseFirestore.getInstance()
        val citiesRef = db.collection("words").whereEqualTo("onlinestatus", true)
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
                                    modelsdata.add(it)
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
        var v1 = modelsdata[position].docID.toString()
        docID2 = v1
        resName_eng2 = modelsdata[position].resName_eng
        resName_sp2 = modelsdata[position].resName_sp
        adult2 = modelsdata[position].adult
        selectedposition=position

        BottomsheetEditWord().apply {
            show(supportFragmentManager, BottomsheetEditWord.TAG)
        }
    }
}
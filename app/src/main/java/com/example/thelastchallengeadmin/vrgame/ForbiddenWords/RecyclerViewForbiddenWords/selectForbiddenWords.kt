package com.example.thelastchallengeadmin.vrgame.ForbiddenWords.RecyclerViewForbiddenWords

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.vrgame.ForbiddenWords.forbiddenwordsshareddata
import com.example.thelastchallengeadmin.vrgame.Mimicry.EditGame.SelectResources.RecyclerView.RecyclerViewAdapterWordss
import com.example.thelastchallengeadmin.vrgame.Mimicry.EditGame.SelectResources.RecyclerView.dataclassrecyclerViewwordss
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_select_words.*
import java.util.ArrayList

class selectForbiddenWords : AppCompatActivity() , RecyclerViewAdapterWordss.OnItemClickListener {
    val db = Firebase.firestore
    private val listOfModels = ArrayList<dataclassrecyclerViewwordss>()
    private var adapter: RecyclerViewAdapterWordss? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_words)
        initrecyclerview()
        readPropertiesData()

        button6.setOnClickListener {
            finish()
        }
    }

    override fun onItemClick(position: Int, documentId: String?, editt: ImageView) {
        val av = listOfModels[position].docID.toString()
        var found=false
        var indexx:Int=0
        val data=listOfModels[position]
        for (i in 0 until forbiddenwordsshareddata.selectedwordss.size) {
            if (forbiddenwordsshareddata.selectwordsdata[i].docID == av) {
                found=true
                indexx=i
            }
        }

        if(found) {
            forbiddenwordsshareddata.selectedwordss.removeAt(indexx)
            editt.setImageResource(R.drawable.uncheck)
        }
        else if(!found){
            forbiddenwordsshareddata.selectedwordss.add(
                listOfModels[position]
            )
            editt.setImageResource(R.drawable.check)
        }
    }

    private fun initrecyclerview(){
        adapter = RecyclerViewAdapterWordss(listOfModels,this)
        val recyclerview : RecyclerView = findViewById(R.id.rvvv79)
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
                                var dcc =
                                    dc.document.toObject(dataclassrecyclerViewwordss::class.java)
                                dcc.let {
                                    Log.i("tag", "$it")
                                    listOfModels.add(it)
                                    forbiddenwordsshareddata.selectwordsdata.add(it)
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
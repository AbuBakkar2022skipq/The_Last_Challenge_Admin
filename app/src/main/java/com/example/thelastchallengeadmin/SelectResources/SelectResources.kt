package com.example.thelastchallengeadmin.SelectResources

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.SelectResources.selectedResourcesData.listOfModelsdata
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.selectresources.*


class selectResources : AppCompatActivity(), selectresourcesRecyclerViewAdapter.OnItemClickListener {
    val db = Firebase.firestore
    private val listOfModelsss = ArrayList<selectresourcesrecyclerviewmodel>()
    private var adapter: selectresourcesRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selectresources)
        initrecyclerview()
        readPropertiesData()

        button6.setOnClickListener {
            finish()
        }
    }

    override fun onItemClick(position: Int, documentId: String?, checkboxx:ImageView) {

        val av = listOfModelsss[position].docID.toString()
        var found=false
        var indexx:Int=0
        val data=listOfModelsss[position]
        for (i in 0 until listOfModelsdata.size) {
            if (listOfModelsdata[i].docID == av) {
                found=true
                indexx=i
            }
        }

        if(found) {
            listOfModelsdata.removeAt(indexx)
            checkboxx.setBackgroundResource(R.drawable.uncheck)
        }
        else if(!found){
            listOfModelsdata.add(
                listOfModelsss[position]
            )
            checkboxx.setBackgroundResource(R.drawable.check)
        }
    }

    private fun initrecyclerview(){
        adapter = selectresourcesRecyclerViewAdapter(listOfModelsss,this)
        val recyclerview : RecyclerView = findViewById(R.id.recycle1)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun readPropertiesData() {
        val db = FirebaseFirestore.getInstance()
        val citiesRef = db.collection("resources").whereEqualTo("onlinestatus", true)
        citiesRef.addSnapshotListener { value, error ->
            if (error == null) {
                value?.let { snapshots ->
                    for (dc in snapshots.documentChanges) {
                        @Suppress("NON_EXHAUSTIVE_WHEN")
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> {
                                var dcc = dc.document.toObject(selectresourcesrecyclerviewmodel::class.java)
                                dcc.let {
                                    Log.i("tag", "$it")
                                    listOfModelsss.add(it)
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
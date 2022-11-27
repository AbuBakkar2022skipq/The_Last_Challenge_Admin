package com.example.thelastchallengeadmin.AddGameRecyclerView.resources

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.AddGameRecyclerView.LocalData.listOfModelzzz
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.SelectResources.selectedResourcesData
import com.example.thelastchallengeadmin.SelectResources.selectresourcesRecyclerViewAdapter
import com.example.thelastchallengeadmin.SelectResources.selectresourcesrecyclerviewmodel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.selectresources.*

class addResourcess: AppCompatActivity(), ResourcesRecyclerViewWrtAdapter.OnItemClickListener {
    val db = Firebase.firestore
    private val listOfModely = ArrayList<recyclerViewResourcesDataModel>()
    private var adapter: ResourcesRecyclerViewWrtAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selectresources)
        initrecyclerview()
        readPropertiesData()

        button6.setOnClickListener {
            finish()
        }
    }

    override fun onItemClick(position: Int, documentId: String?, checkboxx: ImageView) {

        val av = listOfModely[position].docID.toString()
        var found=false
        var indexx:Int=0
        for (i in 0 until listOfModelzzz.size) {
            if (listOfModelzzz[i].docID == av) {
                found=true
                indexx=i

        }}
            if(found) {
                listOfModelzzz.removeAt(indexx)
                checkboxx.setBackgroundResource(R.drawable.uncheck)
            }

            else if(!found){
                listOfModelzzz.add(
                    listOfModely[indexx]
                )
                checkboxx.setBackgroundResource(R.drawable.check)
            }
    }

    private fun initrecyclerview(){
        adapter = ResourcesRecyclerViewWrtAdapter(listOfModely,this)
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
                                var dcc = dc.document.toObject(recyclerViewResourcesDataModel::class.java)
                                dcc.let {
                                    Log.i("tag", "$it")
                                    listOfModely.add(it)
                                    listOfModelzzz.add(it)
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
package com.example.thelastchallengeadmin.resourceAddition

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.R
import com.example.thelastchallengeadmin.resourceAddition.ResourceAdditionFromRecyclerView.add_resourcesRecyclerView
import com.example.thelastchallengeadmin.resourceAddition.ResourceAdditionFromRecyclerView.bottomsheetforrecyclerview
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata.docID1
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata.resIconURL1
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata.resName_eng1
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata.resName_sp1
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata.resdesc_en1
import com.example.thelastchallengeadmin.resourceAddition.resourcesdata.resdesc_sp1
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.add_resources.*
import java.util.ArrayList


class add_resources : AppCompatActivity(), recyclerviewforresourcespage.OnItemClickListener {
    val db = Firebase.firestore
    private val listOfModels = ArrayList<recyclerviewresourcesadapter>()
    private var adapter: recyclerviewforresourcespage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_resources)
        initrecyclerview()
        readPropertiesData()
        button8.setOnClickListener {
            bottomsheet_for_add_resource().apply {
                show(supportFragmentManager, bottomsheet_for_add_resource.TAG)
            }
        }
    }


    private fun initrecyclerview() {
        adapter = recyclerviewforresourcespage(listOfModels, this)
        val recyclerview: RecyclerView = findViewById(R.id.rvvv1)
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
                                var dcc =
                                    dc.document.toObject(recyclerviewresourcesadapter::class.java)
                                dcc.let {
                                    Log.i("tag", "$it")
                                    listOfModels.add(it)
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

    fun getRandomString(length: Int): String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    override fun onItemClick(position: Int, documentId: String?) {
        var v1 = listOfModels[position].docID.toString()
        docID1 = v1
        resName_eng1 = listOfModels[position].resName_eng
        resName_sp1 = listOfModels[position].resName_sp
        resdesc_en1 = listOfModels[position].resdesc_en
        resdesc_sp1 = listOfModels[position].resdesc_sp

        bottomsheetforrecyclerview().apply {
            show(supportFragmentManager, bottomsheetforrecyclerview.TAG)
        }
    }
}
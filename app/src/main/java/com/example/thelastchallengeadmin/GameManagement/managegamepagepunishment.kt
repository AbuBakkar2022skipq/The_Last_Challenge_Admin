package com.example.thelastchallengeadmin.GameManagement

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.AddGameRecyclerView.add_game_from_rv
import com.example.thelastchallengeadmin.GameManagement.gameManagementData.mylistofdata
import com.example.thelastchallengeadmin.GameManagement.gameManagementData.selectedd
import com.example.thelastchallengeadmin.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.gameview.*
import java.util.ArrayList

class managegamepagepunishment : AppCompatActivity(), Recyclerviewaddapter.OnItemClickListener{
    private val listOfModels = ArrayList<game>()
    private var adapter: Recyclerviewaddapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.managegame_page1)
        initrecyclerview()
        readPropertiesData()
    }

    private fun initrecyclerview(){
        adapter = Recyclerviewaddapter(listOfModels,this)
        val recyclerview : RecyclerView = findViewById(R.id.recycler_view1)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun readPropertiesData() {
        val db = FirebaseFirestore.getInstance()
        val citiesRef = db.collection("games").whereEqualTo("get", true)
        citiesRef.addSnapshotListener { value, error ->
            if (error == null) {
                value?.let { snapshots ->
                    for (dc in snapshots.documentChanges) {
                        @Suppress("NON_EXHAUSTIVE_WHEN")
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> {
                                val dcc = dc.document.toObject(game::class.java)
                                dcc.let {
                                    Log.i("tag", "$it")
                                    listOfModels.add(it)
                                    mylistofdata.add(it)
                                    dcc
                                }
                                adapter?.notifyDataSetChanged()
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }


    override fun onItemClick(
        position: Int,
        documentId: String?,
        identifierr: Int,
        active: ImageView
    ) {
        val v1 = listOfModels[position].documentId.toString()

        if (identifierr == 1) {
            FirebaseFirestore.getInstance().collection("games").document(v1).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val intent = Intent(this, add_game_from_rv::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        selectedd = position
                    }
                }
                .addOnFailureListener {
                }
        }
        }
    }
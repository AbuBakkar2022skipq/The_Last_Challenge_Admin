package com.example.thelastchallengeadmin.vrgame.QuizGame.RecyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.R

class QuizGameRecyclerViewAdapter(
private val models: ArrayList<QuizGameRecyclerViewDataclasss>,
private val listener: OnItemClickListener
) : RecyclerView.Adapter<QuizGameRecyclerViewAdapter.ViewzHolder>() {

    private lateinit var mlistener: OnItemClickListener
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewzHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_questiondata_recyclerview, null)
        view.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ViewzHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewzHolder, index: Int) {

        /*holder.like.setOnClickListener(){
            currentdocumentId = models[index].documentId
        }*/

        holder.resnameen.text=models[index].question_en

        holder.editt.setOnClickListener {
            listener.onItemClick(index, models[index].docID)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, documentId: String?)
    }

    inner class ViewzHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val resnameen = itemView.findViewById<TextView>(R.id.resName)

        val editt = itemView.findViewById<ImageView>(R.id.resIcon)

    }
}

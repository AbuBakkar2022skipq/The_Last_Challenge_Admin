package com.example.thelastchallengeadmin.vrgame.Mimicry.EditGame.SelectResources.RecyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thelastchallengeadmin.R

class RecyclerViewAdapterWordss(
    private val models: ArrayList<dataclassrecyclerViewwordss>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerViewAdapterWordss.ViewzHolder>() {

    private lateinit var mlistener: OnItemClickListener
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewzHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.selectwords_resource_wrt_selectwords, null)
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

        holder.resnameen.text=models[index].resName_eng
        holder.resnamesp.text=models[index].resName_sp

        holder.editt.setOnClickListener {
            listener.onItemClick(index, models[index].docID, holder.editt)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, documentId: String?, editt: ImageView)
    }

    inner class ViewzHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val resnameen = itemView.findViewById<TextView>(R.id.resName)
        val resnamesp = itemView.findViewById<TextView>(R.id.namesp)

        val editt = itemView.findViewById<ImageView>(R.id.edittt)

    }
}

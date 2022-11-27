package com.example.thelastchallengeadmin.resourceAddition
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.thelastchallengeadmin.R

class recyclerviewforresourcespage(
    private val models: ArrayList<recyclerviewresourcesadapter>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<recyclerviewforresourcespage.ViewzHolder>() {

    private lateinit var mlistener: OnItemClickListener
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewzHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_resources_rv_resource, null)
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

        holder.editt.setOnClickListener {
            listener.onItemClick(index, models[index].docID)
        }
        val requestoptions = RequestOptions().placeholder(R.drawable.ic_launcher_background).error(
            R.drawable.ic_launcher_background
        )
        holder.resname.setText(models[index].resName_eng)
        Glide.with(holder.resicn.context).applyDefaultRequestOptions(requestoptions).load(models[index].resIconURL).into(holder.resicn)

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, documentId: String?)
    }

    inner class ViewzHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val resname = itemView.findViewById<TextView>(R.id.textView5)
        val resicn = itemView.findViewById<ImageView>(R.id.imageView10)
        val editt = itemView.findViewById<ImageView>(R.id.imageView11)

    }
}

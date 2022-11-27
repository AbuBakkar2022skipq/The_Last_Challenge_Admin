package com.example.thelastchallengeadmin.GameManagement

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

class Recyclerviewaddapter(
    private val models: ArrayList<game>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<Recyclerviewaddapter.ViewsHolder>() {

    private lateinit var mlistener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gameview, null)
        view.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ViewsHolder(view)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mlistener = listener
    }
    override fun getItemCount(): Int {
        return models.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewsHolder, index: Int) {

        /*holder.like.setOnClickListener(){
            currentdocumentId = models[index].documentId
        }*/

        if (models[index].onlinestatus) {
            holder.active.setImageResource(R.drawable.active);
        }

        else if (!models[index].onlinestatus){
            holder.active.setImageResource(R.drawable.inactive);
        }

        var identifierr=0
        holder.like.setOnClickListener {
            identifierr=1
            listener.onItemClick(index, models[index].documentId, identifierr, holder.active)
        }


        holder.active.setOnClickListener {
            identifierr=2
            listener.onItemClick(index, models[index].documentId, identifierr, holder.active)
        }





        holder.gamename1.text = models[index].gameNameEn

        if (models[index].gameiconurl != "") {
            Glide.with(holder.itemView.getContext()).load(models[index].gameiconurl)
                .into(holder.gameicon1)
        } else {
            models[index].gameiconurl =
                "https://www.kindpng.com/picc/m/185-1852869_beer-mug-icon-png-transparent-png.png"
            Glide.with(holder.itemView.context).load(models[index].gameiconurl)
                .into(holder.gameicon1)
        }




    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, documentId: String?, identifierr: Int, active: ImageView)
    }

    inner class ViewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val gamename1 = itemView.findViewById<TextView>(R.id.textView2)


        val gameicon1 = itemView.findViewById<ImageView>(R.id.imageView6)
        fun bind(gamelist: game){
            val requestoptions = RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context).applyDefaultRequestOptions(requestoptions).load(gamelist.gameiconurl).into(gameicon1)
        }
        val like = itemView.findViewById<ImageView>(R.id.imageView8)
        val active= itemView.findViewById<ImageView>(R.id.activestatus)

    }
}
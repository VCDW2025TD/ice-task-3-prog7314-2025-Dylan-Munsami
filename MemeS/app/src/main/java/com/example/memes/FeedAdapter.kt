package com.example.memes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FeedAdapter(private var memes: List<Meme>) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    inner class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val memeImage: ImageView = itemView.findViewById(R.id.ivMeme)
        val memeCaption: TextView = itemView.findViewById(R.id.tvCaption)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meme, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val meme = memes[position]
        Glide.with(holder.itemView)
            .load(meme.imageUrl)
            .into(holder.memeImage)
        holder.memeCaption.text = meme.caption
    }

    override fun getItemCount(): Int = memes.size

    fun submitList(newMemes: List<Meme>) {
        memes = newMemes
        notifyDataSetChanged()
    }
}

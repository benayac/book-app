package com.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.model.ResourceItem
import kotlinx.android.synthetic.main.genre_item.view.*

class GenreAdapter(private val genres: List<ResourceItem>?, private val itemClickListener: (ResourceItem) -> Unit) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(get: ResourceItem?, clickListener: (ResourceItem) -> Unit) {
            itemView.tvGenre?.text = get?.title
            itemView.setOnClickListener {
                if (get != null) {
                    clickListener(get)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val genreView = inflater.inflate(R.layout.genre_item, parent, false)

        return ViewHolder(genreView)
    }

    override fun getItemCount(): Int {
        return genres?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genres?.get(position), itemClickListener)
    }

}

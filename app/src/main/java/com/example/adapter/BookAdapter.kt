package com.example.adapter

import android.provider.Settings.Global.getString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.model.ResultItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.book_item.view.*
import javax.annotation.Resource
import javax.annotation.Resources

class BookAdapter(private val books: List<ResultItem?>?, private val itemClickListener: (ResultItem) -> Unit) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val bookView = inflater.inflate(R.layout.book_item, parent, false)

        return ViewHolder(bookView)
    }

    override fun getItemCount(): Int {
        return books?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(books?.get(position), itemClickListener)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(get: ResultItem?, clickListener: (ResultItem) -> Unit) {
            itemView.tvTitle?.text = get?.title
            itemView.tvAuthor.text = get?.writerByWriterId?.userByUserId?.name
            itemView.tvRating.text = String.format("Rating: %.2f", get?.rateSum)
            itemView.tvViewed.text = String.format("Viewed: %d", get?.viewCount)
            val imgUrl = get?.coverUrl
            Log.d("IMAGE URL", imgUrl)
            Picasso.get()
                .load("https://cabaca.id:8443/api/v2/files/$imgUrl&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948")
                .resize(75,100)
                .into(itemView.imgBookCover)

            itemView.setOnClickListener {
                if (get != null) {
                    clickListener(get)
                }
            }
        }
    }
}
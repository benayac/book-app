package com.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.adapter.BookAdapter
import com.example.model.LatestBookRespone
import com.example.model.ResultItem
import com.example.network.NetworkConfig
import kotlinx.android.synthetic.main.activity_book.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

class ListBookActivity : AppCompatActivity() {

    private lateinit var gridLayoutManager: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        val genreId = intent.getIntExtra("GENRE_ID", 0)
        Log.d("GENRE_ID", genreId.toString())
        gridLayoutManager = GridLayoutManager(this, 2)
        rvListOfBook.layoutManager = gridLayoutManager

        NetworkConfig().getService().getBookByGenre(genreId).enqueue(object: Callback<LatestBookRespone>{
            override fun onFailure(call: Call<LatestBookRespone>, t: Throwable) {
                Log.d("GSON ERROR", t.toString())
                Toast.makeText(this@ListBookActivity, "Error Finding Data", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<LatestBookRespone>,
                response: Response<LatestBookRespone>
            ) {
                val resource = response.body()
                val response = resource?.result
                rvListOfBook.adapter = BookAdapter(response) {book: ResultItem -> bookItemClicked(book)}
                Log.d("GSON RETURN", "SUCCESS")
            }
        })

    }

    private fun bookItemClicked(book: ResultItem) {
        Toast.makeText(this@ListBookActivity, "Clicked: ${book.title}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ChoosenBookActivity::class.java)
        intent.putExtra("BOOK_TITLE", book.title)
        intent.putExtra("BOOK_COVER", book.coverUrl)
        intent.putExtra("BOOK_CATEGORY", book.category?.toString()?.toInt())
        intent.putExtra("BOOK_RATING", book.rateSum)
        intent.putExtra("BOOK_VIEWER", book.viewCount)
        intent.putExtra("BOOK_STATUS", book.status)
        intent.putExtra("BOOK_AUTHOR", book.writerByWriterId?.userByUserId?.name)
        startActivity(intent)
    }
}

package com.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.BookAdapter
import com.example.adapter.GenreAdapter
import com.example.model.GenreResponse
import com.example.model.LatestBookRespone
import com.example.model.ResourceItem
import com.example.model.ResultItem
import com.example.network.NetworkConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var genreLayoutManager: LinearLayoutManager
    private lateinit var latestBookLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupGenreRecyclerView()
        setupLatestBookRecyclerView()
    }

    private fun setupGenreRecyclerView() {
        genreLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvGenreHorizontal.layoutManager = genreLayoutManager

        NetworkConfig().getService().getGenre().enqueue(object : Callback<GenreResponse> {
            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error To Fetch Data", Toast.LENGTH_SHORT).show()
                Log.d("GSON ERROR", t.toString())
            }

            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                val resources = response.body()
                val response : List<ResourceItem>? = resources?.resource
                rvGenreHorizontal.adapter = GenreAdapter(response) { genre: ResourceItem -> genreItemClicked(genre)}
                Log.d("GSON RETURN", response.toString())
            }
        })
    }

    private fun setupLatestBookRecyclerView() {
        latestBookLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rvLatestBookMain.layoutManager = latestBookLayoutManager

        NetworkConfig().getService().getLatestBook().enqueue(object : Callback<LatestBookRespone>{
            override fun onFailure(call: Call<LatestBookRespone>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error To Fetch Data", Toast.LENGTH_SHORT).show()
                Log.d("GSON ERROR", t.toString())
            }

            override fun onResponse(
                call: Call<LatestBookRespone>,
                response: Response<LatestBookRespone>
            ) {
                val resource = response.body()
                val response = resource?.result
                rvLatestBookMain.adapter = BookAdapter(response) { book: ResultItem -> bookItemClicked(book)}
                Log.d("GSON RETURN", "SUCCESS")
            }

        })
    }

    private fun genreItemClicked(genre: ResourceItem){
        Toast.makeText(this, "Clicked: ${genre.id}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ListBookActivity::class.java)
        intent.putExtra("GENRE_ID", genre.id)
        Log.d("GENRE_PASSED", genre.id.toString())
        startActivity(intent)
    }

    private fun bookItemClicked(book: ResultItem) {
        Toast.makeText(this, "Clicked: ${book.title}", Toast.LENGTH_SHORT).show()
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


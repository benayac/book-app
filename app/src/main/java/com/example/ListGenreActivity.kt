package com.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.GenreAdapter
import com.example.model.GenreResponse
import com.example.model.ResourceItem
import com.example.network.NetworkConfig
import kotlinx.android.synthetic.main.activity_list_genre.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListGenreActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_genre)
        linearLayoutManager = LinearLayoutManager(this)

        rvGenre.layoutManager = linearLayoutManager
        Log.d("Activity", "Start Activity")
        NetworkConfig().getService().getGenre().enqueue(object : Callback<GenreResponse>{
            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                Toast.makeText(this@ListGenreActivity, "Error To Fetch Data", Toast.LENGTH_SHORT).show()
                Log.d("GSON ERROR", t.toString())
            }

            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                val resources = response.body()
                val response : List<ResourceItem>? = resources?.resource
                rvGenre.adapter = GenreAdapter(response) { genre: ResourceItem -> genreItemClicked(genre)}
                Log.d("GSON RETURN", response.toString())
            }
        })
    }

    private fun genreItemClicked(genre: ResourceItem){
        Toast.makeText(this@ListGenreActivity, "Clicked: ${genre.id}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ListBookActivity::class.java)
        intent.putExtra("GENRE_ID", genre.id)
        Log.d("GENRE_PASSED", genre.id.toString())
        startActivity(intent)
    }
}

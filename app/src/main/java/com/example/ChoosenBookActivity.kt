package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.network.NetworkConfig
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_choosen_book.*

class ChoosenBookActivity : AppCompatActivity() {

    val bookCategories = arrayOf("Romance", "Adult", "Comedy", "Teenlit", "Action", "Fantasy", "Islami", "Horror",
        "Inspirational", "Medical", "Historical", "Thriller", "Fanfiction", "Mystery",
        "Poetry", "Literature", "Detective", "Crime", "Novelet", "Exorcism")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosen_book)

        val genreId = intent.getIntExtra("BOOK_CATEGORY", 0)
        val bookTitle = intent.getStringExtra("BOOK_TITLE")
        val bookCover = intent.getStringExtra("BOOK_COVER")
        val bookRating = intent.getFloatExtra("BOOK_RATING", 0.0F)
        val bookViewer = intent.getIntExtra("BOOK_VIEWER", 0)
        val bookStatus = intent.getStringExtra("BOOK_STATUS")
        val bookAuthor = intent.getStringExtra("BOOK_AUTHOR")

        tvChoosenAuthor.text = bookAuthor
        tvChoosenGenre.text = bookCategories[genreId]
        tvChoosenRate.text = String.format("Rating %.2f", bookRating)
        tvChoosenStatus.text = bookStatus
        tvChoosenTitle.text = bookTitle
        tvChoosenViewed.text = String.format("Viewer %d", bookViewer)

        Picasso.get()
            .load("https://cabaca.id:8443/api/v2/files/$bookCover&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948")
            .into(imgChoosenBookCover)

    }
}

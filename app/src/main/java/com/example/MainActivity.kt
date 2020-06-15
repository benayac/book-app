package com.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGenre.setOnClickListener {
            startActivity(Intent(this, ListGenreActivity::class.java))
        }
        btnNewBook.setOnClickListener {
            startActivity(Intent(this, ListLatestBookActivity::class.java))
        }
    }
}


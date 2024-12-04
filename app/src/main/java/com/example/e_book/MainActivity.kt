package com.example.e_book

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textTV: TextView
    private lateinit var downloadBTN: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textTV = findViewById(R.id.textTV)
        downloadBTN = findViewById(R.id.downloadBTN)

        val database = Database()
        downloadBTN.setOnClickListener {
            loadBook(database.text).forEach { textTV.append(it + "\n") }
        }

    }
}

fun loadBook(text: String): List<String> {
    return text.split("\\s+".toRegex())
        .map { it.replace("[^a-zA-Z0-9]".toRegex(), "") }
}
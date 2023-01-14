package com.example.newsly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var newsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsList = findViewById(R.id.newsList)

        getNews()
    }

    private fun getNews() {
        val news = RetrofitInstance.newsInstance.getTopHeadLines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                Log.d("RetroResponse", response.body().toString())

                val adapter =
                    response.body()?.let { NewsListAdapter(this@MainActivity, it.articles) }

                newsList.adapter = adapter
                newsList.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("RetroResponse", "Failed to get response")
            }
        })
    }
}
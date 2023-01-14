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
    lateinit var myAdapter: NewsListAdapter
    private var articles = mutableListOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsList = findViewById(R.id.newsList)

        myAdapter = NewsListAdapter(this, articles)
        newsList.adapter = myAdapter
        newsList.layoutManager = LinearLayoutManager(this)

        getNews()
    }

    private fun getNews() {
        val news = RetrofitInstance.newsInstance.getTopHeadLines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                Log.d("RetroResponse", response.body().toString())

                response.body()?.let { articles.addAll(it.articles) }
                myAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("RetroResponse", "Failed to get response")
            }
        })
    }
}
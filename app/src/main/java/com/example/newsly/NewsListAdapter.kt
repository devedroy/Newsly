package com.example.newsly

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class NewsListAdapter(val context: Context, val articles: List<Article>) :
    RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {
    class NewsViewHolder(itemView: View) : ViewHolder(itemView) {
        val newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        val newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        val newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, article.author, Toast.LENGTH_SHORT).show()
        }
    }
}
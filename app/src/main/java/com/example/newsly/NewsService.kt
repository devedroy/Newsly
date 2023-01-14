package com.example.newsly

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "0e788f81ae7e46c18b35187137e2e227"

interface NewsInterface {

    @GET("/v2/top-headlines?apiKey=$API_KEY")
    fun getTopHeadLines(@Query("country") country: String, @Query("page") page: Int): Call<News>

    /*Result URL:
    * https://newsapi.org//v2/top-headlines?apiKey=$API_KEY&country=COUNTRY_CODE&page=PAGE*/
}

object RetrofitInstance {
    val newsInstance: NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }


}
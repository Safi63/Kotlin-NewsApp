package com.example.newsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https://newsapi.org/v2/top-headlines?country=us&apiKey=fa6c17c17a3340478a181959006ff073
// https://newsapi.org/v2/everything?domains=wsj.com&apiKey=fa6c17c17a3340478a181959006ff073

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "fa6c17c17a3340478a181959006ff073"

interface NewsInterface{

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLines(@Query("country")country:String,@Query("page")page:Int) : Call<News>
    // https://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=us&page=1
}
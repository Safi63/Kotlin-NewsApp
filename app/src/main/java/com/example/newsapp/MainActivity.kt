package com.example.newsapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.littlemango.stacklayoutmanager.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter : NewsAdapter
    private var articles = mutableListOf<Article>()
    var pageNum = 1
    var totalResults = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NewsAdapter(this@MainActivity,articles)
        val newsList = findViewById<RecyclerView>(R.id.newsList)
        newsList.adapter = adapter
//        newsList.layoutManager = LinearLayoutManager(this@MainActivity)

        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)

        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                val container = findViewById<ConstraintLayout>(R.id.container)
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
                if (totalResults > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount -5){
                    //next page data
                    pageNum++
                    getNews()
                }
            }

        })

        newsList.layoutManager = layoutManager
        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadLines("us",pageNum)
        news.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null){
                    totalResults = news.totalResults
//                    Log.d("SAFI ULLAH", news.toString())
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                    }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("SAFI ULLAH BUTT", "Error in fetcthin news")
            }
        })
    }
}
package com.recyclerview.recyclerviewpractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.recyclerview.recyclerviewpractice.adapter.RecyclerViewAdapter
import com.recyclerview.recyclerviewpractice.databinding.ActivityMainBinding
import com.recyclerview.recyclerviewpractice.model.ApiResponse
import com.recyclerview.recyclerviewpractice.retrofit.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(),OnClickRowListener {

    lateinit var binding : ActivityMainBinding

    private var courseRV: RecyclerView? = null
    private var recyclerDataArrayList: ArrayList<ApiResponse>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        courseRV = findViewById(R.id.recyclerView)

        recyclerDataArrayList = ArrayList()
       
        getData()
    }

    private fun getData() {
        binding.shimmerLayout.startShimmer()

        val call: Call<List<ApiResponse>> =
            Retrofit.getClient.getApiData()
        call.enqueue(object : Callback<List<ApiResponse>> {

            override fun onResponse(
                call: Call<List<ApiResponse>>,
                response: Response<List<ApiResponse>>,
            ) {
                if (response.isSuccessful) {

//                    binding.progressBar.visibility = View.GONE
                    binding.shimmerLayout.apply {
                        stopShimmer()
                        visibility = View.GONE
                    }

                    recyclerDataArrayList = response.body() as ArrayList<ApiResponse>?

                    for (i in recyclerDataArrayList!!.indices) {
                        recyclerViewAdapter =
                            RecyclerViewAdapter(this@MainActivity, recyclerDataArrayList!!)

                        val manager = LinearLayoutManager(this@MainActivity)

                        courseRV!!.layoutManager = manager

                        courseRV!!.adapter = recyclerViewAdapter
                    }
                }
            }

            override fun onFailure(
                call: Call<List<ApiResponse>>,
                t: Throwable
            ) {
                Log.e("TAG", "onFailure: " + t.message)

                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong, Try again",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    override fun onClickItem(position: Int, item: ApiResponse) {
        DetailScreen.item = item
        val i = Intent(applicationContext, DetailScreen::class.java)
        startActivity(i)
    }

}
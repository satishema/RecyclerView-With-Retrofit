package com.android.recyclerview.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.recyclerview.R
import com.android.recyclerview.adapter.RecyclerViewAdapter
import com.android.recyclerview.databinding.ActivityMainBinding
import com.android.recyclerview.listener.OnClickRowListener
import com.android.recyclerview.model.ApiResponse
import com.android.recyclerview.retrofit.Retrofit
import com.android.recyclerview.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), OnClickRowListener {

    lateinit var binding : ActivityMainBinding

    private val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }


    private var courseRV: RecyclerView? = null
    private var recyclerDataArrayList: List<ApiResponse>? = null
//    private var recyclerDataArrayList: ArrayList<ApiResponse>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        courseRV = findViewById(R.id.recyclerView)
        getData()

        GlobalScope.launch(Dispatchers.IO) {
            recyclerDataArrayList = database.apiDao().getAll()
            for (i in recyclerDataArrayList!!.indices) {
                recyclerViewAdapter =
                    RecyclerViewAdapter(this@MainActivity, recyclerDataArrayList!!)

                val manager = LinearLayoutManager(this@MainActivity)

                courseRV!!.layoutManager = manager

                courseRV!!.adapter = recyclerViewAdapter
            }
        }


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

                    val apiData = response.body()
                    Log.d("TAG", "apiData: $apiData")
                    CoroutineScope(Dispatchers.IO).launch { // Insert Data
                        if (apiData != null) {
                            database.apiDao().insert(apiData)
                        }
                    }
//                    binding.progressBar.visibility = View.GONE
                    binding.shimmerLayout.apply {
                        stopShimmer()
                        visibility = View.GONE
                    }

//                    recyclerDataArrayList = response.body()
//
//                    for (i in recyclerDataArrayList!!.indices) {
//                        recyclerViewAdapter =
//                            RecyclerViewAdapter(this@MainActivity, recyclerDataArrayList!!)
//
//                        val manager = LinearLayoutManager(this@MainActivity)
//
//                        courseRV!!.layoutManager = manager
//
//                        courseRV!!.adapter = recyclerViewAdapter
//                    }
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
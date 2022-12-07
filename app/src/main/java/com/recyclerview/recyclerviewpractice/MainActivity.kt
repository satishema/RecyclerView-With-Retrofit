package com.recyclerview.recyclerviewpractice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.recyclerview.recyclerviewpractice.adapter.RecipeAdapter
import com.recyclerview.recyclerviewpractice.adapter.RecyclerViewAdapter
import com.recyclerview.recyclerviewpractice.databinding.ActivityMainBinding
import com.recyclerview.recyclerviewpractice.model.ApiResponse
import com.recyclerview.recyclerviewpractice.viewModel.RecipeViewModel


class MainActivity : AppCompatActivity(), OnClickRowListener {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RecipeViewModel
    lateinit var recipeAdapter: RecipeAdapter

//    private var recyclerDataArrayList: ArrayList<ApiResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        recyclerDataArrayList = ArrayList()

        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        viewModel.getRecipe()
        viewModel.observeRecipeLiveData().observe(this, Observer { movieList ->
            recipeAdapter.setMovieList(movieList)
        })

//        getData()
    }

    private fun prepareRecyclerView() {
        recipeAdapter = RecipeAdapter(this@MainActivity)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recipeAdapter
        }
    }

    override fun onClickItem(position: Int, item: ApiResponse) {
        DetailScreen.item = item
        val i = Intent(applicationContext, DetailScreen::class.java)
        startActivity(i)
    }

    //    private fun getData() {
//        binding.shimmerLayout.startShimmer()
//
//        val call: Call<List<ApiResponse>> =
//            Retrofit.getClient.getApiData()
//        call.enqueue(object : Callback<List<ApiResponse>> {
//
//            override fun onResponse(
//                call: Call<List<ApiResponse>>,
//                response: Response<List<ApiResponse>>,
//            ) {
//                if (response.isSuccessful) {
//
////                    binding.progressBar.visibility = View.GONE
//                    binding.shimmerLayout.apply {
//                        stopShimmer()
//                        visibility = View.GONE
//                    }
//
//                    recyclerDataArrayList = response.body() as ArrayList<ApiResponse>?
//
//                    for (i in recyclerDataArrayList!!.indices) {
//                        recipeAdapter =
//                            RecyclerViewAdapter(this@MainActivity, recyclerDataArrayList!!)
//
//                        val manager = LinearLayoutManager(this@MainActivity)
//
//                        courseRV!!.layoutManager = manager
//
//                        courseRV!!.adapter = recipeAdapter
//                    }
//                }
//            }
//
//            override fun onFailure(
//                call: Call<List<ApiResponse>>,
//                t: Throwable
//            ) {
//                Log.e("TAG", "onFailure: " + t.message)
//
//                Toast.makeText(
//                    this@MainActivity,
//                    "Something went wrong, Try again",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//        })
//    }

}
package com.android.foodapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.foodapp.FoodRepository
import com.android.foodapp.FoodViewModelFactory
import com.android.foodapp.R
import com.android.foodapp.adapter.RecipeAdapter
import com.android.foodapp.databinding.ActivityMainBinding
import com.android.foodapp.listener.OnClickRowListener
import com.android.foodapp.model.FoodResponse
import com.android.foodapp.room.AppDatabase
import com.android.foodapp.viewModel.RecipeViewModel


class MainActivity : AppCompatActivity(), OnClickRowListener {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RecipeViewModel
    lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val foodRepository = FoodRepository(AppDatabase.getDatabase(this))
        val factory = FoodViewModelFactory(foodRepository)

        prepareRecyclerView()
        viewModel = ViewModelProvider(this, factory)[RecipeViewModel::class.java]

//        viewModel.getRecipe()
//        viewModel.observeRecipeLiveData().observe(this, Observer { foodList ->
////            recipeAdapter.setMovieList(foodList)
//            viewModel.insert(foodList)
//        })

        // To display all items in recycler view
        viewModel.allFoodItems().observe(this, Observer {
            if (it.isNullOrEmpty()) {
                viewModel.getRecipe()
                viewModel.observeRecipeLiveData().observe(this, Observer { foodList ->
//            recipeAdapter.setMovieList(foodList)
                    viewModel.insert(foodList)
                })
            } else {
                recipeAdapter.setMovieList(it)
            }
        })
    }

    private fun prepareRecyclerView() {
        recipeAdapter = RecipeAdapter(this@MainActivity)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recipeAdapter
        }
    }

    override fun onClickItem(position: Int, item: FoodResponse) {
        DetailScreen.item = item
        val i = Intent(applicationContext, DetailScreen::class.java)
        startActivity(i)
    }
}
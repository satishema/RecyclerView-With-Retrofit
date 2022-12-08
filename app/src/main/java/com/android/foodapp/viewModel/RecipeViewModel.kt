package com.android.foodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.foodapp.FoodRepository
import com.android.foodapp.model.FoodResponse
import com.android.foodapp.retrofit.Retrofit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeViewModel(private val repository: FoodRepository) : ViewModel() {

    private var recipeLiveData = MutableLiveData<List<FoodResponse>>()

    fun getRecipe() {
        val call: Call<List<FoodResponse>> = Retrofit.getClient.getApiData()
        call.enqueue(object : Callback<List<FoodResponse>> {

            override fun onResponse(
                call: Call<List<FoodResponse>>,
                response: Response<List<FoodResponse>>,
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        recipeLiveData.value = response.body() as ArrayList<FoodResponse>?
                    }
                } else {
                    return
                }
            }

            override fun onFailure(
                call: Call<List<FoodResponse>>, t: Throwable
            ) {
                Log.e("TAG", "onFailure: " + t.message)

            }

        })
    }

    // In coroutines thread insert item in insert function.
    fun insert(item: List<FoodResponse>) = GlobalScope.launch {
        repository.insert(item)
    }

    // In coroutines thread delete item in delete function.
    fun delete(item: List<FoodResponse>) = GlobalScope.launch {
        repository.delete(item)
    }

    //Here we initialized allGroceryItems function with repository
    fun allFoodItems() = repository.allFoodItems()

    fun observeRecipeLiveData(): LiveData<List<FoodResponse>> {
        return recipeLiveData
    }
}

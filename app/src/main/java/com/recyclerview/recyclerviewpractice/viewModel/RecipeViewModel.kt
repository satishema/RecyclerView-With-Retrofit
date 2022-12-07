package com.recyclerview.recyclerviewpractice.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recyclerview.recyclerviewpractice.model.ApiResponse
import com.recyclerview.recyclerviewpractice.retrofit.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeViewModel : ViewModel() {
    private var recipeLiveData = MutableLiveData<List<ApiResponse>>()
    fun getRecipe() {
        val call: Call<List<ApiResponse>> =
            Retrofit.getClient.getApiData()
        call.enqueue(object : Callback<List<ApiResponse>> {

            override fun onResponse(
                call: Call<List<ApiResponse>>,
                response: Response<List<ApiResponse>>,
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        recipeLiveData.value = response.body() as ArrayList<ApiResponse>?
                    }
                } else {
                    return
                }
            }

            override fun onFailure(
                call: Call<List<ApiResponse>>,
                t: Throwable
            ) {
                Log.e("TAG", "onFailure: " + t.message)

            }

        })
    }

    fun observeRecipeLiveData(): LiveData<List<ApiResponse>> {
        return recipeLiveData
    }
}

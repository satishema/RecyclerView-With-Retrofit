package com.recyclerview.recyclerviewpractice.retrofit

import com.recyclerview.recyclerviewpractice.model.ApiResponse
import com.recyclerview.recyclerviewpractice.model.ApiResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("android-test/recipes.json")
    fun getApiData(

    ): Call<List<ApiResponse>>
}
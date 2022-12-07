package com.android.foodapp.retrofit

import com.android.foodapp.model.FoodResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("android-test/recipes.json")
    fun getApiData(): Call<List<FoodResponse>>

}
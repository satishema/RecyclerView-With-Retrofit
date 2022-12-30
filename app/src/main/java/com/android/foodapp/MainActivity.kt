package com.android.foodapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.foodapp.databinding.ActivityMainBinding
import com.android.foodapp.model.EmployeeVacationBalancesResponse
import com.android.foodapp.retrofit.Retrofit
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), OnClickRowListener {
    private val TAG = MainActivity::class.java.name
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getEmployeeVacationBalances("waseem@psu.edu.sa")
//        getReq()
    }

//    private fun convertStringToModel(jsonResponse: String?): EmployeeVacationBalancesResponse {
//        val jsonObject: JsonObject = JsonParser.parseString("{$jsonResponse}").asJsonObject
//        val gson = Gson()
//        return gson.fromJson(jsonObject, EmployeeVacationBalancesResponse::class.java)
//    }

//    private fun convertStringToModel(jsonResponse: String?): List<EmployeeVacationBalancesResponse> {
//        val jsonArray: JsonArray = JsonParser.parseString(jsonResponse).asJsonArray
//        val gson = Gson()
//        val listType = object : TypeToken<List<EmployeeVacationBalancesResponse>>() {}.type
//        return gson.fromJson(jsonArray, listType)
//    }


    private fun getEmployeeVacationBalances(email: String) {
        val call = Retrofit.service.getEmployeeVacationBalances(email)
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    val modifiedResponse =
                        responseData?.convertToModel<EmployeeVacationBalancesResponse>()
                    Log.d(TAG, "modifiedResponse: $modifiedResponse")

                } else {
                    val responseData = response.body()
                    Log.d(TAG, "onResponse:${responseData.toString()} ")
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // Handle request failure
                Log.d(TAG, "onFailure: $t")
            }
        })
    }

    override fun onClickItem(position: Int, item: EmployeeVacationBalancesResponse) {
         DetailScreen.item = item
         val i = Intent(applicationContext, DetailScreen::class.java)
         startActivity(i)
    }
}

inline fun <reified T> String.convertToModel(): List<T> {
    val gson = Gson()
    val jsonElement: JsonElement = gson.fromJson(this, JsonElement::class.java)
    val listType = object : TypeToken<List<T>>() {}.type
    return when {
        jsonElement.isJsonArray -> gson.fromJson(jsonElement.asJsonArray, listType)
        jsonElement.isJsonObject -> gson.fromJson(jsonElement.asJsonObject, listType)
        jsonElement.isJsonPrimitive -> gson.fromJson(jsonElement.asJsonPrimitive, listType)
        else -> throw IllegalArgumentException("Unrecognized JSON element: $jsonElement")
    }
}

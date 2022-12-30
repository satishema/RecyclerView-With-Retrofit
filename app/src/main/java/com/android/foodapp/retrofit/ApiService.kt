package com.android.foodapp.retrofit

import com.android.foodapp.model.EmployeeVacationBalancesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("EmployeeVacation/GetEmployeesVocationBalances")
    fun getEmployeeVacationBalances(
        @Query("email") email: String
    ): Call<String>
}
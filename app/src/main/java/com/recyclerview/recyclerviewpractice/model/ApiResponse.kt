package com.recyclerview.recyclerviewpractice.model


//class ApiResponse : ArrayList<ApiResponseItem>(){
//
//}

data class ApiResponse(
    val calories: String?,
    val carbos: String?,
    val country: String? = null,
    val description: String?,
    val difficulty: Int?,
    val fats: String?,
    val headline: String?,
    val id: String?,
    val image: String?,
    val name: String?,
    val proteins: String?,
    val thumb: String?,
    val time: String?
)



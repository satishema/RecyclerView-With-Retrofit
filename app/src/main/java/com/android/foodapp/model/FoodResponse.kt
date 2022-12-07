package com.android.foodapp.model


//class FoodResponse : ArrayList<ApiResponseItem>(){
//
//}

data class FoodResponse(
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



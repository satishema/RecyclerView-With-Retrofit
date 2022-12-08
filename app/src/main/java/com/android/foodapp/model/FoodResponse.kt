package com.android.foodapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_response")
data class FoodResponse(
    val calories: String?,
    val carbos: String?,
    val country: String? = null,
    val description: String?,
    val difficulty: Int?,
    val fats: String?,
    val headline: String?,
    @PrimaryKey val id: String,
    val image: String?,
    val name: String?,
    val proteins: String?,
    val thumb: String?,
    val time: String?
)



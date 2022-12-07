package com.android.foodapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "api_response")
data class ApiResponse(
    val calories: String?,
    val carbos: String?,
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



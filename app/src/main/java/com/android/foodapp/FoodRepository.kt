package com.android.foodapp

import com.android.foodapp.model.FoodResponse
import com.android.foodapp.room.AppDatabase

class FoodRepository(private val db: AppDatabase) {
    suspend fun insert(item: List<FoodResponse>) = db.getFoodDao().insert(item)
    suspend fun delete(item: List<FoodResponse>) = db.getFoodDao().delete(item)

    fun allFoodItems() = db.getFoodDao().getAllFoodItems()
}
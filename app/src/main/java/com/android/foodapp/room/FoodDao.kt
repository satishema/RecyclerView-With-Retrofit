package com.android.foodapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.foodapp.model.FoodResponse

@Dao
interface FoodDao {

    // Insert function is used to
    // insert data in database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<FoodResponse>)

    // Delete function is used to
    // delete data in database.
    @Delete
    suspend fun delete(item: List<FoodResponse>)

    // getAllGroceryItems function is used to get
    // all the data of database.
    @Query("SELECT * FROM food_response")
    fun getAllFoodItems(): LiveData<List<FoodResponse>>

}
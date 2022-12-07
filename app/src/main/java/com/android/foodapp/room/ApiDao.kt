package com.android.foodapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.foodapp.model.ApiResponse

@Dao
interface ApiDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert( api_response : List<ApiResponse>)

    @Query("SELECT * FROM api_response")
    fun getAll(): List<ApiResponse>

}
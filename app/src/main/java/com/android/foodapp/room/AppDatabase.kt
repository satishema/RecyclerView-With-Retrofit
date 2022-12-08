package com.android.foodapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.foodapp.model.FoodResponse

@Database(entities = [FoodResponse::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getFoodDao() : FoodDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database.db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}

//@Database(entities = [FoodResponse::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun getFoodDao() : FoodDao
//
//    companion object {
//        @Volatile
//        private var instance: AppDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: createDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "AppDatabase.db").build()
//    }
//}
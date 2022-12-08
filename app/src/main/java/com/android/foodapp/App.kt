package com.android.foodapp

import android.app.Application
import com.android.foodapp.room.AppDatabase

class App : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}
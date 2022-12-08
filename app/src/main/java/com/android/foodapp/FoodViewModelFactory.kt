package com.android.foodapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.foodapp.viewModel.RecipeViewModel

class FoodViewModelFactory(private val repository: FoodRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeViewModel(repository) as T
    }
}
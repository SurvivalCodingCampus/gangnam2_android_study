package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

class SavedRecipesViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedRecipesViewModel::class.java)) {
            val repository = (application as? AppApplication)?.recipeRepository
                ?: throw IllegalStateException("Application must be an instance of AppApplication")
            @Suppress("UNCHECKED_CAST")
            return SavedRecipesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

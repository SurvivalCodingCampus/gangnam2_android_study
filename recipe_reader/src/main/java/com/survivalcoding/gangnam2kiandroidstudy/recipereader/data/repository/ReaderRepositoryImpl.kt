package com.survivalcoding.gangnam2kiandroidstudy.recipereader.data.repository

import android.content.Context
import android.net.Uri
import com.survivalcoding.gangnam2kiandroidstudy.recipereader.domain.model.ReaderRecipe
import com.survivalcoding.gangnam2kiandroidstudy.recipereader.domain.repository.ReaderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReaderRepositoryImpl(
    private val context: Context
) : ReaderRepository {

    override suspend fun getRecipes(): List<ReaderRecipe> = withContext(Dispatchers.IO) {
        val recipeList = mutableListOf<ReaderRecipe>()
        val uri = Uri.parse("content://com.survivalcoding.gangnam2kiandroidstudy.provider/recipes")
        
        // Define columns we want
        val projection = arrayOf("id", "name", "chef", "image")

        val cursor = context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            null
        )

        cursor?.use {
            val idIndex = it.getColumnIndex("id")
            val nameIndex = it.getColumnIndex("name")
            val chefIndex = it.getColumnIndex("chef")
            val imageIndex = it.getColumnIndex("image")

            while (it.moveToNext()) {
                val id = if (idIndex != -1) it.getInt(idIndex) else 0
                val name = if (nameIndex != -1) it.getString(nameIndex) else "Unknown"
                val chef = if (chefIndex != -1) it.getString(chefIndex) else "Unknown"
                val image = if (imageIndex != -1) it.getString(imageIndex) else ""

                recipeList.add(ReaderRecipe(id, name, chef, image))
            }
        }
        recipeList
    }
}

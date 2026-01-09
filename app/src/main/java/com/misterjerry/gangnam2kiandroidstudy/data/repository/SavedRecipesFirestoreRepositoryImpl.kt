package com.misterjerry.gangnam2kiandroidstudy.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.misterjerry.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDao
import com.misterjerry.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository
import kotlinx.coroutines.tasks.await

class SavedRecipesFirestoreRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val dao: SavedRecipesDao
) : SavedRecipesRepository {

    private val userId: String?
        get() = auth.currentUser?.uid

    private fun getCollectionReference(uid: String) =
        firestore.collection("users").document(uid).collection("saved_recipes")

    override suspend fun getSavedRecipes(): List<SavedRecipesEntity> {
        val uid = userId
        if (uid == null) {
            Log.e("SavedRecipesFirestore", "User not logged in. Cannot get saved recipes.")
            return emptyList()
        }
        return try {
            val snapshot = getCollectionReference(uid).get().await()
            snapshot.documents.mapNotNull { document ->
                // Assuming the document ID is the recipe ID or it's a field
                val id = document.getLong("recipeId")?.toInt() ?: document.id.toIntOrNull()
                if (id != null) {
                    SavedRecipesEntity(recipeId = id)
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            Log.e("SavedRecipesFirestore", "Error getting saved recipes", e)
            emptyList()
        }
    }

    override suspend fun deleteSavedRecipe(id: Int): Result<Unit> {
        val uid = userId
        if (uid == null) {
            Log.e("SavedRecipesFirestore", "User not logged in. Cannot delete saved recipe.")
            return Result.failure(IllegalStateException("User not logged in"))
        }
        return try {
            // Assuming document ID is the recipe ID
            getCollectionReference(uid).document(id.toString()).delete().await()
            dao.deleteSavedRecipe(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("SavedRecipesFirestore", "Error deleting saved recipe", e)
            Result.failure(e)
        }
    }

    override suspend fun addSavedRecipe(id: Int): Result<Unit> {
        val uid = userId
        if (uid == null) {
            Log.e("SavedRecipesFirestore", "User not logged in. Cannot add saved recipe.")
            return Result.failure(IllegalStateException("User not logged in"))
        }
        return try {
            val data = hashMapOf(
                "recipeId" to id,
                "savedAt" to System.currentTimeMillis()
            )
            // Use recipe ID as document ID to prevent duplicates easily
            getCollectionReference(uid).document(id.toString()).set(data).await()
            dao.addSavedRecipe(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("SavedRecipesFirestore", "Error adding saved recipe", e)
            Result.failure(e)
        }
    }
}

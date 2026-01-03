package com.misterjerry.gangnam2kiandroidstudy.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SavedRecipesFirestoreRepositoryTest {

    private lateinit var repository: SavedRecipesRepository
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    @Before
    fun setup() {
        // Note: This requires the device/emulator to have internet access and valid google-services.json
        // For strictly hermetic tests, use Firebase Emulator Suite.
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        repository = SavedRecipesFirestoreRepositoryImpl(firestore, auth)
    }

    @Test
    fun testAddSavedRecipe() = runBlocking {
        // Ensure user is logged in for this test to work on a real device
        if (auth.currentUser == null) {
            println("User not logged in. Skipping test logic requiring auth.")
            // Ideally, sign in anonymously here or mock auth
            // auth.signInAnonymously().await()
            return@runBlocking
        }

        val testRecipeId = 9999
        repository.addSavedRecipe(testRecipeId)
        
        // Allow some time for network sync if needed, though await() should handle it.
        
        val savedRecipes = repository.getSavedRecipes()
        val isSaved = savedRecipes.any { it.recipeId == testRecipeId }
        
        assertTrue("Recipe should be saved", isSaved)
        
        // Cleanup
        repository.deleteSavedRecipe(testRecipeId)
    }
}

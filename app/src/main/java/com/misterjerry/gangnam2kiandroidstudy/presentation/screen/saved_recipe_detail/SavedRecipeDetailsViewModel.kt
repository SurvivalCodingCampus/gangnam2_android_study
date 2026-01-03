package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.misterjerry.gangnam2kiandroidstudy.domain.use_case.AddSavedRecipeUseCase
import com.misterjerry.gangnam2kiandroidstudy.domain.use_case.CopyLinkUseCase
import com.misterjerry.gangnam2kiandroidstudy.domain.use_case.DeleteSavedRecipeUseCase
import com.misterjerry.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipeDetailsViewModel(
    savedRecipeId: Int,
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val copyLinkUseCase: CopyLinkUseCase,
    private val deleteSavedRecipeUseCase: DeleteSavedRecipeUseCase,
    private val addSavedRecipeUseCase: AddSavedRecipeUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(SavedRecipeDetailsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getRecipeDetailsUseCase.execute(savedRecipeId)
            _state.value =
                _state.value.copy(
                    ingredientList = result.first,
                    procedureList = result.second,
                    recipe = result.third
                )
        }
    }

    fun changeValue(isSelectIngredient: Boolean) {
        _state.value = _state.value.copy(isSelectIngredient = isSelectIngredient)
    }

    fun toggleMenu(isShowCommand: Boolean) {
        _state.value = _state.value.copy(isDropDownMenuShow = isShowCommand)
    }

    fun toggleShareDialog(isShow: Boolean) {
        _state.value = _state.value.copy(isShareDialogShow = isShow)
    }

    fun copyLink(link: String) {
        copyLinkUseCase.execute(link)
    }

    fun toggleSavedRecipe(id: Int) {
        val currentIsSaved = state.value.recipe.isSaved
        
        // Optimistic update
        _state.value = _state.value.copy(recipe = _state.value.recipe.copy(isSaved = !currentIsSaved))

        viewModelScope.launch {
            val result = if (currentIsSaved) {
                deleteSavedRecipeUseCase.execute(id)
            } else {
                addSavedRecipeUseCase.execute(id)
            }
            
            result.onFailure {
                // Revert state on failure
                _state.value = _state.value.copy(recipe = _state.value.recipe.copy(isSaved = currentIsSaved))
                // You might want to expose this error to the UI to show a Snackbar
                it.printStackTrace() 
            }
        }
    }


}
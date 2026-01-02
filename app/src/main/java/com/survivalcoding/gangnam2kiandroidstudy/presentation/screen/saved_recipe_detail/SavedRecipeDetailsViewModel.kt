package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDao
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipeDetailsViewModel(
    savedRecipeId: Int,
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val copyLinkUseCase: CopyLinkUseCase,
    private val dao: SavedRecipesDao
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
        if (state.value.recipe.isSaved == true) {//삭제
            viewModelScope.launch {

                dao.deleteSavedRecipe(id)
            }
            _state.value = _state.value.copy(recipe = _state.value.recipe.copy(isSaved = false))

        } else {//추가
            viewModelScope.launch {
                dao.addSavedRecipe(id)
            }
            _state.value = _state.value.copy(recipe = _state.value.recipe.copy(isSaved = true))
        }

    }


}
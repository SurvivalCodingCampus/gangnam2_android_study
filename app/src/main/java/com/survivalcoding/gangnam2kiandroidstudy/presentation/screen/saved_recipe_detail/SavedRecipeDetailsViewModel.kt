package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipeDetailsViewModel(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val copyLinkUseCase: CopyLinkUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(SavedRecipeDetailsState())
    val state = _state.asStateFlow()


    fun init(id: Int) {
        viewModelScope.launch {
            val result = getRecipeDetailsUseCase.execute(id)
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


}
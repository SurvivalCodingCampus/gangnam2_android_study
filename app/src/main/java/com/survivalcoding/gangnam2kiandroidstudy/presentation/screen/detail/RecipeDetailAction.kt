package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.detail

sealed interface RecipeDetailAction {
    // UI
    data class OnValueChange(val index: Int) : RecipeDetailAction
    data object OnArrowBackClick : RecipeDetailAction

    // Dropdown
    data object OnDropDownClick : RecipeDetailAction
    data object OnDropDownDismiss : RecipeDetailAction

    // Share
    data object OnShareClick : RecipeDetailAction
    data object OnShareDialogDismiss : RecipeDetailAction
    data class OnCopyLinkClick(val link: String) : RecipeDetailAction

    data object OnMessageShown : RecipeDetailAction // Added for message dismissal
}

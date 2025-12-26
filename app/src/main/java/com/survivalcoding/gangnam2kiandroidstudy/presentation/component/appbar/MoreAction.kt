package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.appbar


sealed interface MoreAction {
    data object Share : MoreAction
    data object Rate : MoreAction
    data object Review : MoreAction
    data object Unsave : MoreAction
}
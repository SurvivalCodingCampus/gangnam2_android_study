package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up


sealed interface SignUpEvent {
    data object SignUpSuccess : SignUpEvent
    data object NavigateToSignIn : SignUpEvent
}
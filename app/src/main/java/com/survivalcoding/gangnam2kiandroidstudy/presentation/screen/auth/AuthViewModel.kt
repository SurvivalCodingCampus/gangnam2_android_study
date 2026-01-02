package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _userLoggedIn = MutableStateFlow(false)
    val userLoggedIn: StateFlow<Boolean> = _userLoggedIn

    init {
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        viewModelScope.launch {
            _userLoggedIn.value = authRepository.getCurrentUserUid() != null
        }
    }

    fun signInAnonymously() {
        // This is a placeholder for actual sign-in logic
        // In a real app, you would call FirebaseAuth sign-in methods
        viewModelScope.launch {
            // Simulate a sign-in operation
            // For now, just set userLoggedIn to true
            _userLoggedIn.value = true
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
            _userLoggedIn.value = false
        }
    }
}

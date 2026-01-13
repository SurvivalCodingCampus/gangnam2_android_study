package com.survivalcoding.gangnam2kiandroidstudy.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object AuthStateHolder {

    private lateinit var firebaseAuth: FirebaseAuth

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unknown)
    val authState = _authState.asStateFlow()

    private val authStateListener = FirebaseAuth.AuthStateListener { auth ->
        val user = auth.currentUser
        Log.d("AuthStateHolder", "currentUser = $user")

        _authState.value = if (auth.currentUser != null) {
            AuthState.Authenticated
        } else {
            AuthState.Unauthenticated
        }
    }

    fun init(firebaseAuth: FirebaseAuth) {
        this.firebaseAuth = firebaseAuth
    }

    fun startListening() {
        check(::firebaseAuth.isInitialized) {
            "AuthStateHolder.init(firebaseAuth) must be called before startListening()"
        }

        _authState.value = if (firebaseAuth.currentUser != null) {
            AuthState.Authenticated
        } else {
            AuthState.Unauthenticated
        }

        firebaseAuth.addAuthStateListener(authStateListener)
    }

    fun stopListening() {
        if (::firebaseAuth.isInitialized) {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }
    /**
     * DEBUG 전용 강제 로그인 처리
     *
     * Firebase를 거치지 않고 인증 상태를 Authenticated로 만든다.
     * NavigationRoot는 이 상태 변화를 감지해 MainNavigation으로 전환한다.
     *
     * 반드시 DEBUG 빌드에서만 사용해야 한다.
     */
    fun forceAuthenticatedForDebug() {
        _authState.value = AuthState.Authenticated
    }

}

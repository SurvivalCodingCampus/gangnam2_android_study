package com.survivalcoding.gangnam2kiandroidstudy.presentation.signin

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import org.koin.androidx.compose.koinViewModel
import com.survivalcoding.gangnam2kiandroidstudy.R

@Composable
fun SignInRoot(
    viewModel: SignInViewModel = koinViewModel(),
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken

            if (idToken != null) {
                viewModel.onAction(SignInAction.GoogleIdTokenReceive(idToken))
            }
        } catch (e: ApiException) {
            // 로그인 실패
        }
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                SignInEvent.NavigateToForgotPassword -> {
                    // TODO 나중에 구현할것
                }
                SignInEvent.NavigateToMain -> onSignInClick()
                SignInEvent.NavigateToSignUp -> onSignUpClick()
                is SignInEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                SignInEvent.GoogleLoginClick -> {
                    val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestIdToken(context.getString(R.string.default_web_client_id))
                        .build()
                    val googleSignInClient = GoogleSignIn.getClient(context, options)

                    launcher.launch(googleSignInClient.signInIntent)
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        SignInScreen(
            uiState = uiState,
            onAction = viewModel::onAction
        )
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}
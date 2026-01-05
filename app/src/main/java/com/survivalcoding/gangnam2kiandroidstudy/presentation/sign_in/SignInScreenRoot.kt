package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.survivalcoding.gangnam2kiandroidstudy.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreenRoot(
    onNavigateToSignUp: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    viewModel: SignInViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val googleIdOption = remember {
        GetGoogleIdOption.Builder()
            .setServerClientId(
                context.getString(R.string.default_web_client_id)
            )
            .setFilterByAuthorizedAccounts(false)
            .build()
    }

    val request = remember {
        GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }

    val credentialManager = remember {
        CredentialManager.create(context)
    }
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                SignInEvent.NavigateToHome -> {
                    onNavigateToHome()
                }
                SignInEvent.NavigateToSignUp -> {
                    onNavigateToSignUp()
                }
                SignInEvent.LaunchGoogleSignIn -> {
                    try {
                        val result = credentialManager.getCredential(
                            context = context,
                            request = request
                        )

                        val credential = result.credential
                        if (credential is GoogleIdTokenCredential) {
                            viewModel.signInWithGoogle(credential.idToken)
                        }

                    } catch (e: Exception) {
                        viewModel.onGoogleIdTokenReceivedError(
                            e.message ?: "Google Sign-In canceled"
                        )
                    }
                }
                is SignInEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    SignInScreen(
        state = state,
        onAction = {
            viewModel.onAction(it)
        }
    )

}
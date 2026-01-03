package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.sign_up

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.misterjerry.gangnam2kiandroidstudy.R


class GoogleLoginClient(private val context: Context) {

    suspend fun getGoogleIdToken(): Result<String> {
        return try {
            val credentialManager = CredentialManager.create(context)

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(
                context = context,
                request = request
            )

            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(result.credential.data)
            Result.success(googleIdTokenCredential.idToken)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
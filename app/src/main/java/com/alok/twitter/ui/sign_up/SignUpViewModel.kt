package com.alok.twitter.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.alok.twitter.model.service.AccountService
import com.alok.twitter.model.service.LogService
import com.alok.twitter.model.service.StorageService
import com.alok.twitter.ui.common.SETTINGS_SCREEN
import com.alok.twitter.ui.common.SIGN_UP_SCREEN
import com.alok.twitter.ui.common.ext.isValidEmail
import com.alok.twitter.ui.common.ext.isValidPassword
import com.alok.twitter.ui.common.ext.passwordMatches
import com.alok.twitter.ui.common.snackbar.SnackbarManager
import com.alok.twitter.viewmodel.BaseViewModel
import com.example.makeitso.screens.sign_up.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.alok.twitter.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    logService: LogService
) : BaseViewModel(logService) {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        viewModelScope.launch(showErrorExceptionHandler) {
            val oldUserId = accountService.getUserId()
            accountService.createAccount(email, password) { error ->
                if (error == null) {
                    linkWithEmail()
                    updateUserId(oldUserId, openAndPopUp)
                } else onError(error)
            }
        }
    }

    private fun linkWithEmail() {
        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.linkAccount(email, password) { error ->
                if (error != null) logService.logNonFatalCrash(error)
            }
        }
    }

    private fun updateUserId(oldUserId: String, openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch(showErrorExceptionHandler) {
            val newUserId = accountService.getUserId()

            storageService.updateUserId(oldUserId, newUserId) { error ->
                if (error != null) logService.logNonFatalCrash(error)
                else openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
            }
        }
    }
}
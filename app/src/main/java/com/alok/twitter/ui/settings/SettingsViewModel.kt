package com.alok.twitter.ui.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.alok.twitter.model.service.AccountService
import com.alok.twitter.model.service.LogService
import com.alok.twitter.model.service.StorageService
import com.alok.twitter.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
    private val storageService: StorageService
) : BaseViewModel(logService) {
    var uiState = mutableStateOf(SettingsUiState())
        private set

    fun initialize() {
        uiState.value = SettingsUiState(accountService.isAnonymousUser())
    }

    fun onSignOutClick() {
        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.signOut()
        }
    }

    fun onDeleteMyAccountClick() {
        viewModelScope.launch(showErrorExceptionHandler) {
            storageService.deleteAllForUser(accountService.getUserId()) { error ->
                if (error == null) deleteAccount() else onError(error)
            }
        }
    }

    private fun deleteAccount() {
        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.deleteAccount { error ->
//                if (error == null) restartApp(SPLASH_SCREEN) else onError(error)
            }
        }
    }
}
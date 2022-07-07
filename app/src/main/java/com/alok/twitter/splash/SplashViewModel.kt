package com.alok.twitter.screens.splash

import androidx.lifecycle.viewModelScope
import com.alok.twitter.model.service.AccountService
import com.alok.twitter.model.service.LogService
import com.alok.twitter.ui.common.LOGIN_SCREEN
import com.alok.twitter.ui.common.MAIN_SCREEN
import com.alok.twitter.ui.common.SPLASH_SCREEN
import com.alok.twitter.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : BaseViewModel(logService) {
    fun onAppStart(openAndPopUp: (String, String) -> Unit , openApp: (String) -> Unit) {
        println("jaimatadi user loggedIn = ${accountService.hasUser()}")
        if (accountService.hasUser()) openAndPopUp(MAIN_SCREEN, SPLASH_SCREEN)
        else {
            openApp(LOGIN_SCREEN)
        }
//        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch(logErrorExceptionHandler) {
            accountService.createAnonymousAccount { error ->
                if (error != null) logService.logNonFatalCrash(error)
                else openAndPopUp(MAIN_SCREEN, SPLASH_SCREEN)
            }
        }
    }
}
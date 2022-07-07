package com.alok.twitter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.alok.twitter.model.service.LogService
import com.alok.twitter.ui.common.snackbar.SnackbarManager
import com.alok.twitter.ui.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(val logService: LogService) : ViewModel() {
    lateinit var navHostController: NavHostController

    fun navigateTo(route: String) {
        navHostController.navigate(route)
    }

    open val showErrorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    open val logErrorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        logService.logNonFatalCrash(throwable)
    }

    open fun onError(error: Throwable) {
        SnackbarManager.showMessage(error.toSnackbarMessage())
        logService.logNonFatalCrash(error)
    }


    fun navigate(route: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
        }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        println("jaimatadi navHostController = $navHostController :: route = $route")
        navHostController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}
package com.alok.twitter.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.alok.twitter.ui.common.composable.BasicButton
import com.alok.twitter.ui.common.composable.BasicTextButton
import com.alok.twitter.ui.common.composable.EmailField
import com.alok.twitter.ui.common.composable.PasswordField
import com.alok.twitter.ui.common.ext.basicButton
import com.alok.twitter.ui.common.ext.fieldModifier
import com.alok.twitter.ui.common.ext.textButton
import com.alok.twitter.R.string as AppText

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    openApp: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    viewModel.navHostController = rememberNavController()
    val uiState by viewModel.uiState
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(AppText.email), modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp)
        )
        EmailField(uiState.email, viewModel::onEmailChange, Modifier.fieldModifier())

        Text(
            text = stringResource(id = AppText.password), modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp)
        )
        PasswordField(uiState.password, viewModel::onPasswordChange, Modifier.fieldModifier())

        BasicButton(AppText.sign_in, Modifier.basicButton()) {
            viewModel.onSignInClick(openAndPopUp)
        }

        BasicButton(AppText.create_account, Modifier.basicButton()) {
            viewModel.onSignUpClick(openApp)
        }

        BasicTextButton(AppText.forgot_password, Modifier.textButton()) {
            viewModel.onForgotPasswordClick()
        }
    }
}

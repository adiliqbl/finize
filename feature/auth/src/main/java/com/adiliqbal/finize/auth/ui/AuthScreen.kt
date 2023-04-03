package com.adiliqbal.finize.auth.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.adiliqbal.finize.ui.util.StudioPreview

@Composable
fun AuthRoute(
	viewModel: AuthViewModel = hiltViewModel(),
	onLogin: (String?) -> Unit,
	onForgotPassword: () -> Unit,
	onRegister: (String?) -> Unit
) {
	AuthScreen()
}

@Composable
fun AuthScreen(

) {

	Box {
		Text(text = "Login Screen")
	}
}

@Preview
@Composable
private fun AuthScreenPreview() = StudioPreview {
	AuthScreen()
}

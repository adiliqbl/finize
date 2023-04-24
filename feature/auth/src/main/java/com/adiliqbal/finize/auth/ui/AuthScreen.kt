package com.adiliqbal.finize.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adiliqbal.finize.auth.R
import com.adiliqbal.finize.ui.DevicePreviews
import com.adiliqbal.finize.ui.extensions.bodyPaddings
import com.adiliqbal.finize.ui.util.AppPreview

@Composable
fun AuthRoute(
	viewModel: AuthViewModel = hiltViewModel(),
	onLogin: () -> Unit
) {
	AuthScreen()
}

@Composable
fun AuthScreen(
	onGoogleClick: () -> Unit
) {
	Box(Modifier.background(MaterialTheme.colorScheme.background)) {
		Column(
			Modifier
				.bodyPaddings()
				.align(Alignment.BottomCenter)
		) {
			Text(
				style = MaterialTheme.typography.displaySmall,
				text = stringResource(id = R.string.app_name)
			)
			Text(
				modifier = Modifier.padding(bottom = 50.dp),
				fontWeight = FontWeight.Bold,
				style = MaterialTheme.typography.displayLarge,
				text = stringResource(id = R.string.app_tagline)
			)

			Button(
				onClick = { },
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 16.dp, end = 16.dp),
				colors = ButtonDefaults.buttonColors(
					containerColor = Color.White,
					contentColor = Color.Black
				)
			) {
				Image(
					modifier = Modifier.size(24.dp),
					painter = painterResource(id = R.drawable.ic_logo_google),
					contentDescription = ""
				)
				Text(
					text = stringResource(id = R.string.login_google_button),
					modifier = Modifier.padding(6.dp)
				)
			}
		}
	}
}

@DevicePreviews
@Composable
private fun AuthScreenPreview() = AppPreview {
	AuthScreen()
}

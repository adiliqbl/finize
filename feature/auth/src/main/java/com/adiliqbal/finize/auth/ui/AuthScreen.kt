package com.adiliqbal.finize.auth.ui

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adiliqbal.finize.auth.BuildConfig
import com.adiliqbal.finize.auth.R
import com.adiliqbal.finize.ui.DevicePreviews
import com.adiliqbal.finize.ui.extensions.bodyPaddings
import com.adiliqbal.finize.ui.util.AppPreview
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@Composable
fun AuthRoute(
	viewModel: AuthViewModel = hiltViewModel(),
	onLogin: () -> Unit
) {
	AuthScreen(
		loginWithGoogle = viewModel::loginWithGoogle
	)
}

@Composable
fun AuthScreen(
	loginWithGoogle: suspend (account: GoogleSignInAccount) -> Unit
) {
	val launcher = rememberFirebaseAuthLauncher(loginWithGoogle)
	val context = LocalContext.current

	val onGoogleLogin = {
		val gso =
			GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestProfile()
				.requestIdToken(BuildConfig.GCP_WEB_CLIENT_ID)
				.requestEmail()
				.build()
		val googleSignInClient = GoogleSignIn.getClient(context, gso)
		launcher.launch(googleSignInClient.signInIntent)
	}

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
				onClick = onGoogleLogin,
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

@Composable
private fun rememberFirebaseAuthLauncher(
	loginWithGoogle: suspend (account: GoogleSignInAccount) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
	val scope = rememberCoroutineScope()
	return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
		val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
		scope.launch {
			loginWithGoogle(task.getResult(ApiException::class.java)!!)
		}
	}
}

@DevicePreviews
@Composable
private fun AuthScreenPreview() = AppPreview {
	AuthScreen(
		loginWithGoogle = {}
	)
}

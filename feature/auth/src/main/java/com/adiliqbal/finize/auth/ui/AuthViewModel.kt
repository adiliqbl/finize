package com.adiliqbal.finize.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adiliqbal.finize.auth.conversions.toUser
import com.adiliqbal.finize.data.manager.SessionManager
import com.adiliqbal.finize.data.repository.AuthRepository
import com.adiliqbal.finize.model.AuthCredentials
import com.adiliqbal.finize.navigation.util.NavigationState
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject
constructor(
	private val sessionManager: SessionManager,
	private val authRepository: AuthRepository
) : ViewModel() {

	private val _navState = MutableStateFlow<NavigationState<Boolean>?>(null)
	val navState = _navState.asStateFlow()

	val loading = MutableStateFlow(false)
	private val error = MutableStateFlow<String?>(null)

	fun loginWithGoogle(account: GoogleSignInAccount) {
		viewModelScope.launch {
			loading.emit(true)
			runAuthApi {
				val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
				val firebaseUser = Firebase.auth.signInWithCredential(credential).await().user
				firebaseUser?.toUser()?.let { user ->
					var newUser = false
					val credentials = if (user.email.let { authRepository.exists(it) }) {
						authRepository.loginWithGoogle(user.id)
					} else {
						newUser = true
						authRepository.register(user)
					}

					onAuth(credentials, newUser)
				}
			}
		}
	}

	private fun onAuth(credentials: AuthCredentials, isNew: Boolean) = viewModelScope.launch {
		sessionManager.login(credentials)
		_navState.emit(NavigationState.Navigate(isNew))
		loading.emit(false)
	}

	private suspend fun runAuthApi(run: suspend () -> Unit) {
		try {
			run()
		} catch (e: Exception) {
			loading.emit(false)
			error.emit(e.message)
		}
	}
}

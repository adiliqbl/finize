package com.adiliqbal.finize.auth.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adiliqbal.finize.data.manager.SessionManager
import com.adiliqbal.finize.data.repository.AuthRepository
import com.adiliqbal.finize.model.AuthCredentials
import com.adiliqbal.finize.navigation.util.NavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
	val error = MutableStateFlow<String?>(null)

	fun loginWithGoogle(context: Context) {
		viewModelScope.launch {
			loading.emit(true)
			runAuthApi {

			}
		}
	}

	private fun onAuth(credentials: AuthCredentials) {
		viewModelScope.launch {
			sessionManager.login(credentials)
			_navState.emit(NavigationState.Navigate(true))
			loading.emit(false)
		}
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

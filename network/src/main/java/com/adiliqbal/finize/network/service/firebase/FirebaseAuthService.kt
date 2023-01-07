package com.adiliqbal.finize.network.service.firebase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseAuthService @Inject constructor(
	private val auth: FirebaseAuth
)
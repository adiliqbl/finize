package com.adiliqbal.finize.auth.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adiliqbal.finize.navigation.NavDestination
import com.adiliqbal.finize.navigation.extensions.composable

object AuthDestination : NavDestination() {
	override val route = "login_route"
	override val destination = "login_destination"
}

@ExperimentalAnimationApi
fun NavGraphBuilder.authGraph(
	onLogin: (String?) -> Unit
) {
	composable(AuthDestination) {
		LoginRoute(onLogin = onLogin)
	}
}

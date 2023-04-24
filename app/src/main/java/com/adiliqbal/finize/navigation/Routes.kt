package com.adiliqbal.finize.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.navigation

@ExperimentalAnimationApi
internal fun NavGraphBuilder.appGraph(controller: NavController) {
	navigation(startDestination = AuthDestination.route, route = AppGraph.AUTH) {
		authGraph(
			onLogin = { inviteCode ->
				controller.navigate(HomeDestination.route(inviteCode)) { asRoot() }
			},
			onForgotPassword = { controller.navigate(ForgotPasswordDestination) },
			onRegister = { inviteCode -> controller.navigate(RegisterDestination.route(inviteCode)) },
			onBackPress = { controller.navigateUp() }
		)
	}
	navigation(startDestination = HomeDestination.route, route = AppGraph.MAIN) {
		homeGraph(
			navController = controller,
			onNavigateToRootDestination = { controller.navigate(it) },
			onLogout = { inviteCode ->
				controller.navigate(AuthDestination.route(inviteCode)) { asRoot() }
			}
		)
	}
}

internal fun NavGraphBuilder.homeBottomNavGraph(controller: NavController, network: Network?) {
	composableSimple(ChannelsDestination) {
		ChannelsRoute(network = network) { controller.navigate(MessagesDestination.route(it.id)) }
	}
}

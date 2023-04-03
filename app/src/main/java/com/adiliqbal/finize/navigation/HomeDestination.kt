package com.adiliqbal.finize.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.*
import com.adiliqbal.finize.navigation.extensions.composable
import com.adiliqbal.finize.navigation.util.FadeNavAnimation
import com.zero.android.navigation.extensions.composable
import com.zero.android.navigation.util.FadeNavAnimation
import com.zero.android.ui.home.HomeRoute

object HomeDestination : NavDestination() {
	override val route = "home_route"
	override val destination = "home_destination"
}

@ExperimentalAnimationApi
internal fun NavGraphBuilder.homeGraph(
	navController: NavController,
	onNavigateToRootDestination: (NavDestination) -> Unit,
	onLogout: (String?) -> Unit
) {
	composable(HomeDestination, animation = FadeNavAnimation) {
		HomeRoute(
			navController = navController,
			navigateToRootDestination = onNavigateToRootDestination,
			onLogout = onLogout
		)
	}
}

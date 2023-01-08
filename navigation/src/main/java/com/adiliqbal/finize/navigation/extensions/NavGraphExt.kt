package com.adiliqbal.finize.navigation.extensions

import androidx.annotation.IdRes
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.adiliqbal.finize.navigation.NavDestination
import com.adiliqbal.finize.navigation.util.NavAnimation
import com.adiliqbal.finize.navigation.util.SlideNavAnimation
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composable(
	destination: NavDestination,
	animation: NavAnimation? = SlideNavAnimation,
	content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
	if (animation != null) {
		composable(
			route = destination.route,
			arguments = destination.arguments,
			deepLinks = destination.deepLinks,
			enterTransition = { animation.enterTransition },
			exitTransition = { animation.exitTransition },
			popEnterTransition = { animation.popEnterTransition },
			popExitTransition = { animation.popExitTransition },
			content = content
		)
	} else {
		composable(
			route = destination.route,
			arguments = destination.arguments,
			deepLinks = destination.deepLinks,
			content = content
		)
	}
}

fun NavGraphBuilder.composableSimple(
	destination: NavDestination,
	content: @Composable (NavBackStackEntry) -> Unit
) {
	composable(
		route = destination.route,
		arguments = destination.arguments,
		deepLinks = destination.deepLinks,
		content = content
	)
}

fun NavOptionsBuilder.asRoot(@IdRes id: Int = 0) = apply {
	popUpTo(id)
	launchSingleTop = true
}

package com.adiliqbal.finize.ui.manager

import kotlinx.coroutines.flow.StateFlow

interface ThemeManager {

	val dynamicThemePalette: StateFlow<Int>

	suspend fun changeThemePalette(default: Boolean = false)
}

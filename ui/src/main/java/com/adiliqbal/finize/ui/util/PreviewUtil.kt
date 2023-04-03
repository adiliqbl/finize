package com.adiliqbal.finize.ui.util

import androidx.compose.runtime.Composable
import com.adiliqbal.finize.ui.design.AppTheme

@Composable
fun StudioPreview(dark: Boolean = false, content: @Composable () -> Unit) {
	AppTheme(dark = dark) { content() }
}

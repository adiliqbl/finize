package com.adiliqbal.finize.ui.design

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val Font = TextStyle()

internal val Typography = Typography(
	displayLarge = Font.copy(
		fontWeight = FontWeight.W400,
		fontSize = 57.sp,
		lineHeight = 64.sp,
		letterSpacing = (-0.25).sp
	),
	displayMedium = Font.copy(
		fontWeight = FontWeight.W400,
		fontSize = 45.sp,
		lineHeight = 52.sp
	),
	displaySmall = Font.copy(
		fontWeight = FontWeight.W400,
		fontSize = 36.sp,
		lineHeight = 44.sp
	),
	headlineLarge = Font.copy(
		fontWeight = FontWeight.W400,
		fontSize = 32.sp,
		lineHeight = 40.sp
	),
	headlineMedium = Font.copy(
		fontWeight = FontWeight.W400,
		fontSize = 28.sp,
		lineHeight = 36.sp
	),
	headlineSmall = Font.copy(
		fontWeight = FontWeight.W400,
		fontSize = 24.sp,
		lineHeight = 32.sp
	),
	titleLarge = Font.copy(
		fontWeight = FontWeight.W700,
		fontSize = 22.sp,
		lineHeight = 28.sp
	),
	titleMedium = Font.copy(
		fontWeight = FontWeight.W700,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.1.sp
	),
	titleSmall = Font.copy(
		fontWeight = FontWeight.W500,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.1.sp
	),
	bodyLarge = Font.copy(
		fontWeight = FontWeight.W400,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp
	),
	bodyMedium = Font.copy(
		fontWeight = FontWeight.W400,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.25.sp
	),
	bodySmall = Font.copy(
		fontWeight = FontWeight.W400,
		fontSize = 12.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.4.sp
	),
	labelLarge = Font.copy(
		fontWeight = FontWeight.W400,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.1.sp
	),
	labelMedium = Font.copy(
		fontWeight = FontWeight.W400,
		fontSize = 12.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.5.sp
	),
	labelSmall = Font.copy(
		fontFamily = FontFamily.Monospace,
		fontWeight = FontWeight.W500,
		fontSize = 10.sp,
		lineHeight = 16.sp
	)
)
package com.adiliqbal.finize.ui.extensions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adiliqbal.finize.ui.design.Padding

fun Modifier.bodyPaddings(vertical: Float = 1f, horizontal: Float = 1f) =
	padding(
		vertical = (Padding.BodyVertical * vertical).dp,
		horizontal = (Padding.BodyHorizontal * horizontal).dp
	)

@Suppress("FunctionName")
fun BodyPadding(vertical: Float = 1f, horizontal: Float = 1f) =
	PaddingValues(
		vertical = (Padding.BodyVertical * vertical).dp,
		horizontal = (Padding.BodyHorizontal * horizontal).dp
	)
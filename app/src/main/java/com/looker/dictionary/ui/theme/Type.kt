package com.looker.dictionary.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val typography = Typography(
	headlineMedium = TextStyle(
		fontWeight = FontWeight.SemiBold,
		fontSize = 28.sp,
		lineHeight = 36.sp,
		letterSpacing = 0.sp
	),
	titleSmall = TextStyle(
		fontWeight = FontWeight.SemiBold,
		fontSize = 18.sp,
		lineHeight = 36.sp,
		letterSpacing = 0.5.sp
	)
)
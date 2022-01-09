package com.looker.dictionary.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun DictionaryTheme(
	isDarkTheme: Boolean = isSystemInDarkTheme(),
	isDynamicColor: Boolean = true,
	content: @Composable () -> Unit
) {
	val colors = if (isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
		if (isDarkTheme) dynamicDarkColorScheme(LocalContext.current)
		else dynamicLightColorScheme(LocalContext.current)
	} else {
		if (isDarkTheme) darkColors else lightColors
	}
	MaterialTheme(
		colorScheme = colors,
		typography = typography,
		content = content
	)
}
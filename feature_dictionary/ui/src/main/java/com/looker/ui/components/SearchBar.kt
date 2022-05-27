package com.looker.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(searchQuery: String, onSearch: (String) -> Unit) {

	val keyboardController = LocalFocusManager.current

	BasicTextField(
		modifier = Modifier.fillMaxWidth(),
		value = searchQuery,
		onValueChange = onSearch,
		singleLine = true,
		textStyle = MaterialTheme.typography.titleLarge.copy(MaterialTheme.colorScheme.onSurface),
		keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
		keyboardActions = KeyboardActions(
			onSearch = {
				onSearch(searchQuery)
				keyboardController.clearFocus(true)
			}
		),
		decorationBox = {
			Surface(
				modifier = Modifier.height(54.dp),
				tonalElevation = 16.dp,
				shape = RoundedCornerShape(16.dp)
			) {
				Box(
					modifier = Modifier.padding(horizontal = 27.dp),
					contentAlignment = Alignment.CenterStart
				) {
					it()
				}
			}
		}
	)
}
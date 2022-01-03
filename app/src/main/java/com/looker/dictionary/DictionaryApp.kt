package com.looker.dictionary

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.statusBarsPadding
import com.looker.dictionary.ui.theme.DictionaryTheme
import com.looker.dictionary.ui.theme.typography
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DictionaryApp : Application()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryApp(
	modifier: Modifier = Modifier,
	content: @Composable (PaddingValues) -> Unit,
) {
	DictionaryTheme {
		ProvideWindowInsets {
			Surface {
				Scaffold(
					modifier = modifier
						.statusBarsPadding()
						.imePadding(),
					containerColor = MaterialTheme.colorScheme.background,
					topBar = {
						CenterAlignedTopAppBar(
							title = {
								Text(
									text = "Dictionary",
									style = typography.headlineMedium
								)
							},
							scrollBehavior = pinnedScrollBehavior()
						)
					},
					content = content
				)
			}
		}
	}
}
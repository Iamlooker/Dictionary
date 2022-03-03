package com.looker.dictionary

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import com.looker.dictionary.ui.theme.DictionaryTheme
import com.looker.dictionary.ui.theme.typography
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DictionaryApp : Application()

@ExperimentalMaterial3Api
@Composable
fun DictionaryApp(
	modifier: Modifier = Modifier,
	content: @Composable (PaddingValues) -> Unit,
) {
	val scrollBehavior = remember { pinnedScrollBehavior() }
	DictionaryTheme {
		ProvideWindowInsets {
			Surface {
				Scaffold(
					modifier = modifier
						.nestedScroll(scrollBehavior.nestedScrollConnection)
						.imePadding(),
					containerColor = MaterialTheme.colorScheme.background,
					topBar = {
						CenterAlignedTopAppBar(
							modifier = Modifier.statusBarsHeight(64.dp),
							title = {
								Text(
									modifier = Modifier.statusBarsPadding(),
									text = stringResource(R.string.app_name),
									style = typography.headlineMedium
								)
							},
							scrollBehavior = scrollBehavior
						)
					},
					content = content
				)
			}
		}
	}
}
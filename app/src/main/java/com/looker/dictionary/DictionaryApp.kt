package com.looker.dictionary

import android.app.Application
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.looker.dictionary.ui.theme.DictionaryTheme
import com.looker.dictionary.ui.theme.typography
import com.looker.presentation.WordInfoViewModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DictionaryApp : Application()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryApp(
	modifier: Modifier = Modifier,
	viewModel: WordInfoViewModel = viewModel(),
	content: @Composable () -> Unit
) {
	val scrollBehavior = remember { pinnedScrollBehavior() }
	val snackBarEvent by viewModel.eventFlow.collectAsState(WordInfoViewModel.UIEvents.ShowSnackBar())
	val customStatusPadding = WindowInsets.statusBars.asPaddingValues()
	DictionaryTheme {
		Scaffold(
			modifier = modifier
				.nestedScroll(scrollBehavior.nestedScrollConnection)
				.imePadding(),
			containerColor = MaterialTheme.colorScheme.background,
			topBar = {
				CenterAlignedTopAppBar(
					modifier = Modifier.height(customStatusPadding.calculateTopPadding() + 64.dp),
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
			snackbarHost = {
				SnackBarView(snackBar = snackBarEvent as WordInfoViewModel.UIEvents.ShowSnackBar)
			}
		) {
			content()
		}
	}
}

@Composable
fun SnackBarView(
	modifier: Modifier = Modifier,
	snackBar: WordInfoViewModel.UIEvents.ShowSnackBar
) {
	AnimatedVisibility(
		visible = snackBar.showError.data ?: false,
		enter = fadeIn() + expandVertically(),
		exit = fadeOut() + shrinkVertically()
	) {
		Surface(
			modifier = modifier
				.padding(16.dp)
				.fillMaxWidth()
				.height(52.dp),
			shape = RoundedCornerShape(16.dp),
			color = MaterialTheme.colorScheme.surfaceVariant
		) {
			Text(
				modifier = Modifier.padding(16.dp),
				text = snackBar.message
			)
		}
	}
}
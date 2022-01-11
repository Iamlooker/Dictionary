package com.looker.dictionary.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.looker.dictionary.feature_dictonary.presentation.WordInfoViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WordSearchPage(
	modifier: Modifier = Modifier,
	viewModel: WordInfoViewModel = viewModel(),
) {
	val state = viewModel.state.value
	LaunchedEffect(true) {
		viewModel.eventFlow.collectLatest {
			when (it) {
				is WordInfoViewModel.UIEvents.ShowSnackBar -> {

				}
			}
		}
	}

	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(horizontal = 16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		LazyColumn(
			modifier = Modifier.fillMaxSize(),
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			item {
				SearchBar(searchQuery = viewModel.searchQuery.value, onSearch = viewModel::onSearch)
			}
			itemsIndexed(state.wordInfoItems) { index, wordInfo ->
				WordInfoItem(
					wordInfo = wordInfo,
					elevation = if (index == 0) 16.dp
					else 0.dp
				)
			}
		}
	}
}
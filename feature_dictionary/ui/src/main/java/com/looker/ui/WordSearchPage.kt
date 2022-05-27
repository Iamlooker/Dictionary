package com.looker.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.looker.presentation.WordInfoViewModel
import com.looker.ui.components.SearchBar

@Composable
fun WordSearchPage(
	modifier: Modifier = Modifier,
	viewModel: WordInfoViewModel = viewModel(),
) {
	val state = viewModel.state.value

	LaunchedEffect(viewModel.searchQuery.value) {
		if (viewModel.searchQuery.value.isEmpty()) viewModel.onSearch(viewModel.searchQuery.value)
	}

	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(horizontal = 16.dp),
		verticalArrangement = Arrangement.spacedBy(12.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		SearchBar(searchQuery = viewModel.searchQuery.value, onSearch = viewModel::onSearch)
		if (state.isLoading) CircularProgressIndicator()
		AnimatedVisibility(visible = !state.isLoading) {
			LazyColumn(
				verticalArrangement = Arrangement.spacedBy(12.dp)
			) {
				itemsIndexed(state.wordInfoItems) { index, wordInfo ->
					com.looker.presentation.WordInfoItem(
						wordInfo = wordInfo,
						elevation = if (index == 0) 16.dp
						else 0.dp
					)
				}
			}
		}
	}
}
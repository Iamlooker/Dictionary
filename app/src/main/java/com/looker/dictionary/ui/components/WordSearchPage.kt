package com.looker.dictionary.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.looker.dictionary.feature_dictonary.presentation.WordInfoViewModel

@Composable
fun WordSearchPage(
    modifier: Modifier = Modifier,
    viewModel: WordInfoViewModel = viewModel(),
) {
    val state = viewModel.state.value
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SearchBar(searchQuery = viewModel.searchQuery.value, onSearch = viewModel::onSearch)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(state.wordInfoItems) { index, wordInfo ->
                WordInfoItem(
                    wordInfo = wordInfo,
                    backgroundColor = if (index < 1) MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.background
                )
            }
        }
    }
}
package com.looker.dictionary.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.looker.dictionary.feature_dictonary.presentation.WordInfoViewModel

@Composable
fun WordSearchPage(
    modifier: Modifier = Modifier,
    viewModel: WordInfoViewModel= viewModel(),
) {
    val state = viewModel.state.value
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BasicTextField(
            value = viewModel.searchQuery.value,
            onValueChange = viewModel::onSearch,
            modifier = Modifier.fillMaxWidth(),
            decorationBox = {
                Surface(
                    modifier = Modifier.height(54.dp),
                    color = MaterialTheme.colors.surface,
                    shape = CircleShape
                ) {
                    Box(
                        modifier = Modifier.padding(16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        it()
                    }
                }
            },
            singleLine = true
        )
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.wordInfoItems) {
                    WordInfoItem(it)
                }
            }
        }
    }
}
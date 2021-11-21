package com.looker.dictionary.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.looker.dictionary.ui.theme.typography

@Composable
fun SearchBar(searchQuery: String, onSearch: (String) -> Unit) {
    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchQuery,
        onValueChange = onSearch,
        singleLine = true,
        textStyle = typography.titleLarge,
        decorationBox = {
            Surface(
                modifier = Modifier.height(54.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                shape = CircleShape
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

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(searchQuery = "Search This", onSearch = {})
}
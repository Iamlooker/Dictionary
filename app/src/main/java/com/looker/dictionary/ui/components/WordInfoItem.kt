package com.looker.dictionary.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.looker.dictionary.feature_dictonary.domain.model.WordInfo

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = wordInfo.word,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            wordInfo.phonetic?.let { Text(text = it, fontWeight = FontWeight.Light) }
            Spacer(modifier = Modifier.height(4.dp))
            wordInfo.origin?.let { Text(text = it) }

            wordInfo.meanings.forEach { meaning ->
                Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
                meaning.definitions.forEachIndexed { i, definition ->
                    Text(text = "${i + 1}. ${definition.definition}")
                    Spacer(modifier = Modifier.height(8.dp))
                    definition.example?.let { example ->
                        Text(text = "Example: $example")
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}
package com.looker.dictionary.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.looker.dictionary.feature_dictonary.domain.model.WordInfo
import com.looker.dictionary.ui.theme.typography

@Composable
fun WordInfoItem(
	wordInfo: WordInfo,
	modifier: Modifier = Modifier,
	elevation: Dp = 0.dp,
) {
	var expanded by remember { mutableStateOf(false) }
	Surface(
		modifier = modifier,
		color = MaterialTheme.colorScheme.background,
		tonalElevation = elevation,
		shape = RoundedCornerShape(24.dp),
		onClick = { expanded = !expanded },
		indication = null
	) {
		Box(
			modifier = Modifier
				.wrapContentHeight()
				.fillMaxWidth()
				.padding(horizontal = 16.dp, vertical = 8.dp),
			contentAlignment = Alignment.CenterStart
		) {
			Column(
				modifier = Modifier
					.animateContentSize()
					.padding(end = 24.dp)
			) {
				Row(
					verticalAlignment = Alignment.Bottom,
					horizontalArrangement = Arrangement.spacedBy(8.dp)
				) {
					Text(text = wordInfo.word, style = typography.headlineMedium)
					wordInfo.phonetic?.let { Text(text = it, style = typography.labelLarge) }
				}
				Spacer(Modifier.height(8.dp))
				HeaderText(header = wordInfo.meanings[0].partOfSpeech)
				Spacer(Modifier.height(8.dp))
				DefinitionText(definition = wordInfo.meanings[0].definitions[0].definition)
				Spacer(Modifier.height(8.dp))
				ExampleText(example = wordInfo.meanings[0].definitions[0].example)
				Spacer(Modifier.height(8.dp))
				AnimatedVisibility(
					visible = expanded,
					enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
					exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top),
				) {
					Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
						wordInfo.meanings.forEachIndexed { meaningIndex, meaning ->
							if (meaningIndex > 0) {
								HeaderText(header = meaning.partOfSpeech)
							}
							meaning.definitions.forEachIndexed { index, def ->
								if (meaningIndex == 0) {
									if (index > 0) {
										DefinitionText(pos = index + 1, definition = def.definition)
										ExampleText(example = def.example)
									}
								} else {
									Text(text = "${index + 1}. ${def.definition}")
									ExampleText(example = def.example)
								}
							}
						}
					}
				}
			}
			if (wordInfo.meanings.size > 1 || wordInfo.meanings[0].definitions.size > 1) {
				ExpandIcon(modifier.align(Alignment.CenterEnd))
			}
		}
	}
}

@Composable
fun ExpandIcon(modifier: Modifier = Modifier) {
	Icon(
		modifier = modifier
			.clip(CircleShape)
			.background(MaterialTheme.colorScheme.primaryContainer),
		imageVector = Icons.Rounded.ArrowDropDown,
		tint = MaterialTheme.colorScheme.primary,
		contentDescription = "Expand Card"
	)
}
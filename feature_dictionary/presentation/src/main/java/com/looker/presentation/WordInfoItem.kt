package com.looker.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.looker.presentation.components.DefinitionText
import com.looker.presentation.components.ExampleText
import com.looker.presentation.components.HeaderText
import com.looker.dictionary_domain.model.WordInfo

@Composable
fun WordInfoItem(
	wordInfo: WordInfo,
	modifier: Modifier = Modifier,
	elevation: Dp = 0.dp,
) {
	var expanded by rememberSaveable { mutableStateOf(false) }
	val animateElevation by animateDpAsState(targetValue = if (expanded) 16.dp else elevation)
	Surface(
		modifier = modifier
			.clip(RoundedCornerShape(24.dp))
			.clickable { expanded = !expanded },
		color = MaterialTheme.colorScheme.background,
		tonalElevation = animateElevation,
		shape = RoundedCornerShape(24.dp),
		border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant)
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
					.padding(end = 24.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp)
			) {
				Row(
					verticalAlignment = Alignment.Bottom,
					horizontalArrangement = Arrangement.spacedBy(8.dp)
				) {
					Text(text = wordInfo.word, style = MaterialTheme.typography.headlineMedium)
					wordInfo.phonetic?.let {
						Text(
							text = it,
							style = MaterialTheme.typography.labelLarge
						)
					}
				}
				HeaderText(header = wordInfo.meanings[0].partOfSpeech)
				DefinitionText(definition = wordInfo.meanings[0].definitions[0].definition)
				ExampleText(example = wordInfo.meanings[0].definitions[0].example)
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
		painter = rememberVectorPainter(image = Icons.Rounded.ArrowDropDown),
		tint = MaterialTheme.colorScheme.primary,
		contentDescription = "Expand Card"
	)
}
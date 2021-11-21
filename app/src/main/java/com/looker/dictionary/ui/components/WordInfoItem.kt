package com.looker.dictionary.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.looker.dictionary.feature_dictonary.domain.model.WordInfo
import com.looker.dictionary.ui.theme.typography

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
) {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        modifier = modifier.clip(RoundedCornerShape(24.dp)),
        color = animateColorAsState(targetValue = backgroundColor).value,
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
                modifier = Modifier.padding(end = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = wordInfo.word, style = typography.headlineMedium)
                    wordInfo.phonetic?.let { Text(text = it, style = typography.labelLarge) }
                }
                Text(text = wordInfo.meanings[0].partOfSpeech, fontWeight = FontWeight.Bold)
                Text(
                    text = "1. ${wordInfo.meanings[0].definitions[0].definition}",
                    style = typography.bodyLarge
                )
                ExampleText(example = wordInfo.meanings[0].definitions[0].example)
                AnimatedVisibility(visible = expanded) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        wordInfo.meanings.forEachIndexed { meaningIndex, meaning ->
                            if (meaningIndex > 0) {
                                Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
                            }
                            meaning.definitions.forEachIndexed { index, def ->
                                if (meaningIndex == 0) {
                                    if (index > 0) {
                                        Text(text = "${index + 1}. ${def.definition}")
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
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .align(Alignment.CenterEnd),
                    imageVector = Icons.Rounded.ArrowDropDown,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentDescription = "Expand Card"
                )
            }
        }
    }
}

@Composable
fun ExampleText(example: String?) {
    example?.let {
        Text(
            text = AnnotatedString(
                text = "Example: ",
                spanStyle = SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    letterSpacing = 0.1.sp
                )
            ) + AnnotatedString(it)
        )
    }
}
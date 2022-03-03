package com.looker.dictionary.presentation

import com.looker.dictionary.domain.model.WordInfo

data class WordInfoState(
	val wordInfoItems: List<WordInfo> = emptyList(),
	val isLoading: Boolean = false,
)

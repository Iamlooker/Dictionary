package com.looker.presentation

import com.looker.dictionary_domain.model.WordInfo

data class WordInfoState(
	val wordInfoItems: List<WordInfo> = emptyList(),
	val isLoading: Boolean = false,
)

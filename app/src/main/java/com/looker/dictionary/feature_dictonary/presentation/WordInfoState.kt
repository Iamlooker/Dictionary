package com.looker.dictionary.feature_dictonary.presentation

import com.looker.dictionary.feature_dictonary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false,
)

package com.looker.dictionary.domain.model

import androidx.annotation.Keep

@Keep
data class WordInfo(
	val meanings: List<Meaning>,
	val origin: String?,
	val phonetic: String?,
	val word: String,
)
package com.looker.dictionary.feature_dictonary.domain.model

data class WordInfo(
	val meanings: List<Meaning>,
	val origin: String?,
	val phonetic: String?,
	val word: String,
)
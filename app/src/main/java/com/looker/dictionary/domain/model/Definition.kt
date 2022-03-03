package com.looker.dictionary.domain.model

import androidx.annotation.Keep

@Keep
data class Definition(
	val antonyms: List<String>,
	val definition: String,
	val example: String?,
	val synonyms: List<String>,
)
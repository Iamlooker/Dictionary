package com.looker.dictionary_domain.model

import androidx.annotation.Keep

@Keep
data class Definition(
	val antonyms: List<String>,
	val definition: String,
	val example: String?,
	val synonyms: List<String>,
)